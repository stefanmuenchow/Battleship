package de.stefanmuenchow.battleship.communication;

import java.util.Set;

import de.stefanmuenchow.battleship.common.Position;
import de.stefanmuenchow.battleship.common.PatchStatus;

public interface ICommunication {
	
	/**
	 * Dient zum Beitreten zu einem Spielraum. Existiert dieser nicht, so wird er erzeugt.
	 * 
	 * @param roomName Raumname als String
	 * @return	<code>true</code>, falls erfolgreich, sonst <code>false</code>.
	 */
	boolean join(String roomName);
	
	/**
	 * Dient zum Senden des eigenen Spielfeldes an den Server.
	 * 
	 * @param shipPositions Menge aller Spielfelder des eigenen Spielfelds, 
	 * die von einem Schiff belegt sind.
	 * @return <code>true</code>, falls Startaufstellung OK, sonst <code>false</code>.
	 */
	boolean lineup(Set<Position> shipPositions);
	
	/**
	 * Wird von Clients an den Server gesendet, um anzuzeigen, 
	 * dass sie bereit für für einen Spielzug sind.
	 */
	void ready();
	
	/**
	 * Führt einen Schuss auf die angegebene Position aus.
	 * 
	 * @param position Ziel für den Schuss
	 * @return
	 */
	PatchStatus shot(Position position);
	
	/**
	 * Kann jederzeit von den Clients an den Server gesendet werden, um anzuzeigen, 
	 * dass der Client das Spiel verlässt. Dies kann z.B. bei Fehlern im Client passieren. 
	 * 
	 * Wenn ein Client die WON- oder LOST-Nachricht erhalten hat, 
	 * kann er erneut mittels LINEUP eine Startaufstellung senden oder das Spiel mit BYE verlassen.
	 */
	void bye();
}
