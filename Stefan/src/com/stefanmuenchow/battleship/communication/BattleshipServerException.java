package com.stefanmuenchow.battleship.communication;

public class BattleshipServerException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BattleshipServerException(String message) {
		super(message);
	}
}
