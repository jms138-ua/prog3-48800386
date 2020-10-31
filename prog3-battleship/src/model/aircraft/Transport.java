package model.aircraft;

import model.Orientation;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa un tipo de avion transport
 */

public class Transport extends Aircraft{

	/** Constructor, contiene sus matrices de representacion
	 * @param orientation -> orientation del avion 
	 * */
	public Transport(Orientation orientation) {
		super(orientation, '⇋', "Transport");
		shape = new int[][] {
		      { 0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 1, 1, 1, 0,	
		    	1, 0, 1, 0, 1,
		    	0, 0, 1, 0, 0},
		      { 0, 1, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
				1, 1, 1, 1, 1,	
				0, 0, 1, 0, 0,
				0, 1, 0, 0, 0},
		      { 0, 0, 1, 0, 0,
				1, 0, 1, 0, 1,	
				0, 1, 1, 1, 0,	
				0, 0, 1, 0, 0,
				0, 0, 1, 0, 0},
		      { 0, 0, 0, 1, 0,
				0, 0, 1, 0, 0,	
				1, 1, 1, 1, 1,	
				0, 0, 1, 0, 0,
				0, 0, 0, 1, 0}}; 
	}
}
