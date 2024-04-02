package es.unican.is2.vistas;


import org.fest.swing.fixture.FrameFixture; 
import org.fest.swing.fixture.JListFixture;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import es.unican.is2.DAO.EmpleadosDAO;
import es.unican.is2.DAO.TiendasDAO;
import es.unican.is2.business.GestionEmpleados;
import es.unican.is2.business.GestionTiendas;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VistaGerenteITest {
	
	private FrameFixture demo;
	
	
	
	@BeforeAll
	public void setUp() {
		TiendasDAO tiendasDAO = new TiendasDAO();
		EmpleadosDAO empleadosDAO = new EmpleadosDAO();
		
		// Crear componentes capa negocio
		GestionTiendas gTiendas = new GestionTiendas(tiendasDAO);
		GestionEmpleados gEmpleados = new GestionEmpleados(tiendasDAO, empleadosDAO);
		
		// Crear componentes capa presentacion
		VistaGerente gui = new VistaGerente(gTiendas, gEmpleados);
		demo = new FrameFixture(gui);
		gui.setVisible(true);
		
	}
	
	@AfterAll
	public void tearDown() {
		demo.cleanUp();
	}
	
	@Test
	public void test() {
		// Comprobamos que la interfaz tiene el aspecto deseado
		demo.button("btnBuscar").requireText("Buscar");
		
		//  Caso de prueba 1 Caso valido tienda con empleados
		
		// Escribimos un nombre
		
		demo.textBox("txtNombreTienda").enterText("Tienda A");
		// Pulsamos el bot�n
		demo.button("btnBuscar").click();	
		
		// Comprobamos la salida
		demo.textBox("txtDireccionTienda").requireText("Direccion A");
		
		demo.list("listNombreEmpleados").requireItemCount(3);
		JListFixture listaEmpleados = demo.list("listNombreEmpleados");
		
		String[] empleadosEsperados = {"Juan Pérez", "María Rodríguez", "Luis Martínez"};
	    
	    listaEmpleados.requireSelectedItems(empleadosEsperados);
	   
		
		demo.textBox("txtTotalContribuyente").requireText("4362.5");
		
		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		
		
		demo.textBox("txtNombreTienda").setText("");
		//  Caso de prueba 2 Caso valido tienda con un empleado
		
		// Escribimos un nombre
		
		demo.textBox("txtNombreTienda").enterText("Tienda B");
		// Pulsamos el bot�n
		demo.button("btnBuscar").click();	
		
		// Comprobamos la salida
		demo.textBox("txtDireccionTienda").requireText("Direccion B");
		
		demo.list("listNombreEmpleados").requireItemCount(1);
		JListFixture listaEmpleados = demo.list("listNombreEmpleados");
		
		String[] empleadosEsperados = {"Lucía Ibáñez"};
	    
	    listaEmpleados.requireSelectedItems(empleadosEsperados);
	   
		
		demo.textBox("txtTotalContribuyente").requireText("2100.0");
		
		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() {
		
		
		demo.textBox("txtNombreTienda").setText("");
		//  Caso de prueba 2 Caso valido tienda con un empleado
		
		// Escribimos un nombre
		
		demo.textBox("txtNombreTienda").enterText("Tienda C");
		// Pulsamos el bot�n
		demo.button("btnBuscar").click();	
		
		// Comprobamos la salida
		demo.textBox("txtDireccionTienda").requireText("Direccion C");
		
		demo.list("listNombreEmpleados").requireItemCount(0);   		
		demo.textBox("txtTotalContribuyente").requireText("0.0");
		
		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test4() {
		
		
		demo.textBox("txtNombreTienda").setText("");
		//  Caso de prueba 2 Caso valido tienda con un empleado
		
		// Escribimos un nombre
		
		demo.textBox("txtNombreTienda").enterText("Tienda D");
		// Pulsamos el bot�n
		demo.button("btnBuscar").click();	
		
		// Comprobamos la salida
		demo.textBox("txtDireccionTienda").requireText("Tienda no existe");
		
		demo.list("listNombreEmpleados").requireItemCount(0);   		
		demo.textBox("txtTotalContribuyente").requireText("");
		
		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
