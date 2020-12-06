package model;

import model.ship.Battleship;
import model.ship.Carrier;
import model.ship.Cruiser;
import model.ship.Destroyer;
import model.aircraft.Bomber;
import model.aircraft.Fighter;
import model.aircraft.Transport;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase que crea las naves del juego,
 * ya sean barcos o aviones, utilizando sus respectivos constructores
 */

public class CraftFactory {
	
	/** Constructor a partir del tipo y orientacion
	 * @param type -> tipo de nave
	 * @param orientation -> orientacion de la nave
	 * @return -> objeto Craft de su tipo
	 */
	public static Craft createCraft(String type, Orientation orientation) {
		switch (type) {
			case "Battleship": 	return new Battleship(orientation);
			case "Carrier": 	return new Carrier(orientation);
			case "Cruiser": 	return new Cruiser(orientation);
			case "Destroyer": 	return new Destroyer(orientation);
			case "Bomber": 		return new Bomber(orientation);
			case "Fighter": 	return new Fighter(orientation);
			case "Transport": 	return new Transport(orientation);
			default: 			return null;
		}
	}
}
