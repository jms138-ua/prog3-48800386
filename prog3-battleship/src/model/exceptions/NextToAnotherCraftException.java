package model.exceptions;

import model.Coordinate;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Excepcion coordenada cercana a un barco
 */

@SuppressWarnings("serial")
public class NextToAnotherCraftException extends CoordinateException {

	/** Constructor
	 * @param coord -> coordenada del error
	 */
	public NextToAnotherCraftException(Coordinate coord) {
		super(coord);
	}
	
	//____________________________________________________________
	
	/** Muestra el error
	 * return -> mensaje
	 */
	@Override
	public String getMessage() {
		return super.getMessage() + "; it is next to another craft";
	}
}
