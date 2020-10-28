package model.ship;

import java.util.HashSet;
import java.util.Set;

import model.Coordinate;

public class Coordinate2D extends Coordinate {

	public Coordinate2D(int x, int y) {
		super(2);
		set(0,x); set(1,y);
	}
	
	
	public Coordinate2D(Coordinate2D coord) {
		super(coord);
	}
	
	//______________________________________________________________________
	
	@Override
	public Coordinate2D copy() {
		return new Coordinate2D(this);
	}
	
	//______________________________________________________________________
	
	public String toString() {
		StringBuilder sketch = new StringBuilder();
		
		sketch.append("(" + get(0) + ", " + get(1) + ")");
		return sketch.toString();
	}
	
	
	@Override
	public Set<Coordinate> adjacentCoordinates() {
		Set<Coordinate> coords_adj = new HashSet<Coordinate>();
		
		for (int i=get(0)-1; i<=get(0)+1; i++) {
			for (int j=get(1)-1; j<=get(1)+1; j++) {
				Coordinate coord_adj = new Coordinate2D(i,j);
				
				if (!coord_adj.equals(this)) {
					coords_adj.add(coord_adj);
				}
				
			}
		}
		return coords_adj;
	}
}
