package model.io;

import model.Coordinate;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;
import model.Board;

public interface IPlayer {
	
	public String getName();
	public void putCrafts(Board b) throws BattleshipIOException, InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException;
	public Coordinate nextShoot(Board b) throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException;
}
