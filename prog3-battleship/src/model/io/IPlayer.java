package model.io;

import model.Coordinate;
import model.Board;

public interface IPlayer {
	
	public String getName();
	public void putCrafts(Board b) throws Exception;
	public Coordinate nextShoot(Board b) throws Exception;
}
