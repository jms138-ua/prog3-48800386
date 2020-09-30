package model;

import java.util.HashSet;
import java.util.Set;

//@author Javier Mellado Sanchez 48800386K

public class Ship {

	private final int BOUNDING_SQUARE_SIZE = 5;
	private final int CRAFT_VALUE = 1;
	private final int HIT_VALUE = -1;
	
	private char symbol;
	private String name;
	
	private Coordinate position;
	private Orientation orientation;
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

	
	public Ship (Orientation orientation, char symbol, String name) {
		this.orientation = orientation;
		this.symbol = symbol;
		this.name = name;
	}
	
	public Coordinate getPosition() {
		return new Coordinate(position);
	}
	
	public void setPosition(Coordinate position) {
		this.position = position;
	}
	
	public String getName() {
		return name;
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public int[][] getShape(){
		return shape;
	}
	
	
	public int getShapeIndex(Coordinate c) {
		return c.get(0)*BOUNDING_SQUARE_SIZE+c.get(1);
	}
	
	public Set<Coordinate> getAbsolutePositions(Coordinate position) {
		Set<Coordinate> components_ship = new HashSet<Coordinate>();
		
		for (int i : shape[orientation.ordinal()]) {
			if (i == CRAFT_VALUE) {
				int x_absolute = position.get(0) + getShapeIndex(position)%BOUNDING_SQUARE_SIZE;
				int y_absolute = position.get(1) + getShapeIndex(position)/BOUNDING_SQUARE_SIZE;
				components_ship.add(new Coordinate(x_absolute, y_absolute));
			}
		}
		return components_ship;
		
	}
	
	public Set<Coordinate> getAbdolutePositions() {
		return getAbsolutePositions(position);
		
	}
	
	public boolean hit(Coordinate c) {
		if (shape[orientation.ordinal()][getShapeIndex(c)] == CRAFT_VALUE) {
			shape[orientation.ordinal()][getShapeIndex(c)] = HIT_VALUE;
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isShotDown() {
		for (int i : shape[orientation.ordinal()]) {
			if (i == CRAFT_VALUE) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isHit(Coordinate c) {
		if (shape[orientation.ordinal()][getShapeIndex(c)] == HIT_VALUE) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return name;
	}
}
