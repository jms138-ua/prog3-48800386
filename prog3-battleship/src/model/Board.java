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

public abstract class Board {
	
	public static final char HIT_SYMBOL = '•';
	public static final char WATER_SYMBOL = ' ';
	public static final char NOTSEEN_SYMBOL = '?';
	public static final char Board_SEPARATOR = '|';
	
	private static final int MAX_BOARD_SIZE = 20;
	private static final int MIN_BOARD_SIZE = 5;
	private int size;
	
	private int numCrafts;
	private int destroyedCrafts;
	
	private Set<Coordinate> seen;
	private Map<Coordinate, Craft> board;
	
	
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
	
	public abstract boolean checkCoordinate(Coordinate coord);
	
	
	public int getSize() {
		return size;
	}
	

	public Craft getCraft(Coordinate coord) {
		return board.get(coord);
	}
	

	public boolean isSeen(Coordinate coord) {
		return seen.contains(coord);
	}
	

	public boolean areAllCraftsDestroyed() {
		return numCrafts-destroyedCrafts == 0;
	}
	

	public Set<Coordinate> getNeighborhood(Craft Craft, Coordinate position) {
		Objects.requireNonNull(Craft);
		Objects.requireNonNull(position);
		Set<Coordinate> coords_neig = new HashSet<Coordinate>();
		
		for (Coordinate coord_ship : Craft.getAbsolutePositions(position)) {
			for (Coordinate coord_adj : coord_ship.adjacentCoordinates()) {
				if (checkCoordinate(coord_adj)) {
					coords_neig.add(coord_adj);
				}
			}
		}
		coords_neig.removeAll(Craft.getAbsolutePositions(position));
		
		return coords_neig;
	}
	

	public Set<Coordinate> getNeighborhood(Craft ship) {
		return (ship.getPosition() == null)? Collections.emptySet() : getNeighborhood(ship, ship.getPosition());
	}
	
	//______________________________________________________________________________________________________________________________________________________
	
	@Override
	public String toString() {
		return "Board " + size + "; crafts: " + numCrafts + "; destroyed: " + destroyedCrafts;
	}
	
	
	public abstract String show(boolean unveil);
	

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
