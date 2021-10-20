package me.shipsimfan.rummikub.cucumber;

import static org.junit.Assert.*;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import me.shipsimfan.rummikub.game.*;

public class StepDefWinning {
	@Given("^player 2 is about to win the game$")
	public void aboutToWin() {
		// Create hands and deck
		Tile[] player1Hand = new Tile[] { new Tile("R10"), new Tile("G10"), new Tile("B10"), new Tile("R1"),
				new Tile("B6"), new Tile("O10"), new Tile("O8"), new Tile("G5"), new Tile("B3") };
		Tile[] player2Hand = new Tile[] { new Tile("G8"), new Tile("G9"), new Tile("G10"), new Tile("G11"),
				new Tile("O9"), new Tile("O10"), new Tile("O11") };
		Tile[] player3Hand = new Tile[] { new Tile("B10"), new Tile("B11"), new Tile("B12"), new Tile("R2"),
				new Tile("O9"), new Tile("R9"), new Tile("R2"), new Tile("R3"), new Tile("G10") };

		Tile[] deck = new Tile[] { new Tile("B13"), new Tile("B12"), new Tile("B11"), new Tile("B10"), new Tile("G9") };

		// Create game
		StepDefAfter30.game = new Game(deck, new Player(player1Hand), new Player(player2Hand), new Player(player3Hand));

		// Play round 1
		int meld1 = StepDefAfter30.game.play("R10");
		StepDefAfter30.game.play("G10", meld1);
		StepDefAfter30.game.play("B10", meld1);
		StepDefAfter30.game.endTurn();

		// Play round 2
		int meld2 = StepDefAfter30.game.play("G8");
		StepDefAfter30.game.play("G9", meld2);
		StepDefAfter30.game.play("G10", meld2);
		StepDefAfter30.game.play("G11", meld2);
		StepDefAfter30.game.endTurn();

		// Play round 3
		int meld3 = StepDefAfter30.game.play("B10");
		StepDefAfter30.game.play("B11", meld3);
		StepDefAfter30.game.play("B12", meld3);
		StepDefAfter30.game.endTurn();

		// Play round 4
		StepDefAfter30.game.endTurn();
	}

	@Then("^there should be no winner$")
	public void checkNoWinner() {
		assertFalse(StepDefAfter30.game.hasWinner());
	}
	
	@Then("^player ([1-3]) should be the winner$")
	public void checkWinner(int player) {
		assertTrue(StepDefAfter30.game.hasWinner());
		assertEquals(player, StepDefAfter30.game.getWinner() + 1);
	}

	@Then("player ([1-3]) should have a score of (.*)")
	public void checkScore(int player, int score) {
		assertEquals(score, StepDefAfter30.game.getScore(player - 1));
	}
}
