package es.unican.is2.listaordenada;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ListaOrdenadaAcotadaTest {
	
	private ListaOrdenadaAcotada listaOrdenadaAcotada;
	
	@BeforeEach
	public void setUp() throws Exception {
		//Creacion de listas de capacidad maxima 10 para las comprobaciones
		listaOrdenadaAcotada = new ListaOrdenadaAcotada<>(10);
	}
	
	@Test
	public void testConstructor() {
		
		//Casos validos
		listaOrdenadaAcotada = new ListaOrdenadaAcotada<>(1);
		listaOrdenadaAcotada.add(1);
		
		// Comprobamos que el tamaño maximo de la lista sea 1
		assertThrows(IllegalStateException.class, () -> {	
				listaOrdenadaAcotada.add(1);
		} );
		assertNotNull(listaOrdenadaAcotada);
		
		
		listaOrdenadaAcotada = new ListaOrdenadaAcotada<>(20);
		for (int i = 0; i < 20; i++) {
			listaOrdenadaAcotada.add(i);
		}
		assertThrows(IllegalStateException.class, () -> {	
			listaOrdenadaAcotada.add(1);
		} );
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
	public void testGet() {
		
		//Casos validos
		listaOrdenadaAcotada = new ListaOrdenadaAcotada(4);
		listaOrdenadaAcotada.add(6);
		listaOrdenadaAcotada.add(4);
		listaOrdenadaAcotada.add(9);
		listaOrdenadaAcotada.add(7);
		
		assertTrue(listaOrdenadaAcotada.get(0).equals(4));
		assertTrue(listaOrdenadaAcotada.get(2).equals(7));
		assertTrue(listaOrdenadaAcotada.get(3).equals(9));
		
		
		// Casos no validos
		
		listaOrdenadaAcotada = new ListaOrdenadaAcotada(0);
		assertThrows(IndexOutOfBoundsException.class, () -> {	
			listaOrdenadaAcotada.get(0);
		} );
		
		listaOrdenadaAcotada = new ListaOrdenadaAcotada(4);
		listaOrdenadaAcotada.add(6);
		listaOrdenadaAcotada.add(4);
		listaOrdenadaAcotada.add(9);
		listaOrdenadaAcotada.add(7);
		
		assertThrows(IndexOutOfBoundsException.class, () -> {	
			listaOrdenadaAcotada.get(4);
		} );
		
		
		assertThrows(IndexOutOfBoundsException.class, () -> {	
			listaOrdenadaAcotada.get(-5);
		} );
		
		assertThrows(IndexOutOfBoundsException.class, () -> {	
			listaOrdenadaAcotada.get(10);
		} );
		
		
	}
	
	
	
	
	@Test
	public void testAdd() {
		//Creo variable elemento para las comprobaciones
		int elemento;
		
		//Casos validos
		//Meto un elemento y compruebo que es correcto 
		listaOrdenadaAcotada.add(1);
		elemento = (int) listaOrdenadaAcotada.get(0);
		assertEquals(1, elemento);
		//Meto mas elementos
		listaOrdenadaAcotada.add(5);
		listaOrdenadaAcotada.add(10);
		listaOrdenadaAcotada.add(3);
		//Compruebo que se ha ordenado
		//Compruebo que el segundo elemento es 3
		elemento =  (int) listaOrdenadaAcotada.get(1);
		assertEquals(3, elemento);
		//Compruebo que el tercer elemento es 5
		elemento =  (int) listaOrdenadaAcotada.get(2);
		assertEquals(5, elemento);
		//Compruebo que el cuarto elemento es 10
		elemento =  (int) listaOrdenadaAcotada.get(3);
		assertEquals(10, elemento);
		//Compruebo que al meter un valor que ya estaba se encuentran en su posicion
		listaOrdenadaAcotada.add(3);
		elemento =  (int) listaOrdenadaAcotada.get(1);
		assertEquals(3, elemento);
		elemento =  (int) listaOrdenadaAcotada.get(2);
		assertEquals(3, elemento);
		
		//Casos no validos
		//Compruebo cuando se mete un valor a NULL
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
	public void testRemove() {
		
		//Casos validos
		
		//Anhado elementos a la lista
		listaOrdenadaAcotada.add(4);
		listaOrdenadaAcotada.add(6);
		listaOrdenadaAcotada.add(7);
		listaOrdenadaAcotada.add(9);
		
		//Compruebo que el remove funciona para el primer elemento
		assertEquals(4, listaOrdenadaAcotada.remove(0));
		assertEquals(6, listaOrdenadaAcotada.get(0));
		assertEquals(7, listaOrdenadaAcotada.get(1));
		assertEquals(9, listaOrdenadaAcotada.get(2));
		
		//Vuelvo a añadir elementos para tener la lista [4, 6, 7, 9]
		listaOrdenadaAcotada.add(4);
		
		//Compruebo que funciona el remove para un elemento intermedio
		assertEquals(7, listaOrdenadaAcotada.remove(2));
		assertEquals(4, listaOrdenadaAcotada.get(0));
		assertEquals(6, listaOrdenadaAcotada.get(1));
		assertEquals(9, listaOrdenadaAcotada.get(2));
		
		//Vuelvo a añadir elementos para tener la lista [4, 6, 7, 9]
		listaOrdenadaAcotada.add(7);
		
		//Compruebo que funciona el remove para el ultimo elemento
		assertEquals(9, listaOrdenadaAcotada.remove(3));
		assertEquals(4, listaOrdenadaAcotada.get(0));
		assertEquals(6, listaOrdenadaAcotada.get(1));
		assertEquals(7, listaOrdenadaAcotada.get(2));
		
		
		//Casos no validos
		assertThrows(IndexOutOfBoundsException.class, () -> {
			listaOrdenadaAcotada.remove(4);
		});
		
		assertThrows(IndexOutOfBoundsException.class, () -> {
			listaOrdenadaAcotada.remove(-5);
		});
		
		assertThrows(IndexOutOfBoundsException.class, () -> {
			listaOrdenadaAcotada.remove(10);
		});
		
		//Vacio lista
		listaOrdenadaAcotada.clear();
		
		assertThrows(IndexOutOfBoundsException.class, () -> {
			listaOrdenadaAcotada.remove(0);
		});
	}

	
	@Test
	public void testSize() {
		//Variable para comprobar el tamanho de la lista
		int tamanho = 0;
		
		//Casos validos
		
		//Compruebo lista vacia
		tamanho = listaOrdenadaAcotada.size();
		assertEquals(0, tamanho);
		//Compruebo lista con 1 elemento
		listaOrdenadaAcotada.add(1);
		tamanho = listaOrdenadaAcotada.size();
		assertEquals(1, tamanho);
		//compruebo lista con 5 elementos
		listaOrdenadaAcotada.add(1);
		listaOrdenadaAcotada.add(1);
		listaOrdenadaAcotada.add(1);
		listaOrdenadaAcotada.add(1);
		tamanho = listaOrdenadaAcotada.size();
		assertEquals(5, tamanho);
		//Compruebo lista llena (10 elementos)
		listaOrdenadaAcotada.add(1);
		listaOrdenadaAcotada.add(1);
		listaOrdenadaAcotada.add(1);
		listaOrdenadaAcotada.add(1);
		listaOrdenadaAcotada.add(1);
		tamanho = listaOrdenadaAcotada.size();
		assertEquals(10, tamanho);
	}
	
	
	@Test
	public void testClear() {
		//Casos validos
		
		//Con una lista vacia
		listaOrdenadaAcotada.clear();
		//Compruebo que la lista esta vacia entonces al hacer get(0) lanza excepcion
		assertThrows(IndexOutOfBoundsException.class, () -> {
			listaOrdenadaAcotada.get(0);
		});
		
		//Con una lista con elementos
		//Anhado elementos
		listaOrdenadaAcotada.add(1);
		listaOrdenadaAcotada.add(1);
		//Compruebo que se han anhadido correctamente
		assertEquals(1, listaOrdenadaAcotada.get(0));
		//Vacio y compruebo que se ha vaciado
		listaOrdenadaAcotada.clear();
		//Compruebo que la lista esta vacia entonces al hacer get(0) lanza excepcion
		assertThrows(IndexOutOfBoundsException.class, () -> {
			listaOrdenadaAcotada.get(0);
		});
		
	}
	
}
