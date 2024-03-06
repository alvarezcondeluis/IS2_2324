package es.unican.is2.business;


import es.unican.is2.domain.Empleado;
import es.unican.is2.domain.Tienda;
import es.unican.is2.exceptions.DataAccessException;
import es.unican.is2.exceptions.OperacionNoValidaException;
import es.unican.is2.interfaces.IEmpleadosDAO;
import es.unican.is2.interfaces.IGestionEmpleados;
import es.unican.is2.interfaces.ITiendasDAO;


public class GestionEmpleados implements IGestionEmpleados{
	
	private ITiendasDAO tiendas;
	private IEmpleadosDAO empleados;
	
	public GestionEmpleados(ITiendasDAO tiendasDAO, IEmpleadosDAO empleadosDAO) {
		this.tiendas = tiendasDAO;
		this.empleados = empleadosDAO;
	}

	public Empleado nuevoEmpleado(Empleado e, String nombre) throws OperacionNoValidaException, DataAccessException {
		Tienda t = tiendas.tiendaPorNombre(nombre);
		if (t == null) {
			return null;
		}
		if (empleados.empleado(e.getDNI()) != null) {
			throw new OperacionNoValidaException("Empleado ya existe");
		};
		t.getEmpleados().add(e);
		empleados.crearEmpleado(e);
		
		return e;
	}

	public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean trasladarEmpleado(String dni, String actual, String destino)
			throws OperacionNoValidaException, DataAccessException {
		Empleado e = empleados.empleado(dni);
		Tienda actualTienda = tiendas.tiendaPorNombre(actual);
		Tienda destinoTienda = tiendas.tiendaPorNombre(destino);
		
		if (e == null || actualTienda == null || destinoTienda == null) {
			return false;
		}
		
		if (!actualTienda.getEmpleados().contains(e)) {
			throw new OperacionNoValidaException("El empleado no pertenece a la tienda");
		}
		actualTienda.getEmpleados().remove(e);
		destinoTienda.getEmpleados().add(e);
		return true;
	}

	public Empleado empleado(String dni) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
