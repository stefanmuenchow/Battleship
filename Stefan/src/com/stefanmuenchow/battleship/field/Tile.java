package com.stefanmuenchow.battleship.field;

public class Tile {
	
	private final Coordinate coordinate;
	private ETileState state;
	private double hitProbability;
	
	public Tile(Coordinate coordinate, double hitProbability) {
		this.coordinate = coordinate;
		this.state = ETileState.Unknown;
		this.hitProbability = hitProbability;
	}
	
	public double getHitProbability() {
		return hitProbability;
	}
	
	public void setHitProbability(final double hitProbability) {
		this.hitProbability = hitProbability;
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
