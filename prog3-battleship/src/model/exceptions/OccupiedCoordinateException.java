package model.exceptions;

import model.Coordinate;

@SuppressWarnings("serial")
public class OccupiedCoordinateException extends BattleshipException {

	public OccupiedCoordinateException(Coordinate coord) {
		super(coord);
	}
	
	//____________________________________________________________
	
	@Override
	public String getMessage() {
		return super.getMessage() + "; it is occupied";
	}
}
