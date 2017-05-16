package bbdd;

import java.sql.*;

import modelos.*;


public class BD_Pelicula extends BD_Conector {
		private static Statement s;		
		private static ResultSet reg;
		
	public BD_Pelicula(String file){
		super (file);
	}

	
	
	/**
	 * Metodo para añadir una pelicula nueva
	 * @author cesar
	 * @param pl
	 * @return
	 */
	public  boolean añadirPelicula(Pelicula pl){
		
		String consulta = "INSERT INTO pelicula VALUES('" + pl.getNombrePelicula() + "','"  + pl.getCodigoPelicula() + "','" + pl.getTotalVendidas() + "')";
		
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
	 * Funcion que cuenta el numero de peliculas que tenemos (usada para generar los nuevos codigos)
	 * @author cesar
	 * @return
	 */
	public int contadorPeliculas(){
		
		String consulta = "SELECT COUNT(codpelicula) FROM pelicula";
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