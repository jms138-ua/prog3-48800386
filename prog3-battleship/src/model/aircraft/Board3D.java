package model.aircraft;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa el tablero del juego en 3d
 * Presenta los metodos no heredados
 */

public class Board3D extends Board{
	
	/** Constructor
	* @param size -> size del avion
	*/
	public Board3D(int size) {
		super(size);
	}
	
	//______________________________________________________________________________________________________________________________________________________
	
	/** Check si la coordenada se encuentra entre los limites del tablero
	* @param coord -> coordenada a chekear
	* @return -> true si esta en los limites, false en caso contrario
	* @throws -> invalid
	*/
	@Override
	public boolean checkCoordinate(Coordinate coord) {
		if (!(coord instanceof Coordinate3D)) {
			throw new IllegalArgumentException();
		}
		int size_board = getSize();
		return !(coord.get(0)<0 || coord.get(1)<0 || coord.get(2)<0 || coord.get(0)>=size_board || coord.get(1)>=size_board || coord.get(2)>=size_board);
	}
	
	//______________________________________________________________________________________________________________________________________________________
	
	/** Representar el objeto Board como un string en forma varias matrices del tablero
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
			for(int i=0; i<size_board; i++) {
				for (int k=0; k<size_board; k++) {
					for (int j=0; j<size_board; j++) {
						Coordinate cell = CoordinateFactory.createCoordinate(new int[] {j,i,k});
						
						if (getCraft(cell) != null) {
							if (getCraft(cell).isHit(cell)) { 	sketch.append(HIT_SYMBOL);}
							else { 								sketch.append(getCraft(cell).getSymbol());}
						}
						else { sketch.append(WATER_SYMBOL);}
					}
					if (k != size_board-1) { sketch.append("|");}
					else if (i != size_board-1) { sketch.append("\n");}
				}
			}
		}
		
		else {
			for(int i=0; i<size_board; i++) {
				for (int k=0; k<size_board; k++) {
					for (int j=0; j<size_board; j++) {
						Coordinate cell = CoordinateFactory.createCoordinate(new int[] {j,i,k});
						
						if (isSeen(cell)) {
							if (getCraft(cell) == null) {				sketch.append(WATER_SYMBOL);}
							else if (getCraft(cell).isShotDown()) { 	sketch.append(getCraft(cell).getSymbol());}
							else { 										sketch.append(HIT_SYMBOL);}
						}
						else { sketch.append(NOTSEEN_SYMBOL);}
					}
					if (k != size_board-1) { sketch.append("|");}
					else if (i != size_board-1) { sketch.append("\n");}
				}
			}
		}
		
		return sketch.toString();
	}
}
