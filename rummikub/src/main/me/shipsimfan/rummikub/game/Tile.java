package me.shipsimfan.rummikub.game;

import java.security.InvalidParameterException;
import java.util.Comparator;

public class Tile {
	enum Color {
		RED, BLUE, GREEN, ORANGE;

		@Override
		public String toString() {
			return switch (this) {
			case RED -> "R";
			case BLUE -> "B";
			case GREEN -> "G";
			case ORANGE -> "O";
			};
		}
	}

	private Color color;
	private int number;
	private boolean newPlay;

	public Tile(String name) {
		color = switch (name.charAt(0)) {
		case 'R' -> Color.RED;
		case 'B' -> Color.BLUE;
		case 'G' -> Color.GREEN;
		case 'O' -> Color.ORANGE;
		default -> throw new InvalidParameterException("Invalid color code for tile name");
		};

		number = Character.getNumericValue(name.charAt(1));
		if (number == 1 && name.length() > 2) {
			number *= 10;
			number += Character.getNumericValue(name.charAt(2));
		}

		if (number < 1 || number > 13)
			throw new InvalidParameterException("Invalid number for tile name");

		newPlay = false;
	}

	public void setNewPlay(boolean value) {
		newPlay = value;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return (newPlay ? "*" : "") + color.toString() + number;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!(o instanceof Tile t))
			return false;

		return color == t.color && number == t.number;
	}

	public static class TileComparator implements Comparator<Tile> {
		public int compare(Tile t1, Tile t2) {
			if (t1.color == t2.color)
				return t1.number - t2.number;

			return t1.color.compareTo(t2.color);
		}
	}
}
