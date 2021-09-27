package me.shipsimfan.rummikub.game;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
	private List<Tile> hand;
	private int pointsRemaining;

	public Player(Tile[] startingHand) {
		hand = new ArrayList<>(Arrays.asList(startingHand));
		hand.sort(new Tile.TileComparator());

		pointsRemaining = 30;
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

	public int getRemainingPoints() {
		return pointsRemaining;
	}

	public void reducePointsRemaining(int amount) {
		if (pointsRemaining == 0)
			return;

		pointsRemaining -= amount;

		if (pointsRemaining < 0)
			pointsRemaining = 0;
	}

	public Tile takeTile(String tile) {
		Tile target = new Tile(tile);
		if (!hand.remove(target))
			throw new InvalidParameterException();

		return target;
	}
}
