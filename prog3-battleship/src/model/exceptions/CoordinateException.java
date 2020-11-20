package model.exceptions;

import model.Coordinate;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Excepcion Coordinate
 */

@SuppressWarnings("serial")
public abstract class CoordinateException extends BattleshipException{
	
	/** Coordenada del error */
	private Coordinate coord; 
	
	
	/** Constructor
	 * @param coord -> coordenada del error
	 */
	public CoordinateException(Coordinate coord) {
		this.coord = coord;
	}
	
	//_______________________________________________
	
	@Override
	public String getMessage() {
		return "Problem with coordinate " + coord;
	}
}
