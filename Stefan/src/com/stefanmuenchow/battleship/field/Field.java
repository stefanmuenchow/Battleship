package com.stefanmuenchow.battleship.field;

import java.util.SortedMap;
import java.util.TreeMap;

public class Field {
	
	private final SortedMap<Coordinate, Tile> tiles;
	private final int size;
	
	public Field(int size) {
		tiles = initTiles(size);
		this.size = size;
	}

	private static SortedMap<Coordinate, Tile> initTiles(int size) {
		SortedMap<Coordinate, Tile> result = new TreeMap<Coordinate, Tile>();
		
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				Coordinate coord = new Coordinate(x, y);
				Tile tile = new Tile(coord);
				result.put(coord, tile);
			}
		}
		
		return result;
	}
	
	public int getSize() {
		return size;
	}
	
	public Tile getTile(final Coordinate coord) {
		return tiles.get(coord);
	}
	
	public void setTileState(final Coordinate coord, final ETileState state) {
		tiles.get(coord).setState(state);
	}
	
	public boolean hasTileStateUnknown(final Coordinate coord) {
		return (tiles.get(coord).getState() == ETileState.Unknown);
	}
}
