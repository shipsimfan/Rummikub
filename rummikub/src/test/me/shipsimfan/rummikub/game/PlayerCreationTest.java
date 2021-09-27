package me.shipsimfan.rummikub.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerCreationTest {
	@Test
	public void playerCreationEmptyTest() {
		// Create player with empty hand
		Player player = new Player(new Tile[0]);

		// Verify empty hand
		assertEquals("", player.getHand());
	}

	@Test
	public void playerCreationOneTest() {
		// Create player with hand
		Player player = new Player(new Tile[] { new Tile("R1") });

		// Verify hand
		assertEquals("R1", player.getHand());
	}

	@Test
	public void playerCreationManyTest() {
		// Create player with hand
		Player player = new Player(
				new Tile[] { new Tile("B3"), new Tile("G8"), new Tile("R7"), new Tile("B5"), new Tile("R12") });

		// Verify hand
		assertEquals("R7,R12,B3,B5,G8", player.getHand());
	}
}
