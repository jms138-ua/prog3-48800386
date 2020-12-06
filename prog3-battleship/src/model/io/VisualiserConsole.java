package model.io;

import java.util.Objects;

import model.Game;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa a un visualizador por consola
 */

public class VisualiserConsole implements IVisualiser{

	/** Juego */
	private Game game;
	
	
	/** Constructor
	* @param game -> juego
	*/
	public VisualiserConsole(Game game) {
		Objects.requireNonNull(game);
		this.game = game;
	}
	
	//_____________________________________________________
	
	@Override
	public void show() {
		System.out.println(game.toString());
	}
	
	@Override
	public void close() {}
}
