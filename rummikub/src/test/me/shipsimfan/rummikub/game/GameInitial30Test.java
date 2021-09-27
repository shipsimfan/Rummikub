package me.shipsimfan.rummikub.game;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GameInitial30Test {
	void performTest(String[] tiles) {
		performTest(tiles, false);
	}

	void performTest(String[] tiles, boolean winner) {
		// Generate hand and target
		List<Tile> hand = new ArrayList<>();
		String table = "";

		boolean meld = true;
		boolean comma = false;
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

		if (!winner) {
			hand.add(new Tile("R1"));
		}

		Tile[] hand_arr = new Tile[hand.size()];
		hand.toArray(hand_arr);

		// Rig the initial game
		Game game = new Game(new Tile[0], new Player(hand_arr), new Player(new Tile[0]), new Player(new Tile[0]));

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

		if (winner) {
			assertEquals("", game.getCurrentHand());
			assertTrue(game.hasWinner());
			assertEquals(0, game.getWinner());
		} else {
			assertEquals("R1", game.getCurrentHand());
			assertFalse(game.hasWinner());
		}
	}

	@Test
	public void GameInitial30Test1() {
		performTest(new String[] { "R11", "R12", "R13" });
	}

	@Test
	public void GameInitial30Test2() {
		performTest(new String[] { "R12", "G12", "B12" });
	}

	@Test
	public void GameInitial30Test3() {
		performTest(new String[] { "R9", "R10", "R11", "R12", "R13" });
	}

	@Test
	public void GameInitial30Test4() {
		performTest(new String[] { "R13", "G13", "B13", "O13" });
	}

	@Test
	public void GameInitial30Test5() {
		performTest(new String[] { "R2", "R3", "R4", "/", "B7", "B8", "B9" });
	}

	@Test
	public void GameInitial30Test6() {
		performTest(new String[] { "R2", "B2", "O2", "/", "G4", "O4", "B4", "R4", "/", "O5", "B5", "R5" });
	}

	@Test
	public void GameInitial30Test7() {
		performTest(new String[] { "R8", "G8", "O8", "/", "R2", "R3", "R4" });
	}

	@Test
	public void GameInitial30Test8() {
		performTest(
				new String[] { "R2", "O2", "B2", "/", "G2", "G3", "G4", "/", "R3", "B3", "O3", "/", "B5", "B6", "B7" });
	}

	@Test
	public void GameInitial30Test9() {
		performTest(new String[] { "R2", "B2", "G2", "O2", "/", "G3", "G4", "G5", "G6", "G7", "/", "O4", "O5", "O6",
				"O7", "O8" }, true);
	}
}
