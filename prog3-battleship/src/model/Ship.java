package model;

import java.util.Set;
import java.util.HashSet;


//@author Javier Mellado Sanchez 48800386K

public class Ship {

	private static final int BOUNDING_SQUARE_SIZE = 5;
	private static final int CRAFT_VALUE = 1;
	private static final int HIT_VALUE = -1;

	private String name;
	private char symbol;
	
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
	
	//_________________________________________________________________________________________________
	
	public String getName() {
		return name;
	}
	
	
	public char getSymbol() {
		return symbol;
	}
	
	
	public Coordinate getPosition() {
		return new Coordinate(position);
	}
	
	
	public void setPosition(Coordinate position) {
		this.position = position;
	}
	
	
	public Orientation getOrientation() {
		return orientation;
	}
	
	
	public int[][] getShape(){
		return shape;
	}
	

	public int getShapeIndex(Coordinate c) {
		return c.get(0)*BOUNDING_SQUARE_SIZE+c.get(1);
	}
	
	
	public boolean isHit(Coordinate c) {
		return shape[orientation.ordinal()][getShapeIndex(c.subtract(position))] == HIT_VALUE;
	}
	
	
	public boolean isShotDown() {
		for (int i : shape[orientation.ordinal()]) {
			if (i == CRAFT_VALUE) {
				return false;
			}
		}
		return true;
	}
	
	//_________________________________________________________________________________________________
	
	@Override
	public String toString() {
		StringBuilder sketch = new StringBuilder();
		
		sketch.append(" ----- \n");
		for (int cell : shape[orientation.ordinal()]) {
			if (cell == CRAFT_VALUE) { 		sketch.append(symbol);}
			else if (cell == HIT_VALUE) { 	sketch.append(Board.HIT_SYMBOL);}
			else { 							sketch.append(Board.WATER_SYMBOL);}
		}
		
		sketch.insert(BOUNDING_SQUARE_SIZE+3, "|");
		for (int i=BOUNDING_SQUARE_SIZE*2+4; i<sketch.length(); i+=BOUNDING_SQUARE_SIZE+1) {
			sketch.insert(i, "|\n|");
			i+=2;
		}
		sketch.append("|\n ----- ");
		
		return name + " (" + orientation + ")\n" + sketch.toString();
	}
	
	
	public Set<Coordinate> getAbsolutePositions(Coordinate position) {
		Set<Coordinate> components_ship = new HashSet<Coordinate>();
		
		int i = 0;
		for (int cell : shape[orientation.ordinal()]) {
			if (cell==CRAFT_VALUE || cell==HIT_VALUE) {
				int x_absolute = position.get(0) + (i % BOUNDING_SQUARE_SIZE);
				int y_absolute = position.get(1) + (i / BOUNDING_SQUARE_SIZE);
				components_ship.add(new Coordinate(x_absolute, y_absolute));
			}
			i++;
		}
		return components_ship;
	}
	
	
	public Set<Coordinate> getAbsolutePositions() {
		return getAbsolutePositions(position);
	}
	
	
	public boolean hit(Coordinate c) {
		if (shape[orientation.ordinal()][getShapeIndex(c)] == CRAFT_VALUE) {
			shape[orientation.ordinal()][getShapeIndex(c)] = HIT_VALUE;
			return true;
		}
		else { return false;}
	}
}
