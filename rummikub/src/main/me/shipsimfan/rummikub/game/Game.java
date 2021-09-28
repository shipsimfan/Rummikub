package me.shipsimfan.rummikub.game;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game {
	private List<List<Tile>> table;
	private Player[] players;
	private int currentPlayer;
	private Stack<Tile> deck;

	private int winner;

	public Game(Tile[] deck, Player player1, Player player2, Player player3) {
		table = new ArrayList<>();
		players = new Player[] { player1, player2, player3 };
		currentPlayer = 0;

		winner = -1;

		this.deck = new Stack<Tile>();
		for(Tile t : deck)
			this.deck.add(t);
	}

	// Play without meld number creates a new meld and returns the number
	public int play(String tile) {
		// Ignore plays after win
		if (winner >= 0)
			return 0;

		// Create new meld
		table.add(new ArrayList<>());
		int meld = table.size() - 1;

		// Play tile to new meld
		play(tile, meld);
		return meld;
	}

	public void play(String tile, int meld) {
		// Ignore plays after win
		if (winner >= 0)
			return;

		// Take tile from hand
		Tile t = players[currentPlayer].takeTile(tile);
		t.setNewPlay(true);

		// Reduce initial 30 points
		int amount = t.getNumber();
		if (amount > 10)
			amount = 10;
		players[currentPlayer].reducePointsRemaining(amount);

		// Play tile to meld
		table.get(meld).add(t);

		// Check for winner
		if (players[currentPlayer].getHand().equals(""))
			winner = currentPlayer;
	}

	public void draw() {
		// Remove tile from deck
		Tile t = deck.pop();
		
		// Give tile to current player
		players[currentPlayer].addTile(t);
		
		// End turn
		endTurn();
	}

	public void endTurn() {
		// Select next player
		if (currentPlayer == 2)
			currentPlayer = 0;
		else
			currentPlayer++;

		// Clear flags on tiles
		for (List<Tile> meld : table)
			for (Tile t : meld)
				t.setNewPlay(false);
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public int getCurrentRemainingPoints() {
		return players[currentPlayer].getRemainingPoints();
	}

	public String getTable() {
		String string = "";

		for (int i = 0; i < table.size(); i++) {
			if (i != 0)
				string += ",";

			string += "{";

			List<Tile> meld = table.get(i);
			for (int j = 0; j < meld.size(); j++) {
				if (j != 0)
					string += ",";

				string += meld.get(j).toString();
			}

			string += "}";
		}

		return string;
	}

	public String getHand(int player) {
		if(player < 0 || player > 2)
			throw new InvalidParameterException();
		
		return players[player].getHand();
	}

	public String getCurrentHand() {
		return getHand(currentPlayer);
	}

	public boolean hasWinner() {
		return winner != -1;
	}

	public int getWinner() {
		return winner;
	}
	
	public int getScore(int player) {
		return 0;
	}
}
