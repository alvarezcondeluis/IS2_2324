package es.unican.is2.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.*;

public class EmpleadoTest {
	
	private Empleado empleado;
	
	@BeforeEach
	public void setUp() throws Exception {
		empleado = new Empleado("72207836G", "Luis", Categoria.ENCARGADO, LocalDate.parse("2015-08-03"));
	}
	
	@Test
	public void testConstructor() {
		//Casos Validos
		assertEquals("72207836G", empleado.getDNI());
		assertEquals("Luis", empleado.getNombre());
		assertEquals(Categoria.ENCARGADO, empleado.getCategoria());
		assertEquals(LocalDate.parse("2015-08-03"), empleado.getFechaContratacion());
		
		empleado = new Empleado("72207836G", "Juanito", Categoria.AUXILIAR, LocalDate.now().minusDays(1));
		assertEquals("72207836G", empleado.getDNI());
		assertEquals("Juanito", empleado.getNombre());
		assertEquals(Categoria.AUXILIAR, empleado.getCategoria());
		assertEquals(LocalDate.now().minusDays(1), empleado.getFechaContratacion());
		
		empleado = new Empleado("13778450R", "Pepe", Categoria.VENDEDOR, LocalDate.now());
		assertEquals("13778450R", empleado.getDNI());
		assertEquals("Pepe", empleado.getNombre());
		assertEquals(Categoria.VENDEDOR, empleado.getCategoria());
		assertEquals(LocalDate.now(), empleado.getFechaContratacion());
		
		
		//Casos No Validos
		assertThrows(IllegalArgumentException.class, 
				() -> new Empleado(null, "Luis", Categoria.ENCARGADO, LocalDate.parse("2015-08-03")));
		assertThrows(IllegalArgumentException.class, 
				() -> new Empleado("", "Luis", Categoria.ENCARGADO, LocalDate.parse("2015-08-03")));
		assertThrows(IllegalArgumentException.class, 
				() -> new Empleado("72207836G", null, Categoria.ENCARGADO, LocalDate.parse("2015-08-03")));
		assertThrows(IllegalArgumentException.class, 
				() -> new Empleado("72207836G", "", Categoria.ENCARGADO, LocalDate.parse("2015-08-03")));
		assertThrows(IllegalArgumentException.class, 
				() -> new Empleado("72207836G", "Luis", null, LocalDate.parse("2015-08-03")));
		assertThrows(IllegalArgumentException.class, 
				() -> new Empleado("72207836G", "Luis", Categoria.ENCARGADO, LocalDate.now().plusDays(1)));		
	}
	
	@Test
	public void testSueldoBruto() {
		
		empleado.setFechaContratacion(LocalDate.now().minusYears(21));
		assertEquals(2200, empleado.sueldoBruto());
		
		// NO FUNCIONA. HAY Q REPARAR LOS SETTERS Y LA FUNCION calcularComplemento que tampoco funciona bien 
		empleado.setCategoria(Categoria.VENDEDOR);
		empleado.darDeBaja();
		empleado.setFechaContratacion(LocalDate.now().minusYears(20));
		assertEquals(1200, empleado.sueldoBruto());
		
		empleado.setCategoria(Categoria.AUXILIAR);
		empleado.setFechaContratacion(LocalDate.now().minusYears(15));
		empleado.darDeBaja();
		assertEquals(825, empleado.sueldoBruto());
		
		empleado.setCategoria(Categoria.ENCARGADO);
		empleado.darDeBaja();
		empleado.setFechaContratacion(LocalDate.now().minusYears(10));
		assertEquals(1537.5, empleado.sueldoBruto());
		
		empleado.setCategoria(Categoria.AUXILIAR);
		empleado.darDeAlta();
		empleado.setFechaContratacion(LocalDate.now().minusYears(6));
		assertEquals(1050, empleado.sueldoBruto());
		
		empleado.setCategoria(Categoria.VENDEDOR);
		empleado.setFechaContratacion(LocalDate.now().minusYears(5));
		assertEquals(1500, empleado.sueldoBruto());
		
		empleado.setCategoria(Categoria.ENCARGADO);
		empleado.setFechaContratacion(LocalDate.now().minusYears(2));
		assertEquals(2000, empleado.sueldoBruto());
		
		empleado.setCategoria(Categoria.AUXILIAR);
		empleado.setFechaContratacion(LocalDate.now());
		assertEquals(1000, empleado.sueldoBruto());
	}

}
