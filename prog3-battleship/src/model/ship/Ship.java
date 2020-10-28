package model.ship;

import model.Craft;
import model.Orientation;

public abstract class Ship extends Craft {
	
	public Ship (Orientation orientation, char symbol, String name) {
		super(orientation, symbol, name);
	}
}
