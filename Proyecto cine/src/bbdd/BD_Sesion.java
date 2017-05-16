package bbdd;

import java.sql.*;

import modelos.*;


public class BD_Sesion extends BD_Conector {
		private static Statement s;		
		private static ResultSet reg;
		
	public BD_Sesion(String file){
		super (file);
	}

	/**
	 * Metodo que borra todas las sesiones de una pelicula 
	 * @author cesar
	 * @param codigoPelicula
	 * @return
	 */
	public int borrarSesiones(String codigoPelicula){
		
		String consulta = "DELETE FROM sesion WHERE codpelicula='" +  codigoPelicula + "'";	
		
		try{
			this.abrir();
			s=c.createStatement();
			int filas=s.executeUpdate(consulta);	
			s.close();
			this.cerrar();
			return filas;
			
		}
		catch ( SQLException e){
			this.cerrar();
			return -1;
			}
	}
	
	
	/**
	 * Metodo para añadir una nueva sesion
	 * @author cesar
	 * @param pl
	 * @return
	 */
	
	public  boolean añadirSesion(Sesion ss){
		
		String consulta = "INSERT INTO pelicula VALUES('" + ss.getCodSesion() + "','"  + ss.getNumeroSala() + "','" + ss.getHora() + "','" + ss.getNumeroButacasTotal() + "','" + ss.getCodPelicula() + "')";
		
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
	 * Metodo para contar las sesiones que tenemos en la bbdd
	 * @author cesar
	 * @return
	 */
	public int contadorSesion(){
		
		String consulta = "SELECT COUNT(codsesion) FROM sesion";
		int numero=0;
		
		try{
			String t="";
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(consulta);
			if ( reg.next())							
				numero= reg.getInt(1); //cogemos el codigo del cliente para devolverle							
			s.close();
			this.cerrar();
			return numero;
		}
		catch ( SQLException e){
	
			return -1;			
		}	
	}
	
}