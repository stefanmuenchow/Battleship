package de.bartels.battleship;

import static org.junit.Assert.*;

import org.junit.Test;

import de.bartels.battleship.domain.Position;

public class PositionTestCase {

	@Test
	public void test() {
		Position pos = Position.Factory.create(1, 1);
		assertNotNull(pos);
		
		assertEquals("A1", pos.toString());
		assertEquals("A1", pos.toString());
		
		assertEquals(1, pos.getX());
		assertEquals(1, pos.getY());
		
		Position pos2 = Position.Factory.create(1, 1);
		assertNotNull(pos);
		
//		assertEquals(pos, pos2);
//		assertNotSame(pos, pos2);
	}

}
