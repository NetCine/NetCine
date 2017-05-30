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
	/**
	 * Metodo para sacar la ultima compra de un cliente 
	 * @author Javier
	 * @return 
	 */
	public String UltimaCompraCliente(String codCliente){
		
		String consulta = "SELECT MAX(SUBSTR(codcompra,3,4)) FROM compras WHERE codcliente = '"+codCliente+"'";
		String cod="";
		try{
			String t="";
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(consulta);
			if ( reg.next())							
				cod= reg.getString(1);						
			s.close();
			this.cerrar();
			return cod;
		}
		catch ( SQLException e){
			return null;			
		}	
	}
	/**
	 * Metodo para sacar las entradas compradas para poder restarlas en el campo peliculas y sumarlas en la tabla sesion. 
	 * @author Javier
	 * @return 
	 */
	public int CantidadUltimaCompra(String codCompra){
		
		String consulta = "SELECT numentradas FROM compras WHERE codcompra = '"+codCompra+"'";
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
	/**
	 * Metodo para sacar el precio de las entradas. 
	 * @author Javier
	 * @return 
	 */
	public double precioUltimaCompra(String codCompra){
		
		String consulta = "SELECT precioentradas FROM compras WHERE codcompra = '"+codCompra+"'";
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
	/**
	 * Funcion que devuelve el codigo de sesion de la ultima compra (EDITAR COMPRA) 
	 * @author Javier
	 * @return 
	 */
	public String CodSesionUltimaCompra(String codCompra){
		
		String consulta = "SELECT codsesion FROM compras WHERE codcompra = '"+codCompra+"'";
		String cod="";
		try{
			String t="";
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(consulta);
			if ( reg.next())							
				cod= reg.getString(1);						
			s.close();
			this.cerrar();
			return cod;
		}
		catch ( SQLException e){
			return "A";			
		}	
	}
	/**
	 * Funcion que resta el numero de butacas compradas y el dinero que costaban estas(EDITAR COMPRA)
	 * @author Javier
	 * @return
	 */	
	public int RestaButacasyDinero(int entradas, String codCompra, double precio){
		String consulta = "UPDATE compras SET numentradas = numentradas - "+entradas+" , precioentradas = precioentradas - "+precio+" WHERE codcompra = '"+codCompra+"'";
		try{
			this.abrir();
			s=c.createStatement();
			int n=s.executeUpdate(consulta);				
			s.close();
			this.cerrar();
			return n;
		}
		catch ( SQLException e){
			return -1;			
		}	
	}
	/**
	 * Funcion que suma el numero de butacas compradas y el dinero que cuestan estas(EDITAR COMPRA)
	 * @author Javier
	 * @return
	 */	
	public int SumaButacasyDinero(int entradas, String codCompra, double precio){
		String consulta = "UPDATE compras SET numentradas = numentradas + "+entradas+" , precioentradas = precioentradas + "+precio+" WHERE codcompra = '"+codCompra+"'";
		try{
			this.abrir();
			s=c.createStatement();
			int n=s.executeUpdate(consulta);				
			s.close();
			this.cerrar();
			return n;
		}
		catch ( SQLException e){
	
			return -1;			
		}	
	}
	/**
	 * Funcion para editar el CodSesion de una compra ya realizada(EDITAR PELI)
	 * @author Javier
	 * @return
	 */	
	public int EditarNumSesion(String codSesion, String codCompra){
		String consulta = "UPDATE compras SET codsesion = '"+codSesion+"'  WHERE codcompra = '"+codCompra+"'";
		try{
			this.abrir();
			s=c.createStatement();
			int n=s.executeUpdate(consulta);				
			s.close();
			this.cerrar();
			return n;
		}
		catch ( SQLException e){
			return -1;			
		}	
	}
	
}