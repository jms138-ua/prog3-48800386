package model.score;

import model.CellStatus;
import model.io.IPlayer;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa la puntuacion del jugador por todos los impactos
 */

public class HitScore extends Score<CellStatus> {
	
	/** Constructor 
	* @param player -> jugador
	*/
	public HitScore(IPlayer player) {
		super(player);
	}
	
	//_______________________________________________________________________
	
	@Override
	public void score(CellStatus sc) {
		if (sc==CellStatus.HIT || sc==CellStatus.DESTROYED) {
			score++;
		}
	}
}
