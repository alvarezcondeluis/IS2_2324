package es.unican.is2.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.*;

class TiendaITest {
 
	Tienda tienda;
	@BeforeEach
	public void setUp() throws Exception {
		tienda = new Tienda("Lupa", "Calle Valdenoja 38");
	} 
	
	@Test
	public void testConstructor() {
		//Casos validos
		assertEquals("Lupa", tienda.getNombre());
		assertEquals("Calle Valdenoja 38", tienda.getDireccion());
		//Casos no validos
		assertThrows(IllegalArgumentException.class, 
				() -> new Tienda(null, "Calle Valdenoja  38"));
		assertThrows(IllegalArgumentException.class, 
				() -> new Tienda("", "Calle Valdenoja  38"));
		assertThrows(IllegalArgumentException.class, 
				() -> new Tienda("Lupa", null));
		assertThrows(IllegalArgumentException.class, 
				() -> new Tienda("Lupa", ""));
	}
	
	@Test
	public void testGastoMensualSueldos() {
		//Casos validos
		
		//Lista de empleados vacía (Sin empleados)
		assertEquals(0.0, tienda.gastoMensualSueldos());
		//Lista de empleados con 1 empleado
		Empleado e1 = new Empleado("72207836G", "Luis", Categoria.ENCARGADO, LocalDate.now().minusYears(21));
		tienda.anhadeEmpleado(e1);
		assertEquals(2200.0, tienda.gastoMensualSueldos());
		//Lista de empleados con varios empleados
		Empleado e2 = new Empleado("72254336A", "Adrian", Categoria.AUXILIAR, LocalDate.now().minusYears(6));
		tienda.anhadeEmpleado(e2);
		Empleado e3 = new Empleado("72347836G", "Paco", Categoria.VENDEDOR, LocalDate.now().minusYears(5));
		tienda.anhadeEmpleado(e3);
		assertEquals(4750.0, tienda.gastoMensualSueldos());
	}

}
