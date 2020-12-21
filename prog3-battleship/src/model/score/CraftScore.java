package model.score;

import model.Craft;
import model.io.IPlayer;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa la puntuacion del jugador por todas las naves destruidas
 */

public class CraftScore extends Score<Craft>{

	/** Constructor 
	* @param player -> jugador
	*/
	public CraftScore(IPlayer player) {
		super(player);
	}
	
	//_______________________________________________________________________
	
	@Override
	public void score(Craft sc) {
		score += sc.getValue();
	}
}
