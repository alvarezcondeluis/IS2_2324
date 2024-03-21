package es.unican.is2.domain;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
/**
 * Clase que representa un empleado de la franquicia, 
 * con sus datos personales 
 * y su estado en la franquicia (baja y categoria)
 */
public class Empleado {
	
	private String DNI;
	private String nombre;
	private Categoria categoria;
	private LocalDate fechaContratacion;
	private boolean baja = false;
	private double sueldoBase = 0;
	
	public Empleado() {	}
	
	/**
	 * Constructor del empleado con DNI, nombre, categoria y fecha de contratacion.
	 * Por defecto, baja se inicializa a false. 
	 * @param DNI
	 * @param nombre
	 * @param categoria
	 * @param fechaContratacion
	 */
	public Empleado(String DNI, String nombre, Categoria categoria, LocalDate fechaContratacion) 
							throws IllegalArgumentException, NullPointerException {
		if (DNI == null || DNI == ""|| nombre == null || nombre == "" 
				|| fechaContratacion.compareTo(LocalDate.now()) > 0) {
			throw new IllegalArgumentException();
		} 
		this.nombre = nombre;
		this.DNI=DNI;
		this.categoria=categoria;
		this.fechaContratacion=fechaContratacion;
		if (this.categoria == Categoria.ENCARGADO) {
			this.sueldoBase = 2000;
		} else if (this.categoria == Categoria.VENDEDOR) {
			this.sueldoBase = 1500;
		} else if (this.categoria == Categoria.AUXILIAR) {
			this.sueldoBase = 1000;
		} else {
			System.out.println("Fallo de categoria");
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Retorna el sueldo bruto del empleado
	 */
	public double sueldoBruto() {
		double bruto = sueldoBase + this.calcularComplemento();
		if (this.baja) {
			bruto = bruto * 0.75; 
		}
		return bruto;
	}
	
	private double calcularComplemento() {
		LocalDate fechaActual = LocalDate.now();
		long añosTranscurridos = fechaContratacion.until(fechaActual, ChronoUnit.YEARS);
		if (añosTranscurridos > 5 && añosTranscurridos <= 10) {
			return 50;
		} else if (añosTranscurridos > 10 && añosTranscurridos <= 20) {
			return 100;
		} else {
			return 200;
		}
	}
	
	/** 
	 * Dar de baja al empleado
	 */
	public void darDeBaja() {
		this.baja=true;
	}
	
	/**
	 * Dar de alta al empleado
	 */
	public void darDeAlta() {
		this.baja=false;
	}
	
	
	/**
	 * Retorna el dni del vendedor
	 * @return id
	 */
	public String getDNI() {
		return DNI;
	}
	
	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Retorna la categoria del empleado
	 *  @return categoria
	 */
	public Categoria getCategoria () {
		return categoria;
	}
	
	/**
	 * Retorna la fecha de contrato
	 * @return Fecha de contratacion
	 */
	public LocalDate getFechaContratacion() {
		return fechaContratacion;
	}
	
	/**
	 * Retorna si el empleado est� de baja
	 * @return true si esta de baja
	 *         false si no lo esta
	 */
	public boolean getBaja() {
		return baja;
	}
		
	
	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setFechaContratacion(LocalDate fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	
	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
