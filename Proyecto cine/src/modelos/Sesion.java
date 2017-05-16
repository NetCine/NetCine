package modelos;

import java.time.*;
/**
 * Fichero para el objeto sesion
 * @author cesar
 *
 */
public class Sesion {
	
	private int numeroButacasTotal=40; // todas las salas parten con 40 plazas, este campo se decrementara al realizar una venta de entradas
	
	private String codSesion, codPelicula;
	private int numeroSala;
	private LocalDate hora;
	
	/**
	 * Constructor para la clase Sesion
	 * @author cesar
	 * @param codSesion
	 * @param codPelicula
	 * @param numeroSala
	 * @param hora
	 */
	public Sesion(String codSesion, String codPelicula, int numeroSala,	LocalDate hora) {
		super();
		this.codSesion = codSesion;
		this.codPelicula = codPelicula;
		this.numeroSala = numeroSala;
		this.hora = hora;
		this.numeroButacasTotal=40;
	}

	/**
	 * @author cesar
	 * @return the numeroButacasTotal
	 */
	public int getNumeroButacasTotal() {
		return numeroButacasTotal;
	}

	/**
	 * @author cesar
	 * @return the codSesion
	 */
	public String getCodSesion() {
		return codSesion;
	}

	/**
	 * @author cesar
	 * @param codSesion the codSesion to set
	 */
	public void setCodSesion(String codSesion) {
		this.codSesion = codSesion;
	}

	/**
	 * @author cesar
	 * @return the codPelicula
	 */
	public String getCodPelicula() {
		return codPelicula;
	}

	/**
	 * @author cesar
	 * @param codPelicula the codPelicula to set
	 */
	public void setCodPelicula(String codPelicula) {
		this.codPelicula = codPelicula;
	}

	/**
	 * @author cesar
	 * @return the numeroSala
	 */
	public int getNumeroSala() {
		return numeroSala;
	}

	/**
	 * @author cesar
	 * @param numeroSala the numeroSala to set
	 */
	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}

	/**
	 * @author cesar
	 * @return the hora
	 */
	public LocalDate getHora() {
		return hora;
	}

	/**
	 * @author cesar
	 * @param hora the hora to set
	 */
	public void setHora(LocalDate hora) {
		this.hora = hora;
	}

	
	
	
}
