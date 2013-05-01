package de.bartels.battleship.protocol;

import java.util.List;

import de.bartels.battleship.domain.Position;
import de.bartels.battleship.domain.ShipPosition;

public interface BattleshipProtocol {
	
	/**
	 * Enumeration mit allen verfügbaren Protokoll-typen
	 */
	public enum Type {
		STRING,
	}
	
	/**
	 * Dient zum Beitreten zu einem Spielraum. Existiert dieser nicht, so wird er erzeugt.
	 * 
	 * @param roomName Raumname als String; Notwendig, damit mehrere Spiele parallel laufen können
	 */
	void join(String roomName);
	
	/**
	 * Dient zum Senden des eigenen Spielfeldes an den Server.
	 * 
	 * @param shipPositions Angabe aller Felder, die von einem Schiff besetzt sind
	 */
	void lineup(List<ShipPosition> shipPositions);
	
	/**
	 * Führt einen Schuss auf die angegebene Position aus.
	 * 
	 * @param position Ziel für den Schuss
	 */
	void shot(Position position);
	
	/**
	 * Kann jederzeit von den Clients an den Server gesendet werden, 
	 * um anzuzeigen, dass der Client das Spiel verlässt. 
	 * Dies kann z.B. bei Fehlern im Client passieren.
	 * 
	 * Wenn ein Client die WON- oder LOST-Nachricht erhalten hat, 
	 * kann er erneut mittels LINEUP eine Startaufstellung senden 
	 * oder das Spiel mit BYE verlassen.
	 */
	void bye();
}
