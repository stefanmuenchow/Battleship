package com.stefanmuenchow.battleship.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FieldTest {
	private final static Coordinate COORD1 = new Coordinate("C", 2);
	private final static Coordinate COORD2 = new Coordinate("A", 1);
	private final static Coordinate COORD3 = new Coordinate("I", 10);
	
	private Field field;

	@Before
	public void setUp() throws Exception {
		field = new Field(10);
	}
	
	@Test
	public void testInit() {
		assertEquals(100, field.getTilesWithUnknownState().size());
	}
	
	@Test
	public void testSetTileState() {
		field.setTileState(COORD1, ETileState.Water);
		field.setTileState(COORD2, ETileState.Ship);
		field.setTileState(COORD3, ETileState.Unknown);
		assertEquals(ETileState.Water, field.getTileState(COORD1));
		assertEquals(ETileState.Ship, field.getTileState(COORD2));
		assertEquals(ETileState.Unknown, field.getTileState(COORD3));
	}
	
	@Test
	public void testGetTilesWithUnknownState() {
		field.setTileState(COORD1, ETileState.Water);
		field.setTileState(COORD2, ETileState.Ship);
		List<Tile> tilesWithUnknownState = field.getTilesWithUnknownState();
		
		assertEquals(98, tilesWithUnknownState.size());
		assertFalse(tilesWithUnknownState.contains(new Tile(COORD1)));
		assertFalse(tilesWithUnknownState.contains(new Tile(COORD2)));
	}
	
	@Test
	public void testSetDiagonalNeighborsWater() {
		field.setDiagonalNeighborsWater(COORD1);
		assertEquals(ETileState.Water, field.getTileState(COORD1.add(-1, -1)));
		assertEquals(ETileState.Water, field.getTileState(COORD1.add(1, -1)));
		assertEquals(ETileState.Water, field.getTileState(COORD1.add(1, 1)));
		assertEquals(ETileState.Water, field.getTileState(COORD1.add(-1, 1)));
		
		field.setDiagonalNeighborsWater(COORD3);
		assertEquals(ETileState.Water, field.getTileState(COORD1.add(-1, -1)));
	}
	
	@Test
	public void testGetNeumannNeighbors() {
		List<Tile> neighbors1 = field.getNeumannNeighbors(COORD1);
		assertTrue(neighbors1.contains(new Tile(COORD1.add(0, -1))));
		assertTrue(neighbors1.contains(new Tile(COORD1.add(1, 0))));
		assertTrue(neighbors1.contains(new Tile(COORD1.add(0, 1))));
		assertTrue(neighbors1.contains(new Tile(COORD1.add(-1, 0))));
		
		List<Tile> neighbors2 = field.getNeumannNeighbors(COORD3);
		assertTrue(neighbors2.contains(new Tile(COORD3.add(0, -1))));
		assertTrue(neighbors2.contains(new Tile(COORD3.add(-1, 0))));
	}
	
	@Test
	public void testEquals() {
		Tile tile1 = new Tile(COORD1);
		Tile tile2 = new Tile(new Coordinate(2, 1));
		
		assertTrue(tile1.equals(tile2));
		assertFalse(tile1.equals(new Tile(COORD2)));
	}
}
