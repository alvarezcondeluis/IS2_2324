package es.unican.is2.listaordenada;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import es.unican.is2.listaOrdenadaAcotada.ListaOrdenadaAcotada;

class ListaOrdenadaAcotadaTest {
	
	private ListaOrdenadaAcotada listaOrdenadaAcotada;
	
	@Test
	public void testConstructor() {
		//Casos validos
		listaOrdenadaAcotada = new ListaOrdenadaAcotada(1);
		assertEquals(1, listaOrdenadaAcotada.size());
	}

}
