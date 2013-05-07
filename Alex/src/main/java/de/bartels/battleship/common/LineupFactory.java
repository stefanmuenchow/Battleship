package de.bartels.battleship.common;

import java.io.File;
import java.util.List;

import de.bartels.battleship.domain.ShipPosition;

/**
 * factory to create a lineup
 * 
 * @author bartels
 *
 */
public final class LineupFactory {
	
	private LineupFactory() { throw new AssertionError(); }
	
	/**
	 * parses the given file and returns the lineup that was defined in the file
	 * 
	 * @param lineupFile the file to parse the lineup froms
	 * 
	 * @return the lineup
	 */
	public static final List<ShipPosition> create(final File lineupFile) {
		
		// TODO implement me;
		
		return null;
	}
	
	/**
	 * creates a auto generated lineup
	 * 
	 * @return the lineup
	 */
	public static final List<ShipPosition> create() {
		
		// TODO implement me;
		
		return null;
	}
}
