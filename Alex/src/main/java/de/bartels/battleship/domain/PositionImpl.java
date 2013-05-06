package de.bartels.battleship.domain;

class PositionImpl implements Position {

	private final int x;
	private final int y;
	
	public PositionImpl(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	// TODO override equals and hashCode, using apache-commons EqualsBuilder and HashCodeBuilder
	
	@Override
	public String toString() {
		return Position.X_AXIS_STRING_REPRESENTATION[x - 1] + Y_AXIS_STRING_REPRESENTATION[y - 1];
	}
}
