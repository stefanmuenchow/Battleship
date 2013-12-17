package com.stefanmuenchow.battleship.strategy;

import java.util.List;
import java.util.Random;

import com.stefanmuenchow.battleship.communication.BattleshipServer;
import com.stefanmuenchow.battleship.communication.EServerResult;
import com.stefanmuenchow.battleship.field.Coordinate;
import com.stefanmuenchow.battleship.field.ETileState;
import com.stefanmuenchow.battleship.field.Field;
import com.stefanmuenchow.battleship.field.Tile;

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
		List<Tile> tilesWithUnknownState = field.getTilesWithUnknownState();
		Random rand = new Random();
		int tileIndex = rand.nextInt(tilesWithUnknownState.size());
		Coordinate coord = tilesWithUnknownState.get(tileIndex).getCoordinate();
		lastShotResult = server.shot(coord);
		
		if (lastShotResult == EServerResult.Hit) {
			field.setTileState(coord, ETileState.Ship);
			field.setDiagonalNeighborsWater(coord);
		} else {
			field.setTileState(coord, ETileState.Water);
		}
		
		return coord;
	}

	@Override
	public EServerResult getLastShotResult() {
		return lastShotResult;
	}
}
