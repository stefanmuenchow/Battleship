package de.bartels.battleship.common;

/**
 * indicates that the class is buildable
 * 
 * @author bartels
 *
 * @param <T> type from the object to build
 */
public interface Buildable<T> {

	/**
	 * build the object
	 * 
	 * @return new instance from type T
	 */
	public T build();
	
}
