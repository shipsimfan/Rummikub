package me.shipsimfan.rummikub.server;

import me.shipsimfan.rummikub.Config;
import me.shipsimfan.rummikub.game.Game;
import me.shipsimfan.rummikub.game.Player;
import me.shipsimfan.rummikub.game.Tile;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private final ServerSocket socket;
	private Client[] clients;

	private Tile[] deck;
	private Tile[][] players;

	public static void main(String[] args) {
		try {
			// Start server
			Server server;
			if (args.length > 1)
				server = new Server(Config.PORT, args[1], new String[] { args[2], args[3], args[4] });
			else
				server = new Server(Config.PORT);

			System.out.println("Server listening on port " + Config.PORT);

			// Run server
			server.run();
		} catch (Exception e) {
			System.err.println("Network error while running server: " + e);
			System.exit(1);
		}
	}

	public Server(int port) throws IOException {
		socket = new ServerSocket(port);
		players = new Tile[0][];
	}

	public Server(int port, String deck, String[] players) throws IOException {
		socket = new ServerSocket(port);

		String[] deckTiles = deck.split(",");
		this.deck = new Tile[deckTiles.length];
		for (int i = 0; i < deckTiles.length; i++)
			this.deck[i] = new Tile(deckTiles[i]);

		this.players = new Tile[3][];
		for (int p = 0; p < 3; p++) {
			String[] playerTiles = players[p].split(",");
			this.players[p] = new Tile[playerTiles.length];
			for (int i = 0; i < playerTiles.length; i++)
				this.players[p][i] = new Tile(playerTiles[i]);
		}
	}

	public void run() throws IOException {
		while (true) {
			// Wait for clients to connect
			waitForPlayers();

			// Run game
			runGame();

			// Disconnect players
			for (int i = 0; i < 3; i++)
				clients[i].close();
		}
	}

	private void runGame() throws IOException {
		// Tell all clients to start game
		sendToAll("begin");
		sendToAll("The game is beginning!");

		// Rig if necessary
		Game game;
		if (players.length == 0)
			game = new Game();
		else {
			game = new Game(deck, new Player(players[0]), new Player(players[1]), new Player(players[2]));
		}

		// Run game
		while (!game.hasWinner()) {
			int currentPlayer = game.getCurrentPlayer();

			sendToAll("");
			sendToAll("Player " + (currentPlayer + 1) + "'s turn");

			boolean draw = true;
			while (true) {
				clients[currentPlayer].send("Table: " + game.getTable());
				clients[currentPlayer].send("Your hand: " + game.getCurrentHand());
				clients[currentPlayer].send("play");

				String response = clients[currentPlayer].read();
				if (response.equals("")) {
					sendToAll("Board: " + game.getTable());

					if (draw)
						game.draw();
					else
						game.endTurn();

					break;
				} else if (response.equals("end")) {
					sendToAll("end");
					return;
				}

				try {
					int initialMeld = Integer.parseInt(response);

					String tile = clients[currentPlayer].read();
					String destinationMeld = clients[currentPlayer].read();

					if (destinationMeld.equals(""))
						game.reuse(initialMeld, tile, Integer.parseInt(destinationMeld));
					else
						game.reuse(initialMeld, tile);
				} catch (NumberFormatException e) {
					draw = false;

					String meld = clients[currentPlayer].read();
					if (meld.equals(""))
						game.play(response);
					else
						game.play(response, Integer.parseInt(meld));
				}
			}
		}

		// Tell each player to disconnect
		sendToAll("end");
	}

	private void waitForPlayers() throws IOException {
		int numClients = 0;
		clients = new Client[3];

		// Loop until three clients connect
		while (numClients < 3) {
			// Send current number of players
			System.out.println("Currently " + numClients + "/3 players connected");
			for (int i = 0; i < numClients; i++) {
				clients[i].send("Currently " + numClients + "/3 players connected");
			}

			// Wait for new connection
			Socket client = this.socket.accept();

			// Add new player
			clients[numClients] = new Client(client);
			clients[numClients].send("You are player " + (numClients + 1));

			// Inform other players of new connection
			for (int i = 0; i < numClients; i++) {
				clients[i].send("Player " + (numClients + 1) + " has connected");
			}
			numClients++;
		}
	}

	private void sendToAll(String message) throws IOException {
		for (int i = 0; i < 3; i++)
			clients[i].send(message);
	}
}
