package model.io;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Clase interfaz que representa los tipos de visualizadores
 * Presenta los metodos para controlar los visualizadores
 */

public interface IVisualiser {
	
	/** Muestra por el visualizador un instante */
	public void show();
	
	/** Cierra el visualizador */
	public void close();
}
