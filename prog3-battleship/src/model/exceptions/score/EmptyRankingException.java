package model.exceptions.score;

import model.exceptions.BattleshipException;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Excepcion Empty Ranking
 */

@SuppressWarnings("serial")
public class EmptyRankingException extends BattleshipException{
	
	/** Mensaje del error */
	private String message;
	
	
	/** Constructor
	 * @param msg -> mensaje del error
	 */
	public EmptyRankingException(String msg) {
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
