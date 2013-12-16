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
}
