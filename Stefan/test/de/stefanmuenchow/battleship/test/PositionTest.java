package de.stefanmuenchow.battleship.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.stefanmuenchow.battleship.common.Position;

public class PositionTest {
	
	private Position coord = new Position(4, 5);

	@Test
	public void testConstruct1() {
		new Position(4, 5);
	}
	
	@Test
	public void testConstruct2() {
		Position tmp = new Position("A3");
		assertEquals(0, tmp.getX());
		assertEquals(2, tmp.getY());
	}
	
	@Test(expected = RuntimeException.class)
	public void testConstructFail() {
		new Position(11, 16);
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
