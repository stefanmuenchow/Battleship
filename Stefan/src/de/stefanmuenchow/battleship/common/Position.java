package de.stefanmuenchow.battleship.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Position {
	
	private final int x;
	
	private final int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
		checkInvariant();
	}
	
	public Position(String positionFormat) {
		Pattern p = Pattern.compile("([A-J])(\\d+)");
		Matcher m = p.matcher(positionFormat);

		if (m.matches()) {
			String letter = m.group(1);
			String number = m.group(2);
			x = (int) (letter.charAt(0) - 65);
			y = Integer.parseInt(number) - 1;
			
			checkInvariant();
		} else {
			throw new RuntimeException("Invalid position: " + positionFormat);
		}
	}
	
	private void checkInvariant() {
		if (x > 9 || y > 9) {
			throw new RuntimeException("Invalid position");
		}
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the postion's name, e.g. "A7"
	 * @return	Position as letter and number
	 */
	public String format() {
		char c = (char) (65 + x);
		return String.format("%c%d", c, y + 1);
	}
	
	@Override
	public String toString() {
		return "[" + x + ";" + y + "]";
	}
}
