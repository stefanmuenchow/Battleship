package de.bartels.battleship.protocol.text;

import java.util.List;

import de.bartels.battleship.domain.Position;
import de.bartels.battleship.domain.ShipPosition;
import de.bartels.battleship.protocol.BattleshipProtocol;

public class BattleshipTextProtocol implements BattleshipProtocol {

	private final String playerName;
	
	public BattleshipTextProtocol(final String playerName) {
		this.playerName = playerName;
	}
	
	public void join(String roomName) {
		// TODO Auto-generated method stub
		
	}

	public void lineup(List<ShipPosition> shipPositions) {
		// TODO Auto-generated method stub
		
	}

	public void shot(Position position) {
		// TODO Auto-generated method stub
		
	}

	public void bye() {
		// TODO Auto-generated method stub
		
	}

	
	
}
