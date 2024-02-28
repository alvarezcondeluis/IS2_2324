package es.unican.is2.business;


import es.unican.is2.domain.Tienda;
import es.unican.is2.exceptions.DataAccessException;
import es.unican.is2.exceptions.OperacionNoValidaException;
import es.unican.is2.interfaces.IGestionTiendas;
import es.unican.is2.interfaces.ITiendasDAO;

public class GestionTiendas implements IGestionTiendas {
	
	
	public GestionTiendas(ITiendasDAO tiendasDAO) {
		// TODO Auto-generated constructor stub
	}

	public Tienda nuevaTienda(Tienda t) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Tienda tienda(String nombre) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
