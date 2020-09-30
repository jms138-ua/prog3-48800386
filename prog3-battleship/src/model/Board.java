package model;

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
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
		return 0 == numCrafts-destroyedCrafts;
	}
	
	
	public Set<Coordinate> getNeighBorhood(Ship ship, Coordinate position) {
		Set<Coordinate> v = new HashSet<Coordinate>();
		Set<Coordinate> v2 = new HashSet<Coordinate>();
		for (Coordinate coor : ship.getAbsolutePositions(position)) {
			v.addAll(coor.adjancentCoordinates());
		}
		for (Coordinate u : v) {
			if (!checkCoordinate(u)) {
				v2.add(u);
			}
		}
		v.removeAll(ship.getAbsolutePositions(position));
		v.removeAll(v2);
		return v;
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
					Coordinate coor = new Coordinate(j,i);
					
					if (board.containsKey(coor)) {
						if (board.get(coor).isHit(coor)) { 	sketch.append(HIT_SYMBOL);}
						else { 								sketch.append(board.get(coor).getSymbol());}
					}
					else { sketch.append(WATER_SYMBOL);}
				}
				sketch.append("\n");
			}
		}
		else {
			for (int i=0; i<size; i++) {
				for (int j=0; j<size; j++) {
					Coordinate coor = new Coordinate(j,i);
					
					if (seen.contains(coor)) {
						if (board.get(coor).isShotDown()) { 	sketch.append(board.get(coor).getSymbol());}
						else if (board.get(coor).isHit(coor)) { sketch.append(HIT_SYMBOL);}
						else { 									sketch.append(WATER_SYMBOL);}
					}
					else { sketch.append(NOTSEEN_SYMBOL);}
				}
				sketch.append("\n");
			}
		}
		
		return sketch.toString();
	}
	
	
	public boolean addShip(Ship ship, Coordinate position) {
		if (!Collections.disjoint(board.keySet(), ship.getAbsolutePositions(position))) {
			System.err.println("Error in Board.addShip, position " + position + " is already occupied");
			return false;
		}
		else if (!Collections.disjoint(board.keySet(), getNeighBorhood(ship, position))) {
			System.err.println("Error in Board.addShip, position " + position + " is next to another ship");
			return false;
		}
		else {
			for (Coordinate position_craft : ship.getAbsolutePositions(position)) {
				if (!checkCoordinate(position_craft)) {
					System.err.println("Error in Board.addShip, position " + position + " is out of the board");
					return false;
				}
				else {
					ship.setPosition(position);
					board.put(position_craft, ship);
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
			System.err.println("Error in Board.hit, position " + c + " is not occupied yet");
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
