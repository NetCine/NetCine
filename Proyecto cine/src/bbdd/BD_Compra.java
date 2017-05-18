package bbdd;

import java.sql.*;

import modelos.*;
/**
 * Clase para lo refente al objeto compra
 * @author Javier
 *
 */

public class BD_Compra extends BD_Conector {
		private static Statement s;		
		private static ResultSet reg;
		
	public BD_Compra(String file){
		super (file);
	}
	/**
	 * Metodo para dar de alta una compra
	 * @author Javier
	 * @param Compr
	 * @return
	 */
	public  boolean añadirCompra(Compra Compr){
		
		String consulta = "INSERT INTO compras VALUES('" + Compr.getCodCompra() + "','" +Compr.getCodCliente()+"','"  + Compr.getCodSesion() +"','"  +Compr.getNumEntradas()+"','"  + Compr.getPrecioEntradas() +  "')";
		
		try{
			this.abrir();
			s=c.createStatement();
			s.executeUpdate(consulta);
			s.close();
			this.cerrar();
			return true;
		}
		catch ( SQLException e){
			this.cerrar();
			return false;
		}
	}
	/**
	 * Metodo para calcular el codigo de compra
	 * @author Javier
	 * @return
	 */
	public int contadorCompras(){
		
		String consulta = "SELECT COUNT(CodCompra) FROM compras";
		int numero=0;
		try{
			String t="";
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(consulta);
			if ( reg.next())							
				numero= reg.getInt(1);						
			s.close();
			this.cerrar();
			return numero;
		}
		catch ( SQLException e){
			return -1;			
		}	
}
	
	
	
}