package model;

import java.util.Set;
import java.util.HashSet;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa los barcos del juego,
 * que son representadas en matriz.
 * Presenta los metodos para controlar el barco
 */

public class Ship {
	
	/** Constante que indica el size del barco */
	private static final int BOUNDING_SQUARE_SIZE = 5;
	/** Constante que representa las coordenadas no impactadas */
	private static final int CRAFT_VALUE = 1;
	/** Constante que representa las coordenadas impactadas */
	private static final int HIT_VALUE = -1;

	/** Nombre para representar al barco */
	private String name;
	/** Simbolo para representar las coordenadas del barco */
	private char symbol;
	
	/** Coordenada absoluta que representa al barco */
	private Coordinate position;
	/** Orientacion del barco */
	private Orientation orientation;
	/** Matrices del barco para cada orientacion */
	private int[][] shape = new int[][] {
		// NORTH
		{0, 0, 0, 0, 0, 	// ·····
		 0, 0, 1, 0, 0, 	// ··#··
		 0, 0, 1, 0, 0, 	// ··#··
		 0, 0, 1, 0, 0, 	// ..#..
		 0, 0, 0, 0, 0},	// ·····
		// EAST
		{0, 0, 0, 0, 0, 	// ·····
		 0, 0, 0, 0, 0, 	// ·····
		 0, 1, 1, 1, 0, 	// ·###·
		 0, 0, 0, 0, 0, 	// ·····
		 0, 0, 0, 0, 0},	// ·····
		// SOUTH
		{0, 0, 0, 0, 0, 	// ·····
		 0, 0, 1, 0, 0,	 	// ··#··
		 0, 0, 1, 0, 0, 	// ··#··
		 0, 0, 1, 0, 0, 	// ..#..
		 0, 0, 0, 0, 0},	// ·····
		// WEST
		{0, 0, 0, 0, 0, 	// ·····
		 0, 0, 0, 0, 0, 	// ·····
		 0, 1, 1, 1, 0, 	// ·###·
		 0, 0, 0, 0, 0, 	// ·····
		 0, 0, 0, 0, 0}}; 	// ·····

	
	/** Constructor
	* @param orientation -> orietation del barco
	* @param symbol -> simbolo del barco
	* @param name -> nombre del barco
	*/
	public Ship (Orientation orientation, char symbol, String name) {
		this.orientation = orientation;
		this.symbol = symbol;
		this.name = name;
	}
	
	//________________________________________________________________________________________________________________
	
	/** Getter del nombre
	 * @return -> nombre
	 */
	public String getName() {
		return name;
	}
	
	/** Getter del simbolo
	 * @return -> simbolo
	 */
	public char getSymbol() {
		return symbol;
	}
	
	/** Getter de la posicion con copia defensiva
	 * @return -> posicion absoluta que representa al barco 
	 */
	public Coordinate getPosition() {
		return (position == null)? null : position.copy();
	}
	
	/** Setter de la posicion
	 * @param position -> posicion absoluta que representa al barco 
	 */
	public void setPosition(Coordinate position) {
		this.position = position;
	}
	
	/** Getter de la orientacion
	 * @return -> orientacion
	 */
	public Orientation getOrientation() {
		return orientation;
	}
	
	/** Getter de las matrices
	 * @return -> posicion
	 */
	public int[][] getShape(){
		return shape;
	}
	
	/** Como realmente la matriz es un array, esta funcion
	 * convierte una coordenada relativa en el indice para el array
	 * @param coord -> coordenada a convertir
	 * @return -> indice del array
	 */
	public int getShapeIndex(Coordinate coord) {
		return coord.get(1)*BOUNDING_SQUARE_SIZE+coord.get(0);
	}
	
	/** Check si esa coordenada del barco fue impactada
	 * @param coord -> coordenada a checkear
	 * @return -> true si fue impactada, false en caso contrario
	 */
	public boolean isHit(Coordinate coord) {
		return shape[orientation.ordinal()][getShapeIndex(coord.subtract(position))] == HIT_VALUE;
	}
	
	/** Check si el barco esta destruido completamente
	 * @return -> true si esta destruido, false en caso contrario
	 */
	public boolean isShotDown() {
		for (int i : shape[orientation.ordinal()]) {
			if (i == CRAFT_VALUE) {
				return false;
			}
		}
		return true;
	}
	
	//________________________________________________________________________________________________________________
	
	/** Representar el objeto Ship como un string en forma de su matriz con sus simbolos correspondientes
	 * Para ello copiamos el vector y posteriormente agregamos los saltos de linea y cuadricula
	 * Tambien se indican propiedades del tablero
	 * */
	@Override
	public String toString() {
		StringBuilder sketch = new StringBuilder();
		
		sketch.append(" ");
		for (int i=0; i<BOUNDING_SQUARE_SIZE; i++) { sketch.append("-");}
		sketch.append("\n");
		
		for (int cell : shape[orientation.ordinal()]) {
			if (cell == CRAFT_VALUE) { 		sketch.append(symbol);}
			else if (cell == HIT_VALUE) { 	sketch.append(Board.HIT_SYMBOL);}
			else { 							sketch.append(Board.WATER_SYMBOL);}
		}
		
		sketch.insert(BOUNDING_SQUARE_SIZE+2, "|");
		for (int i=BOUNDING_SQUARE_SIZE*2+3; i<sketch.length(); i+=BOUNDING_SQUARE_SIZE+3) {
			sketch.insert(i, "|\n|");
		}
		
		sketch.append("|\n ");
		for (int i=0; i<BOUNDING_SQUARE_SIZE; i++) { sketch.append("-");}
		
		return name + " (" + orientation + ")\n" + sketch.toString();
	}
	
	/** Coordenadas absolutas donde se encuentra en realidad el barco
	 * @param position -> posicion absoluta que representa al barco 
	 * @return -> set de coordenadas absolutas
	 */
	public Set<Coordinate> getAbsolutePositions(Coordinate position) {
		Set<Coordinate> coords_ship = new HashSet<Coordinate>();
		
		int i = 0;
		for (int cell : shape[orientation.ordinal()]) {
			if (cell==CRAFT_VALUE || cell==HIT_VALUE) {
				Coordinate coord_ship_rel = new Coordinate(i%BOUNDING_SQUARE_SIZE, i/BOUNDING_SQUARE_SIZE);
				Coordinate coord_ship_abs = coord_ship_rel.add(position);
				coords_ship.add(coord_ship_abs);
			}
			i++;
		}
		return coords_ship;
	}
	
	/** Coordenadas absolutas donde se encuentra en realidad el barco si se encuentra en tablero
	 * @return -> set de coordenadas absolutas
	 */
	public Set<Coordinate> getAbsolutePositions() {
		return getAbsolutePositions(position);
	}
	
	/** Impactar contra una coordenada de la matriz
	 * @param coord -> coordenada a impactar
	 * @return -> true si el barco se encuentra en tablero y la posicion era una del barco sin impactar,
	 * false en caso contrario
	 */
	public boolean hit(Coordinate coord) {
		if (position!=null && shape[orientation.ordinal()][getShapeIndex(coord.subtract(position))]==CRAFT_VALUE) {
			shape[orientation.ordinal()][getShapeIndex(coord.subtract(position))] = HIT_VALUE;
			return true;
		}
		else { return false;}
	}
}
