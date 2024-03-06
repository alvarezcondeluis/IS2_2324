package es.unican.is2.business;


import es.unican.is2.domain.Tienda;
import es.unican.is2.exceptions.DataAccessException;
import es.unican.is2.exceptions.OperacionNoValidaException;
import es.unican.is2.interfaces.IGestionTiendas;
import es.unican.is2.interfaces.ITiendasDAO;

public class GestionTiendas implements IGestionTiendas {
	
	private ITiendasDAO tiendas;
	
	public GestionTiendas(ITiendasDAO tiendasDAO) {
		this.tiendas = tiendasDAO;
	}

	
	public Tienda nuevaTienda(Tienda t) throws DataAccessException {
		
		return tiendas.tienda(t.getId());
	}

	public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {
		Tienda tienda = tiendas.tiendaPorNombre(nombre);
		
		if (tienda == null) {
			return null;
		} else {
			if (tienda.getEmpleados().size() > 0) {
				throw new OperacionNoValidaException("Tienda tiene empleados");
			}
			
			return tiendas.eliminarTienda(tienda.getId());
		}
		
	}

	public Tienda tienda(String nombre) throws DataAccessException {
		
		return tiendas.tiendaPorNombre(nombre);
	}

}
