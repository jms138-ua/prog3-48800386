package model;

import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import model.exceptions.CoordinateAlreadyHitException;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa las naves del juego,
 * que son representadas en matriz.
 * Presenta los metodos para controlar a la nave
 */

public abstract class Craft {
	
	/** Constante que indica el size de la nave */
	public static final int BOUNDING_SQUARE_SIZE = 5;
	/** Constante que representa las coordenadas no impactadas */
	private static final int CRAFT_VALUE = 1;
	/** Constante que representa las coordenadas impactadas */
	private static final int HIT_VALUE = -1;
	
	/** Nombre para representar a la nave */
	protected String name;
	/** Simbolo para representar las coordenadas del barco */
	protected char symbol;
	
	/** Coordenada absoluta que representa a la nave */
	private Coordinate position;
	/** Orientacion de la nave */
	protected Orientation orientation;
	/** Matrices de la orientacion de la nave */
	protected int[][] shape;

	
	/** Constructor
	* @param orientation -> orietation de la nave
	* @param symbol -> simbolo de la nave
	* @param name -> nombre de la nave
	*/
	public Craft(Orientation orientation, char symbol, String name) {
		this.orientation = orientation;
		this.symbol = symbol;
		this.name = name;
	}

	//_______________________________________________________________________________________________________________________________________
	
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
	 * @return -> posicion absoluta que representa a la nave
	 */
	public Coordinate getPosition() {
		return (position == null)? null : position.copy();
	}

	/** Setter de la posicion
	 * @param position -> posicion absoluta que representa a la nave
	 */
	public void setPosition(Coordinate position) {
		this.position = position.copy();
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
	public int[][] getShape() {
		return shape;
	}
	
	/** Puntuacion por destruir la nave
	 * @return -> score
	 */
	public abstract int getValue();
	
	/** Como realmente la matriz es un array, esta funcion
	 * convierte una coordenada relativa en el indice para el array
	 * @param coord -> coordenada a convertir
	 * @return -> indice del array
	 */
	public int getShapeIndex(Coordinate coord) {
		Objects.requireNonNull(coord);
		return coord.get(1)*BOUNDING_SQUARE_SIZE+coord.get(0);
	}

	/** Check si esa coordenada de la nave fue impactada
	 * @param coord -> coordenada a checkear
	 * @return -> true si fue impactada, false en caso contrario
	 */
	public boolean isHit(Coordinate coord) {
		Objects.requireNonNull(coord);
		return shape[orientation.ordinal()][getShapeIndex(coord.subtract(position))] == HIT_VALUE;
	}

	/** Check si la nave esta destruido completamente
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

	//_______________________________________________________________________________________________________________________________________

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

	/** Coordenadas absolutas donde se encuentra en realidad la nave
	 * @param position -> posicion absoluta que representa a la nave
	 * @return -> set de coordenadas absolutas
	 */
	public Set<Coordinate> getAbsolutePositions(Coordinate position) {
		Objects.requireNonNull(position);
		Set<Coordinate> coords_ship = new HashSet<Coordinate>();
		
		int i = 0;
		for (int cell : shape[orientation.ordinal()]) {
			if (cell==CRAFT_VALUE || cell==HIT_VALUE) {
				Coordinate coord_ship_rel = CoordinateFactory.createCoordinate(new int[]{i%BOUNDING_SQUARE_SIZE, i/BOUNDING_SQUARE_SIZE, 0});
				Coordinate coord_ship_abs = position.add(coord_ship_rel);
				coords_ship.add(coord_ship_abs);
			}
			i++;
		}
		return coords_ship;
	}

	/** Coordenadas absolutas donde se encuentra en realidad la nave si se encuentra en tablero
	 * @return -> set de coordenadas absolutas
	 */
	public Set<Coordinate> getAbsolutePositions() {
		return getAbsolutePositions(position);
	}

	/** Impactar contra una coordenada de la matriz
	 * @param coord -> coordenada a impactar
	 * @return -> true si la nave se encuentra en tablero y la posicion era una de la nave sin impactar, false en caso contrario
	 * @throws  CoordinateAlreadyHitException -> coordenada ya impactada
	 */
	public boolean hit(Coordinate coord) throws CoordinateAlreadyHitException {
		int shape_value = shape[orientation.ordinal()][getShapeIndex(coord.subtract(position))];
		
		if (position!=null && shape_value==CRAFT_VALUE) {
			shape[orientation.ordinal()][getShapeIndex(coord.subtract(position))] = HIT_VALUE;
			return true;
		}
		else if (shape_value == HIT_VALUE) {
			throw new CoordinateAlreadyHitException(coord);
		}
		else { return false;}
	}
}