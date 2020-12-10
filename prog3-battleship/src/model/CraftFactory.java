package model;

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
		try {
			return (Craft) Class.forName("model."+type).getConstructor(Orientation.class).newInstance(orientation);
		}
		catch (Exception e) { return null;}
	}
}
