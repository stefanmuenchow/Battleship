package com.stefanmuenchow.battleship.field;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordinateTest {

	@Test
	public void testCreation() {
		Coordinate coord = new Coordinate("A", 5);
		assertEquals(0, coord.getX());
		assertEquals(4, coord.getY());
	}
	
	@Test
	public void testFormat() {
		Coordinate coord = new Coordinate(9, 9);
		assertEquals("J10", coord.format());
	}
	
	@Test
	public void testEquals() {
		Coordinate coord1 = new Coordinate("B", 2);
		Coordinate coord2 = new Coordinate(1, 1);
		assertTrue(coord1.equals(coord2));
	}
	
	@Test
	public void testAdd() {
		Coordinate coord1 = new Coordinate("C", 4);
		Coordinate coord2 = new Coordinate("D", 5);
		assertEquals(coord2, coord1.add(1, 1));
	}
	
	@Test
	public void testCompare() {
		Coordinate coord1 = new Coordinate("C", 4);
		Coordinate coord2 = new Coordinate("D", 5);
		Coordinate coord3 = new Coordinate("C", 6);
		assertTrue(coord1.compareTo(coord2) < 0);
		assertTrue(coord1.compareTo(coord3) < 0);
		assertTrue(coord2.compareTo(coord3) > 0);
		assertTrue(coord3.compareTo(coord1.add(0, 2)) == 0);
	}
}
