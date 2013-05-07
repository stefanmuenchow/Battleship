package de.bartels.battleship.protocol;

import java.util.List;

import de.bartels.battleship.domain.Position;
import de.bartels.battleship.domain.ShipPosition;

class BattleshipTextProtocol implements BattleshipProtocol {

	private final String playerName;
	private final String host;
	private final int port;
	private final BattleshipProtocolHandler handler;
	
	BattleshipTextProtocol(final BattleshipProtocol.Builder builder) {
		this.playerName = builder.playerName;
		this.host = builder.host;
		this.port = builder.port;
		this.handler = builder.handler;
	}

	@Override
	public void join(String roomName, JoinCallback callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lineup(List<ShipPosition> shipPositions, LineupCallback callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shot(Position position, ShotCallback callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bye() {
		// TODO Auto-generated method stub
		
	}
}
