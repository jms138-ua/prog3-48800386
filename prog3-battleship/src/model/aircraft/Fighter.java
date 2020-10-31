package model.aircraft;

import model.Orientation;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa un tipo de avion fighter
 */

public class Fighter extends Aircraft{

	/** Constructor, contiene sus matrices de representacion
	 * @param orientation -> orientation del avion
	 * */
	public Fighter(Orientation orientation) {
		super(orientation, '⇄', "Fighter");
		shape = new int[][] {
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 1, 1, 1, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
				0, 0, 1, 0, 0,	
				1, 1, 1, 1, 0,	
				0, 0, 1, 0, 0,
				0, 0, 0, 0, 0},
		      { 0, 0, 1, 0, 0,
				0, 0, 1, 0, 0,	
				0, 1, 1, 1, 0,	
				0, 0, 1, 0, 0,
				0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
				0, 0, 1, 0, 0,	
				0, 1, 1, 1, 1,	
				0, 0, 1, 0, 0,
				0, 0, 0, 0, 0}}; 
	}
}
