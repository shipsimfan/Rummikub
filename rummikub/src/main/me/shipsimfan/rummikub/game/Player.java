package me.shipsimfan.rummikub.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
	private List<Tile> hand;

	public Player(Tile[] startingHand) {
		hand = new ArrayList<>(Arrays.asList(startingHand));
		hand.sort(new Tile.TileComparator());
	}

	public String getHand() {
		String string = "";

		for (int i = 0; i < hand.size(); i++) {
			if (i != 0)
				string += ",";

			string += hand.get(i).toString();
		}

		return string;
	}
}
