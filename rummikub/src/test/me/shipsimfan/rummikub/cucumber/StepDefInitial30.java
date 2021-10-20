package me.shipsimfan.rummikub.cucumber;

import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import me.shipsimfan.rummikub.game.*;

/*
 * Deck: B10, B11, B12, B13
Player 1 Hand: R1,R9,R10,R11,R12,B1,B9,B10,B11,B12,G9,O1"
 */

public class StepDefInitial30 {
	@Given("^the game has started$")
	public void startGame() {
		// Create player hands and deck
		Tile[] player1Hand = new Tile[] { new Tile("R1"), new Tile("R9"), new Tile("R10"), new Tile("R11"),
				new Tile("R12"), new Tile("B1"), new Tile("B9"), new Tile("G9"), new Tile("O1") };

		Tile[] player2Hand = new Tile[] { new Tile("R2") };
		Tile[] player3Hand = new Tile[] { new Tile("R3") };
		Tile[] deck = new Tile[] { new Tile("B13"), new Tile("B12"), new Tile("B11"), new Tile("B10") };
	
		// Create game
		StepDefAfter30.game = new Game(deck, new Player(player1Hand), new Player(player2Hand), new Player(player3Hand));
	}
	
	@Then("^player ([1-3]) should have ([0-9]+) points remaining$")
	public void checkRemainingPoints(int player, int points) {
		assertEquals(points, StepDefAfter30.game.getRemainingPoints(player - 1));
	}
}
