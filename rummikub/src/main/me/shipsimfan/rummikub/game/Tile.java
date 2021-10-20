package me.shipsimfan.rummikub.game;

import java.security.InvalidParameterException;
import java.util.Comparator;

public class Tile {
	enum Color {
		RED, BLUE, GREEN, ORANGE, JOKER;

		@Override
		public String toString() {
			return switch (this) {
			case RED -> "R";
			case BLUE -> "B";
			case GREEN -> "G";
			case ORANGE -> "O";
			case JOKER -> "J";
			};
		}
	}

	private Color color;
	private int number;
	private boolean newPlay;
	private boolean reuse;

	public Tile(String name) {
		int number = 1;
		if (!name.equals("J")) {
			number = Character.getNumericValue(name.charAt(1));
			if (number == 1 && name.length() > 2) {
				number *= 10;
				number += Character.getNumericValue(name.charAt(2));
			}
		}

		constructor(name.charAt(0), number);
	}

	public Tile(char color, int number) {
		constructor(color, number);
	}

	private void constructor(char color, int number) {
		this.color = switch (color) {
		case 'R' -> Color.RED;
		case 'B' -> Color.BLUE;
		case 'G' -> Color.GREEN;
		case 'O' -> Color.ORANGE;
		case 'J' -> Color.JOKER;
		default -> throw new InvalidParameterException("Invalid color code for tile name");
		};

		if (number < 1 || number > 13)
			throw new InvalidParameterException("Invalid number for tile name");

		this.number = number;
		newPlay = false;
		reuse = false;
	}

	public void setNewPlay() {
		newPlay = true;
	}

	public void setReuse() {
		reuse = true;
	}

	public void clearFlags() {
		newPlay = false;
		reuse = false;
	}

	public boolean isNewPlay() {
		return newPlay;
	}
	
	public int getNumber() {
		return number;
	}

	public char getColor() {
		return color.toString().charAt(0);
	}

	@Override
	public String toString() {
		if (color.equals(Color.JOKER))
			return "J";
		else
			return (newPlay ? "*" : "") + (reuse ? "!" : "") + color.toString() + number;
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
