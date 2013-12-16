package com.stefanmuenchow.battleship.field;

import static org.junit.Assert.*;

import org.junit.Test;

public class FieldTest {

	@Test
	public void testInit() {
		Field field = new Field(10);
		
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				Coordinate coord = new Coordinate(x, y);
				assertTrue(field.getTile(coord).getState().equals(ETileState.Unknown));
			}
		}
	}
}
