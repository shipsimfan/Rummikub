package me.shipsimfan.rummikub.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameSimpleReuseTest {
	private Game game;

	private void setup(String[] player1Hand) {
		setup(player1Hand, new String[0]);
	}

	private void setup(String[] player1Hand, String[] player2Hand) {
		// Set first turn hand
		String[] player1InitialHand = new String[] { "R1", "R11", "O11", "B11", "G11" };
		String[] player2InitialHand = new String[] { "B1", "R12", "B12", "G12" };
		String[] player3InitialHand = new String[] { "G1", "O7", "O8", "O9", "O10", "O11", "O12", "O13" };

		// Assemble player hands
		Tile[] hand1 = new Tile[player1Hand.length + player1InitialHand.length];
		for (int i = 0; i < player1Hand.length; i++)
			hand1[i] = new Tile(player1Hand[i]);
		for (int i = 0; i < player1InitialHand.length; i++)
			hand1[i + player1Hand.length] = new Tile(player1InitialHand[i]);

		Tile[] hand2 = new Tile[player2Hand.length + player2InitialHand.length];
		for (int i = 0; i < player2Hand.length; i++)
			hand2[i] = new Tile(player2Hand[i]);
		for (int i = 0; i < player2InitialHand.length; i++)
			hand2[i + player2Hand.length] = new Tile(player2InitialHand[i]);

		Tile[] hand3 = new Tile[player3InitialHand.length];
		for (int i = 0; i < hand3.length; i++)
			hand3[i] = new Tile(player3InitialHand[i]);

		// Create game
		game = new Game(new Tile[] { new Tile("O1"), new Tile("O2") }, new Player(hand1), new Player(hand2),
				new Player(hand3));

		// Play first turn
		int meld1 = game.play("R11");
		game.play("O11", meld1);
		game.play("B11", meld1);
		game.play("G11", meld1);

		game.endTurn();

		int meld2 = game.play("R12");
		game.play("B12", meld2);
		game.play("G12", meld2);

		game.endTurn();

		int meld3 = game.play("O7");
		game.play("O8", meld3);
		game.play("O9", meld3);
		game.play("O10", meld3);
		game.play("O11", meld3);
		game.play("O12", meld3);
		game.play("O13", meld3);

		game.endTurn();

		// Verify game state
		assertEquals(0, game.getCurrentPlayer());
		assertEquals("{R11,O11,B11,G11},{R12,B12,G12},{O7,O8,O9,O10,O11,O12,O13}", game.getTable());
		assertFalse(game.hasWinner());
	}

	@Test
	public void GameSimpleReuseTest1() {
		// Setup
		setup(new String[0], new String[] { "B11", "G11" });

		// Player 1 draws
		game.draw();

		// Player 2 plays
		int meld = game.play("B11");
		game.play("G11", meld);
		game.reuse(0, "R11", meld);

		// Verify game state
		assertEquals("B1", game.getCurrentHand());
		assertEquals("{O11,B11,G11},{R12,B12,G12},{O7,O8,O9,O10,O11,O12,O13},{*B11,*G11,!R11}", game.getTable());
		assertEquals(1, game.getCurrentPlayer());
		assertFalse(game.hasWinner());
	}

	@Test
	public void GameSimpleReuseTest2() {
		// Setup
		setup(new String[0], new String[] { "R12", "R13" });

		// Player 1 draws
		game.draw();

		// Player 2 plays
		int meld = game.reuse(0, "R11");
		game.play("R12", meld);
		game.play("R13", meld);

		// Verify game state
		assertEquals("B1", game.getCurrentHand());
		assertEquals("{O11,B11,G11},{R12,B12,G12},{O7,O8,O9,O10,O11,O12,O13},{!R11,*R12,*R13}", game.getTable());
		assertEquals(1, game.getCurrentPlayer());
		assertFalse(game.hasWinner());
	}

	@Test
	public void GameSimpleReuseTest3() {
		// Setup
		setup(new String[] { "B7", "R7" });

		// Player 1 plays
		int meld = game.reuse(2, "O7");
		game.play("B7", meld);
		game.play("R7", meld);

		// Verify game state
		assertEquals("R1", game.getCurrentHand());
		assertEquals("{R11,O11,B11,G11},{R12,B12,G12},{O8,O9,O10,O11,O12,O13},{!O7,*B7,*R7}", game.getTable());
		assertEquals(0, game.getCurrentPlayer());
		assertFalse(game.hasWinner());
	}

	@Test
	public void GameSimpleReuseTest4() {
		// Setup
		setup(new String[] { "R13", "B13" });

		// Player 1 plays
		int meld = game.play("R13");
		game.play("B13", meld);
		game.reuse(2, "O13", meld);

		// Verify game state
		assertEquals("R1", game.getCurrentHand());
		assertEquals("{R11,O11,B11,G11},{R12,B12,G12},{O7,O8,O9,O10,O11,O12},{*R13,*B13,!O13}", game.getTable());
		assertEquals(0, game.getCurrentPlayer());
		assertFalse(game.hasWinner());
	}

	@Test
	public void GameSimpleReuseTest5() {
		// Setup
		setup(new String[] { "O8", "O9" });

		// Player 1 plays
		int meld = game.play("O8");
		game.play("O9", meld);
		game.reuse(2, "O10", meld);

		// Verify game state
		assertEquals("R1", game.getCurrentHand());
		assertEquals("{R11,O11,B11,G11},{R12,B12,G12},{O7,O8,O9},{O11,O12,O13},{*O8,*O9,!O10}", game.getTable());
		assertEquals(0, game.getCurrentPlayer());
		assertFalse(game.hasWinner());
	}
}
