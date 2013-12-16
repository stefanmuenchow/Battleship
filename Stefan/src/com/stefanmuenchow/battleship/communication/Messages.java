package com.stefanmuenchow.battleship.communication;

public class Messages {
	
	private Messages() { }
	
	public static final String JOIN = "JOIN";
	public static final String JOIN_OK = "JOIN_OK";
	public static final String BYE = "BYE";
	public static final String LINEUP = "LINEUP";
	public static final String LINEUP_OK = "LINEUP_OK";
	public static final String READY = "READY";
	public static final String SHOT = "SHOT";
	public static final String HIT = "HIT";
	public static final String WATER = "WATER";
	public static final String SUNK = "SUNK";
	public static final String WON = "WON";
	public static final String LOST = "LOST";
}
