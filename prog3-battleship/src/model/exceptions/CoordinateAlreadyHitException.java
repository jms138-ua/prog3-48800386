package model.exceptions;

import model.Coordinate;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Excepcion coordenada ya impactada
 */

@SuppressWarnings("serial")
public class CoordinateAlreadyHitException extends BattleshipException {

	/** Constructor
	 * @param coord -> coordenada del error
	 */
	public CoordinateAlreadyHitException(Coordinate coord) {
		super(coord);
	}
	
	//____________________________________________________________
	
	/** Muestra el error
	 * return -> mensaje
	 */
	@Override
	public String getMessage() {
		return super.getMessage() + "; it was already hit";
	}
}

