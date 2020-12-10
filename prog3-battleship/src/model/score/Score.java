package model.score;

import java.util.Objects;

import model.io.IPlayer;

public abstract class Score<T> implements Comparable<Score<T>> {
	
	private IPlayer player;
	
	protected int score;
	
	
	public Score(IPlayer player) {
		this.player = Objects.requireNonNull(player);
		score = 0;
	}
	
	//________________________________________________________________________________________________________
	
	public int getScore() {
		return score;
	}
	
	//________________________________________________________________________________________________________
	
	@Override
	public String toString() {
		return player.getName() + ": " + score;
	}
	

	public int compareTo(Score<T> other) {
		int result_cmp = new Integer(score).compareTo(new Integer(other.getScore()));
		if (result_cmp != 0) { 	return result_cmp;}
		else { 					return player.getName().compareTo(other.player.getName());}
	}
	
	public abstract void score(T sc);
}
