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
		try {
			return (IVisualiser) Class.forName("model.io.Visualiser"+mode).getConstructor(Game.class).newInstance(game);
		}
		catch (Exception e) { return null;}
	}
}
