package de.stefanmuenchow.battleship.strategy;

import de.stefanmuenchow.battleship.Battlefield;
import de.stefanmuenchow.battleship.common.Position;

public interface IStrategy {
	
	Position getNextShotPosition(Battlefield battlefield);

}
