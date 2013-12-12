package com.stefanmuenchow.battleship.field;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordinateTest {

	@Test
	public void testCreation() {
		Coordinate coord = new Coordinate("A", 5);
		assertEquals(0, coord.getX());
		assertEquals(5, coord.getY());
	}
	
	@Test
	public void testFormat() {
		Coordinate coord = new Coordinate(9, 9);
		assertEquals("J10", coord.format());
	}
	
	@Test
	public void testEquals() {
		Coordinate coord1 = new Coordinate("B", 2);
		Coordinate coord2 = new Coordinate(1, 2);
		assertTrue(coord1.equals(coord2));
	}
}
