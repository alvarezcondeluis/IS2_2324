package es.unican.is2.domain;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import es.unican.is2.exceptions.DataAccessException;

/**
 * Clase que representa una tienda con un conjunto de vendedores. Gestiona las
 * ventas realizadas y las comisiones asignadas a cada vendedor. Los datos de la
 * tienda se almacenan en un fichero de texto que se pasa como parametro al
 * crear la tienda
 */
public class Tienda {

	private List<Vendedor> vendedores = new LinkedList<Vendedor>();
	private String direccion;
	private String nombreTienda;

	private String datos;

	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 * @param datos Path absoluto del fichero de datos
	 * @throws DataAccessException 
	 */
	public Tienda(String datos) {                  //WMC +1 
		this.datos = datos;
		
		try {
			this.inicializaDatos();
		} catch (DataAccessException e) {            //WMC +1  //CCoG +1
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Inicializa los datos que se encuentran almacenados en el fichero datosTienda.txt
	 */
	private void inicializaDatos () throws DataAccessException {           //WMC +1 
		vendedores = new LinkedList<Vendedor>();
		Scanner in = null; 
		try {
			
			// abre el fichero
			in = new Scanner(new FileReader(datos));
			
			// lee los vendedores 
			leerVendedoresPorTipo(in);
		} catch (FileNotFoundException e) {                           //WMC +1 //CCoG +1
			throw new DataAccessException();
		} finally {
			if (in != null) {                                      //WMC +1     //CCoG +1
				in.close();
			}
		} // try
	}
	
	private Scanner leerVendedoresPorTipo(Scanner in) throws FileNotFoundException {          //WMC +1 
		
		
		// configura el formato de numeros
		in.useLocale(Locale.ENGLISH);
		nombreTienda = in.nextLine();
		direccion = in.nextLine();
		in.next();
		Vendedor ven = null;
		// lee los vendedores senior
		while (in.hasNext() && !in.next().equals("Junior")) {                //WMC +2  //CCoG +2

			String nombre = in.next();
			in.next();
			String idIn = in.next();
			in.next();
			String dni = in.next();
			in.next();
			double totalVentas = in.nextDouble();
			in.next();
			double totalComision = in.nextDouble();
			ven = new VendedorSenior(nombre, idIn, dni);
			ven.setTotalVentas(totalVentas);
			ven.setComision(totalComision);
			vendedores.add(ven);
		}
		// lee los vendedores junior
		while (in.hasNext() && !in.next().equals("Practicas")) {             //WMC +2  //CCoG +2
			String nombre = in.next();
			in.next();
			String idIn = in.next();
			in.next();
			String dni = in.next();
			in.next();
			double totalVentas = in.nextDouble();
			in.next();
			double totalComision = in.nextDouble();
			ven = new VendedorJunior(nombre, idIn, dni);
			ven.setTotalVentas(totalVentas);
			ven.setComision(totalComision);
			vendedores.add(ven);
		}
		while (in.hasNext()) {                                               //WMC +1   //CCoG +1                 
			in.next();
			String nombre = in.next();
			in.next();
			String idIn = in.next();
			in.next();
			String dni = in.next();
			in.next();
			double totalVentas = in.nextDouble();
			ven = new VendedorEnPracticas(nombre, idIn, dni);
			ven.setTotalVentas(totalVentas);
			vendedores.add(ven);
		}
		
		return in;
	}

	/**
	 * Retorna la direccion de la tienda
	 * @return Direccion de la tienda
	 */
	public String direccion() {                                          //WMC +1 
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * @return Nombre de la tienda
	 */
	public String nombreTienda() {                                               //WMC +1 
		return nombreTienda;
	}

	
	/**
	 * Anhade un nuevo vendedor a la tienda
	 * @param nuevo El vendedor a anhadir
	 * @return true si el vendedor se ha anhadido 
	 *         false si ya existe el vendedor
	 * @throws DataAccessException 
	 */
	public boolean anhadeVendedor(Vendedor nuevo) throws DataAccessException {            //WMC +1 
		Vendedor v = buscaVendedor(nuevo.getId());
		if (v != null) {                                                //WMC +1    //CCoG +1                
			return false;
		}
		vendedores.add(nuevo);
		vuelcaDatos();
		return true;
	}

	
	/**
	 * Elimina el vendedor cuyo id se pasa como argumento
	 * @param id
	 * @return true si se elimina el vendedor false si no existe el vendedor
	 * @throws DataAccessException 
	 */
	public boolean eliminaVendedor(String id) throws DataAccessException  {                //WMC +1 
		Vendedor v = buscaVendedor(id);
		if (v == null) {                                                              //WMC +1  //CCoG +1                                    
			return false;
		}
		vendedores.remove(v);
		vuelcaDatos();
		return true;
	}

	
	/**
	 * Anhade una venta a un vendedor
	 * @param id      Id del vendedor
	 * @param importe Importe de la venta
	 * @return true si se anhade la venta false si no se encuentra el vendedor
	 */
	public boolean anhadeVenta(String id, double importe) throws DataAccessException {              //WMC +1 
		Vendedor v = buscaVendedor(id);
		if (v == null) {                                                                   //WMC +1   //CCoG +1                       
			return false;
		}
		v.anhadeVenta(importe);
		vuelcaDatos();
		return true;
	}
	
	
	
	/**
	 * Retorna el vendedor con el id indicado
	 * 
	 * @param id Id del vendedor
	 * @return vendedor con ese dni o null si no existe ninguno
	 */
	private Vendedor buscaVendedor(String id) {                    //WMC +1 
		for (Vendedor v : vendedores) {                      //WMC +1    //CCoG +1
			if (v.getId().equals(id)) {                        //WMC +1 //CCoG +2
				return v; 
			}
		}
		return null;
	}
	
	
	/**
	 * Retorna la lista de vendedores actuales de la tienda
	 * 
	 * @return La lista de vendedores
	 */
	public List<Vendedor> getVendedores() {                //WMC +1 
		return vendedores;
	}

	/**
	 * Actualiza el fichero datosTienda.txt con los datos actualizados de
	 * los vendedores
	 */
	private void vuelcaDatos() throws DataAccessException {                       //WMC +1         
		PrintWriter out = null;
		List<Vendedor> senior = new LinkedList<Vendedor>();
		List<Vendedor> junior = new LinkedList<Vendedor>();
		List<Vendedor> practicas = new LinkedList<Vendedor>();

		for (Vendedor v : vendedores) {                                            //WMC +1   //CCoG +1              
			if (v instanceof VendedorEnPracticas) {                               //WMC +1    //CCoG +2       
				practicas.add(v);
			} else if (v instanceof VendedorJunior) {                              //WMC +1   //CCoG +1 
					junior.add(v);
			} else                                                               //WMC +1    //CCoG +1                    
					senior.add(v);
			}
		

		try {
			out = escribirDatos(senior, junior, practicas);
		} catch (IOException e) {                                                 //WMC +1  //CCoG +1      
			throw new DataAccessException();

		} finally {
			if (out != null)                                                    //WMC +1  //CCoG +1                   
				out.close();
		}
	}

	
	/**
	 * Escribe los datos que se encuentan en las listas
	 */
	private PrintWriter escribirDatos(List<Vendedor> senior, List<Vendedor> junior, List<Vendedor> practicas)
			throws IOException {                                              //WMC +1 
		PrintWriter out;
		out = new PrintWriter(new FileWriter(datos));
		out.println(nombreTienda);
		out.println(direccion);
		out.println();
		out.println("Senior");
		pintarListasPlantilla(senior, out);
		out.println();
		out.println("Junior");
		pintarListasPlantilla(junior, out);
		out.println();
		out.println("Practicas");
		pintarListaPracticas(practicas, out);
		return out;
	}
	
	/**
	 * Pinta la lista de Vendedores en practicas
	 */
	private void pintarListaPracticas(List<Vendedor> lista, PrintWriter out) {                 //WMC +1 
		for (Vendedor v : lista) {                                                              //WMC +1 //CCoG +1
			out.println("  Nombre: " + v.getNombre() + " Id: " + v.getId() + " DNI: " + v.getDni()
					+ " TotalVentasMes: " + v.getTotalVentas());
		}
	}

	/**
	 * Pinta la lista de vendedores en plantilla 
	 */
	private void pintarListasPlantilla(List<Vendedor> lista, PrintWriter out) {                  //WMC +1 
		for (Vendedor v : lista) {                                                                //WMC +1 //CCoG +1
			out.println("  Nombre: " + v.getNombre() + " Id: " + v.getId() + " DNI: " + v.getDni()
					+ " TotalVentasMes: " + v.getTotalVentas() + " TotalComision: "+ v.getComision());
		}
	}

}
