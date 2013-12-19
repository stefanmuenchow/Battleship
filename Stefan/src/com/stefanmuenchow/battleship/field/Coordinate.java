package com.stefanmuenchow.battleship.field;

public class Coordinate implements Comparable<Coordinate> {
	
	private final int x;
	private final int y;
	
	public Coordinate(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordinate(final String l, final int n) {
		this(convert(l), n - 1);
	}
	
	private static int convert(final String letter) {
		return letter.charAt(0) - 65;
	}
	
	public String format() {
		char c = (char) (getX() + 65);
		int yVal = y + 1;
		return new String(c + "" + yVal);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Coordinate add(int valX, int valY) {
		Coordinate result = new Coordinate(getX() + valX, getY() + valY);
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public int compareTo(Coordinate o) {
		int diffX = getX() - o.getX();
		int diffY = getY() - o.getY();
		
		if (diffX == 0) {
			return diffY;
		} else {
			return diffX;
		}
	}
	
	@Override
	public String toString() {
		return "[" + getX() + ", " + getY() + "]";
	}
}
