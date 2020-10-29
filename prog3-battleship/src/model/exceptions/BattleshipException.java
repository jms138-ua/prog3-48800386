package model.exceptions;

import model.Coordinate;

@SuppressWarnings("serial")
public abstract class BattleshipException extends Exception {
	
	private Coordinate coord; 
			
	public BattleshipException(Coordinate coord) {
		this.coord = coord;
	}
	
	//_______________________________________________
	
	@Override
	public String getMessage() {
		return "Problem with coordinate " + coord;
	}
}
