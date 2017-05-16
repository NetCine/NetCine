package modelos;

public class Nomina {
	
	private double cantidad;
	private String dni, codNomina;
	
	
	public Nomina(double cantidad, String dni, String codNomina) {
		super();
		this.cantidad = cantidad;
		this.dni = dni;
		this.codNomina = codNomina;
	}


	public double getCantidad() {
		return cantidad;
	}


	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getCodNomina() {
		return codNomina;
	}


	public void setCodNomina(String codNomina) {
		this.codNomina = codNomina;
	}
	
	

}
