package model.exceptions;

import model.Coordinate;

@SuppressWarnings("serial")
public class CoordinateAlreadyHitException extends BattleshipException {

	public CoordinateAlreadyHitException(Coordinate coord) {
		super(coord);
	}
	
	//____________________________________________________________
	
	@Override
	public String getMessage() {
		return super.getMessage() + "; it was already hit";
	}
}

