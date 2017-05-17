package modelos;

public class Compra {
	private String CodCompra;
	private String CodCliente;
	private String CodSesion;
	private int NumEntradas;
	private double PrecioEntradas;
	
	public Compra(String codCompra, String codCliente, String codSesion,
			int numEntradas, double precioEntradas) {
		super();
		CodCompra = codCompra;
		CodCliente = codCliente;
		CodSesion = codSesion;
		NumEntradas = numEntradas;
		PrecioEntradas = precioEntradas;
	}

	public String getCodCompra() {
		return CodCompra;
	}

	public void setCodCompra(String codCompra) {
		CodCompra = codCompra;
	}

	public String getCodCliente() {
		return CodCliente;
	}

	public void setCodCliente(String codCliente) {
		CodCliente = codCliente;
	}

	public String getCodSesion() {
		return CodSesion;
	}

	public void setCodSesion(String codSesion) {
		CodSesion = codSesion;
	}

	public int getNumEntradas() {
		return NumEntradas;
	}

	public void setNumEntradas(int numEntradas) {
		NumEntradas = numEntradas;
	}

	public double getPrecioEntradas() {
		return PrecioEntradas;
	}

	public void setPrecioEntradas(double precioEntradas) {
		PrecioEntradas = precioEntradas;
	}

	@Override
	public String toString() {
		return "Compra [CodCompra=" + CodCompra + ", CodCliente=" + CodCliente
				+ ", CodSesion=" + CodSesion + ", NumEntradas=" + NumEntradas
				+ ", PrecioEntradas=" + PrecioEntradas + "]";
	}
	
}
