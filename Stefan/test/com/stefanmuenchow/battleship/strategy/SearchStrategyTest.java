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

public class SearchStrategyTest {
	private static final Coordinate HIT_COORDINATE = new Coordinate("A", 1);
	
	private BattleshipServer serverMock;
	private Field field;
	private IStrategy strategy;
	
	@Before
	public void setUp() throws Exception {
		serverMock = mock(BattleshipServer.class);
		when(serverMock.shot(HIT_COORDINATE)).thenReturn(EServerResult.Hit);
		when(serverMock.shot(new Coordinate("A", 2))).thenReturn(EServerResult.Water);
		when(serverMock.shot(new Coordinate("B", 1))).thenReturn(EServerResult.Water);
		when(serverMock.shot(new Coordinate("B", 2))).thenReturn(EServerResult.Water);
		
		field = new Field(2);
		strategy = new SearchStrategy(serverMock, field);
	}

	@Test
	public void testSearchStrategy() {
		// Shoot maximum 4 times
		for (int i = 0; i < 4; i++) {
			Coordinate coord = strategy.performNextShot();
			
			if (coord.equals(HIT_COORDINATE)) {
				assertEquals(EServerResult.Hit, strategy.getLastShotResult());
				assertEquals(ETileState.Ship, field.getTileState(coord));
				assertEquals(ETileState.Water, field.getTileState(coord.add(1, 1)));
				
				/* If HIT_COORDINATE was hit within the first three tries, 
				 * reduce number of shots to three (diagonal coordinate is set to WATER). */
				if (i <= 2) {
					i++;
				}
			} else {
				assertEquals(EServerResult.Water, strategy.getLastShotResult());
			}
		}
		
	}
}
