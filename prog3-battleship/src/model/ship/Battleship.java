package model.ship;

import model.Orientation;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa un tipo de barco battleship
 */

public class Battleship extends Ship{
	
	/** Constructor, contiene sus matrices de representacion
	 * @param orientation -> orientation del barco
	 * */
	public Battleship(Orientation orientation) {
		super(orientation, 'O', "Battleship");
		shape = new int[][] {
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0,	
				0, 1, 1, 1, 1,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0,	
				0, 1, 1, 1, 1,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0}}; 
	}

	
	@Override
	public int getValue() {
		return 6;
	}
}
