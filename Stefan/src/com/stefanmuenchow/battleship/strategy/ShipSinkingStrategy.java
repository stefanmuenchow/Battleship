package com.stefanmuenchow.battleship.strategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.stefanmuenchow.battleship.communication.BattleshipServer;
import com.stefanmuenchow.battleship.communication.EServerResult;
import com.stefanmuenchow.battleship.field.Coordinate;
import com.stefanmuenchow.battleship.field.ETileState;
import com.stefanmuenchow.battleship.field.Field;
import com.stefanmuenchow.battleship.field.Tile;

public class ShipSinkingStrategy implements IStrategy {
	
	private final BattleshipServer server;
	private final Field field;
	private List<Coordinate> hitCoordinates;
	private EServerResult lastShotResult;
	private int[] searchVector;

	public ShipSinkingStrategy(final BattleshipServer server, final Field field, final Coordinate firstHitCoord) {
		this.server = server;
		this.field = field;
		hitCoordinates = new LinkedList<>();
		hitCoordinates.add(firstHitCoord);
		searchVector = new int[2];
	}

	@Override
	public Coordinate performNextShot() {
		Coordinate coord = null;
		
		if (hitCoordinates.size() == 1) {
			List<Tile> neumannNeighbors = field.getNeumannNeighbors(hitCoordinates.get(0));
			List<Tile> neighborsWithUnknownState = getTilesWithUnknownState(neumannNeighbors);
			
			Random random = new Random();
			int tileIndex = random.nextInt(neighborsWithUnknownState.size());
			coord = neighborsWithUnknownState.get(tileIndex).getCoordinate();
		} else if (hitCoordinates.size() >= 2) {
			if (lastShotResult == EServerResult.Water) {
				searchVector = new int[] { -searchVector[0], -searchVector[1] };
				coord = hitCoordinates.get(0).add(searchVector[0], searchVector[1]);
			} else {
				Coordinate lastHitCoord = hitCoordinates.get(hitCoordinates.size() - 1);
				coord = lastHitCoord.add(searchVector[0], searchVector[1]);
			}
		}
		
		lastShotResult = server.shot(coord);
		
		if (lastShotResult == EServerResult.Hit || lastShotResult == EServerResult.Sunk) {
			field.setTileState(coord, ETileState.Ship);
			field.setDiagonalNeighborsWater(coord);
			
			if (hitCoordinates.size() == 1) {
				calculateSearchVector(coord);
			}
			
			hitCoordinates.add(coord);
			
			// When ship is sunk the next tile in direction of search vector has to be water
			if (lastShotResult == EServerResult.Sunk) {
				Coordinate waterTile = coord.add(searchVector[0], searchVector[1]);
				field.setTileState(waterTile, ETileState.Water);
			}
		} else {
			field.setTileState(coord, ETileState.Water);
		}
		
		return coord;
	}

	private void calculateSearchVector(Coordinate coord) {
		Coordinate firstHitCoord = hitCoordinates.get(0);
		
		searchVector = new int[] {
			coord.getX() - firstHitCoord.getX(),
			coord.getY() - firstHitCoord.getY(),
		};
	}

	private List<Tile> getTilesWithUnknownState(List<Tile> tiles) {
		List<Tile> result = new LinkedList<>();
		
		for (Tile t : tiles) {
			if (t.getState() == ETileState.Unknown) {
				result.add(t);
			}
		}
		
		return result;
	}

	@Override
	public EServerResult getLastShotResult() {
		return lastShotResult;
	}

}
