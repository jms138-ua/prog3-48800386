package model.io;

import java.util.Objects;

import model.Game;

public class VisualiserConsole implements IVisualiser{

	private Game game;
	
	
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
