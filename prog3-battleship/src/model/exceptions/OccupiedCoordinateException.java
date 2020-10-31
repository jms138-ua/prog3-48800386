package model.exceptions;

import model.Coordinate;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Excepcion coordenada ya ocupada por otro barco
 */

@SuppressWarnings("serial")
public class OccupiedCoordinateException extends BattleshipException {

	/** Constructor
	 * @param coord -> coordenada del error
	 */
	public OccupiedCoordinateException(Coordinate coord) {
		super(coord);
	}
	
	//____________________________________________________________
	
	/** Muestra el error
	 * return -> mensaje
	 */
	@Override
	public String getMessage() {
		return super.getMessage() + "; it is occupied";
	}
}
