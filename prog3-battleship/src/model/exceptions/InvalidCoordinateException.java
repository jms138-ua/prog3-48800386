package model.exceptions;

import model.Coordinate;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Excepcion coordenada invalida
 */

@SuppressWarnings("serial")
public class InvalidCoordinateException extends CoordinateException {

	/** Constructor
	 * @param coord -> coordenada del error
	 */
	public InvalidCoordinateException(Coordinate coord) {
		super(coord);
	}
	
	//____________________________________________________________
	
	/** Muestra el error
	 * return -> mensaje
	 */
	@Override
	public String getMessage() {
		return super.getMessage() + "; it is not valid";
	}
}
