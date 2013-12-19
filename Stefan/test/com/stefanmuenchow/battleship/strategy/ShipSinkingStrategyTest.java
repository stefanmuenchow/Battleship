package com.stefanmuenchow.battleship.strategy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.stefanmuenchow.battleship.communication.BattleshipServer;
import com.stefanmuenchow.battleship.communication.EServerResult;
import com.stefanmuenchow.battleship.field.Coordinate;
import com.stefanmuenchow.battleship.field.ETileState;
import com.stefanmuenchow.battleship.field.Field;

public class ShipSinkingStrategyTest {
	private static final Coordinate SHIP_COORDINATE_1 = new Coordinate("B", 3);
	private static final Coordinate SHIP_COORDINATE_2 = new Coordinate("B", 4);
	private static final Coordinate SHIP_COORDINATE_3 = new Coordinate("B", 5);
	private static final Coordinate SHIP_COORDINATE_4 = new Coordinate("B", 6);

	private BattleshipServer serverMock;
	private Field field;
	private IStrategy strategy;
	
	@Before
	public void setUp() throws Exception {
		serverMock = mock(BattleshipServer.class);
		when(serverMock.shot(SHIP_COORDINATE_1)).thenReturn(EServerResult.Hit);
		when(serverMock.shot(SHIP_COORDINATE_1.add(0, -1))).thenReturn(EServerResult.Water);
		when(serverMock.shot(SHIP_COORDINATE_3)).thenReturn(EServerResult.Hit);
		when(serverMock.shot(SHIP_COORDINATE_4)).thenReturn(EServerResult.Hit);
		when(serverMock.shot(SHIP_COORDINATE_4.add(0, 1))).thenReturn(EServerResult.Water);
		when(serverMock.shot(SHIP_COORDINATE_2.add(1, 0))).thenReturn(EServerResult.Water);
		when(serverMock.shot(SHIP_COORDINATE_2.add(-1, 0))).thenReturn(EServerResult.Water);
		
		field = new Field(10);
		field.setTileState(SHIP_COORDINATE_2, ETileState.Ship);
		strategy = new ShipSinkingStrategy(serverMock, field, SHIP_COORDINATE_2);
	}

	@Test
	public void testShipSinkingStrategy() {
		Coordinate coord = null;
		
		// First search for next ship coordinate
		while (strategy.getLastShotResult() != EServerResult.Hit) {
			coord = strategy.performNextShot();
		}
		
		// The second hit determines which coordinate will be the last
		if (coord.equals(SHIP_COORDINATE_1)) {
			when(serverMock.shot(SHIP_COORDINATE_4)).thenReturn(EServerResult.Sunk);
		} else if (coord.equals(SHIP_COORDINATE_3)) {
			when(serverMock.shot(SHIP_COORDINATE_1)).thenReturn(EServerResult.Sunk);
		}
		
		// After that the ship has to be sunk after 3 more shots
		for (int i = 0; i < 3; i++) {
			strategy.performNextShot();
		}
		
		assertEquals(EServerResult.Sunk, strategy.getLastShotResult());
		
		// Check if field tiles were set correctly
		assertEquals(ETileState.Water, field.getTileState(SHIP_COORDINATE_1.add(0, -1)));
		assertEquals(ETileState.Ship, field.getTileState(SHIP_COORDINATE_1));
		assertEquals(ETileState.Ship, field.getTileState(SHIP_COORDINATE_2));
		assertEquals(ETileState.Ship, field.getTileState(SHIP_COORDINATE_3));
		assertEquals(ETileState.Ship, field.getTileState(SHIP_COORDINATE_4));
		assertEquals(ETileState.Water, field.getTileState(SHIP_COORDINATE_4.add(0, 1)));
	}
}
