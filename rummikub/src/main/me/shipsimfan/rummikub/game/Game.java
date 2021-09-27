package me.shipsimfan.rummikub.game;

public class Game {
	public Game(Tile[] deck, Player player1, Player player2, Player player3) {
	}

	// Play without meld number creates a new meld and returns the number
	public int play(String tile) {
		return 0;
	}

	public void play(String tile, int meld) {

	}
	
	public int getCurrentPlayer() {
		return 0;
	}
	
	public int getCurrentRemainingPoints() {
		return 0;
	}
	
	public String getTable() {
		return "";
	}
	
	public String getCurrentHand() {
		return "";
	}
	
	public boolean hasWinner() {
		return false;
	}
	
	public int getWinner() {
		return 0;
	}
}
