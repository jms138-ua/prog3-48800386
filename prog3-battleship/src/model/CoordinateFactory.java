package model;

import model.ship.Coordinate2D;
import model.aircraft.Coordinate3D;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que crea las coordenadas del juego,
 * ya sean 2d o 3d, utilizando sus respectivos constructores
 */

public class CoordinateFactory {
	
	/** Constructor a partir de un array de componentes
	 * @param coord -> array de componentes
	 * @return -> objeto Coordinate de su dimension
	 */
	public static Coordinate createCoordinate(int ... coord) {
		if (coord.length == 2) { 		return new Coordinate2D(coord[0], coord[1]);}
		else if (coord.length == 3) {	return new Coordinate3D(coord[0], coord[1], coord[2]);}
		else {							throw new IllegalArgumentException();}
	}
}