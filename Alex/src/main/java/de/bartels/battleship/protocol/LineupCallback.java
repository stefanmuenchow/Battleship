package de.bartels.battleship.protocol;

public interface LineupCallback {

	void onLineupOk();
	
	void onLineupFailure();
}
