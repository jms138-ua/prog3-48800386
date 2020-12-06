package model.io;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import model.exceptions.io.BattleshipIOException;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que crea las jugadores del juego,
 * ya sean en modo file o random, utilizando sus respectivos constructores
 */

public class PlayerFactory {
	
	/** Constructor a partir del nombre y modo
	 * @param name -> nombre del jugador
	 * @param mode -> modo del jugador
	 * @return -> objeto jugador de su modo, null si no se ha podido crear el random
	 * @throws BattleshipIOException -> invalid io
	 * */
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
