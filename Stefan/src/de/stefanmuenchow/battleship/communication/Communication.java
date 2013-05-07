package de.stefanmuenchow.battleship.communication;

import java.util.Set;

import org.apache.log4j.Logger;

import de.stefanmuenchow.battleship.common.Position;
import de.stefanmuenchow.battleship.common.PatchStatus;

public class Communication implements ICommunication {
	private static final Logger LOG = Logger.getLogger(Communication.class);
	
	String playerName;
	
	public Communication(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public boolean join(String roomName) {
		LOG.debug("Sending message: JOIN " + roomName);
		return true;
	}

	@Override
	public boolean lineup(Set<Position> shipPositions) {
		StringBuffer sb = new StringBuffer();
		for (Position p : shipPositions) {
			sb.append(p.format() + ";");
		}
		sb.setLength(sb.length() - 1);
		
		LOG.debug("Sending message: LINEUP " + sb.toString());
		return true;
	}

	@Override
	public void ready() {
		LOG.debug("Sending message: READY");
		
		try {
			Thread.sleep(2000l);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	@Override
	public PatchStatus shot(Position position) {
		LOG.debug("Sending message: SHOT " + position.format());
		return PatchStatus.Water;
	}

	@Override
	public void bye() {
		LOG.debug("Sending message: BYE");
	}
}
