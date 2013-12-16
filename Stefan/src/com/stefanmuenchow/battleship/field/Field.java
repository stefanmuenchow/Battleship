package com.stefanmuenchow.battleship.field;

import java.util.SortedMap;
import java.util.TreeMap;

public class Field {
	
	private final SortedMap<Coordinate, Tile> tiles;
	
	public Field(int sizeX, int sizeY) {
		tiles = initTiles(sizeX, sizeY);
	}

	private static SortedMap<Coordinate, Tile> initTiles(int sizeX, int sizeY) {
		SortedMap<Coordinate, Tile> result = new TreeMap<Coordinate, Tile>();
		
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				Coordinate coord = new Coordinate(x, y);
				Tile tile = new Tile(coord, 0.5);
				result.put(coord, tile);
			}
		}
		
		return result;
	}
	
	public Tile getTile(final Coordinate coord) {
		return tiles.get(coord);
	}
	
	public void setTileState(final Coordinate coord, final ETileState state) {
		tiles.get(coord).setState(state);
	}
	
	public void setHitProbability(final Coordinate coord, final double value) {
		tiles.get(coord).setHitProbability(value);
	}
}
