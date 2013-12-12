package com.stefanmuenchow.battleship.strategy;

import com.stefanmuenchow.battleship.field.Coordinate;
import com.stefanmuenchow.battleship.field.Field;

public interface IStrategy {
	Coordinate getNextTargetCoord(final Field field);
}
