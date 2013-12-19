package com.stefanmuenchow.battleship;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.stefanmuenchow.battleship.communication.BattleshipServer;

public class BattleshipClientTest {
	
	private BattleshipClient client;

	@Before
	public void setUp() throws Exception {
		BattleshipServer serverMock = mock(BattleshipServer.class);
		client = new BattleshipClient(serverMock, "room1");
	}

	@Test
	public void testReadLineup() {
		String lineup = client.readLineup("./testLineup.txt");
		assertEquals("I2;I3;I4;I5;I6;A5;B5;C5;D5;A7;A8;A9;F2;F3", lineup);
	}
}
