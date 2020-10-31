package model.aircraft;

import model.Craft;
import model.Orientation;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa los aviones del juego
 */

public abstract class Aircraft extends Craft {

	/** Constructor 
	* @param orientation -> orietation del avion
	* @param symbol -> simbolo del avion
	* @param name -> nombre del avion
	*/
	public Aircraft(Orientation orientation, char symbol, String name) {
		super(orientation, symbol, name);
	}
}
