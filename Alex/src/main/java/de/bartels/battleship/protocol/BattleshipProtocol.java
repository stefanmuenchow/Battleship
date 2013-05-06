package de.bartels.battleship.protocol;

import java.util.List;

import de.bartels.battleship.common.Buildable;
import de.bartels.battleship.domain.Position;
import de.bartels.battleship.domain.ShipPosition;

public interface BattleshipProtocol {
	
	
	/**
	 * Dient zum Beitreten zu einem Spielraum. Existiert dieser nicht, so wird er erzeugt.
	 * 
	 * @param roomName Raumname als String; Notwendig, damit mehrere Spiele parallel laufen können
	 * @param callback Callback um die antwort des Servers zu verabeiten
	 */
	void join(String roomName, JoinCallback callback);
	
	/**
	 * Dient zum Senden des eigenen Spielfeldes an den Server.
	 * 
	 * @param shipPositions Angabe aller Felder, die von einem Schiff besetzt sind
	 * @param callback Callback um die antwort des Servers zu verabeiten
	 */
	void lineup(List<ShipPosition> shipPositions, LineupCallback callback);
	
	/**
	 * Führt einen Schuss auf die angegebene Position aus.
	 * 
	 * @param position Ziel für den Schuss
	 * @param callback Callback um die antwort des Servers zu verabeiten
	 */
	void shot(Position position, ShotCallback callback);
	
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
	
	/**
	 * Enumeration mit allen verfügbaren Protokoll-typen
	 */
	public enum Type {
		TEXT() {
			@Override
			public BattleshipProtocol create(Builder builder) {
				return new BattleshipTextProtocol(builder);
			}
		};
		
		public abstract BattleshipProtocol create(Builder builder);
	}
	
	public static final class Builder implements Buildable<BattleshipProtocol> {
		protected String host;
		protected int port = -1;
		protected Type type = null;
		protected String playerName;
		protected BattleshipProtocolHandler handler = null;
		
		public Builder host(final String host) {
			this.host = host;
			return this;
		}
		
		public Builder port(final int port) {
			this.port = port;
			return this;
		}
		
		public Builder type(final Type type) {
			this.type = type;
			return this;
		}
		
		public Builder playerName(final String playerName) {
			this.playerName = playerName;
			return this;
		}
		
		public Builder handler(final BattleshipProtocolHandler handler) {
			this.handler = handler;
			return this;
		}
		
		@Override
		public BattleshipProtocol build() {
			if(host == null) {
				throw new IllegalStateException("the host must be set");
			}
			
			if(port < 0) {
				throw new IllegalStateException("The port must be set and greater than 0");
			}
			
			if(type == null) {
				throw new IllegalStateException("The protocol type must be defined");
			}
			
			if(playerName == null) {
				throw new IllegalStateException("The player name must be set");
			}
			
			return type.create(this);
		}
	}
	
}
