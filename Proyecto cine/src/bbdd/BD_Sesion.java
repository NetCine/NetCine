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
	/**
	 * Metodo para Sacar el codigo de sesion una vez introducido el codigo de pelicula (REALIZAR COMPRAS)
	 * @author Javier
	 * @return
	 */
	public String GetterCodSesion(String CodPel){
		
		String consulta = "SELECT codsesion FROM sesion WHERE '"+CodPel+"'= codpelicula";
		String cod="";
		try{
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
	 * Metodo para Sacar la cantidad de butacas que quedan en la sesion.
	 * @author Javier
	 * @return
	 */
	public int NumeroButacasRestantes(String CodSesion){
		
		String consulta = "SELECT numbutacastotal FROM sesion WHERE '"+CodSesion+"'= codsesion";
		int cod=0;
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(consulta);
			if ( reg.next())							
				cod= reg.getInt(1);						
			s.close();
			this.cerrar();
			return cod;
		}
		catch ( SQLException e){
	
			return -1;			
		}	
	}
	/**
	 * Metodo para restar las butacas una vez se ha comprobado que la compra es valida
	 * @author Javier
	 * @return
	 */
	public int RestaButacas(int buta){
		String consulta = "UPDATE sesion WHERE numbutacastotal - '"+buta+"'";
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(consulta);				
			s.close();
			this.cerrar();
			return 1;
		}
		catch ( SQLException e){
	
			return -1;			
		}	
	}
}