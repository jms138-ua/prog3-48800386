package model;

import java.util.Objects;

import model.io.IPlayer;
import model.io.IVisualiser;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.io.BattleshipIOException;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa el juego
 * Presenta los metodos para controlar el juego
 */

public class Game {
	
	/** Tablero del jugador1 */
	private Board board1;
	/** Tablero del jugador2 */
	private Board board2;
	/** Jugador1 */
	private IPlayer player1;
	/** Jugador2 */
	private IPlayer player2;
	
	/** Bool si el juego a empezado */
	private boolean gameStarted;
	/** Numero de impactos */
	private int shootCounter;
	/** Siguente jugador a impactar */
	public int nextToShoot;
	
	
	/** Constructor
	* @param board1 -> tablero1
	* @param board2 -> tablero2
	* @param player1 -> jugador1
	* @param player2 -> jugador2
	*/
	public Game(Board board1, Board board2, IPlayer player1, IPlayer player2) {
		this.board1 = Objects.requireNonNull(board1);
		this.board2 = Objects.requireNonNull(board2);
		this.player1 = Objects.requireNonNull(player1);
		this.player2 = Objects.requireNonNull(player2);
	}
	
	//__________________________________________________________________________________
	
	/** Getter del tablero1
	 * @return -> tablero1
	 */
	public Board getBoard1() {
		return board1;
	}
	
	/** Getter del tablero2
	 * @return -> tablero2
	 */
	public Board getBoard2() {
		return board2;
	}
	
	/** Getter del jugador1
	 * @return -> jugador1
	 */
	public IPlayer getPlayer1() {
		return player1;
	}
	
	/** Getter del jugador2
	 * @return -> jugador2
	 */
	public IPlayer getPlayer2() {
		return player2;
	}
	
	/** Getter del ultimo jugador que ha impactado
	 * @return -> jugador
	 */
	public IPlayer getPlayerLastShoot() {
		if (!gameStarted) { return null;}
		return (nextToShoot == 1) ? player2 : player1;
		
	}
	
	/** Check si el juego ha terminado
	* @return -> true si ha empezado y algun jugador ha perdido, false en caso contrario
	*/
	public boolean gameEnded() {
		return gameStarted && (board1.areAllCraftsDestroyed() || board2.areAllCraftsDestroyed());
	}
	
	//__________________________________________________________________________________
	
	/** Representar todo el juego, con los tableros de cada jugador y demás propiedades
	 * @return -> string del juego
	 */
	@Override
	public String toString() {
		StringBuilder sketch = new StringBuilder();
		if (!gameStarted) { 	sketch.append("=== GAME NOT STARTED ===\n");}
		else if (gameEnded()) { sketch.append("=== GAME ENDED ===\n");}
		else {					sketch.append("=== ONGOING GAME ===\n");}
		sketch.append("==================================\n");
					
		sketch.append(player1.getName() + "\n==================================\n" + board1.show(false) + "\n==================================\n");
		sketch.append(player2.getName() + "\n==================================\n" + board2.show(false) + "\n==================================\n");
		
		sketch.append("Number of shots: " + shootCounter);
		if (gameEnded()) { 
			if (board2.areAllCraftsDestroyed()) {  sketch.append("\n" + player1.getName() + " wins");}
			else 				  				{  sketch.append("\n" + player2.getName() + " wins");}
		}
		
		return sketch.toString();
	}
	
	/** Empezar el juego, poniendo los barcos en el tablero e inicializar atributos del juego */
	public void start() {
		try {
			player1.putCrafts(board1);
			player2.putCrafts(board2);
		}
		catch (Exception e) { throw new RuntimeException();}
		
		gameStarted = true;
		shootCounter = 0;
		nextToShoot = 1;
	}
	
	/** El jugador que le toque el turno impacta contra el que le habia impactado antes
	 * @return -> true si se ha podido impactar, false en caso contrario
	 */
	public boolean playNext(){
		try {
			if (nextToShoot == 1) { if(player1.nextShoot(board2) == null) {return false;};}
			else				  { if(player2.nextShoot(board1) == null) {return false;};}
		}
		catch (BattleshipIOException | InvalidCoordinateException e) { throw new RuntimeException();}
		catch (CoordinateAlreadyHitException e) {
			if (nextToShoot == 1) { System.out.println("Action by " + player1.getName() + ":" + e.getMessage());}
			else 				  { System.out.println("Action by " + player2.getName() + ":" + e.getMessage());}
		}
		shootCounter++;
		nextToShoot = 3-nextToShoot;
		return true;
	}
	
	/** Empezar la partida, controla todo lo necesario para gestionar la partida y mostar el progreso
	 * @param visualiser -> visualizador para mostrar el progreso
	 */
	public void playGame(IVisualiser visualiser) {
		start(); visualiser.show();
		while (!gameEnded() && playNext()) { visualiser.show();}
		visualiser.close();
	}
}
