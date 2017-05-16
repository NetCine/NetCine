package modelos;

/**
 * Clase para lo refente al objeto usuario del cine
 * @author cesar
 *
 */

public class Usuario {

	private String codUsuario, correo, clave;

	
	/**
	 * Constructor para Usuario
	 * @author cesar
	 * @param codUsuario
	 * @param correo
	 * @param clave
	 */
	
	public Usuario(String codUsuario, String correo, String clave) {
		super();
		this.codUsuario = codUsuario;
		this.correo = correo;
		this.clave = clave;
	}

	/**
	 * 
	 * @author cesar
	 * @param correo
	 * @param clave
	 */
	public Usuario(String correo, String clave){
		
		this.correo=correo;
		this.clave=clave;
		
	}
	
	/**
	 * @author cesar
	 * @return the codUsuario
	 */
	public String getCodUsuario() {
		return codUsuario;
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
	 * @param codUsuario the codUsuario to set
	 */
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
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
	
	
	
}
