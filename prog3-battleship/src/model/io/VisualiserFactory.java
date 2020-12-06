package model.io;

import model.Game;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que crea los visualizadores del juego,
 * ya sean en modo consola o GIF, utilizando sus respectivos constructores
 */

public class VisualiserFactory {

	/** Constructor a partir del modo y juego
	 * @param mode -> modo del visualizador
	 * @param game -> juego
	 * @return -> objeto visualizador, null si no se ha podido crear
	 */
	public static IVisualiser createVisualiser(String mode, Game game) {
		if (mode == "Console") { 	return new VisualiserConsole(game);}
		else if (mode == "GIF") {	return new VisualiserGIF(game);}
		else {						return null;}
	}
}
