package me.shipsimfan.rummikub.game;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Game {
	private List<List<Tile>> table;
	private Player[] players;
	private int currentPlayer;
	private Stack<Tile> deck;

	private boolean draw;
	private boolean initial30;

	private int winner;
	private int[] scores;

	public Game() {
		table = new ArrayList<>();

		// Create the deck
		deck = new Stack<>();
		for (int s = 0; s < 4; s++) {
			String color = switch (s) {
			case 0 -> "R";
			case 1 -> "B";
			case 2 -> "G";
			case 3 -> "O";
			default -> throw new InvalidParameterException();
			};

			for (int i = 1; i < 14; i++) {
				deck.add(new Tile(color + i));
				deck.add(new Tile(color + i));
			}
		}
		Collections.shuffle(deck);

		// Deal the hands
		players = new Player[3];
		for (int p = 0; p < 3; p++) {
			Tile[] hand = new Tile[14];
			for (int i = 0; i < 14; i++)
				hand[i] = deck.pop();

			players[p] = new Player(hand);
		}

		currentPlayer = 0;

		draw = true;
		initial30 = true;

		winner = -1;
	}

	public Game(Tile[] deck, Player player1, Player player2, Player player3) {
		table = new ArrayList<>();
		players = new Player[] { player1, player2, player3 };
		currentPlayer = 0;

		draw = true;
		initial30 = true;

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
		// Don't draw after playing
		draw = false;

		// Ignore plays after win
		if (winner >= 0)
			return;

		// Take tile from hand
		Tile t = players[currentPlayer].takeTile(tile);
		t.setNewPlay();

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
				if (i == winner)
					continue;

				scores[i] = players[i].calculateScore();
			}
		}
	}

	// Reuse without target meld number creates a new meld and returns the number
	public int reuse(int initialMeld, String tile) {
		// Ignore plays after win
		if (winner >= 0)
			return 0;

		// Create new meld
		table.add(new ArrayList<>());
		int meld = table.size() - 1;

		// Play tile to new meld
		if (reuse(initialMeld, tile, meld))
			meld++;
		return meld;
	}

	public boolean reuse(int initialMeld, String tile, int targetMeld) {
		// Don't draw after reusing
		draw = false;

		// Take tile from initial meld
		Tile t = new Tile(tile);
		List<Tile> meld = table.get(initialMeld);
		boolean found = false;
		for (int i = 0; i < meld.size(); i++) {
			if (meld.get(i).equals(t)) {
				found = true;
				if (i == 0 || i == meld.size() - 1)
					;
				else {
					if (meld.get(i - 1).equals(new Tile(t.getColor(), t.getNumber() - 1))
							&& meld.get(i + 1).equals(new Tile(t.getColor(), t.getNumber() + 1))) {

						Tile[] newMeld = new Tile[meld.size() - i - 1];
						for (int j = meld.size() - 1; j > i; j--) {
							newMeld[j - i - 1] = meld.get(j);
							meld.remove(j);
						}
						table.add(new ArrayList<>(Arrays.asList(newMeld)));
					}
				}
				meld.remove(i);
				break;
			}
		}

		if (!found)
			throw new InvalidParameterException();

		// Place tile into new meld
		t.setReuse();
		table.get(targetMeld).add(t);

		return false;
	}

	private void draw() {
		// Remove tile from deck
		Tile t = deck.pop();

		// Give tile to current player
		players[currentPlayer].addTile(t);
	}

	private void resetBoard() {
		// TODO: Reset replayed tiles

		// Reset points
		if(initial30)
			players[currentPlayer].resetPoints();
		
		// Reset newly played tiles
		for (int j = 0; j < table.size(); j++) {
			List<Tile> meld = table.get(j);
			for (int i = 0; i < meld.size(); i++) {
				Tile t = meld.get(i);

				if (t.isNewPlay()) {
					// Remove from meld
					meld.remove(i);
					i--;

					// Add to players hand
					t.clearFlags();
					players[currentPlayer].addTile(t);
				}
			}

			if (meld.size() == 0) {
				table.remove(j);
				j--;
			}
		}
	}

	private boolean verifyBoard() {
		// Verify point status
		if (initial30 && getCurrentRemainingPoints() != 0)
			return false;

		// Check each meld
		for (List<Tile> meld : table) {
			// Check meld length
			if (meld.size() < 3)
				return false;

			// Get first tile (ignoring jokers)
			int idx = 0;
			Tile t1 = meld.get(idx++);
			while (idx < meld.size() && t1.getColor() == 'J')
				t1 = meld.get(idx++);

			if (t1.getColor() == 'J')
				return true; // I suppose a full meld of jokers is valid

			// Get second tile (ignoring jokers)
			int runDifference = 1;
			Tile t2 = meld.get(idx++);
			while (idx < meld.size() && t2.getColor() == 'J') {
				t2 = meld.get(idx++);
				runDifference++;
			}

			if (t2.getColor() == 'J')
				return true;

			// Check meld type
			if (t1.getNumber() == t2.getNumber()) {
				// Verify set
				if (meld.size() > 4)
					return false;

				boolean[] colors = new boolean[] { false, false, false, false };
				for (Tile t : meld) {
					int color = switch (t.getColor()) {
					case 'R' -> 0;
					case 'B' -> 1;
					case 'G' -> 2;
					case 'O' -> 3;
					default -> -1;
					};

					// Ignore jokers
					if (color == -1)
						continue;

					if (colors[color])
						return false; // Duplicate color
					colors[color] = true;

					if (t.getNumber() != t1.getNumber())
						return false; // Wrong number
				}

			} else if (t1.getNumber() == t2.getNumber() - runDifference) {
				// Verify run
				for (int i = 0; i < meld.size(); i++) {
					Tile t = meld.get(i);
					if (t.getColor() == 'J')
						continue; // Ignore jokers

					if (t.getColor() != t1.getColor())
						return false; // Wrong color

					if (t.getNumber() != t1.getNumber() + i)
						return false; // Wrong number
				}
			} else
				return false; // Invalid meld
		}

		return true;
	}

	public void endTurn() {
		if (draw)
			draw();
		else if (!verifyBoard()) {
			// Reset and draw three
			resetBoard();
			draw();
			draw();
			draw();
		}

		// Select next player
		if (currentPlayer == 2)
			currentPlayer = 0;
		else
			currentPlayer++;
		
		draw = true;
		initial30 = getCurrentRemainingPoints() != 0;

		// Clear flags on tiles
		for (List<Tile> meld : table)
			for (Tile t : meld)
				t.clearFlags();
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public int getRemainingPoints(int player) {
		if (player < 0 || player > 2)
			throw new InvalidParameterException();

		return players[player].getRemainingPoints();
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
