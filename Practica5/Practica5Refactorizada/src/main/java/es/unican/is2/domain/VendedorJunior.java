package es.unican.is2.domain;

public class VendedorJunior extends Vendedor {
	
	private static final double PORCENTAJE_COMISION = 0.005;
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param dni
	 * @param tipo
	 */
	public VendedorJunior(String nombre, String id, String dni) {    
		super(nombre, id,dni);
		
	}
	
	@Override
	public void anhadeVenta(double importe)  {                       
		setComision(getComision()+ importe * PORCENTAJE_COMISION);
		totalVentas += importe;
	}	
	
	@Override
	public boolean equals(Object obj) {                     
		if (!(obj instanceof VendedorJunior))               
			return false;
		VendedorJunior v = (VendedorJunior) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni()));      
	}
}
