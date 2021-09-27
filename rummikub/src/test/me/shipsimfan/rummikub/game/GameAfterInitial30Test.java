package me.shipsimfan.rummikub.game;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GameAfterInitial30Test {
	void performTest(String[] tiles) {
		// Generate hand and target
		List<Tile> hand = new ArrayList<>();
		String table = "{R11,R12,R13},{B11,B12,B13},{O11,O12,O13}";

		boolean meld = true;
		boolean comma = true;
		for (String tile : tiles) {
			if (meld) {
				if (comma)
					table += ",";
				table += "{";
				meld = false;
				comma = false;
			}

			if (tile == "/") {
				table += "}";
				comma = true;
				meld = true;
				continue;
			}

			if (comma) {
				table += ",";
				comma = false;
			}

			table += "*";
			table += tile;
			hand.add(new Tile(tile));
			comma = true;
		}

		if (!meld) {
			table += "}";
		}

		hand.add(new Tile("R1"));
		hand.add(new Tile("R11"));
		hand.add(new Tile("R12"));
		hand.add(new Tile("R13"));

		Tile[] hand_arr = new Tile[hand.size()];
		hand.toArray(hand_arr);

		// Perform initial rigging
		Game game = new Game(new Tile[0], new Player(hand_arr),
				new Player(new Tile[] { new Tile("B11"), new Tile("B12"), new Tile("B13"), new Tile("R1") }),
				new Player(new Tile[] { new Tile("O11"), new Tile("O12"), new Tile("O13"), new Tile("R1") }));

		assertEquals(0, game.getCurrentPlayer());

		int initMeld1 = game.play("R11");
		game.play("R12", initMeld1);
		game.play("R13", initMeld1);

		game.endTurn();
		assertEquals(1, game.getCurrentPlayer());

		int initMeld2 = game.play("B11");
		game.play("B12", initMeld2);
		game.play("B13", initMeld2);

		game.endTurn();
		assertEquals(2, game.getCurrentPlayer());

		int initMeld3 = game.play("O11");
		game.play("O12", initMeld3);
		game.play("O13", initMeld3);

		game.endTurn();
		assertEquals(0, game.getCurrentPlayer());

		// Play the cards
		int currentMeld = -1;
		for (String tile : tiles) {
			if (tile == "/") {
				currentMeld = -1;
				continue;
			}

			if (currentMeld == -1)
				// Create new hand
				currentMeld = game.play(tile);
			else
				game.play(tile, currentMeld);
		}

		// Test the game state
		assertEquals(0, game.getCurrentPlayer());
		assertEquals(0, game.getCurrentRemainingPoints());
		assertEquals(table, game.getTable());
		assertEquals("R1", game.getCurrentHand());
		assertFalse(game.hasWinner());
	}

	@Test
	public void GameAfterInitial30Test1() {
		performTest(new String[] { "G2", "G3", "G4" });
	}

	@Test
	public void GameAfterInitial30Test2() {
		performTest(new String[] { "G2", "G3", "G4", "/", "O8", "O9", "O10" });
	}

	@Test
	public void GameAfterInitial30Test3() {
		performTest(new String[] { "G2", "R2", "O2" });
	}

	@Test
	public void GameAfterInitial30Test4() {
		performTest(new String[] { "G2", "R2", "O2", "/", "O8", "R8", "B8", "G8" });
	}

	@Test
	public void GameAfterInitial30Test5() {
		performTest(new String[] { "G2", "R2", "O2", "/", "O8", "O9", "O10" });
	}

	@Test
	public void GameAfterInitial30Test6() {
		performTest(new String[] { "G2", "R2", "O2", "/", "G3", "R3", "O3", "/", "G8", "G9", "G10", "G11", "G12" });
	}
}
