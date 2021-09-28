package me.shipsimfan.rummikub.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameScoreTest {
	@Test
	public void GameScoreTest() {
		// Rig initial state
		Tile[] hand1 = new Tile[] { new Tile("G2"), new Tile("G2"), new Tile("O2"), new Tile("R3"), new Tile("B3"),
				new Tile("B3"), new Tile("R5"), new Tile("B6"), new Tile("O7"), new Tile("R9"), new Tile("R10"),
				new Tile("G11"), new Tile("B12"), new Tile("B13") };
		Tile[] hand2 = new Tile[] { new Tile("R2"), new Tile("B2"), new Tile("G2"), new Tile("O2"), new Tile("G3"),
				new Tile("G4"), new Tile("G6"), new Tile("G7"), new Tile("O4"), new Tile("O5"), new Tile("O6"),
				new Tile("O7"), new Tile("O8") };
		Tile[] hand3 = new Tile[] { new Tile("R4"), new Tile("O6"), new Tile("O6"), new Tile("B7"), new Tile("R7"),
				new Tile("G8"), new Tile("R10"), new Tile("R11"), new Tile("R12"), new Tile("R13"), new Tile("B10"),
				new Tile("B11"), new Tile("B12"), new Tile("B13") };
		Tile[] deck = new Tile[] { new Tile("G5"), new Tile("R2") };
		Game game = new Game(deck, new Player(hand1), new Player(hand2), new Player(hand3));

		// Play moves
		assertEquals(0, game.getCurrentPlayer());
		game.draw();

		assertEquals(1, game.getCurrentPlayer());
		game.draw();

		assertEquals(2, game.getCurrentPlayer());
		int meld1 = game.play("R10");
		game.play("R11", meld1);
		game.play("R12", meld1);
		game.play("R13", meld1);

		int meld2 = game.play("B10");
		game.play("B11", meld2);
		game.play("B12", meld2);
		game.play("B13", meld2);

		game.endTurn();

		assertEquals(0, game.getCurrentPlayer());
		int meld3 = game.play("G2");
		game.play("O2", meld3);
		game.play("R2", meld3);

		game.endTurn();

		assertEquals(1, game.getCurrentPlayer());
		int meld4 = game.play("R2");
		game.play("B2", meld4);
		game.play("G2", meld4);
		game.play("O2", meld4);

		int meld5 = game.play("G3");
		game.play("G4", meld5);
		game.play("G5", meld5);
		game.play("G6", meld5);
		game.play("G7", meld5);

		int meld6 = game.play("O4");
		game.play("O5", meld6);
		game.play("O6", meld6);
		game.play("O7", meld6);
		game.play("O8", meld6);

		// Verify game state
		assertEquals("{R10,R11,R12,R13},{B10,B11,B12,B13},{G2,O2,R2},{*R2,*B2,*G2,*O2},{*G3,*G4,*G5,*G6,*G7},{*O4,*O5,*O6,*O7,*O8}",
				game.getTable());
		assertTrue(game.hasWinner());
		assertEquals(1, game.getWinner());
		assertEquals(-78, game.getScore(0));
		assertEquals(0, game.getScore(1));
		assertEquals(-38, game.getScore(2));
	}
}
