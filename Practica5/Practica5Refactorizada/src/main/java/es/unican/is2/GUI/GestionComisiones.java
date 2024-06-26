package es.unican.is2.GUI;
import es.unican.is2.domain.*;
import es.unican.is2.exceptions.*;
import java.util.Collections;

import java.util.LinkedList;
import java.util.List;

import fundamentos.Menu;
import fundamentos.Lectura;
import fundamentos.Mensaje;

/**
 * Gestion de las comisiones de vendedores de una tienda
 */
public class GestionComisiones {

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) {                                     //WMC +1                  
		// opciones del menu
		final int NUEVA_VENTA = 0, VENDEDOR_DEL_MES = 1, VENDEDORES = 2;


		// crea la tienda
		Tienda tienda = new Tienda("C:\\temp\\datosTienda.txt");

		// crea la ventana de menu
		Menu menu = new Menu("Comisiones tienda");
		menu.insertaOpcion("Anhadir venta", NUEVA_VENTA);
		menu.insertaOpcion("Vendedor del mes", VENDEDOR_DEL_MES);
		menu.insertaOpcion("Vendedores por ventas", VENDEDORES);
		int opcion;
		boolean salir = false;
		// lazo de espera de comandos del usuario
		while (!salir) {  // Cambia la condición del bucle para que dependa de la variable de control
		    opcion = menu.leeOpcion();
		    
		    switch (opcion) {
		        case NUEVA_VENTA:
		            accionNuevaVenta(tienda);
		            break;
		        case VENDEDOR_DEL_MES:
		            accionVendedorDelMes(tienda);
		            break;
		        case VENDEDORES:
		            accionVendedores(tienda);
		            break;
		        default:
		            salir = true;  // Establece la variable de control en true para salir del bucle
		            break;
		    }
		}
	}

	/**
	 * Acciones que se realizan al pulsar boton Vendedores
	 */
	private static void accionVendedores(Tienda tienda) {                       //WMC +1 
		List<Vendedor> vendedores;
		String msj;
		vendedores = tienda.getVendedores();
		
		Collections.sort(vendedores, (o1, o2) -> {
		    if (o1.getTotalVentas() > o2.getTotalVentas())
		        return -1;
		    else if (o1.getTotalVentas() < o2.getTotalVentas())
		        return 1;
		    return 0;
		});
		msj = "";
		for (Vendedor vn : vendedores) {                                        //WMC +1  //CCOG +1                       
			msj += vn.getNombre() + " (" + vn.getId()+ ") "+vn.getTotalVentas() + "\n";
		}
		mensaje("VENDEDORES", msj);
	}

	/**
	 * Acciones que se realizan al pulsar boton Vendedor Del Mes
	 */
	private static void accionVendedorDelMes(Tienda tienda) {                    //WMC +1 
		List<Vendedor> vendedores;
		List<Vendedor> resultado;
		String msj;
		vendedores = tienda.getVendedores();
		resultado = new LinkedList<Vendedor>();
		double maxVentas = 0.0;
		for (Vendedor v : vendedores) {                                           //WMC +1     //CCOG +1           
			if (v.getTotalVentas() > maxVentas) {                                 //WMC +1    //CCOG +2
				maxVentas = v.getTotalVentas();
				resultado.clear();
				resultado.add(v);
			} else if (v.getTotalVentas() == maxVentas) {                        //WMC +1   //CCOG +1
				resultado.add(v);
			}
		}

		msj = "";
		for (Vendedor vn : resultado) {                                            //WMC +1     //CCOG +1          
			msj += vn.getNombre() + "\n";
		}
		mensaje("VENDEDORES DEL MES", msj);
	}

	
	/**
	 * Acciones que se realizan al pulsar boton NuevaVenta
	 */
	private static void accionNuevaVenta(Tienda tienda) {                        //WMC +1 
		String dni;
		Lectura lect;
		lect = new Lectura("Datos Venta");
		lect.creaEntrada("ID Vendedor", "");
		lect.creaEntrada("Importe", "");
		lect.esperaYCierra();
		dni = lect.leeString("ID Vendedor");
		double importe = lect.leeDouble("Importe");
		try {
			if (!tienda.anhadeVenta(dni, importe)) {                             //WMC +1   //CCOG +1  
				mensaje("ERROR", "El vendedor no existe");
			}
		} catch (DataAccessException e) {                                         //WMC +1     //CCOG +1  
			mensaje("ERROR", "No se pudo guardar el cambio");
		}
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo Titulo de la ventana
	 * @param txt    Texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) {                     //WMC +1       
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}

}
