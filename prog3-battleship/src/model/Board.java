package model;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa el tablero del juego
 * Presenta los metodos para controlar el tablero
 */


public class Board {
	/** Constante que representa las coordenadas impactadas en simbolo */
	public static final char HIT_SYMBOL = '•';
	/** Constante que representa las coordenadas no impactadas en simbolo */
	public static final char WATER_SYMBOL = ' ';
	/** Constante que representa las coordenadas no vistas en simbolo */
	public static final char NOTSEEN_SYMBOL = '?';
	/** Constante que indica el size maximo del tablero */
	private static final int MAX_BOARD_SIZE = 20;
	/** Constante que indica el size minimo del tablero */
	private static final int MIN_BOARD_SIZE = 5;
	
	/** Size del tablero */
	private int size;
	/** Numero de barcos */
	private int numCrafts;
	/** Numero de barcos destruidos */
	private int destroyedCrafts;
	/** Coordenadas vistas */
	private Set<Coordinate> seen;
	
	/** Todas las coordenas ocupadas por todos los barcos, indicando cual es su barco */
	private Map<Coordinate, Ship> board;
	
	/** Constructor
	* @param size -> size del barco
	*/
	public Board(int size) {
		if (size < MIN_BOARD_SIZE || size > MAX_BOARD_SIZE) {
			System.err.println("El size del Board no se encuentra entre los limites permitidos");
			size = MIN_BOARD_SIZE;
		}
		
		this.size = size;
		numCrafts = 0;
		destroyedCrafts = 0;
		seen = new HashSet<Coordinate>();
		board = new HashMap<Coordinate, Ship>();
	}
	
	//___________________________________________________________________________________________________________
	
	/** Check si la coordenada se encuentra entre los limites del tablero
	* @param coord -> coordenada a chekear
	* @return -> true si esta en los limites, false en caso contrario
	*/
	public boolean checkCoordinate(Coordinate coord) {
		return !(coord.get(0)<0 || coord.get(1)<0 || coord.get(0)>=size || coord.get(1)>=size);
	}
	
	/** Getter del size
	 * @return -> size
	 */
	public int getSize() {
		return size;
	}
	
	/** Getter del barco a partir de una coordenada del barco
	 * @param coord -> coordenada 
	 * @return -> barco
	 */
	public Ship getShip(Coordinate coord) {
		return board.get(coord);
	}
	
	/** Check si la coordenada ha sido vista
	 * @param coord -> coordenada a checkear
	 * @return -> true ha sido vista, false en caso contrario
	 */
	public boolean isSeen(Coordinate coord) {
		return seen.contains(coord);
	}
	
	/** Check si todos los barcos estan destruidos completamente
	 * @return -> true si estan destruidos, false en caso contrario
	 */
	public boolean areAllCraftsDestroyed() {
		return numCrafts-destroyedCrafts == 0;
	}
	
	/** Coordenadas vecinas al barco
	 * @param ship -> barco
	 * @param position -> posicion que representa al barco 
	 * @return -> set de coordenadas vecinas al barco
	 */
	public Set<Coordinate> getNeighborhood(Ship ship, Coordinate position) {
		Set<Coordinate> coords_neig = new HashSet<Coordinate>();
		
		for (Coordinate coord_ship : ship.getAbsolutePositions(position)) {
			for (Coordinate coord_adj : coord_ship.adjacentCoordinates()) {
				if (checkCoordinate(coord_adj)) {
					coords_neig.add(coord_adj);
				}
			}
		}
		coords_neig.removeAll(ship.getAbsolutePositions(position));
		
		return coords_neig;
	}
	
	/** Coordenadas vecinas al barco si se encuentra en tablero
	 * @param ship -> barco
	 * @return -> set de coordenadas vecinas al barco
	 */
	public Set<Coordinate> getNeighborhood(Ship ship) {
		return (ship.getPosition() == null)? Collections.emptySet() : getNeighborhood(ship, ship.getPosition());
	}
	
	//___________________________________________________________________________________________________________
	
