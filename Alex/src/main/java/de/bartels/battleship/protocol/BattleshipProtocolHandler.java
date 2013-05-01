package de.bartels.battleship.protocol;

/**
 * Handler um auf aktionen zu reagieren die vom Server an den Klienten geschickt werden.
 * 
 * @author bartels
 *
 */
public interface BattleshipProtocolHandler {

	/**
	 * Wird an den Spieler gesendet, der am Zug ist. 
	 * Der Server lost beim ersten Zug einen Spieler aus, 
	 * der eine TURN-Nachricht bekommt. 
	 * 
	 * War ein Spieler an der Reihe 
	 * und hat ein Schiff des Gegners getroffen oder ein Schiff versenkt, 
	 * so bekommt er erneut die TURN-Nachricht.
	 */
	void onTurn();
	
	/**
	 * Wird an den Spieler gesendet, 
	 * wenn das letztes Schiff des Gegners versenkt und das Spiel gewonnen ist.
	 */
	void onWon();
	
	/**
	 * Wird an den Spieler gesendet, wenn dieser verloren hat.
	 */
	void onLost();
	
}
