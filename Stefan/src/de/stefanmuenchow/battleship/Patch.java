package de.stefanmuenchow.battleship;

import de.stefanmuenchow.battleship.common.PatchStatus;
import de.stefanmuenchow.battleship.common.Position;

public class Patch {
	private final Position position;
	private PatchStatus status;
	private double hitProbability;
	
	public Patch(Position position, PatchStatus status, double hitProbability) {
		this.position = position;
		this.status = status;
		this.hitProbability = hitProbability;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public PatchStatus getStatus() {
		return status;
	}
	
	public void setStatus(PatchStatus status) {
		this.status = status;
		
		if (status == PatchStatus.Water || status == PatchStatus.Ship) {
			setHitProbability(0d);
		}
	}
	
	public double getHitProbability() {
		return hitProbability;
	}
	
	public void setHitProbability(double hitProbability) {
		this.hitProbability = hitProbability;
	}
	
	@Override
	public String toString() {
		return String.format("Patch(%s, %s, %.2f)", position, status, hitProbability);
	}
}
