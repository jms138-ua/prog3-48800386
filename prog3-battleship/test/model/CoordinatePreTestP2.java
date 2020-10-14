package model;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/* Para realizar los test se sugiere usar métodos de la librería de junit como:
 * assertEquals; assertSame; assertNotSame, assertTrue; assertFalse
 */
public class CoordinatePreTestP2 {
	
    int []vcoor= {0,0,-70,-2,20}; //Para crear coordenadas
    final int DIM = vcoor.length;
    List<Coordinate> lcoor;
    
	@Before
	public void setUp() throws Exception {
		lcoor = new ArrayList<Coordinate>();
		//Se crean las Coordinates (0,0),(0,-70), (-70,-2),(-2,20);
		for (int i=0; i<DIM-1; i++) {
			lcoor.add(new Coordinate(vcoor[i],vcoor[i+1]));
		}
	}

	
	//TODO testCopy
	/* Crea copias de las Coordinates creadas en el setUp() y comprueba que:
	 * 1 - La copia y el original no son la misma.
	 * 2 - La copia tiene los mismos valores, en las componentes respectivas, que el objeto copiado.
	 */
	@Test
	public void testCopy() {
		fail ("Realizar el test propuesto");
	}

	//TODO testAdjacentCoordinates
	/* Crea una Coordinate y a partir de ella obten las Coordinate adyacentes 
	 * con tu método adjacentCoordinates(). Guárdalas en un Set. 
	 * Para cada una de las posiciones adyacentes a la Coordinate inicial, crea una 
	 * Coordinate, y comprueba que están contenidas en el Set.
	 */
	@Test
	public void testAdjacentCoordinates() {
		
      fail("Realiza el test de adjacentCoordiantes propuesto");
	}
	
	

}
