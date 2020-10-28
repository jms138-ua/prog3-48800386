package model.ship;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;

public class Board2D extends Board{
	
	public Board2D(int size) {
		super(size);
	}
	
	//_____________________________________________________________________________________________________________
	
	@Override
	public boolean checkCoordinate(Coordinate coord) {
		if (!(coord instanceof Coordinate2D)) {
			throw new IllegalArgumentException();
		}
		int size_board = getSize();
		return !(coord.get(0)<0 || coord.get(1)<0 || coord.get(0)>=size_board || coord.get(1)>=size_board);
	}
	
	//_____________________________________________________________________________________________________________
	
	@Override
	public String show(boolean unveil) {
		StringBuilder sketch = new StringBuilder();

		int size_board = getSize();
		if (unveil) {
			for (int i=0; i<size_board; i++) {
				for (int j=0; j<size_board; j++) {
					Coordinate cell = CoordinateFactory.createCoordinate(new int[] {j,i});
					
					if (getCraft(cell) != null) {
						if (getCraft(cell).isHit(cell)) { 	sketch.append(HIT_SYMBOL);}
						else { 								sketch.append(getCraft(cell).getSymbol());}
					}
					else { sketch.append(WATER_SYMBOL);}
				}
				if (i != size_board-1) { sketch.append("\n");}
			}
		}
		
		else {
			for (int i=0; i<size_board; i++) {
				for (int j=0; j<size_board; j++) {
					Coordinate cell = CoordinateFactory.createCoordinate(new int[] {j,i});
					
					if (isSeen(cell)) {
						if (getCraft(cell) == null) {				sketch.append(WATER_SYMBOL);}
						else if (getCraft(cell).isShotDown()) { 	sketch.append(getCraft(cell).getSymbol());}
						else { 										sketch.append(HIT_SYMBOL);}
					}
					else { sketch.append(NOTSEEN_SYMBOL);}
				}
				if (i != size_board-1) { sketch.append("\n");}
			}
		}
		
		return sketch.toString();
	}
}
