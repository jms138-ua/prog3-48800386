package model.ship;

import java.util.HashSet;
import java.util.Set;

import model.Coordinate;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa las coordenadas del juego en 2d.
 * Presenta los metodos no heredados
 */

public class Coordinate2D extends Coordinate {

	/** Constructor a partir de las componentes (x,y)
	 * @param x -> posicion x
	 * @param y -> posicion y
	 */
	public Coordinate2D(int x, int y) {
		super(2);
		set(0,x); set(1,y);
	}
	
	/** Constructor a partir de otro objeto Coordinate, copia sus componentes
	 * @param coord -> objeto Coordinate
	 */
	public Coordinate2D(Coordinate2D coord) {
		super(coord);
	}
	
	//______________________________________________________________________
	
	/** Nuevo objeto Coordinate a partir de la copia de este
	 * @return -> nuevo objeto Coordinate
	 */
	@Override
	public Coordinate2D copy() {
		return new Coordinate2D(this);
	}
	
	//______________________________________________________________________
	
	/** Representar el objeto Coordinate como un string de la forma "(x,y)" */
	@Override
	public String toString() {
		StringBuilder sketch = new StringBuilder();
		
		sketch.append("(" + get(0) + ", " + get(1) + ")");
		return sketch.toString();
	}
	
	/** Set de coordenadas adyacentes a la actual
	 * @return -> set de coordenadas adyacentes
	 */
	@Override
	public Set<Coordinate> adjacentCoordinates() {
		Set<Coordinate> coords_adj = new HashSet<Coordinate>();
		
		for (int i=get(0)-1; i<=get(0)+1; i++) {
			for (int j=get(1)-1; j<=get(1)+1; j++) {
				Coordinate coord_adj = new Coordinate2D(i,j);
				
				if (!coord_adj.equals(this)) {
					coords_adj.add(coord_adj);
				}
				
			}
		}
		return coords_adj;
	}
}
