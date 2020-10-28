package model.aircraft;

import model.Craft;
import model.Orientation;

public abstract class Aircraft extends Craft {

	public Aircraft(Orientation orientation, char symbol, String name) {
		super(orientation, symbol, name);
	}
}
