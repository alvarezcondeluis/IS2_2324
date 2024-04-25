package es.unican.is2.domain;

public class VendedorJunior extends Vendedor {
	
	private static final double PORCENTAJE_COMISION = 0.005;
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param dni
	 * @param tipo
	 */
	public VendedorJunior(String nombre, String id, String dni) {                  //WMC +1 
		super(nombre, id,dni);
		
	}
	
	@Override
	public void anhadeVenta(double importe)  {                                    //WMC +1 
		setComision(getComision()+ importe * PORCENTAJE_COMISION);
		totalVentas += importe;
	}	
	
	@Override
	public boolean equals(Object obj) {                                         //WMC +1     
		if (!(obj instanceof VendedorJunior))                                   //WMC +1    //CCOG +1
			return false;
		VendedorJunior v = (VendedorJunior) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni()));     //CCOG +1 
	}
}
