package bbdd;

import java.sql.*;
import java.util.Vector;

import modelos.*;


public class BD_Pelicula extends BD_Conector {
		private static Statement s;		
		private static ResultSet reg;
		
	public BD_Pelicula(String file){
		super (file);
	}

	
	/**
	 * Metodo que borra una pelicula
	 * @author cesar
	 * @param codigoPelicula
	 * @return
	 */
	public int borrarPelicula(String codigoPelicula){
		
		String consulta = "DELETE FROM pelicula WHERE codpelicula='" + codigoPelicula + "'";	
		
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
	 * Metodo para aņadir una pelicula nueva
	 * @author cesar
	 * @param pl
	 * @return
	 */
	public  boolean aņadirPelicula(Pelicula pl){
		
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
	/**
	 * Funcion que Devuelve la cartelera entera
	 * @author Javier
	 * @return
	 */	
	public  Vector<Pelicula> listadoPeliculas(){
		String cadenaSQL="SELECT * FROM pelicula";
		Vector<Pelicula> listaPelis=new Vector<Pelicula>();
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			while ( reg.next()){
				listaPelis.add(new Pelicula(reg.getString("nombre"),reg.getString("codpelicula")));
			}
			s.close();
			this.cerrar();
			return listaPelis;
		}
		catch ( SQLException e){		
			return null;			
		}
	}
	/**
	 * Funcion que Suma el numero de butacas compradas al numero de butacas que lleva en total la pelicula
	 * @author Javier
	 * @return
	 */	
	public int SumaPelis(int entradas, String codPelicula){
		String consulta = "UPDATE pelicula SET totalvendidas = totalvendidas + '"+entradas+"' WHERE codpelicula = '"+codPelicula+"'";
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
	 * Funcion que resta el numero de butacas compradas al numero de butacas que lleva en total la pelicula(EDITAR COMPRA)
	 * @author Javier
	 * @return
	 */	
	public int RestaPelis(int entradas, String codPelicula){
		String consulta = "UPDATE pelicula SET totalvendidas = totalvendidas - '"+entradas+"' WHERE codpelicula = '"+codPelicula+"'";
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
	 * Funcion que Devuelve la cartelera entera
	 * @author Javier
	 * @return
	 */	
	public String PeliTaquillera(){
		String cadenaSQL="SELECT nombre, MAX(totalvendidas) FROM pelicula";
		String nombre="";
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			while ( reg.next()){
				nombre= reg.getString(1);
			}
			s.close();
			this.cerrar();
			return nombre;
		}
		catch ( SQLException e){		
			return null;			
		}
	}
}