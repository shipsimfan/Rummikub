package me.shipsimfan.rummikub.game;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Game {
	private List<List<Tile>> table;
	private Player[] players;
	private int currentPlayer;
	private Stack<Tile> deck;

	private int winner;
	private int[] scores;

	public Game() {
		table = new ArrayList<>();
		
		// Create the deck
		deck = new Stack<>();
		for(int s = 0; s < 4; s++) {
			String color = switch(s) {
			case 0 -> "R";
			case 1 -> "B";
			case 2 -> "G";
			case 3 -> "O";
			default -> throw new InvalidParameterException();
			};
			
			for(int i = 1; i < 14; i++) {
				deck.add(new Tile(color + i));
				deck.add(new Tile(color + i));
			}
		}
		Collections.shuffle(deck);

		// Deal the hands
		players = new Player[3];
		for(int p = 0; p < 3; p++) {
			Tile[] hand = new Tile[14];
			for(int i = 0; i < 14; i++)
				hand[i] = deck.pop();
			
			players[p] = new Player(hand);
		}
		
		currentPlayer = 0;
		
		winner = -1;
	}
	
	public Game(Tile[] deck, Player player1, Player player2, Player player3) {
		table = new ArrayList<>();
		players = new Player[] { player1, player2, player3 };
		currentPlayer = 0;

		winner = -1;

		this.deck = new Stack<Tile>();
		for (Tile t : deck)
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
		if (players[currentPlayer].getHand().equals("")) {
			// Set winner
			winner = currentPlayer;

			// Calculate scores
			scores = new int[] { 0, 0, 0 };
			for (int i = 0; i < 3; i++) {
				if(i == winner)
					continue;
				
				scores[i] = players[i].calculateScore();
			}
		}
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
		if (player < 0 || player > 2)
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
		if (player < 0 || player > 2)
			throw new InvalidParameterException();

		return scores[player];
	}
}
