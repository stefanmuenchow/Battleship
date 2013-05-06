package de.bartels.battleship.domain;

public interface Position {

	static final String[] X_AXIS_STRING_REPRESENTATION = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
	static final String[] Y_AXIS_STRING_REPRESENTATION = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	
	int getX();
	
	int getY();
	
	/**
	 * factory to create position instances
	 */
	public static final class Factory {
		private Factory() { throw new AssertionError(); }
		
		/**
		 * creates a new position instance
		 * 
		 * @param x
		 * @param y
		 * 
		 * @return new position instance
		 */
		public static final Position create(final int x, final int y) {
			if(x <= 0 || x > 10) {
				throw new IllegalArgumentException("the x parameter must be greater than 0 and lower than 11");
			}
			
			if(y <= 0 || y > 10) {
				throw new IllegalArgumentException("the y parameter must be greater than 0 and lower than 11");
			}
			
			return new PositionImpl(x, y);
		}
	}
}
