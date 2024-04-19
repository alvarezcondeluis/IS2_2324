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
	private String nombre;

	private String datos;

	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 * @param datos Path absoluto del fichero de datos
	 * @throws DataAccessException 
	 */
	public Tienda(String datos) { 
		this.datos = datos;
		
		try {
			this.inicializaDatos();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	/**
	 * Retorna la direccion de la tienda
	 * @return Direccion de la tienda
	 */
	public String direccion() {  
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * @return Nombre de la tienda
	 */
	public String nombre() {
		return nombre;
	}

	/**
	 * Anhade un nuevo vendedor a la tienda
	 * @param nuevo El vendedor a anhadir
	 * @return true si el vendedor se ha anhadido 
	 *         false si ya existe el vendedor
	 * @throws DataAccessException 
	 */
	public boolean anhadeVendedor(Vendedor nuevo) throws DataAccessException { 
		Vendedor v = buscaVendedor(nuevo.getId());
		if (v != null) {                                          
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
	public boolean eliminaVendedor(String id) throws DataAccessException  {  
		Vendedor v = buscaVendedor(id);
		if (v == null) {                                                  
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
	public boolean anhadeVenta(String id, double importe) throws DataAccessException { 
		Vendedor v = buscaVendedor(id);
		if (v == null) {                                     
			return false;
		}
		
		v.anhadeVenta(importe);
		vuelcaDatos();
		return true;
	}
	
	private void inicializaDatos () throws DataAccessException {
		vendedores = new LinkedList<Vendedor>();
		Scanner in = null;
		try {
			// abre el fichero
			in = new Scanner(new FileReader(datos));
			// configura el formato de numeros
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();
			Vendedor ven = null;
			// lee los vendedores senior
			while (in.hasNext() && !in.next().equals("Junior")) {        

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
			while (in.hasNext() && !in.next().equals("Practicas")) {       
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
			while (in.hasNext()) {                             
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
		} catch (FileNotFoundException e) {            
			throw new DataAccessException();
		} finally {
			if (in != null) {                     
				in.close();
			}
		} // try
	}
	
	
	/**
	 * Retorna el vendedor con el id indicado
	 * 
	 * @param id Id del vendedor
	 * @return vendedor con ese dni o null si no existe ninguno
	 */
	private Vendedor buscaVendedor(String id) { 


		for (Vendedor v : vendedores) {         
			if (v.getId().equals(id)) {     
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
	public List<Vendedor> getVendedores() {  
	
		return vendedores;

	}

	/**
	 * Actualiza el fichero datosTienda.txt con los datos actualizados de
	 * los vendedores
	 */
	private void vuelcaDatos() throws DataAccessException {               
		PrintWriter out = null;
		List<Vendedor> senior = new LinkedList<Vendedor>();
		List<Vendedor> junior = new LinkedList<Vendedor>();
		List<Vendedor> practicas = new LinkedList<Vendedor>();

		for (Vendedor v : vendedores) {                                   
			if (v instanceof VendedorEnPracticas) {                     
				practicas.add(v);
			} else if (v instanceof VendedorJunior) {                
	
					junior.add(v);
			} else                                                              
					senior.add(v);
			}
		

		try {

			out = new PrintWriter(new FileWriter(datos));

			out.println(nombre);
			out.println(direccion);
			out.println();
			out.println("Senior");
			for (Vendedor v : senior) {                                 
				
				out.println("  Nombre: " + v.getNombre() + " Id: " + v.getId() + " DNI: " + v.getDni()
						+ " TotalVentasMes: " + v.getTotalVentas() + " TotalComision: "+ v.getComision());
			}
			out.println();
			out.println("Junior");
			for (Vendedor v : junior) {                                
				
				out.println("  Nombre: " + v.getNombre() + " Id: " + v.getId() + " DNI: " + v.getDni()
						+ " TotalVentasMes: " + v.getTotalVentas() + " TotalComision: "+ v.getComision());
			}
			out.println();
			out.println("Practicas");
			for (Vendedor v : practicas) {                               
				
				out.println("  Nombre: " + v.getNombre() + " Id: " + v.getId() + " DNI: " + v.getDni()
						+ " TotalVentasMes: " + v.getTotalVentas());
			}
		} catch (IOException e) {                                       
			throw new DataAccessException();

		} finally {
			if (out != null)                                           
				out.close();
		}
	}

}
