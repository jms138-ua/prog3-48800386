package model.exceptions;

import model.Coordinate;

@SuppressWarnings("serial")
public class InvalidCoordinateException extends BattleshipException {

	public InvalidCoordinateException(Coordinate coord) {
		super(coord);
	}
	
	//____________________________________________________________
	
	@Override
	public String getMessage() {
		return super.getMessage() + "; it is not valid";
	}
}
