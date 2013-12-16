package com.stefanmuenchow.battleship.strategy;

import com.stefanmuenchow.battleship.communication.EServerResult;
import com.stefanmuenchow.battleship.field.Coordinate;
import com.stefanmuenchow.battleship.field.Field;

public class SearchStrategy implements IStrategy {
	
	private final Field field;
	private EServerResult lastShotResult;

	public SearchStrategy(final Field field) {
		this.field = field;
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
