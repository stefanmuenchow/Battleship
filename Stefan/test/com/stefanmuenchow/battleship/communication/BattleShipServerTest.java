package com.stefanmuenchow.battleship.communication;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.stefanmuenchow.battleship.field.Coordinate;

import static org.mockito.Mockito.*;

public class BattleShipServerTest {
	private static final String PLAYER_NAME = "stefan";
	private static final String EXISTING_ROOM = "existingRoom";
	private static final String NOT_EXISTING_ROOM = "notExistingRoom";
	private static final String CORRECT_LINEUP = "I2;I3;I4;I5;I6;A5;B5;C5;D5;E10;F10;G10;H10;A7;A8;A9;J8;J9;J10;B2;C2;D2;F2;F3;F6;F7;C7;D7;C9;C10";
	private static final String INCORRECT_LINEUP = ";J8;J9;J11;B2;C21;D2!F2;F3;F6;F7 ;,C8;D7;C9;C10";
	private static final Coordinate WATER_COORDINATE = new Coordinate("B", 1);
	private static final Coordinate SHIP_COORDINATE_1 = new Coordinate("B", 2);
	private static final Coordinate SHIP_COORDINATE_2 = new Coordinate("B", 3);
	private static final Coordinate INVALID_COORDINATE = new Coordinate("X", 56);
	
	BattleshipServerConnection connectionMock;
	BattleshipServer server;

	@Before
	public void setUp() throws Exception {
		connectionMock = mock(BattleshipServerConnection.class);
		when(connectionMock.send(Messages.JOIN + " " + EXISTING_ROOM + " " + PLAYER_NAME + "\n")).thenReturn(Messages.JOIN_OK);
		when(connectionMock.send(Messages.JOIN + " " + NOT_EXISTING_ROOM + " " + PLAYER_NAME + "\n")).thenReturn(Messages.JOIN_FAILURE);
		when(connectionMock.send(Messages.LINEUP + " " + PLAYER_NAME + " " + CORRECT_LINEUP + "\n")).thenReturn(Messages.LINEUP_OK);
		when(connectionMock.send(Messages.LINEUP + " " + PLAYER_NAME + " " + INCORRECT_LINEUP + "\n")).thenReturn(Messages.LINEUP_FAILURE);
		
		server = new BattleshipServer(connectionMock, PLAYER_NAME);
	}

	@Test
	public void testJoin() {
		server.join(EXISTING_ROOM);
	}
	
	@Test(expected=BattleshipServerException.class)
	public void testJoinFailure() {
		server.join(NOT_EXISTING_ROOM);
	}

	@Test
	public void testLineup() {
		server.lineup(CORRECT_LINEUP);
	}
	
	@Test(expected=BattleshipServerException.class)
	public void testLineupFailure() {
		server.lineup(INCORRECT_LINEUP);
	}

	@Test
	public void testReadyTurn() {
		when(connectionMock.send(Messages.READY + " " + PLAYER_NAME + "\n")).thenReturn(Messages.TURN);
		assertEquals(EServerResult.Turn, server.ready());
	}
	
	@Test
	public void testReadyLost() {
		when(connectionMock.send(Messages.READY + " " + PLAYER_NAME + "\n")).thenReturn(Messages.LOST);
		assertEquals(EServerResult.Lost, server.ready());
	}
	
	@Test
	public void testShot() {
		when(connectionMock.send(Messages.SHOT + " " + PLAYER_NAME + " " + WATER_COORDINATE.format() + "\n")).thenReturn(Messages.WATER);
		when(connectionMock.send(Messages.SHOT + " " + PLAYER_NAME + " " + SHIP_COORDINATE_1.format() + "\n")).thenReturn(Messages.HIT);
		when(connectionMock.send(Messages.SHOT + " " + PLAYER_NAME + " " + SHIP_COORDINATE_2.format() + "\n")).thenReturn(Messages.SUNK);
		assertEquals(EServerResult.Water, server.shot(WATER_COORDINATE));
		assertEquals(EServerResult.Hit, server.shot(SHIP_COORDINATE_1));
		assertEquals(EServerResult.Sunk, server.shot(SHIP_COORDINATE_2));
	}
	
	@Test(expected=BattleshipServerException.class)
	public void testShotFailure() {
		when(connectionMock.send(Messages.SHOT + " " + PLAYER_NAME + " " + INVALID_COORDINATE.format() + "\n")).thenReturn(Messages.SHOT_FAILURE);
		server.shot(INVALID_COORDINATE);
	}

	@Test
	public void testBye() {
		server.bye();
	}
}
