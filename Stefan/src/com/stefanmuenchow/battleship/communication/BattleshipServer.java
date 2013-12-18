package com.stefanmuenchow.battleship.communication;

import com.stefanmuenchow.battleship.field.Coordinate;

public class BattleshipServer {

	private final BattleshipServerConnection connection;
	private final String playerName;
	
	public BattleshipServer(final BattleshipServerConnection connection, final String playerName) {
		this.connection = connection;
		this.playerName = playerName;
	}
	
	private String createMessage(String... elements) {
		StringBuffer sb = new StringBuffer();
		
		for (String e : elements) {
			sb.append(e).append(" ");
		}
		
		sb.setLength(sb.length() - 1);
		sb.append("\n");
		return sb.toString();
	}
	
	public void join(final String roomName) {
		String result = connection.send(createMessage(Messages.JOIN, roomName, playerName));
		
		if (!result.startsWith(Messages.JOIN_OK)) {
			throw new BattleshipServerException("Unable to join room '" + roomName + "'");
		}
	}
	
	public void lineup(final String shipPositions) {
		String result = connection.send(createMessage(Messages.LINEUP, playerName, shipPositions));
		
		if (!result.startsWith(Messages.LINEUP_OK)) {
			throw new BattleshipServerException("Lineup not accepted by server.");
		}
	}
	
	public EServerResult ready() {
		String result = connection.send(createMessage(Messages.READY, playerName));
		return EServerResult.parse(result);
	}
	
	public EServerResult shot(final Coordinate coordinate) {
		String result = connection.send(createMessage(Messages.SHOT, playerName, coordinate.format()));
		
		if (result.equals(Messages.SHOT_FAILURE)) {
			throw new BattleshipServerException("Coordinate " + coordinate + " is invalid!");
		}
		
		return EServerResult.parse(result);
	}
	
	public void bye() {
		connection.send(createMessage(Messages.BYE, playerName));
		connection.close();
	}
}