	/** Indicar propiedades del tablero */
	@Override
	public String toString() {
		return "Board " + size + "; crafts: " + numCrafts + "; destroyed: " + destroyedCrafts;
	}
	
	/** Representar el objeto Board como un string en forma de matriz del tablero
	 * con sus barcos con simbolos correspondientes.
	 * Existen dos maneras de representarlo segun si ei es el propietario u oponente
	 * @param unveil -> si es el propietario u oponente
	 * @return -> string en forma de matriz de tablero
	 * */
	public String show(boolean unveil) {
		StringBuilder sketch = new StringBuilder();

		if (unveil) {
			for (int i=0; i<size; i++) {
				for (int j=0; j<size; j++) {
					Coordinate cell = new Coordinate(j,i);
					
					if (board.containsKey(cell)) {
						if (board.get(cell).isHit(cell)) { 	sketch.append(HIT_SYMBOL);}
						else { 								sketch.append(board.get(cell).getSymbol());}
					}
					else { sketch.append(WATER_SYMBOL);}
				}
				if (i != size-1) { sketch.append("\n");}
			}
		}
		
		else {
			for (int i=0; i<size; i++) {
				for (int j=0; j<size; j++) {
					Coordinate cell = new Coordinate(j,i);
					
					if (seen.contains(cell)) {
						if (!board.containsKey(cell)) {				sketch.append(WATER_SYMBOL);}
						else if (board.get(cell).isShotDown()) { 	sketch.append(board.get(cell).getSymbol());}
						else { 										sketch.append(HIT_SYMBOL);}
					}
					else { sketch.append(NOTSEEN_SYMBOL);}
				}
				if (i != size-1) { sketch.append("\n");}
			}
		}
		
		return sketch.toString();
	}
	
	/** Agregar barco al tablero. Para ello se comprueba:
	 *  que no este fuera del tablero, ni ocupe una coordenada o coordenada vecina de otro barco
	 * @param ship -> barco a agregar
	 * @param position -> posicion que representa al barco 
	 * @return -> true si se ha podido agregar, falso en caso contrario
	 * */
	public boolean addShip(Ship ship, Coordinate position) {
		List<Coordinate> coords_cpy1 = new ArrayList<Coordinate>(board.keySet());
		List<Coordinate> coords_cpy2 = new ArrayList<Coordinate>(board.keySet());
		
		for (Coordinate coord_ship : ship.getAbsolutePositions(position)) {
			if (!checkCoordinate(coord_ship)) {
				System.err.println("Error in Board.addShip, position " + coord_ship + " is out of the board");
				return false;
			}
		}
		
		if (coords_cpy2.removeAll(ship.getAbsolutePositions(position))) {
			coords_cpy1.removeAll(coords_cpy2);
			System.err.println("Error in Board.addShip, position " + coords_cpy1.get(0) + " is already occupied");
			return false;
		}
		if (coords_cpy2.removeAll(getNeighborhood(ship, position))) {
			coords_cpy1.removeAll(coords_cpy2);
			System.err.println("Error in Board.addShip, position " + coords_cpy1.get(0) + " is next to another ship");
			return false;
		}
		
		for (Coordinate coord_ship : ship.getAbsolutePositions(position)) {
			ship.setPosition(position);
			board.put(coord_ship, ship);
		}
		numCrafts++;
		return true;
	}
	
	/** Impactar contra una coordenada de la matriz del tablero
	 * @param coord -> coordenada a impactar
	 * @return -> situacion que pueden suceder cuando se hace un disparo
	 * */
	public CellStatus hit(Coordinate coord) {
		if (!checkCoordinate(coord)) {
			System.err.println("Error in Board.hit, position " + coord + " is out of the board");
			return CellStatus.WATER;
		}
		else if (!board.keySet().contains(coord)) {
			seen.add(new Coordinate(coord));
			return CellStatus.WATER;
		}
		else {
			board.get(coord).hit(coord);
			seen.add(new Coordinate(coord));
			
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
