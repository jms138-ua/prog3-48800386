package model;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Objects;

import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa el tablero del juego
 * Presenta los metodos para controlar el tablero
 */

public abstract class Board {
	
	/** Constante que representa las coordenadas impactadas en simbolo */
	public static final char HIT_SYMBOL = '•';
	/** Constante que representa las coordenadas no impactadas en simbolo */
	public static final char WATER_SYMBOL = ' ';
	/** Constante que representa las coordenadas no vistas en simbolo */
	public static final char NOTSEEN_SYMBOL = '?';
	/** Constante que representa la separacion del tablero */
	public static final char Board_SEPARATOR = '|';
	
	/** Constante que indica el size maximo del tablero */
	private static final int MAX_BOARD_SIZE = 20;
	/** Constante que indica el size minimo del tablero */
	private static final int MIN_BOARD_SIZE = 5;
	/** Size del tablero */
	private int size;
	
	/** Numero de naves */
	private int numCrafts;
	/** Numero de naves destruidos */
	private int destroyedCrafts;
	
	/** Coordenadas vistas */
	private Set<Coordinate> seen;
	/** Todas las coordenas ocupadas por todas las naves, indicando cual es su nave */
	private Map<Coordinate, Craft> board;
	
	
	/** Constructor
	* @param size -> size de la nave
	* @throws -> si no s epermite ese size
	*/
	public Board(int size) {
		if (size < MIN_BOARD_SIZE || size > MAX_BOARD_SIZE) {
			throw new IllegalArgumentException();
		}
		
		this.size = size;
		numCrafts = 0;
		destroyedCrafts = 0;
		seen = new HashSet<Coordinate>();
		board = new HashMap<Coordinate, Craft>();
	}
	
	//______________________________________________________________________________________________________________________________________________________
	
	/** Check si la coordenada se encuentra entre los limites del tablero
	* @param coord -> coordenada a chekear
	* @return -> true si esta en los limites, false en caso contrario
	*/
	public abstract boolean checkCoordinate(Coordinate coord);
	
	/** Getter del size
	 * @return -> size
	 */
	public int getSize() {
		return size;
	}
	
	/** Getter de la nave a partir de una coordenada del barco
	 * @param coord -> coordenada 
	 * @return -> barco
	 */
	public Craft getCraft(Coordinate coord) {
		return board.get(coord);
	}
	
	/** Check si la coordenada ha sido vista
	 * @param coord -> coordenada a checkear
	 * @return -> true ha sido vista, false en caso contrario
	 */
	public boolean isSeen(Coordinate coord) {
		return seen.contains(coord);
	}
	
	/** Check si todas las naves estan destruidos completamente
	 * @return -> true si estan destruidos, false en caso contrario
	 */
	public boolean areAllCraftsDestroyed() {
		return numCrafts-destroyedCrafts == 0;
	}
	
	/** Coordenadas vecinas a la nave
	 * @param craft -> nave
	 * @param position -> posicion que representa a la nave
	 * @return -> set de coordenadas vecinas a la nave
	 */
	public Set<Coordinate> getNeighborhood(Craft craft, Coordinate position) {
		Objects.requireNonNull(craft);
		Objects.requireNonNull(position);
		Set<Coordinate> coords_neig = new HashSet<Coordinate>();
		
		for (Coordinate coord_ship : craft.getAbsolutePositions(position)) {
			for (Coordinate coord_adj : coord_ship.adjacentCoordinates()) {
				if (checkCoordinate(coord_adj)) {
					coords_neig.add(coord_adj);
				}
			}
		}
		coords_neig.removeAll(craft.getAbsolutePositions(position));
		
		return coords_neig;
	}
	
	/** Coordenadas vecinas a la nave si se encuentra en tablero
	 * @param craft -> nave
	 * @return -> set de coordenadas vecinas a la nave
	 */
	public Set<Coordinate> getNeighborhood(Craft craft) {
		Objects.requireNonNull(craft);
		return (craft.getPosition() == null)? Collections.emptySet() : getNeighborhood(craft, craft.getPosition());
	}
	
	//______________________________________________________________________________________________________________________________________________________
	
	/** Indicar propiedades del tablero */
	@Override
	public String toString() {
		return "Board " + size + "; crafts: " + numCrafts + "; destroyed: " + destroyedCrafts;
	}
	
	/** Representar el objeto Board como un string en forma de matriz/matrices del tablero
	 * con sus naves con simbolos correspondientes.
	 * Existen dos maneras de representarlo segun si ei es el propietario u oponente
	 * @param unveil -> si es el propietario u oponente
	 * @return -> string en forma de matriz de tablero
	 * */
	public abstract String show(boolean unveil);
	
	/** Agregar nave al tablero. Para ello se comprueba:
	 *  que no este fuera del tablero, ni ocupe una coordenada o coordenada vecina de otra nave
	 * @param craft -> nave a agregar
	 * @param position -> posicion que representa a la nave
	 * @return -> true si se ha podido agregar, falso en caso contrario
	 * @throws InvalidCoordinateException -> invalid
	 * @throws OccupiedCoordinateException -> occupied
	 * @throws NextToAnotherCraftException -> next
	 * */
	public boolean addCraft(Craft craft, Coordinate position) throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException{
		List<Coordinate> coords_cpy1 = new ArrayList<Coordinate>(board.keySet());
		List<Coordinate> coords_cpy2 = new ArrayList<Coordinate>(board.keySet());
		
		for (Coordinate coord_ship : craft.getAbsolutePositions(position)) {
			if (!checkCoordinate(coord_ship)) {
				throw new InvalidCoordinateException(coord_ship);
			}
		}
		
		if (coords_cpy2.removeAll(craft.getAbsolutePositions(position))) {
			coords_cpy1.removeAll(coords_cpy2);
			throw new OccupiedCoordinateException(coords_cpy1.get(0));
		}
		if (coords_cpy2.removeAll(getNeighborhood(craft, position))) {
			coords_cpy1.removeAll(coords_cpy2);
			throw new NextToAnotherCraftException(coords_cpy1.get(0));
		}
		
		for (Coordinate coord_ship : craft.getAbsolutePositions(position)) {
			craft.setPosition(position);
			board.put(coord_ship, craft);
		}
		numCrafts++;
		return true;
	}
	
	/** Impactar contra una coordenada de la matriz del tablero
	 * @param coord -> coordenada a impactar
	 * @return -> situacion que pueden suceder cuando se hace un disparo
	 * @throws InvalidCoordinateException -> invalid
	 * @throws CoordinateAlreadyHitException -> ya impactada
	 * */
	public CellStatus hit(Coordinate coord) throws InvalidCoordinateException, CoordinateAlreadyHitException{
		if (!checkCoordinate(coord)) {
			throw new InvalidCoordinateException(coord);
		}
		else if (!board.keySet().contains(coord)) {
			seen.add(coord.copy());
			return CellStatus.WATER;
		}
		else {
			board.get(coord).hit(coord);
			seen.add(coord.copy());
			
			if (board.get(coord).isShotDown()) {
				seen.addAll(getNeighborhood(board.get(coord)));
				destroyedCrafts++;
				return CellStatus.DESTROYED;
			}
			else {
				return CellStatus.HIT;
			}
		}
	}
}
