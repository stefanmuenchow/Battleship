package de.stefanmuenchow.battleship;

public class Coordinate {
	
	private final int x;
	
	private final int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
		checkInvariant();
	}
	
	private void checkInvariant() {
		if (x > 9 || y > 9) {
			throw new RuntimeException("Invalid coordinate!");
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
	 * Returns the coordinate name, e.g. "A7"
	 * @return	Coordinate as letter and number
	 */
	public String format() {
		char c = (char) (65 + x);
		return String.format("%c%d", c, y + 1);
	}
}
