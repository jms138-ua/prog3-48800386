package model;

import java.util.Arrays;
import java.util.Set;
import java.util.Objects;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa las coordenadas del juego,
 * que son representadas en un array.
 * Presenta los metodos para controlar la coordenada
 */

public abstract class Coordinate {
	
	/** Array que guarda las componentes*/
	private int[] components;
	
	
	/** Constructor
	 * @param dim -> dimension de la coordenada
	 */
	protected Coordinate (int dim) {
		components = new int[dim];
	}
	
	/** Constructor a partir de otro objeto Coordinate, copia sus componentes
	 * @param coord -> objeto Coordinate
	 */
	protected Coordinate (Coordinate coord) {
		components = new int[coord.components.length];
		for (int i=0; i<coord.components.length; i++) {
			components[i] = coord.components[i];
		}
	}
	
	//______________________________________________________________________
	
	/** Getter del array especificando componente
	 * @param component -> especificacion de la componente
	 * @return -> componente
	 * @throws -> si no se encuentra componente
	 */
	public int get(int component) {
		if (component>=0 && component<components.length) {
			return components[component];
		}
		else { throw new IllegalArgumentException();}
	}
	
	/** Setter del array especificando componente con su nuevo valor
	 * @param component -> especificacion de la componente
	 * @param value -> nuevo valor del componente
	 * @throws -> si no se encuentra componente
	 */
	protected void set(int component, int value) {
		if (component>=0 && component<components.length) {
			components[component] = value;
		}
		else { throw new IllegalArgumentException();}
	}
	
	/** Nuevo objeto Coordinate a partir de la copia de este
	 * @return -> nuevo objeto Coordinate
	 */
	public abstract Coordinate copy();
	
	//______________________________________________________________________
	
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
		Objects.requireNonNull(coord);
		Coordinate new_coord = this.copy();
		
		for (int i=0; i<components.length; i++) {
			try { new_coord.set(i, new_coord.get(i) + coord.get(i));}
			catch (IllegalArgumentException e) {}
		}
		return new_coord;
	}
	
	/** Nueva coordenada a partir de la resta con otro objeto Coordinate
	 * @param coord -> Objeto Coordinate para restar
	 * @return -> nuevo objeto Coordinate con sus coordenadas segun el resultado de la operacion
	 */
	public Coordinate subtract(Coordinate coord) {
		Objects.requireNonNull(coord);
		Coordinate new_coord = this.copy();
		
		for (int i=0; i<components.length; i++) {
			try { new_coord.set(i, new_coord.get(i) - coord.get(i));}
			catch (IllegalArgumentException e) {}
		}
		return new_coord;
	}
	
	/** Set de coordenadas adyacentes a la actual
	 * @return -> set de coordenadas adyacentes
	 */
	public abstract Set<Coordinate> adjacentCoordinates();
}
