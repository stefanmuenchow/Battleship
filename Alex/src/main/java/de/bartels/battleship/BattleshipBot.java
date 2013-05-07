package de.bartels.battleship;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.bartels.battleship.common.Buildable;
import de.bartels.battleship.common.LineupFactory;
import de.bartels.battleship.domain.ShipPosition;

/**
 * interface to implement battleship bots
 * 
 * @author bartels
 *
 */
public interface BattleshipBot {
	
	/**
	 * start up the bot
	 */
	void startup();
	
	/**
	 * shutdown the bot
	 */
	void shutdown();
	
	/**
	 * Builder to create a new battleship bot
	 * 
	 * @author bartels
	 *
	 */
	public final static class Builder implements Buildable<BattleshipBot> {
		private static final Logger logger = LoggerFactory.getLogger(Builder.class);
		
		protected String playerName = null;
		protected String roomName = null;
		protected String host = null;
		protected int port = -1;
		protected List<ShipPosition> lineup;
		
		public Builder lineupFile(final String file) {
			File lineupFile = new File(file);
			if(!lineupFile.exists()) {
				throw new IllegalArgumentException("The given file does not exist: " + file);
			}
			
			this.lineup = LineupFactory.create(lineupFile);
			return this;
		}
		
		public Builder port(final int port) {
			if(port <= 0) {
				throw new IllegalArgumentException("The port must be a positive integer");
			}
			this.port = port;
			return this;
		}
		
		public Builder host(final String host) {
			this.host = host;
			return this;
		}
		
		public Builder roomName(final String roomName) {
			this.roomName = roomName;
			return this;
		}
		
		public Builder playerName(final String playerName) {
			this.playerName = playerName;
			return this;
		}
		
		@Override
		public BattleshipBot build() {
			
			// check if all mandatory fields are set
			if(host == null) {
				throw new IllegalStateException("the host property must be set");
			}
			
			if(roomName == null) {
				throw new IllegalStateException("the roomName property must be set");
			}
			
			if(port == -1) {
				throw new IllegalStateException("the port property must be set");
			}
			
			// check if we have to set some default values
			
			if(playerName == null) {
				playerName = "Alex's-BattleshipBot";
			}
			
			if(lineup == null) {
				// if no lineup was specified we use a default
				lineup = LineupFactory.create();
			}
			
			logger.debug("Build battleship bot. Host: {}, Port: {}, Room name: {}, Player name: {}, Lineup: {}", 
					new Object[]{host, port, roomName, playerName, lineup});
			
			return new BattleshipBotImpl(this);
		}
	}
}
