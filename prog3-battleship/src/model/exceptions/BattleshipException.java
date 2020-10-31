package model.exceptions;

import model.Coordinate;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Excepcion general padre
 */

@SuppressWarnings("serial")
public abstract class BattleshipException extends Exception {
	
	/** Coordenada del error */
	private Coordinate coord; 
	
	/** Constructor
	 * @param coord -> coordenada del error
	 */
	public BattleshipException(Coordinate coord) {
		this.coord = coord;
	}
	
	//_______________________________________________
	
	/** Muestra el error
	 * return -> mensaje
	 */
	@Override
	public String getMessage() {
		return "Problem with coordinate " + coord;
	}
}
