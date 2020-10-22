package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa las coordenadas del juego,
 * que son representadas en un array (x,y).
 * Presenta los metodos para controlar la coordenada
 */

public class Coordinate {
	
	/** Array que guarda las componentes (x,y) */
	private int[] components;
	
	/** Constructor a partir de las componentes (x,y)
	 * @param x -> posicion x
	 * @param y -> posicion y
	 */
	public Coordinate (int x, int y) {
		components = new int[2];
		components[0] = x;
		components[1] = y;
	}
	
	/** Constructor a partir de otro objeto Coordinate, copia sus componentes (x,y)
	 * @param coord -> objeto Coordinate
	 */
	public Coordinate (Coordinate coord) {
		components = new int[2];
		for (int i=0; i<components.length; i++) {
			components[i] = coord.components[i];
		}
	}
	
	//__________________________________________________________________________________________________
	
	/** Getter del array especificando componente (x o y)
	 * @param component -> especificacion de la componente
	 * @return -> componente, -1 en caso de error
	 */
	public int get(int component) {
		if (component>=0 && component<components.length) {
			return components[component];
		}
		else {
			System.err.println("Error in Coordinate.get, component" + component + " is out of range");
		}
		return -1;
	}
	
	/** Setter del array especificando componente (x o y) con su nuevo valor
	 * @param component -> especificacion de la componente
	 * @param value -> nuevo valor del componente
	 */
	protected void set(int component, int value) {
		if (component>=0 && component<components.length) {
			components[component] = value;
		}
		else {
			System.err.println("Error in Coordinate.set, component" + component + " is out of range");
		}
	}
	
	/** Nuevo objeto Coordinate a partir de la copia de este
	 * @return -> nuevo objeto Coordinate
	 */
	public Coordinate copy() {
		return new Coordinate(this);
	}
	
	//__________________________________________________________________________________________________
	
	/** Representar el objeto Coordinate como un string de la forma "(x,y)" */
	@Override
	public String toString() {
		StringBuilder sketch = new StringBuilder();
		sketch.append("(");
		
		for (int i=0; i<components.length; i++) {
			sketch.append(components[i]);
			
			if (i < components.length-1) {
				sketch.append(", ");
			}
		}
		sketch.append(")");
		return sketch.toString();
	}
	
	/** Identificador del objeto 
	 * @return -> identificador
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(components);
		return result;
	}

	/** Comparar objeto o metodos de ambos objetos
	 * @param obj -> objecto a comparar
	 * @return -> true si es el mismo objeto, false en caso contrario
	 * */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true;}
		if (obj == null) { return false;}
		if (getClass() != obj.getClass()) { return false;}
		Coordinate other = (Coordinate) obj;
		if (!Arrays.equals(components, other.components)) { return false;}
		return true;
	}
	
	/** Nueva coordenada a partir de la suma con otro objeto Coordinate
	 * @param coord -> objeto Coordinate para sumar
	 * @return -> nuevo objeto Coordinate con sus coordenadas segun el resultado de la operacion
	 */
	public Coordinate add(Coordinate coord) {
		Coordinate new_coord = new Coordinate(this);
		
		for (int i=0; i<components.length; i++) {
			new_coord.set(i, new_coord.get(i) + coord.get(i));
		}
		return new_coord;
	}
	
	/** Nueva coordenada a partir de la resta con otro objeto Coordinate
	 * @param coord -> Objeto Coordinate para restar
	 * @return -> nuevo objeto Coordinate con sus coordenadas segun el resultado de la operacion
	 */
	public Coordinate subtract(Coordinate coord) {
		Coordinate new_coord = new Coordinate(this);
		
		for (int i=0; i<components.length; i++) {
			new_coord.set(i, new_coord.get(i) - coord.get(i));
		}
		return new_coord;
	}
	
	/** Set de coordenadas adyacentes a la actual
	 * @return -> set de coordenadas adyacentes
	 */
	public Set<Coordinate> adjacentCoordinates() {
		Set<Coordinate> coords_adj = new HashSet<Coordinate>();
		
		for (int i=components[0]-1; i<=components[0]+1; i++) {
			for (int j=components[1]-1; j<=components[1]+1; j++) {
				Coordinate coord_adj = new Coordinate(i,j);
				
				if (!coord_adj.equals(this)) {
					coords_adj.add(coord_adj);
				}
				
			}
		}
		return coords_adj;
	}
}
