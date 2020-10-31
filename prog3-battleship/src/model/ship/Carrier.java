package model.ship;

import model.Orientation;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa un tipo de barco carrier
 */

public class Carrier extends Ship{
	
	/** Constructor, contiene sus matrices de representacion
	 * @param orientation -> orientation del barco
	 * */
	public Carrier(Orientation orientation) {
		super(orientation, '®', "Carrier");
		shape = new int[][] {
		      { 0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
		        0, 0, 0, 0, 0,	
				1, 1, 1, 1, 1,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0},
		      { 0, 0, 1, 0, 0,
				0, 0, 1, 0, 0,	
				0, 0, 1, 0, 0,	
				0, 0, 1, 0, 0,
				0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0,	
				1, 1, 1, 1, 1,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0}}; 
	}
}
