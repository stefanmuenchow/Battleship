package de.bartels.battleship.protocol;

import de.bartels.battleship.protocol.text.BattleshipTextProtocol;

public class BattleshipProtocolFactory {

	private BattleshipProtocolFactory() { throw new AssertionError(); }
	
	public static final BattleshipProtocol create(final BattleshipProtocol.Type type, final String playerName) {
		switch (type) {
			case STRING:
				return new BattleshipTextProtocol(playerName);
			default:
				throw new IllegalArgumentException("Unsupported protocol type: " + type);
		}
	}
}
