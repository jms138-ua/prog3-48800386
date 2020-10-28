package model.exceptions;

import model.Coordinate;

@SuppressWarnings("serial")
public class NextToAnotherCraftException extends BattleshipException {

	public NextToAnotherCraftException(Coordinate coord) {
		super(coord);
	}
	
	//____________________________________________________________
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
