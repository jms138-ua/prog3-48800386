package model.exceptions.io;

import model.exceptions.BattleshipException;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Excepcion IO
 */

@SuppressWarnings("serial")
public class BattleshipIOException extends BattleshipException{
	
	private String message;
	
	
	public BattleshipIOException(String msg) {
		message = msg;
	}
	
	//_______________________________________________
	
	/** Muestra el error
	 * return -> mensaje
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
