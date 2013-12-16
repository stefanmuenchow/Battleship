package com.stefanmuenchow.battleship.strategy;

import java.util.Random;

import com.stefanmuenchow.battleship.communication.BattleshipServer;
import com.stefanmuenchow.battleship.communication.EServerResult;
import com.stefanmuenchow.battleship.field.Coordinate;
import com.stefanmuenchow.battleship.field.ETileState;
import com.stefanmuenchow.battleship.field.Field;

public class SearchStrategy implements IStrategy {
	
	private final BattleshipServer server;
	private final Field field;
	private EServerResult lastShotResult;

	public SearchStrategy(final BattleshipServer server, final Field field) {
		this.server = server;
		this.field = field;
	}

	@Override
	public Coordinate performNextShot() {
		Random rand = new Random();
		int coordX = rand.nextInt(field.getSize());
		int coordY = rand.nextInt(field.getSize());
		Coordinate coord = new Coordinate(coordX, coordY);

		while (!field.hasTileStateUnknown(coord)) {
			coordX = rand.nextInt(field.getSize());
			coordY = rand.nextInt(field.getSize());
			coord = new Coordinate(coordX, coordY);
		}

		lastShotResult = server.shot(coord);
		
		if (lastShotResult == EServerResult.Hit) {
			field.setTileState(coord, ETileState.Ship);
			setDiagonalNeighborsWater(coord);
		} else {
			field.setTileState(coord, ETileState.Water);
		}
		
		return coord;
	}

	private void setDiagonalNeighborsWater(final Coordinate coord) {
		int[][] directionVectors = new int[][] {
				new int[] { -1, -1 },
				new int[] { 1, -1 },
				new int[] { 1, 1 },
				new int[] { -1, 1 }
		};
		
		for (int[] d : directionVectors) {
			Coordinate diagonalNeighbor = coord.add(d[0], d[1]);
			
			if (diagonalNeighbor.getX() >= 0 
				&& diagonalNeighbor.getX() <= 9 
				&& diagonalNeighbor.getY() >= 0 
				&& diagonalNeighbor.getY() <= 9) {
				
				field.setTileState(diagonalNeighbor, ETileState.Water);
			}
		}
	}

	@Override
	public EServerResult getLastShotResult() {
		return lastShotResult;
	}
}
