package bbdd;

import java.sql.*;

import modelos.*;

/**
 * Clase para lo refente al objeto nomina de los empleados del cine
 * @author cesar
 *
 */
public class BD_Nomina extends BD_Conector {
		private static Statement s;		
		private static ResultSet reg;
		
	public BD_Nomina(String file){
		super (file);
	}

	/**
	 * Metodo para crear una nueva nomina
	 * @author cesar
	 * @param nom
	 * @return
	 */
	public  boolean añadirNomina(Nomina nom){
		
		String consulta = "INSERT INTO nomina VALUES('" + nom.getCantidad() + "','" +nom.getDni()+"','"  + nom.getCodNomina() +  "')";
		
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
	 * Metodo para borrar una nomina que se pasa por parametro
	 * @author cesar
	 * @param nom
	 * @return
	 */
	public int borrarNomina(Nomina nom){
		
		String consulta = "DELETE FROM nomina WHERE cantidad='" +  nom.getCantidad() + "' AND dni='" + nom.getDni() +"' AND codnomina='" + nom.getCodNomina() + "'";	
		
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
	 * Funcion que nos da el codigo de una nomina en funcion de la cantidad y el dni
	 * @author cesar
	 * @param nom
	 * @return
	 */
	public String buscarNomina(Nomina nom){ // Devolvemos el codigo de la nomina
		
		String consulta = "SELECT codnomina FROM nomina WHERE  cantidad='" + nom.getCantidad() +"' AND dni='" + nom.getDni() + "'";
		
		try{
			String t="";
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(consulta);
			if ( reg.next())							
				t= reg.getString(1); //cogemos el codigo de la nomina para devolverle							
			s.close();
			this.cerrar();
			return t;
		}
		catch ( SQLException e){
	
			return null;
			
		}
	
	}
	
	/**
	 * Funcion que nos da la cantidad de nominas que tenemos en el sistema
	 * @author cesar
	 * @return
	 */
	public int contadorNominas(){
		
		String consulta = "SELECT COUNT(codnomina) FROM nomina";
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