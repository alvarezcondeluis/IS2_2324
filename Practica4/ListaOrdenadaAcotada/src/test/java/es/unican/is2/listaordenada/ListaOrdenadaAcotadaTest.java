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
	public void testAdd() {
		//Creo lista de tamaño 10
		listaOrdenadaAcotada = new ListaOrdenadaAcotada<>(10);
		//Creo variable elemento para las comprobaciones
		int elemento;
		
		//Casos validos
		listaOrdenadaAcotada.add(1);
		elemento = (int) listaOrdenadaAcotada.get(0);
		assertEquals(elemento, 1);
		
		listaOrdenadaAcotada.add(5);
		listaOrdenadaAcotada.add(10);
		listaOrdenadaAcotada.add(3);
		//Compruebo que el segundo elemento es 3
		elemento =  (int) listaOrdenadaAcotada.get(1);
		assertEquals(elemento, 3);
		//Compruebo que el tercer elemento es 5
		elemento =  (int) listaOrdenadaAcotada.get(2);
		assertEquals(elemento, 5);
		//Compruebo que el cuarto elemento es 10
		elemento =  (int) listaOrdenadaAcotada.get(3);
		assertEquals(elemento, 10);
		//Compruebo que al meter un valor que ya estaba se encuentran en su posicion
		listaOrdenadaAcotada.add(3);
		elemento =  (int) listaOrdenadaAcotada.get(1);
		assertEquals(elemento, 3);
		elemento =  (int) listaOrdenadaAcotada.get(2);
		assertEquals(elemento, 3);
		
		//Casos no validos
		assertThrows(NullPointerException.class, () -> {
			listaOrdenadaAcotada.add(null);
		});
		
		//Lleno la lista
		listaOrdenadaAcotada.add(4);
		listaOrdenadaAcotada.add(6);
		listaOrdenadaAcotada.add(7);
		listaOrdenadaAcotada.add(8);
		listaOrdenadaAcotada.add(9);
		//Compruebo que si meto un valor cuando esta llena salta error
		assertThrows(IllegalStateException.class, () -> {
			listaOrdenadaAcotada.add(50);
		});
		
		
	}

	@Test
	public void testGet() {
		
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
