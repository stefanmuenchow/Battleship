package com.stefanmuenchow.battleship.strategy;

import com.stefanmuenchow.battleship.communication.EServerResult;
import com.stefanmuenchow.battleship.field.Coordinate;


public interface IStrategy {
	Coordinate performNextShot();
	EServerResult getLastShotResult();
}
