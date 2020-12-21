package model.score;

import java.util.TreeSet;
import java.util.SortedSet;

import java.util.NoSuchElementException;
import model.exceptions.score.EmptyRankingException;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que representa un set ordenado de un tipo de puntuacion del jugador
 * @param <ScoreType> -> tipo de la puntuacion
 */

public class Ranking<ScoreType extends Score<?>>{
	
	/** Set ordenado de un tipo de puntuacion */
	private SortedSet<ScoreType> scoreSet;
	
	
	/** Constructor
	 */
	public Ranking() {
		scoreSet = new TreeSet<>();
	}
	
	//________________________________________________________________________________________________________
	
	/** Getter del set ordenado de un tipo de puntuacion
	 * @return -> set
	 */
	public SortedSet<ScoreType> getSortedRanking() {
		return scoreSet;
	}
	
	//________________________________________________________________________________________________________
	
	/** Objeto Score ganador, como es un set ordenador coge el ultimo elemento (el de mayor puntuacion)
	 * @return -> coordenada impactada
	 * @throws EmptyRankingException -> empty ranking
	 */
	public ScoreType getWinner() throws EmptyRankingException {
		try {
			return scoreSet.last();
		}
		catch (NoSuchElementException e) { throw new EmptyRankingException("Empty ranking");}
	}
	
	/** Agregar nueva puntuacion
	 * @param st -> nueva puntuacion
	 */
	public void addScore(ScoreType st) {
		scoreSet.add(st);
	}
}
