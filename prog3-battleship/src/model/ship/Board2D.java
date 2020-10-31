package model.ship;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa el tablero del juego en 2d
 * Presenta los metodos no heredados
 */

public class Board2D extends Board{
	
	/** Constructor
	* @param size -> size del avion
	*/
	public Board2D(int size) {
		super(size);
	}
	
	//_____________________________________________________________________________________________________________
	
	/** Check si la coordenada se encuentra entre los limites del tablero
	* @param coord -> coordenada a chekear
	* @return -> true si esta en los limites, false en caso contrario
	* @throws -> invalid
	*/
	@Override
	public boolean checkCoordinate(Coordinate coord) {
		if (!(coord instanceof Coordinate2D)) {
			throw new IllegalArgumentException();
		}
		int size_board = getSize();
		return !(coord.get(0)<0 || coord.get(1)<0 || coord.get(0)>=size_board || coord.get(1)>=size_board);
	}
	
	//_____________________________________________________________________________________________________________
	
	/** Representar el objeto Board como un string en forma de matriz del tablero
	 * con sus naves con simbolos correspondientes.
	 * Existen dos maneras de representarlo segun si ei es el propietario u oponente
	 * @param unveil -> si es el propietario u oponente
	 * @return -> string en forma de matriz de tablero
	 * */
	@Override
	public String show(boolean unveil) {
		StringBuilder sketch = new StringBuilder();

		int size_board = getSize();
		if (unveil) {
			for (int i=0; i<size_board; i++) {
				for (int j=0; j<size_board; j++) {
					Coordinate cell = CoordinateFactory.createCoordinate(new int[] {j,i});
					
					if (getCraft(cell) != null) {
						if (getCraft(cell).isHit(cell)) { 	sketch.append(HIT_SYMBOL);}
						else { 								sketch.append(getCraft(cell).getSymbol());}
					}
					else { sketch.append(WATER_SYMBOL);}
				}
				if (i != size_board-1) { sketch.append("\n");}
			}
		}
		
		else {
			for (int i=0; i<size_board; i++) {
				for (int j=0; j<size_board; j++) {
					Coordinate cell = CoordinateFactory.createCoordinate(new int[] {j,i});
					
					if (isSeen(cell)) {
						if (getCraft(cell) == null) {				sketch.append(WATER_SYMBOL);}
						else if (getCraft(cell).isShotDown()) { 	sketch.append(getCraft(cell).getSymbol());}
						else { 										sketch.append(HIT_SYMBOL);}
					}
					else { sketch.append(NOTSEEN_SYMBOL);}
				}
				if (i != size_board-1) { sketch.append("\n");}
			}
		}
		
		return sketch.toString();
	}
}
