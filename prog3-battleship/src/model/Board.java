package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class Board {
	public static final char HIT_SYMBOL = '•';
	public static final char WATER_SYMBOL = ' ';
	public static final char NOTSEEN_SYMBOL = '?';
	private static final int MAX_BOARD_SIZE = 20;
	private static final int MIN_BOARD_SIZE = 5;
	
	private int size;
	private int numCrafts;
	private int destroyedCrafts;
	private Set<Coordinate> seen;
	
	private Map<Coordinate, Ship> board;
	
	
	public Board(int size) {
		if (size < MIN_BOARD_SIZE || size > MAX_BOARD_SIZE) {
			System.err.println("Error indicando el size Board");
			size = MIN_BOARD_SIZE;
		}
		
		this.size = size;
		numCrafts = 0;
		destroyedCrafts = 0;
		seen = new HashSet<Coordinate>();
		board = new HashMap<Coordinate, Ship>();
	}
	
	//_________________________________________________________________________________________________
	
	public boolean checkCoordinate(Coordinate c) {
		return !(c.get(0)<0 || c.get(1)<0 || c.get(0)>=size || c.get(1)>=size);
	}
	
	
	public int getSize() {
		return size;
	}
	
	
	public Ship getShip(Coordinate c) {
		return board.get(c);
	}
	
	
	public boolean isSeen(Coordinate c) {
		return seen.contains(c);
	}
	
	
	public boolean areAllCraftsDestroyed() {
		return numCrafts-destroyedCrafts == 0;
	}
	
	
	public Set<Coordinate> getNeighBorhood(Ship ship, Coordinate position) {
		Set<Coordinate> components_neighborhood = new HashSet<Coordinate>();
		
		for (Coordinate coord_ship : ship.getAbsolutePositions(position)) {
			for (Coordinate coord_adjancent : coord_ship.adjancentCoordinates()) {
				if (checkCoordinate(coord_adjancent)) {
					components_neighborhood.add(coord_adjancent);
				}
			}
		}
		components_neighborhood.removeAll(ship.getAbsolutePositions(position));
		
		return components_neighborhood;
	}
	
	public Set<Coordinate> getNeighBorhood(Ship ship) {
		return getNeighBorhood(ship, ship.getPosition());
	}
	
	//_________________________________________________________________________________________________
	
	@Override
	public String toString() {
		return "Board " + size + "; crafts: " + numCrafts + "; destroyed: " + destroyedCrafts;
	}
	
	
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
				sketch.append("\n");
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
				sketch.append("\n");
			}
		}
		
		return sketch.toString();
	}
	
	
	public boolean addShip(Ship ship, Coordinate position) {
		List<Coordinate> coords_cpy1 = new ArrayList<Coordinate>(board.keySet());
		List<Coordinate> coords_cpy2 = new ArrayList<Coordinate>(board.keySet());
		
		if (coords_cpy2.removeAll(ship.getAbsolutePositions(position))) {
			coords_cpy1.removeAll(coords_cpy2);
			System.err.println("Error in Board.addShip, position " + coords_cpy1.get(0) + " is already occupied");
			return false;
		}
		else if (coords_cpy2.removeAll(getNeighBorhood(ship, position))) {
			coords_cpy1.removeAll(coords_cpy2);
			System.err.println("Error in Board.addShip, position " + coords_cpy1.get(0) + " is next to another ship");
			return false;
		}
		else {
			for (Coordinate coord_ship : ship.getAbsolutePositions(position)) {
				if (!checkCoordinate(coord_ship)) {
					System.err.println("Error in Board.addShip, position " + coord_ship + " is out of the board");
					return false;
				}
				else {
					ship.setPosition(position);
					board.put(coord_ship, ship);
				}
			}
			numCrafts++;
			return true;
		}
	}
	
	
	public CellStatus hit(Coordinate c) {
		if (!checkCoordinate(c)) {
			System.err.println("Error in Board.hit, position " + c + " is out of the board");
			return CellStatus.WATER;
		}
		else if (!board.keySet().contains(c)) {
			seen.add(c);
			return CellStatus.WATER;
		}
		else {
			board.get(c).hit(c);
			
			if (board.get(c).isShotDown()) {
				seen.addAll(getNeighBorhood(board.get(c), c));
				destroyedCrafts++;
				return CellStatus.DESTROYED;
			}
			else {
				seen.add(c);
				return CellStatus.HIT;
			}
		}
	}
}
