package model.io;

import java.util.Objects;
import java.io.BufferedReader;

import model.Board;
import model.Craft;
import model.CraftFactory;
import model.Coordinate;
import model.CoordinateFactory;
import model.Orientation;
import model.CellStatus;

import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;
import java.io.IOException;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa a un jugador a traves un archivo
 */

public class PlayerFile implements IPlayer {
	
	/** Nombre del jugador */
	private String name;
	
	/** Buffer del archivo de lectura */
	private BufferedReader br;
	
	/** Situacion del ultimo disparo */
	public CellStatus lastShotStatus;
	
	
	/** Constructor
	* @param name -> nombre del jugador
	* @param br -> buffer del archivo de lectura
	*/
	public PlayerFile(String name, BufferedReader br) {
		this.name = name;
		this.br = Objects.requireNonNull(br);
	}
	
	//__________________________________________________________________________________________________________________________________________________________
	
	@Override
	public CellStatus getLastShotStatus() {
		return lastShotStatus;
	}
	
	//__________________________________________________________________________________________________________________________________________________________
	
	@Override
	public String getName() {
		return name + " (PlayerFile)";
	}
	
	@Override
	public void putCrafts(Board board) throws BattleshipIOException, InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException{
		Objects.requireNonNull(board);
		
		String line;
		try {
			while (!((line = br.readLine())==null || line.matches("endput|exit"))) {
				String[] arg_put = line.split("\\s+");
				
				if (!arg_put[0].equals("put")) { throw new BattleshipIOException("command syntax error");}
				
				Coordinate coord;
				if (arg_put.length == 5) {
					coord = CoordinateFactory.createCoordinate(Integer.parseInt(arg_put[3]), Integer.parseInt(arg_put[4]));
				}
				else if (arg_put.length == 6) {
					coord = CoordinateFactory.createCoordinate(Integer.parseInt(arg_put[3]), Integer.parseInt(arg_put[4]),Integer.parseInt(arg_put[5]));
				}
				else { throw new IOException();}
				
				Craft craft;
				try { 					craft = CraftFactory.createCraft(arg_put[1], Orientation.valueOf(arg_put[2]));}
				catch (Exception e) {	throw new NumberFormatException();}
				
				board.addCraft(craft, coord);
			}
		}
		catch (IOException | NumberFormatException e){ throw new BattleshipIOException("put command syntax error");}
	}
	
	@Override
	public Coordinate nextShoot(Board board) throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException{
		try {
			String line = br.readLine();
			if (line == null) { lastShotStatus = null; return null;}
			
			String[] arg_shoot = line.split("\\s+");
			if (arg_shoot[0].equals("exit")) { 	lastShotStatus = null; return null;}
			if (!arg_shoot[0].matches("shoot|exit")) { 			throw new BattleshipIOException("command syntax error");}
			
			Coordinate coord;
			if (arg_shoot.length == 3) {
				coord = CoordinateFactory.createCoordinate(Integer.parseInt(arg_shoot[1]), Integer.parseInt(arg_shoot[2]));
			}
			else if (arg_shoot.length == 4) {
				coord = CoordinateFactory.createCoordinate(Integer.parseInt(arg_shoot[1]), Integer.parseInt(arg_shoot[2]),Integer.parseInt(arg_shoot[3]));
			}
			else { throw new IOException();}
			
			lastShotStatus = board.hit(coord);
			return coord;
		}
		catch (IOException | NumberFormatException e){ throw new BattleshipIOException("shoot command syntax error");}
	}
}
