package model.aircraft;

import java.util.HashSet;
import java.util.Set;

import model.Coordinate;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa las coordenadas del juego en 3d.
 * Presenta los metodos no heredados
 */

public class Coordinate3D extends Coordinate {

	/** Constructor a partir de las componentes (x,y,z)
	 * @param x -> posicion x
	 * @param y -> posicion y
	 * @param z -> posicion z
	 */
	public Coordinate3D(int x, int y, int z) {
		super(3);
		set(0,x); set(1,y); set(2,z);
	}
	
	/** Constructor a partir de otro objeto Coordinate, copia sus componentes
	 * @param coord -> objeto Coordinate
	 */
	public Coordinate3D(Coordinate3D coord) {
		super(coord);
	}
	
	//______________________________________________________________________
	
	/** Nuevo objeto Coordinate a partir de la copia de este
	 * @return -> nuevo objeto Coordinate
	 */
	@Override
	public Coordinate3D copy() {
		return new Coordinate3D(this);
	}
	
	//______________________________________________________________________
	
	/** Representar el objeto Coordinate como un string de la forma "(x,y,z)" */
	@Override
	public String toString() {
		StringBuilder sketch = new StringBuilder();
		
		sketch.append("(" + get(0) + ", " + get(1) + ", " + get(2) + ")");
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
				for (int k=get(2)-1; k<=get(2)+1; k++) {
					Coordinate coord_adj = new Coordinate3D(i,j,k);
					
					if (!coord_adj.equals(this)) {
						coords_adj.add(coord_adj);
					}
				}
				
			}
		}
		return coords_adj;
	}
}
