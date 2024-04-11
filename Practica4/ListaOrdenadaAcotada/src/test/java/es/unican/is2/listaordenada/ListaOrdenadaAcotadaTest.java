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
		listaOrdenadaAcotada.add(1);
		
		// Comprobamos que el tamaño maximo de la lista sea 1
		assertThrows(IllegalStateException.class, () -> {	
				listaOrdenadaAcotada.add(1);
		} );
		assertNotNull(listaOrdenadaAcotada);
		
		
		listaOrdenadaAcotada = new ListaOrdenadaAcotada<>(20);
		assertNotNull(listaOrdenadaAcotada);
		
		listaOrdenadaAcotada = new ListaOrdenadaAcotada<>(1000000);
		assertNotNull(listaOrdenadaAcotada);
		
		listaOrdenadaAcotada = new ListaOrdenadaAcotada<>(0);
		assertNotNull(listaOrdenadaAcotada);
		assertThrows(IllegalStateException.class, () -> {	
			listaOrdenadaAcotada.add(1);
		} );
		
		// Casos no validos
		
		assertThrows(NegativeArraySizeException.class, () -> {	
			listaOrdenadaAcotada = new ListaOrdenadaAcotada<>(-10000);
		} );
		
		
		assertThrows(NegativeArraySizeException.class, () -> {	
			listaOrdenadaAcotada = new ListaOrdenadaAcotada<>(-1);
		} );
	}

	@Test
	public void getTest() {
		
		//Casos validos
		listaOrdenadaAcotada = new ListaOrdenadaAcotada(4);
		listaOrdenadaAcotada.add(6);
		listaOrdenadaAcotada.add(4);
		listaOrdenadaAcotada.add(9);
		listaOrdenadaAcotada.add(7);
		
		// Comprobamos que el tamaño maximo de la lista sea 1
		assertThrows(IllegalStateException.class, () -> {	
				listaOrdenadaAcotada.add(1);
		} );
		assertNotNull(listaOrdenadaAcotada);
		
		
		listaOrdenadaAcotada = new ListaOrdenadaAcotada<>(20);
		assertNotNull(listaOrdenadaAcotada);
		
		listaOrdenadaAcotada = new ListaOrdenadaAcotada<>(1000000);
		assertNotNull(listaOrdenadaAcotada);
		
		listaOrdenadaAcotada = new ListaOrdenadaAcotada<>(0);
		assertNotNull(listaOrdenadaAcotada);
		assertThrows(IllegalStateException.class, () -> {	
			listaOrdenadaAcotada.add(1);
		} );
		
		// Casos no validos
		
		assertThrows(NegativeArraySizeException.class, () -> {	
			listaOrdenadaAcotada = new ListaOrdenadaAcotada<>(-10000);
		} );
		
		
		assertThrows(NegativeArraySizeException.class, () -> {	
			listaOrdenadaAcotada = new ListaOrdenadaAcotada<>(-1);
		} );
	}
}
