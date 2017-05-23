package modelos;

/**
 * Clase para el objeto peticion
 * @author cesar
 *
 */
public class Peticion {

	private String codigoPeticion, motivoPeticion, dniEmpleado;
	private int estado, revisado;
	
	
	/**
	 * Metodo para crear una peticion nueva
	 * @author cesar
	 * @param codigoPeticion
	 * @param motivoPeticion
	 * @param dniEmpleado
	 */
	public Peticion(String codigoPeticion, String motivoPeticion, String dniEmpleado){
		super();
		this.codigoPeticion = codigoPeticion;
		this.motivoPeticion = motivoPeticion;
		this.dniEmpleado = dniEmpleado;
		this.estado = 0;
		this.revisado = 0;
	}


	/**
	 * @author cesar
	 * @return the codigoPeticion
	 */
	public String getCodigoPeticion() {
		return codigoPeticion;
	}


	/**
	 * @author cesar
	 * @param codigoPeticion the codigoPeticion to set
	 */
	public void setCodigoPeticion(String codigoPeticion) {
		this.codigoPeticion = codigoPeticion;
	}


	/**
	 * @author cesar
	 * @return the motivoPeticion
	 */
	public String getMotivoPeticion() {
		return motivoPeticion;
	}


	/**
	 * @author cesar
	 * @param motivoPeticion the motivoPeticion to set
	 */
	public void setMotivoPeticion(String motivoPeticion) {
		this.motivoPeticion = motivoPeticion;
	}


	/**
	 * @author cesar
	 * @return the dniEmpleado
	 */
	public String getDniEmpleado() {
		return dniEmpleado;
	}


	/**
	 * @author cesar
	 * @param dniEmpleado the dniEmpleado to set
	 */
	public void setDniEmpleado(String dniEmpleado) {
		this.dniEmpleado = dniEmpleado;
	}


	/**
	 * @author cesar
	 * @return the estado
	 */
	public int isEstado() {
		return estado;
	}


	/**
	 * @author cesar
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}


	/**
	 * @author cesar
	 * @return the revisado
	 */
	public int isRevisado() {
		return revisado;
	}


	/**
	 * @author cesar
	 * @param revisado the revisado to set
	 */
	public void setRevisado(int revisado) {
		this.revisado = revisado;
	}
	@Override
	/**
	 * toString clase peticion
	 * @author cesar
	 */
	public String toString() {
		return "codigo de peticion=" + codigoPeticion
				+ ", cuyo motivo de peticion es: " + motivoPeticion + ", pedida por el empleado con dni: "
				+ dniEmpleado + ", su estado es: " + estado + ", y su estado de revision: " + revisado
				+ "";
	}
		
}
