package model.ship;

import model.Orientation;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa un tipo de barco destroyer
 */

public class Destroyer extends Ship{
	
	/** Constructor, contiene sus matrices de representacion
	 * @param orientation -> orientation del barco
	 * */
	public Destroyer(Orientation orientation) {
		super(orientation, 'Ω', "Destroyer");
		shape = new int[][] {
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0,	
				0, 1, 1, 0, 0,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
				0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0,	
				0, 1, 1, 0, 0,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0}};
	}
	
	
	@Override
	public int getValue() {
		return 3;
	}
}
