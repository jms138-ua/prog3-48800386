package model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import model.ship.Battleship;

public class CraftFactoryPreTest {

	//TODO
	/* Comprueba la correcta creación de todos los Craft para todas las orientaciones */
	@Test
	public void testCreateCraftOk() {
		Craft craft;
		for (Orientation orient : Orientation.values()) {
			craft = CraftFactory.createCraft("Battleship", orient);
			assertTrue (craft instanceof Battleship );
			fail("Completa el test");
		}
	}

	//TODO
	/* Comprueba que createCraft devuelve null si el Craft es desconocido */
	@Test
	public void testCreateCraftWrong() {
		fail("Realiza el test");
	}
}
