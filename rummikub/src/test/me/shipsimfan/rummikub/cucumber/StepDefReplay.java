package me.shipsimfan.rummikub.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import me.shipsimfan.rummikub.game.*;

/*
Player 1 Hand: "B11,B13,G1,G10,G11,G13,O1,O10,O11"
Player 2 Hand: "R8,R9,R10,R11,R12,R13,J,O1"
Player 3 Hand: "R1,R10,R11,R12,B1,G1"

Deck: G2, G3, G4, G5

Initial Board State: {B11,G11,O11},{J,R8,R9,R10,R11,R12,R13},{R10,R11,R12},{R1,B1,G1}"
*/

public class StepDefReplay {
	@Given("^3 rounds have been played$")
	public void prepReplay() {
		// Prepare hands and deck
		Tile[] player1Hand = new Tile[] { new Tile("B11"), new Tile("B13"), new Tile("G1"), new Tile("G10"),
				new Tile("G11"), new Tile("G13"), new Tile("O1"), new Tile("O10"), new Tile("O11") };

		Tile[] player2Hand = new Tile[] { new Tile("R8"), new Tile("R9"), new Tile("R10"), new Tile("R11"),
				new Tile("R12"), new Tile("R13"), new Tile("O1"), new Tile("J") };

		Tile[] player3Hand = new Tile[] { new Tile("R1"), new Tile("R2"), new Tile("R10"), new Tile("R11"),
				new Tile("R12"), new Tile("B1"), new Tile("G1") };

		Tile[] deck = new Tile[] { new Tile("G5"), new Tile("G4"), new Tile("G3"), new Tile("G2") };
		
		// Create game
		StepDefAfter30.game = new Game(deck, new Player(player1Hand), new Player(player2Hand), new Player(player3Hand));
		
		// Play round 1
		int meld1 = StepDefAfter30.game.play("B11");
		StepDefAfter30.game.play("G11", meld1);
		StepDefAfter30.game.play("O11", meld1);
		StepDefAfter30.game.endTurn();
		
		// Play round 2
		int meld2 = StepDefAfter30.game.play("J");
		StepDefAfter30.game.play("R8", meld2);
		StepDefAfter30.game.play("R9", meld2);
		StepDefAfter30.game.play("R10", meld2);
		StepDefAfter30.game.play("R11", meld2);
		StepDefAfter30.game.play("R12", meld2);
		StepDefAfter30.game.play("R13", meld2);
		StepDefAfter30.game.endTurn();
		
		// Play round 3
		int meld3 = StepDefAfter30.game.play("R10");
		StepDefAfter30.game.play("R11", meld3);
		StepDefAfter30.game.play("R12", meld3);
		
		int meld4 = StepDefAfter30.game.play("R1");
		StepDefAfter30.game.play("B1",meld4);
		StepDefAfter30.game.play("G1", meld4);
		
		StepDefAfter30.game.endTurn();
	}
	
	@When("^I replay \"(.*)\" from meld ([0-9]+) to a new meld$")
	public void replayToNewMeld(String tile, int meld) {
		StepDefAfter30.last_meld = StepDefAfter30.game.reuse(meld, tile);
	}
	
	@When("^I replay \"(.*)\" from meld ([0-9]+) to meld ([0-9]+)")
	public void repaly(String tile, int initialMeld, int targetMeld) {
		StepDefAfter30.game.reuse(initialMeld, tile, targetMeld);
	}
	
	@When("^I replay \"(.*)\" from meld ([0-9]+) to the last played meld")
	public void replayToLast(String tile, int meld) {
		StepDefAfter30.game.reuse(meld, tile, StepDefAfter30.last_meld);
	}
}
