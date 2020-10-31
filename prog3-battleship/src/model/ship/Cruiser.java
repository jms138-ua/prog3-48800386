package model.ship;

import model.Orientation;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa un tipo de barco crusier
 */

public class Cruiser extends Ship{
	
	/** Constructor, contiene sus matrices de representacion
	 * @param orientation -> orientation del barco
	 * */
	public Cruiser(Orientation orientation) {
		super(orientation, 'Ø', "Cruiser");
		shape = new int[][] {
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0,	
				0, 1, 1, 1, 0,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
				0, 0, 1, 0, 0,	
				0, 0, 1, 0, 0,	
				0, 0, 1, 0, 0,
				0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0,	
				0, 1, 1, 1, 0,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0}}; 
	}
}
