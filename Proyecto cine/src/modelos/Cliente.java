package modelos;

import java.time.*;
import java.util.*;

/**
 * Clase para lo refente al objeto cliente  del cine
 * @author cesar
 *
 */

public class Cliente {

	private String codCLiente;
	private String nombre, apellidos, dni, telefono,correo, clave;
	private int numeroTarjeta, descuento;
	private LocalDate fechaNacimiento;
	
	
	/**
	 * Constructor cliente
	 * @author cesar
	 * @param codCLiente
	 * @param nombre
	 * @param apellidos
	 * @param dni
	 * @param telefono
	 * @param correo
	 * @param clave
	 * @param numeroTarjeta
	 * @param descuento
	 * @param fechaNacimiento
	 */
	
	public Cliente(String codCLiente, String nombre, String apellidos, String dni, String telefono, String correo,
			String clave, int numeroTarjeta, int descuento, LocalDate fechaNacimiento) {
		super();
		this.codCLiente = codCLiente;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
		this.correo = correo;
		this.clave = clave;
		this.numeroTarjeta = numeroTarjeta;
		this.descuento = descuento;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	/**
	 * Constructor 2 cliente
	 * @author cesar
	 * @param nombre
	 * @param apellidos
	 * @param dni
	 */
	public Cliente(String nombre, String apellidos, String dni){
		
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.dni=dni;
		
	}


	/**
	 * @author cesar
	 * @return the codCLiente
	 */
	public String getCodCLiente() {
		return codCLiente;
	}


	/**
	 * @author cesar
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @author cesar
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}


	/**
	 * @author cesar
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}


	/**
	 * @author cesar
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}


	/**
	 * @author cesar
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}


	/**
	 * @author cesar
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}


	/**
	 * @author cesar
	 * @return the numeroTarjeta
	 */
	public int getNumeroTarjeta() {
		return numeroTarjeta;
	}


	/**
	 * @author cesar
	 * @return the descuento
	 */
	public int getDescuento() {
		return descuento;
	}


	/**
	 * @author cesar
	 * @return the fechaNacimiento
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	/**
	 * @author cesar
	 * @param codCLiente the codCLiente to set
	 */
	public void setCodCLiente(String codCLiente) {
		this.codCLiente = codCLiente;
	}


	/**
	 * @author cesar
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @author cesar
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	/**
	 * @author cesar
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}


	/**
	 * @author cesar
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	/**
	 * @author cesar
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}


	/**
	 * @author cesar
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}


	/**
	 * @author cesar
	 * @param numeroTarjeta the numeroTarjeta to set
	 */
	public void setNumeroTarjeta(int numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}


	/**
	 * @author cesar
	 * @param descuento the descuento to set
	 */
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}


	/**
	 * @author cesar
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
	
	
}
