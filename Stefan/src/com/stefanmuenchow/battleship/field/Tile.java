package com.stefanmuenchow.battleship.field;

public class Tile {
	
	private final Coordinate coordinate;
	private ETileState state;
	
	public Tile(Coordinate coordinate) {
		this.coordinate = coordinate;
		this.state = ETileState.Unknown;
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	public ETileState getState() {
		return state;
	}
	
	public void setState(final ETileState state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coordinate == null) ? 0 : coordinate.hashCode());
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
		Tile other = (Tile) obj;
		if (coordinate == null) {
			if (other.coordinate != null)
				return false;
		} else if (!coordinate.equals(other.coordinate))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "(" + getCoordinate() + "; " + getState() + ")";
	}
}
