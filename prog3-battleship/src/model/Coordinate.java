package model;

/** @author Javier Mellado Sanchez 48800386K */

import java.util.Arrays;

/**
 * Clase que representa las coordenadas del juego,
 * que son representadas en un array.
 * Presenta los metodos basicos para operar
 */

public class Coordinate {
	
	/** Array que guarda las coordenadas (x,y) */
	int[] components;
	
	/** Constructor a partir de las coordenadas (x,y) */
	public Coordinate(int x, int y) {
		components = new int[2];
		components[0] = x;
		components[1] = y;
	}
	
	/** Constructor a partir de otro objeto Coordinate, copia sus coordenadas (x,y) */
	public Coordinate(Coordinate c) {
		components = new int[2];
		for (int i=0; i<components.length; i++) {
			components[i] = c.components[i];
		}
	}
	//___________________________________________________________________________________
	
	/** Representar el objeto como un string de la forma "(x,y)" */
	@Override
	public String toString() {
		StringBuilder concatenation = new StringBuilder();
		concatenation.append("(");
		
		for (int i=0; i<components.length; i++) {
			concatenation.append(components[i]);
			
			if (i < components.length-1) {
				concatenation.append(", ");
			}
		}
		concatenation.append(")");
		return concatenation.toString();
	}
	
	/** Identificador del objeto */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(components);
		return result;
	}

	/** Comparar objeto o metodos de ambos objetos */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (!Arrays.equals(components, other.components))
			return false;
		return true;
	}

	/**
	 * Nueva coordenada a partir de la suma con otro objeto Coordinate
	 * @param Objeto Coordinate para sumar
	 * @return Un nuevo objeto Coordinate con sus coordenadas segun el resultado de la operacion
	 */
	public Coordinate add(Coordinate c) {
		Coordinate new_c = new Coordinate(this);
		
		for (int i=0; i<components.length; i++) {
			new_c.set(i, new_c.get(i) + c.get(i));
		}
		return new_c;
	}
	
	/**
	 * Nueva coordenada a partir de la resta con otro objeto Coordinate
	 * @param Objeto Coordinate para restar
	 * @return Un nuevo objeto Coordinate con sus coordenadas segun el resultado de la operacion
	 */
	public Coordinate subtract(Coordinate c) {
		Coordinate new_c = new Coordinate(this);
		
		for (int i=0; i<components.length; i++) {
			new_c.set(i, new_c.get(i) - c.get(i));
		}
		return new_c;
	}
	
	/** Getter del array especificando coordenada (x o y) */
	public int get(int component) {
		if (component>=0 && component<components.length) {
			return components[component];
		}
		else {
			System.err.println("Error in Coordinate.get, component" + component + " is out of range");
		}
		return -1;
	}
	
	/** Setter del array especificando coordenada (x o y) con su nuevo valor */
	protected void set(int component, int value) {
		if (component>=0 && component<components.length) {
			components[component] = value;
		}
		else {
			System.err.println("Error in Coordinate.set, component" + component + " is out of range");
		}
	}
	
}
