package model.ship;

import model.Craft;
import model.Orientation;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa los barcos del juego
 */

public abstract class Ship extends Craft {
	
	/** Constructor 
	* @param orientation -> orietation del barco
	* @param symbol -> simbolo del barco
	* @param name -> nombre del barco
	*/
	public Ship (Orientation orientation, char symbol, String name) {
		super(orientation, symbol, name);
	}
}
