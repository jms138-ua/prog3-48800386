package model;

import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import model.exceptions.CoordinateAlreadyHitException;

public abstract class Craft {

	private static final int BOUNDING_SQUARE_SIZE = 5;
	private static final int CRAFT_VALUE = 1;
	private static final int HIT_VALUE = -1;
	
	protected String name;
	protected char symbol;
	
	private Coordinate position;
	protected Orientation orientation;
	protected int[][] shape;

	
	public Craft (Orientation orientation, char symbol, String name) {
		this.orientation = orientation;
		this.symbol = symbol;
		this.name = name;
	}

	//_______________________________________________________________________________________________________________________________________

	public String getName() {
		return name;
	}


	public char getSymbol() {
		return symbol;
	}

	
	public Coordinate getPosition() {
		return (position == null)? null : position.copy();
	}

	
	public void setPosition(Coordinate position) {
		this.position = position.copy();
	}

	
	public Orientation getOrientation() {
		return orientation;
	}

	
	public int[][] getShape() {
		return shape;
	}


	public int getShapeIndex(Coordinate coord) {
		Objects.requireNonNull(coord);
		return coord.get(1)*BOUNDING_SQUARE_SIZE+coord.get(0);
	}


	public boolean isHit(Coordinate coord) {
		return (position == null)? false : shape[orientation.ordinal()][getShapeIndex(coord.subtract(position))] == HIT_VALUE;
	}


	public boolean isShotDown() {
		for (int i : shape[orientation.ordinal()]) {
			if (i == CRAFT_VALUE) {
				return false;
			}
		}
		return true;
	}

	//_______________________________________________________________________________________________________________________________________

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

	
	public Set<Coordinate> getAbsolutePositions() {
		return getAbsolutePositions(position);
	}

	
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