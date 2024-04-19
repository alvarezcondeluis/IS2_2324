package es.unican.is2.domain;

public class VendedorSenior extends Vendedor {
	
	private static final double PORCENTAJE_COMISION = 0.01;
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param dni
	 * @param tipo
	 */
	public VendedorSenior(String nombre, String id, String dni) {    
		super(nombre, id,dni);
		
	}
	
	@Override
	public void anhadeVenta(double importe)  {  
		
		setComision(getComision()+ importe * PORCENTAJE_COMISION);
		totalVentas += importe;
	}	
	
	@Override
	public boolean equals(Object obj) {                     
		if (!(obj instanceof VendedorSenior))               
			return false;
		VendedorSenior v = (VendedorSenior) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni()));      
	}
}
