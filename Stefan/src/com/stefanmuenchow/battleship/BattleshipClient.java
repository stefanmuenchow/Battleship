package com.stefanmuenchow.battleship;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.stefanmuenchow.battleship.communication.BattleshipServer;
import com.stefanmuenchow.battleship.communication.EServerResult;
import com.stefanmuenchow.battleship.field.Coordinate;
import com.stefanmuenchow.battleship.field.Field;
import com.stefanmuenchow.battleship.strategy.IStrategy;
import com.stefanmuenchow.battleship.strategy.SearchStrategy;
import com.stefanmuenchow.battleship.strategy.ShipSinkingStrategy;

public class BattleshipClient {
	private static final Logger LOGGER = Logger.getLogger(BattleshipClient.class);
	private static final String LINEUP_FILE = "./lineup.txt";
	
	private final BattleshipServer server;
	private final String roomName;
	private final Field field;
	
	public BattleshipClient(String ip, int port, String playerName, String roomName) {
		server = new BattleshipServer(ip, port, playerName);
		this.roomName = roomName;
		this.field = new Field(10, 10);
	}
	
	public BattleshipServer getServer() {
		return server;
	}
	
	public void run() {
		String lineup = readLineup(LINEUP_FILE);
		LOGGER.info(lineup);
		joinGame(lineup);
		play();
		leaveGame();
	}
	
	private String readLineup(final String lineupFile) {
		StringBuffer result = new StringBuffer();
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(lineupFile));
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				result.append(line);
				result.append(";");
			}
			
			while (result.charAt(result.length() - 1) == ';') {
				result.setLength(result.length() - 1);
			}
			
			return result.toString();
		} catch (IOException e) {
			throw new RuntimeException("Unable to read lineup file: " + lineupFile);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new RuntimeException("Unable to close lineup file.");
				}
			}
		}
	}

	private void joinGame(final String lineup) {
		server.join(roomName);
		LOGGER.info("Successfully joined room '" + roomName + "'");
		server.lineup(lineup);
		LOGGER.info("Successfully sent lineup.");
	}
	
	private void play() {
		final IStrategy searchStrategy = new SearchStrategy(field);
		IStrategy currentStrategy = searchStrategy;
		EServerResult result = null;
		
		while (result != EServerResult.Won
			   && result != EServerResult.Lost) {
			
			result = server.ready();
			
			if (result == EServerResult.Turn) {
				Coordinate coord = currentStrategy.performNextShot();
				result = currentStrategy.getLastShotResult();
				
				if (currentStrategy instanceof ShipSinkingStrategy
					&& result == EServerResult.Sunk) {
					
					currentStrategy = searchStrategy;
				} else if (currentStrategy instanceof SearchStrategy
						   && result == EServerResult.Hit) {
					
					currentStrategy = new ShipSinkingStrategy(field, coord);
				}
			}
		}
	}
	
	private void leaveGame() {
		server.bye();
	}

	public static void main(String[] args) {
		if (args.length < 4) {
			LOGGER.error("Not enough arguments");
			return;
		}
		
		String ip = args[0];
		int port = Integer.valueOf(args[1]);
		String playerName = args[2];
		String roomName = args[3];
		
		BattleshipClient client = new BattleshipClient(ip, port, playerName, roomName);
		client.run();
	}
}
