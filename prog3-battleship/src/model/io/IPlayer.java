package model.io;

import model.Coordinate;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;
import model.Board;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase interfaz que representa los tipos de jugadores
 * Presenta los metodos para controlar el juego del jugador
 */

public interface IPlayer {
	
	/** Getter de nombre del jugador
	 * @return -> nombre
	 */
	public String getName();
	
	/** Poner los barcos en el tablero
	 * @param board -> tablero
	 * @throws BattleshipIOException -> invalid io
	 * @throws InvalidCoordinateException -> invalid
	 * @throws OccupiedCoordinateException -> occupied
	 * @throws NextToAnotherCraftException -> next
	 */
	public void putCrafts(Board board) throws BattleshipIOException, InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException;
	
	/** Impactar al tablero
	 * @param board -> tablero
	 * @return -> coordenada impactada
	 * @throws BattleshipIOException -> invalid io
	 * @throws InvalidCoordinateException -> invalid
	 * @throws CoordinateAlreadyHitException -> ya impactada
	 */
	public Coordinate nextShoot(Board board) throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException;
}
