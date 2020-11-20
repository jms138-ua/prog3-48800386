package model.io;

import model.Game;

public class VisualiserFactory {

	public static IVisualiser createVisualiser(String mode, Game game) {
		if (mode == "Console") { 	return new VisualiserConsole(game);}
		else if (mode == "GIF") {	return new VisualiserGIF(game);}
		else {						return null;}
	}
}
