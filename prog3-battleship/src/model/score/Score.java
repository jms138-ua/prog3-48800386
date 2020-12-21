package model.score;

import java.util.Objects;

import model.io.IPlayer;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa un tipo de puntuacion del jugador
 * @param <T> -> tipo de la puntuacion
 */

public abstract class Score<T> implements Comparable<Score<T>> {
	
	/** Jugador */
	private IPlayer player;
	/** Puntuacion del jugaodr */
	protected int score;
	
	
	/** Constructor
	* @param player -> jugador
	*/
	public Score(IPlayer player) {
		this.player = Objects.requireNonNull(player);
		score = 0;
	}
	
	//________________________________________________________________________________________________________
	
	/** Getter de la puntuacion
	 * @return -> puntuacion
	 */
	public int getScore() {
		return score;
	}
	
	//________________________________________________________________________________________________________
	
	/** Representar el objeto Score como un string */
	@Override
	public String toString() {
		return player.getName() + ": " + score;
	}
	
	/** Comparar dos resultados, usado en la ordenacion
	 * @param score_other -> Objeto Coordinate para comparar
	 * @return -> resultado
	 */
	public int compareTo(Score<T> score_other) {
		int result_cmp = new Integer(score).compareTo(new Integer(score_other.getScore()));
		if (result_cmp != 0) { 	return result_cmp;}
		else { 					return player.getName().compareTo(score_other.player.getName());}
	}
	
	/** Sumar nueva puntuacion
	 * @param sc -> nueva puntuacion
	 */
	public abstract void score(T sc);
}
