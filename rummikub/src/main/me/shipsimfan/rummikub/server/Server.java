package me.shipsimfan.rummikub.server;

import me.shipsimfan.rummikub.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private final ServerSocket socket;
	private Client[] clients;
	
	private static boolean singleRun = false;

	public static void main(String[] args) {
		if(args.length > 1)
			singleRun = true;
		
		try {
			// Start server
			Server server = new Server(Config.PORT);
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
			
			if(singleRun)
				return;
		}
	}

	private void runGame() throws IOException {
		// Tell all clients to start game
		sendToAll("begin");
		sendToAll("The game is beginning!");

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
