package com.stefanmuenchow.battleship.strategy;

import com.stefanmuenchow.battleship.communication.EServerResult;
import com.stefanmuenchow.battleship.field.Coordinate;
import com.stefanmuenchow.battleship.field.Field;

public class ShipSinkingStrategy implements IStrategy {
	
	private final Field field;
	private final Coordinate firstHitCoord;
	private EServerResult lastShotResult;

	public ShipSinkingStrategy(final Field field, final Coordinate firstHitCoord) {
		this.field = field;
		this.firstHitCoord = firstHitCoord;
	}

	@Override
	public Coordinate performNextShot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EServerResult getLastShotResult() {
		return lastShotResult;
	}

}
