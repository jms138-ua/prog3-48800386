package model.io;

import java.util.Objects;
import java.io.BufferedReader;
import java.io.IOException;

import model.Board;
import model.Craft;
import model.CraftFactory;
import model.Coordinate;
import model.CoordinateFactory;
import model.Orientation;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;

public class PlayerFile implements IPlayer {
	
	private String name;
	
	private BufferedReader br;
	
	
	public PlayerFile(String name, BufferedReader br) {
		this.name = name;
		this.br = Objects.requireNonNull(br);
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
				
				if (!arg_put[0].matches("put|endput|exit")) { throw new BattleshipIOException(null);}
				
				Coordinate coord;
				if (arg_put.length == 5) {
					coord = CoordinateFactory.createCoordinate(Integer.parseInt(arg_put[3]), Integer.parseInt(arg_put[4]));
				}
				else if (arg_put.length == 6) {
					coord = CoordinateFactory.createCoordinate(Integer.parseInt(arg_put[3]), Integer.parseInt(arg_put[4]),Integer.parseInt(arg_put[5]));
				}
				else { throw new BattleshipIOException(null);}
				Craft craft = CraftFactory.createCraft(arg_put[1], Orientation.valueOf(arg_put[2]));
				
				board.addCraft(craft, coord);
			}
		}
		catch (IOException | IllegalArgumentException e){ throw new BattleshipIOException("put command syntax error");}
	}
	
	
	@Override
	public Coordinate nextShoot(Board board) throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException{
		try {
			String[] arg_shoot = br.readLine().split("\\s+");
			if (arg_shoot[0].equals("exit") || arg_shoot[0]==null) { 	return null;}
			if (!arg_shoot[0].matches("shoot|exit")) { 			throw new BattleshipIOException(null);}
			
			Coordinate coord;
			if (arg_shoot.length == 3) {
				coord = CoordinateFactory.createCoordinate(Integer.parseInt(arg_shoot[1]), Integer.parseInt(arg_shoot[2]));
			}
			else if (arg_shoot.length == 4) {
				coord = CoordinateFactory.createCoordinate(Integer.parseInt(arg_shoot[1]), Integer.parseInt(arg_shoot[2]),Integer.parseInt(arg_shoot[3]));
			}
			else { throw new BattleshipIOException(null);}
			
			board.hit(coord);
			return coord;
		}
		catch (IOException | NumberFormatException e){ throw new BattleshipIOException("shoot command syntax error");}
	}
}
