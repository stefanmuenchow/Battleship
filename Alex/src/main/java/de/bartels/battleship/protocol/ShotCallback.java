package de.bartels.battleship.protocol;

public interface ShotCallback {

	void onWater();
	void onHit();
	void onSunk();
}
