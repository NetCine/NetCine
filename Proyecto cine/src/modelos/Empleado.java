package modelos;

import java.util.*;
import java.time.*;

/**
 * Clase para lo refente al objeto empleado del cine
 * @author cesar
 *
 */

public class Empleado {
	
	private String codEmple, nombre, apellidos, dni, telefono, correo, clave, codNomina, funcion, descipcionPuesto;
	private LocalDate fecha;
	
	/**
	 * Constructor de empleado
	 * @author cesar
	 * @param codEmple
	 * @param nombre
	 * @param apellidos
	 * @param dni
	 * @param telefono
	 * @param correo
	 * @param clave
	 * @param codNomina
	 * @param funcion
	 * @param descipcionPuesto
	 * @param fecha
	 */
	public Empleado(String codEmple, String nombre, String apellidos, String dni, String telefono, String correo,
			String clave, String codNomina, String funcion, String descipcionPuesto, LocalDate fecha) {
		super();
		this.codEmple = codEmple;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
		this.correo = correo;
		this.clave = clave;
		this.codNomina = codNomina;
		this.funcion = funcion;
		this.descipcionPuesto = descipcionPuesto;
		this.fecha = fecha;
	}
	
	/**
	 * 
	 * @author cesar
	 * @param nombre
	 * @param apellidos
	 * @param dni
	 */
	public Empleado(String nombre, String apellidos, String dni){
		
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.dni=dni;
		
	}

	/**
	 * @author cesar
	 * @return the codEmple
	 */
	public String getCodEmple() {
		return codEmple;
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
	 * @return the codNomina
	 */
	public String getCodNomina() {
		return codNomina;
	}

	/**
	 * @author cesar
	 * @return the funcion
	 */
	public String getFuncion() {
		return funcion;
	}

	/**
	 * @author cesar
	 * @return the descipcionPuesto
	 */
	public String getDescipcionPuesto() {
		return descipcionPuesto;
	}

	/**
	 * @author cesar
	 * @return the fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * @author cesar
	 * @param codEmple the codEmple to set
	 */
	public void setCodEmple(String codEmple) {
		this.codEmple = codEmple;
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
	 * @param codNomina the codNomina to set
	 */
	public void setCodNomina(String codNomina) {
		this.codNomina = codNomina;
	}

	/**
	 * @author cesar
	 * @param funcion the funcion to set
	 */
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	/**
	 * @author cesar
	 * @param descipcionPuesto the descipcionPuesto to set
	 */
	public void setDescipcionPuesto(String descipcionPuesto) {
		this.descipcionPuesto = descipcionPuesto;
	}

	/**
	 * @author cesar
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	
	
	
	
	
}
