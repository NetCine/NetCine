package modelos;

/**
 * Fichero para el objeto pelicula
 * @author cesar
 *
 */
public class Pelicula {

	private static int totalVendidas; // Se trata de un estatico que se incrementara para cada entrada que se venda para una pelicula independientemente de en que sesion
	private String nombrePelicula;
	private String codigoPelicula;
	
	/**
	 * Metodo constructor para la clase pelicula
	 * @author cesar
	 * @param nombrePelicula
	 * @param codigoPelicula
	 */
	public Pelicula(String nombrePelicula, String codigoPelicula) {
		super();
		this.nombrePelicula = nombrePelicula;
		this.codigoPelicula = codigoPelicula;
		this.totalVendidas = 0; // Al crear la pelicula las entradas vendidas van a ser 0
	}

	/**
	 * @author cesar
	 * @return the totalVendidas
	 */
	public static int getTotalVendidas() {
		return totalVendidas;
	}

	/**
	 * @author cesar
	 * @param totalVendidas the totalVendidas to set
	 */
	public static void setTotalVendidas(int totalVendidas) {
		Pelicula.totalVendidas = totalVendidas;
	}

	/**
	 * @author cesar
	 * @return the nombrePelicula
	 */
	public String getNombrePelicula() {
		return nombrePelicula;
	}

	/**
	 * @author cesar
	 * @param nombrePelicula the nombrePelicula to set
	 */
	public void setNombrePelicula(String nombrePelicula) {
		this.nombrePelicula = nombrePelicula;
	}

	/**
	 * @author cesar
	 * @return the codigoPelicula
	 */
	public String getCodigoPelicula() {
		return codigoPelicula;
	}

	/**
	 * @author cesar
	 * @param codigoPelicula the codigoPelicula to set
	 */
	public void setCodigoPelicula(String codigoPelicula) {
		this.codigoPelicula = codigoPelicula;
	}
	
	
	
	
}
