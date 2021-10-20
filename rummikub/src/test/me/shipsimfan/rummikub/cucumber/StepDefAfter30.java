package me.shipsimfan.rummikub.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import me.shipsimfan.rummikub.game.*;
import static org.junit.Assert.assertEquals;

/*
Initial Player 1 Hand: R1,R1,R2,R10,R13,B1,G1,O1,O2,O3,O4,O5,J
Initial Player 2 Hand: G10,B10,O10,R8
Initial Player 3 Hand: R9,G9,B9,O9,R9
Deck: B10, B11, B12, B13
 */

public class StepDefAfter30 {
	private Game game = null;
	private int last_meld = -1;

	@Given("^the initial 30 points have been played")
	public void playInitial30() {
		// Create the players
		Tile[] player1Hand = new Tile[] { new Tile("R1"), new Tile("R1"), new Tile("R2"), new Tile("R10"),
				new Tile("R10"), new Tile("R11"), new Tile("R12"), new Tile("R13"), new Tile("B1"), new Tile("G1"),
				new Tile("O1"), new Tile("O2"), new Tile("O3"), new Tile("O4"), new Tile("O5"), new Tile("J") };

		Tile[] player2Hand = new Tile[] { new Tile("G10"), new Tile("B10"), new Tile("O10"), new Tile("R8") };

		Tile[] player3Hand = new Tile[] { new Tile("R9"), new Tile("B9"), new Tile("G9"), new Tile("O9"),
				new Tile("R9") };

		// Create the deck
		Tile[] deck = new Tile[] { new Tile("B13"), new Tile("B12"), new Tile("B11"), new Tile("B10") };

		// Create the game
		game = new Game(deck, new Player(player1Hand), new Player(player2Hand), new Player(player3Hand));
		
		// Play round 1
		int meld1 = game.play("R10");
		game.play("R11", meld1);
		game.play("R12", meld1);
		game.endTurn();
		
		// Play round 2
		int meld2 = game.play("G10");
		game.play("B10", meld2);
		game.play("O10", meld2);
		game.endTurn();
		
		// Play round 3
		int meld3 = game.play("R9");
		game.play("G9", meld3);
		game.play("B9", meld3);
		game.play("O9", meld3);
		game.endTurn();
	}
	
	@When("^I play \"(.*)\" to a new meld$")
	public void playToNewMeld(String tile) {
		last_meld = game.play(tile);
	}

	@When("^I play \"(.*)\" to the last played meld$")
	public void playToLastMeld(String tile) {
		game.play(tile, last_meld);
	}
	
	@When("^I play \"(.*)\" to meld ([0-9]+)$")
	public void playToMeld(String tile, int meld) {
		last_meld = meld;
		game.play(tile, meld);
	}
	
	@When("^I end my turn$")
	public void endTurn() {
		game.endTurn();
	}
	
	@Then("^the board should be \"(.*)\"$")
	public void boardCheck(String board) {
		assertEquals(board, game.getTable());
	}
	
	@Then("^player ([1-3])'s hand should be \"(.*)\"")
	public void handCheck(int player, String hand) {
		assertEquals(hand, game.getHand(player - 1));
	}
}
