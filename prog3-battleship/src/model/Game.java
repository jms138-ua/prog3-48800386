package model;

import java.util.Objects;

import model.io.IPlayer;
import model.io.IVisualiser;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.io.BattleshipIOException;

public class Game {
	
	private Board board1;
	private Board board2;
	private IPlayer player1;
	private IPlayer player2;

	private boolean gameStarted;
	private int shootCounter;
	private int nextToShoot;
	
	
	public Game(Board board1, Board board2, IPlayer player1, IPlayer player2) {
		this.board1 = Objects.requireNonNull(board1);
		this.board2 = Objects.requireNonNull(board2);
		this.player1 = Objects.requireNonNull(player1);
		this.player2 = Objects.requireNonNull(player2);
	}
	
	//__________________________________________________________________________________
	
	public Board getBoard1() {
		return board1;
	}
	
	
	public Board getBoard2() {
		return board2;
	}
	
	
	public IPlayer getPlayer1() {
		return player1;
	}
	
	
	public IPlayer getPlayer2() {
		return player2;
	}
	
	
	public IPlayer getPlayerLastShoot() {
		if (!gameStarted) { return null;}
		return (nextToShoot == 1) ? player2 : player1;
		
	}
	
	public boolean gameEnded() {
		return gameStarted && (board1.areAllCraftsDestroyed() || board2.areAllCraftsDestroyed());
	}
	
	//__________________________________________________________________________________
	
	public String toString() {
		StringBuilder sketch = new StringBuilder();
		if (!gameStarted) { 	sketch.append("=== GAME NOT STARTED ===\n");}
		else if (gameEnded()) { sketch.append("=== GAME ENDED ===\n");}
		else {					sketch.append("=== ONGOING GAME ===\n");}
		sketch.append("==================================\n");
				
		sketch.append(player1.getName() + "\n==================================\n" + board1.show(false) + "\n==================================\n");
		sketch.append(player2.getName() + "\n==================================\n" + board2.show(false) + "\n==================================\n");
		
		sketch.append("Number of shots: " + shootCounter);
		
		return sketch.toString();
	}
	
	
	public void start() {
		try {
			player1.putCrafts(board1);
			player2.putCrafts(board2);
		}
		catch (Exception e) { throw new RuntimeException();}
		
		gameStarted = false;
		shootCounter = 0;
		nextToShoot = 1;
	}
	

	public boolean playNext() throws Exception{
		try {
			if (nextToShoot == 1) 	{ if(player1.nextShoot(board2) == null) {return false;};}
			else					{ if(player2.nextShoot(board1) == null) {return false;};}
			shootCounter++;
			nextToShoot = 3-nextToShoot;
		}
		catch (BattleshipIOException | InvalidCoordinateException e) { throw new RuntimeException();}
		catch (CoordinateAlreadyHitException e) { System.out.println("Action by " + player1.getName() + e.getMessage());}
		return true;
	}
	
	
	public void playGame(IVisualiser visualiser) throws Exception {
		visualiser.show(); start(); visualiser.show();
		while (playNext() && !gameEnded()) { visualiser.show();}
		visualiser.close();
	}
}
