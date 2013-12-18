package com.stefanmuenchow.battleship.communication;

public enum EServerResult {
	Turn,
	Water,
	Hit,
	Sunk,
	Won,
	Lost,
	Undefined;
	
	public static EServerResult parse(String protocolName) {
		for (EServerResult e : values()) {
			if (e.toString().equalsIgnoreCase(protocolName)) {
				return e;
			}
		}
		
		return Undefined;
	}
}
