package model.io;

import java.util.Objects;
import java.io.File;
import java.awt.Color;

import model.Game;
import model.Board;
import model.exceptions.io.BattleshipIOException;
import model.io.gif.AnimatedGIF;
import model.io.gif.FrameGIF;

public class VisualiserGIF implements IVisualiser{
	
	private Game game;
	
	private AnimatedGIF agif;
	
	
	public VisualiserGIF (Game game) {
		this.game = Objects.requireNonNull(game);
		agif = new AnimatedGIF();
	}
	
	//________________________________________________________________
	
	@Override
	public void show() {
		Board board1 = game.getBoard1();
		Board board2 = game.getBoard2();
		int board_size = board1.getSize();
		
		try {
			char fix_endl[] = {'\n'};
			char cells_board1[] = board1.show(false).toCharArray();
			char cells_board2[] = board2.show(false).toCharArray();
			char cells[] = new char[cells_board1.length + 1 + cells_board2.length];
			System.arraycopy(cells_board1, 0, cells, 0, cells_board1.length);
			System.arraycopy(fix_endl, 0, cells, cells_board1.length, 1);
			System.arraycopy(cells_board2, 0, cells, cells_board1.length+1, cells_board2.length);
			
			FrameGIF frame = new FrameGIF(cells_board1.length/board_size, board_size*2+1);
			
			int i = 0, j = 0;
			for (char cell : cells){
				if (cell == Board.NOTSEEN_SYMBOL) { 		frame.printSquare(i, j, Color.LIGHT_GRAY); 	i++;}
				else if (cell == Board.WATER_SYMBOL) { 		frame.printSquare(i, j, Color.BLUE);		i++;}
				else if (cell == Board.HIT_SYMBOL) {		frame.printSquare(i, j, Color.RED);			i++;}
				else if (cell == Board.Board_SEPARATOR) { 	frame.printSquare(i, j, Color.ORANGE);		i++;}
				else if (cell == '\n'){
					j++;
					if(j==board_size) {
						for (int k=0; k<i; k++) {
							frame.printSquare(k, j, Color.DARK_GRAY);
						}
						j++;
					}
					i = 0;
				}
				else{	frame.printSquare(i, j, Color.RED);			i++;}
			}
			
			agif.addFrame(frame);
		}
		catch (BattleshipIOException e) { System.out.println(e.getMessage());}
	}
	
	
	@Override
	public void close() {
		try { agif.saveFile(new File("files/output.gif"));}
		catch (BattleshipIOException e) { throw new RuntimeException();}
	}
}
