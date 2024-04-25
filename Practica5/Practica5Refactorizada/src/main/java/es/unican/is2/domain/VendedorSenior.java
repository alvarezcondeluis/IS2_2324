package es.unican.is2.domain;

public class VendedorSenior extends Vendedor {
	
	private static final double PORCENTAJE_COMISION = 0.01;
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param dni
	 * @param tipo
	 */
	public VendedorSenior(String nombre, String id, String dni) {                        //WMC +1 
		super(nombre, id,dni);
		
	}
	
	@Override
	public void anhadeVenta(double importe)  {                                           //WMC +1 
		setComision(getComision()+ importe * PORCENTAJE_COMISION);
		totalVentas += importe;
	}	
	
	@Override
	public boolean equals(Object obj) {                                                  //WMC +1 
		if (!(obj instanceof VendedorSenior))                                            //WMC +1 
			return false;
		VendedorSenior v = (VendedorSenior) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni()));      
	}
}
