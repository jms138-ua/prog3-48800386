package model;

/**
 * @author Javier Mellado Sanchez 48800386K
 * Enum para representar las distintas situaciones
 * que pueden suceder cuando se hace un disparo
 */

public enum CellStatus {
	/** Barco no impactado */
	WATER,
	/** Barco impactado */
	HIT,
	/** Barco destruido */
	DESTROYED
}
