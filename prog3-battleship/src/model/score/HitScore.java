package model.score;

import model.CellStatus;
import model.io.IPlayer;

public class HitScore extends Score<CellStatus> {
	
	public HitScore(IPlayer player) {
		super(player);
	}
	
	//_______________________________________________________________________
	
	public void score(CellStatus sc) {
		if (sc==CellStatus.HIT || sc==CellStatus.DESTROYED) {
			score++;
		}
	}
}
