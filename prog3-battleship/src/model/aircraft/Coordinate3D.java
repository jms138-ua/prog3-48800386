package model.aircraft;

import java.util.HashSet;
import java.util.Set;

import model.Coordinate;

public class Coordinate3D extends Coordinate {

	public Coordinate3D(int x, int y, int z) {
		super(3);
		set(0,x); set(1,y); set(2,z);
	}
	
	
	public Coordinate3D(Coordinate3D coord) {
		super(coord);
	}
	
	//______________________________________________________________________
	
	@Override
	public Coordinate3D copy() {
		return new Coordinate3D(this);
	}
	
	//______________________________________________________________________
	
	public String toString() {
		StringBuilder sketch = new StringBuilder();
		
		sketch.append("(" + get(0) + ", " + get(1) + ", " + get(2) + ")");
		return sketch.toString();
	}
	
	
	@Override
	public Set<Coordinate> adjacentCoordinates() {
		Set<Coordinate> coords_adj = new HashSet<Coordinate>();
		
		for (int i=get(0)-1; i<=get(0)+1; i++) {
			for (int j=get(1)-1; j<=get(1)+1; j++) {
				for (int k=get(2)-1; k<=get(2)+1; k++) {
					Coordinate coord_adj = new Coordinate3D(i,j,k);
					
					if (!coord_adj.equals(this)) {
						coords_adj.add(coord_adj);
					}
				}
				
			}
		}
		return coords_adj;
	}
}
