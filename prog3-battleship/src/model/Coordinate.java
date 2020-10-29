package model;

import java.util.Arrays;
import java.util.Set;
import java.util.Objects;

public abstract class Coordinate {
	
	private int[] components;
	
	
	protected Coordinate (int dim) {
		components = new int[dim];
	}
	
	
	protected Coordinate (Coordinate coord) {
		components = new int[coord.components.length];
		for (int i=0; i<coord.components.length; i++) {
			components[i] = coord.components[i];
		}
	}
	
	//______________________________________________________________________
	
	public int get(int component) {
		if (component>=0 && component<components.length) {
			return components[component];
		}
		else { throw new IllegalArgumentException();}
	}
	
	
	protected void set(int component, int value) {
		if (component>=0 && component<components.length) {
			components[component] = value;
		}
		else { throw new IllegalArgumentException();}
	}
	
	
	public abstract Coordinate copy();
	
	//______________________________________________________________________

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(components);
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true;}
		if (obj == null) { return false;}
		if (getClass() != obj.getClass()) { return false;}
		Coordinate other = (Coordinate) obj;
		if (!Arrays.equals(components, other.components)) { return false;}
		return true;
	}
	
	
	public Coordinate add(Coordinate coord) {
		Objects.requireNonNull(coord);
		Coordinate new_coord = this.copy();
		
		for (int i=0; i<components.length; i++) {
			try { new_coord.set(i, new_coord.get(i) + coord.get(i));}
			catch (IllegalArgumentException e) {}
		}
		return new_coord;
	}
	
	
	public Coordinate subtract(Coordinate coord) {
		Objects.requireNonNull(coord);
		Coordinate new_coord = this.copy();
		
		for (int i=0; i<components.length; i++) {
			try { new_coord.set(i, new_coord.get(i) - coord.get(i));}
			catch (IllegalArgumentException e) {}
		}
		return new_coord;
	}
	
	
	public abstract Set<Coordinate> adjacentCoordinates();
}
