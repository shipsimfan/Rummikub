package me.shipsimfan.rummikub.game;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<List<Tile>> table;
	private Player[] players;
	private int currentPlayer;

	private int winner;

	public Game(Tile[] deck, Player player1, Player player2, Player player3) {
		table = new ArrayList<>();
		players = new Player[] { player1, player2, player3 };
		currentPlayer = 0;

		winner = -1;
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
	
	public void endTurn() {}

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

	public String getCurrentHand() {
		return players[currentPlayer].getHand();
	}

	public boolean hasWinner() {
		return winner != -1;
	}

	public int getWinner() {
		return winner;
	}
}
