package de.stefanmuenchow.battleship;


public class BattleshipBot {
	
	
	public BattleshipBot() {
	}
	
	public static void main(String[] args) {
		Battlefield bf = new Battlefield();
		bf.setHitProbability(5, 4, 0.8);
		bf.setHitProbability(6, 7, 1.0);

		System.out.println(bf.getProbabilitiesWithPatches());
	}
}
