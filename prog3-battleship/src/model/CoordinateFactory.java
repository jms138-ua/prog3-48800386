package model;

import model.ship.Coordinate2D;
import model.aircraft.Coordinate3D;

public class CoordinateFactory {
	
	public static Coordinate createCoordinate(int[] coord) {
		if (coord.length == 2) { return new Coordinate2D(coord[0], coord[1]);}
		else if (coord.length == 3) {return new Coordinate3D(coord[0], coord[1], coord[2]);}
		else {return null;}
	}
}