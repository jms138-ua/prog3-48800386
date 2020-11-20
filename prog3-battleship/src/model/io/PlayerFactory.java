package model.io;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import model.exceptions.io.BattleshipIOException;

public class PlayerFactory {

	public static IPlayer createPlayer(String name, String mode) throws BattleshipIOException {
		if (mode.contains(".") || mode.contains("\\") || mode.contains("/")) {
			try { 					return new PlayerFile(name, new BufferedReader(new FileReader(mode)));}
			catch (IOException e) {	throw new BattleshipIOException("The file cannot be opened");}
		}
		else {
			try {								return new PlayerRandom(name, Long.parseLong(mode));}
			catch (NumberFormatException e) {	return null;}
		}
	}
}
