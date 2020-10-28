package model.exceptions;

import model.Coordinate;

@SuppressWarnings("serial")
public abstract class BattleshipException extends Exception {
	
	public BattleshipException(Coordinate coord) {
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
