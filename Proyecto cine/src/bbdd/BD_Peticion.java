package bbdd;

import java.sql.*;
import java.util.Vector;

import modelos.*;

/**
 * Clase para unir clase peticion con bbdd
 * @author cesar
 *
 */
public class BD_Peticion extends BD_Conector {
		private static Statement s;		
		private static ResultSet reg;
		
	public BD_Peticion(String file){
		super (file);
	}

	/**
	 * Metodo que nos devuelve un vector con todas las peticiones pendientes 
	 * @author cesar
	 * @return
	 */
	public  Vector <Peticion> listaPeticionesPendientes(){
		
		String cadena="SELECT * from peticion WHERE revisado=" + 0 + "";
		
		Vector <Peticion> peticionesPendientes = new Vector <Peticion> ();
		
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			while ( reg.next()){
				
				peticionesPendientes.add(new Peticion(reg.getString("codpeticion"), reg.getString("motivopeticion"), reg.getString("dniemple")));
			}
			s.close();
			this.cerrar();
			return peticionesPendientes;
		}
		catch ( SQLException e){		
			return null;			
		}
	}
	
	/**
	 * Metodo que pone como revisadas todas las peticiones
	 * @author cesar
	 * @return
	 */
	public int ponerPeticionesComoRevisadas(){
		
		String consulta = "UPDATE peticion SET revisado=" + 1 +"";
		
		try{
			String t="";
			this.abrir();
			s=c.createStatement();
			int filas=s.executeUpdate(consulta);							
			s.close();
			this.cerrar();
			return filas;
		}
		catch ( SQLException e){
	
			return -1;			
		}	
	
	}
	
	/**
	 * Metodo para aceptar una peticion cuyo codigo es el que le pasamos por parametro
	 * @author cesar
	 * @param codPeticion
	 * @return
	 */
	public int aceptarPeticion(String codPeticion){
		
		String consulta = "UPDATE peticion SET estado=" + 1 + " WHERE codpeticion='" + codPeticion +"'";
		
		try{
			String t="";
			this.abrir();
			s=c.createStatement();
			int filas=s.executeUpdate(consulta);							
			s.close();
			this.cerrar();
			return filas;
		}
		catch ( SQLException e){
	
			return -1;			
		}	
	
	}
	
}