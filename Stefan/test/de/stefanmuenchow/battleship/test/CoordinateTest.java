package de.stefanmuenchow.battleship.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.stefanmuenchow.battleship.Coordinate;

public class CoordinateTest {
	
	private Coordinate coord = new Coordinate(4, 5);

	@Test
	public void testConstruct() {
		new Coordinate(4, 5);
	}
	
	@Test(expected = RuntimeException.class)
	public void testConstructFail() {
		new Coordinate(11, 16);
	}

	@Test
	public void testGetX() {
		assertEquals(4, coord.getX());
	}

	@Test
	public void testGetY() {
		assertEquals(5, coord.getY());
	}

	@Test
	public void testFormat() {
		assertEquals("E6", coord.format());
	}
}
