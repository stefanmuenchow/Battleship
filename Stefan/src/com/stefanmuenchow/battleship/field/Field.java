package com.stefanmuenchow.battleship.field;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Field {
	
	private final SortedMap<Coordinate, Tile> tiles;
	private final int dimension;
	
	public Field(final int dimension) {
		tiles = initTiles(dimension);
		this.dimension = dimension;
	}

	private static SortedMap<Coordinate, Tile> initTiles(final int size) {
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
	
	public int getDimension() {
		return dimension;
	}
	
	public ETileState getTileState(final Coordinate coord) {
		return tiles.get(coord).getState();
	}
	
	public void setTileState(final Coordinate coord, final ETileState state) {
		tiles.get(coord).setState(state);
	}
	
	public List<Tile> getTilesWithUnknownState() {
		List<Tile> result = new LinkedList<Tile>();
		for (Tile t : tiles.values()) {
			if (t.getState() == ETileState.Unknown) {
				result.add(t);
			}
		}
		
		return result;
	}

	public void setDiagonalNeighborsWater(final Coordinate coord) {
		int[][] directionVectors = new int[][] {
				new int[] { -1, -1 },
				new int[] { 1, -1 },
				new int[] { 1, 1 },
				new int[] { -1, 1 }
		};
		
		for (int[] d : directionVectors) {
			Coordinate diagonalNeighbor = coord.add(d[0], d[1]);
			
			if (diagonalNeighbor.getX() >= 0 
				&& diagonalNeighbor.getX() <= (getDimension() - 1) 
				&& diagonalNeighbor.getY() >= 0 
				&& diagonalNeighbor.getY() <= (getDimension() - 1)) {
				
				setTileState(diagonalNeighbor, ETileState.Water);
			}
		}
	}
	
	public List<Tile> getNeumannNeighbors(final Coordinate coord) {
		List<Tile> result = new ArrayList<>(4);
		int[][] directionVectors = new int[][] {
				new int[] { 0, -1 },
				new int[] { 1, 0 },
				new int[] { 0, 1 },
				new int[] { -1, 0 }
		};
		
		for (int[] d : directionVectors) {
			Coordinate diagonalNeighbor = coord.add(d[0], d[1]);
			
			if (diagonalNeighbor.getX() >= 0 
				&& diagonalNeighbor.getX() <= (getDimension() - 1) 
				&& diagonalNeighbor.getY() >= 0 
				&& diagonalNeighbor.getY() <= (getDimension() - 1)) {
				
				result.add(tiles.get(diagonalNeighbor));
			}
		}
		
		return result;
	}
}
