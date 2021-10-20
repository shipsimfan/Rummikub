package me.shipsimfan.rummikub.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class GameDrawTest {
	void performTest(String[] startingHand, String target, String drawTile) {
		// Generate hand
		Tile[] hand = new Tile[startingHand.length];
		for (int i = 0; i < hand.length; i++)
			hand[i] = new Tile(startingHand[i]);

		// Rig game
		Game game = new Game(new Tile[] { new Tile(drawTile) }, new Player(hand),
				new Player(new Tile[] { new Tile("R1") }), new Player(new Tile[0]));

		// Perform draw
		game.endTurn();

		// Verify hand and turn end
		assertEquals("", game.getTable());
		assertEquals(target, game.getHand(0));
		assertEquals(1, game.getCurrentPlayer());
		assertFalse(game.hasWinner());
	}

	@Test
	public void GameDrawTest1() {
		performTest(
				new String[] { "G2", "R2", "O2", "G3", "R3", "O3", "O8", "O9", "O10", "R8", "R9", "R10", "G12", "R7" },
				"R2,R3,R7,R8,R9,R10,B6,G2,G3,G12,O2,O3,O8,O9,O10", "B6");
	}

	@Test
	public void GameDrawTest2() {
		performTest(
				new String[] { "G2", "G2", "O2", "R3", "B3", "B3", "R5", "B6", "O7", "R9", "R10", "G11", "B12", "B13" },
				"R3,R5,R9,R10,R10,B3,B3,B6,B12,B13,G2,G2,G11,O2,O7", "R10");

	}
}
