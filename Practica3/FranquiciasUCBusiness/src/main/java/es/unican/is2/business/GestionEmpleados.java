package es.unican.is2.business;


import es.unican.is2.domain.Empleado;
import es.unican.is2.exceptions.DataAccessException;
import es.unican.is2.exceptions.OperacionNoValidaException;
import es.unican.is2.interfaces.IEmpleadosDAO;
import es.unican.is2.interfaces.IGestionEmpleados;
import es.unican.is2.interfaces.ITiendasDAO;


public class GestionEmpleados implements IGestionEmpleados{

	public GestionEmpleados(ITiendasDAO tiendasDAO, IEmpleadosDAO empleadosDAO) {
		// TODO Auto-generated constructor stubsfd
	}

	public Empleado nuevoEmpleado(Empleado e, String nombre) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean trasladarEmpleado(String dni, String actual, String destino)
			throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	public Empleado empleado(String dni) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
