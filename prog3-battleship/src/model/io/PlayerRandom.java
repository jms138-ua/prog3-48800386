package model.io;

import java.util.Objects;
import java.util.Random;

import model.Board;
import model.aircraft.Board3D;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.Craft;
import model.CraftFactory;
import model.Coordinate;
import model.CoordinateFactory;
import model.Orientation;

public class PlayerRandom implements IPlayer{

	private String name;
	
	private Random random;
	
	
	public PlayerRandom(String name, long seed) {
		this.name = name;
		random = new Random(seed);
	}
	
	//__________________________________________________________________________________________________________________________________________________________
	
	private int genRandomInt(int min, int max) {
		return random.nextInt(max-min) + min;
	}
	
	
	private Coordinate getRamdomCoordinate(Board board, int offset) {
		int board_size = board.getSize();
		if (board instanceof Board3D) {
			return CoordinateFactory.createCoordinate(genRandomInt(0-offset, board_size), genRandomInt(0-offset, board_size), genRandomInt(0-offset, board_size));
		}
		else{
			return CoordinateFactory.createCoordinate(genRandomInt(0-offset, board_size), genRandomInt(0-offset, board_size));
		}
	}
	
	//__________________________________________________________________________________________________________________________________________________________
	
	@Override
	public String getName() {
		return name + " (PlayerRandom)";
	}

	
	@Override
	public void putCrafts(Board board){
		Objects.requireNonNull(board);
		
		String[] crafts_type= {"Battleship","Carrier","Cruiser","Destroyer","Bomber","Fighter","Transport"};
		for (String craft_type : crafts_type) {
			if (board instanceof Board3D || !craft_type.matches("Bomber|Fighter|Transport")) {
				boolean add_check = false;
				int i_add_chek = 0;
				while (!add_check && i_add_chek<100) {
					try {
						Orientation orientation_random = Orientation.values()[random.nextInt(4)];
						Craft craft_random = CraftFactory.createCraft(craft_type, orientation_random);
						Coordinate position_random = getRamdomCoordinate(board, Craft.BOUNDING_SQUARE_SIZE);
						
						board.addCraft(craft_random, position_random);
						add_check = true;
					}
					catch (Exception e) { i_add_chek++;}
				}
			}
		}
	}
	
	
	@Override
	public Coordinate nextShoot(Board board) throws InvalidCoordinateException, CoordinateAlreadyHitException {
		Coordinate coord = getRamdomCoordinate(board, 0);
		
		board.hit(coord);
		return coord;
	}
}
