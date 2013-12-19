package com.stefanmuenchow.battleship;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.stefanmuenchow.battleship.communication.BattleShipServerTest;
import com.stefanmuenchow.battleship.field.CoordinateTest;
import com.stefanmuenchow.battleship.field.FieldTest;
import com.stefanmuenchow.battleship.strategy.SearchStrategyTest;
import com.stefanmuenchow.battleship.strategy.ShipSinkingStrategyTest;

@RunWith(Suite.class)
@SuiteClasses({ BattleShipServerTest.class, CoordinateTest.class,
		FieldTest.class, SearchStrategyTest.class,
		ShipSinkingStrategyTest.class, BattleshipClientTest.class })
public class AllTests {

}
