package bbdd;

import java.sql.*;
import modelos.*;

/**
 * 
 * @author cesar
 *
 */

public class BD_Empleado extends BD_Conector {
		private static Statement s;		
		private static ResultSet reg;

	/**
	 * 
	 * @author cesar
	 * @param file
	 */
	public BD_Empleado(String file){
		super (file);
	}

	/**
	 * 
	 * @author cesar
	 * @param usu
	 * @return
	 */
	public boolean aņadirEmpleado(Empleado emple){
		String consulta = "INSERT INTO empleado VALUES('" + emple.getCodEmple() + "','" + emple.getNombre() + "','" + emple.getApellidos() + "','" + emple.getDni() + "','" + emple.getTelefono() + "','" + emple.getCorreo() + "','" + emple.getClave() + "','" + emple.getFecha() + "','" + emple.getCodNomina() + "','" + emple.getFuncion() + "','" + emple.getDescipcionPuesto() + "')";
	
	
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
	 * 
	 * @author cesar
	 * @param cl
	 * @return
	 */
	public int borrarEmpleado(Empleado emple){
		
		String consulta = "DELETE FROM empleado WHERE codemple='" +  emple.getCodEmple() + "' AND nombre='" + emple.getNombre()+"' AND apellidos='" + emple.getApellidos()+"' AND dni='" + emple.getDni()+"' AND telefono='" + emple.getTelefono()+"' AND correo='" + emple.getCorreo()+"' AND clave='" + emple.getClave() +"' AND jornadas='" + emple.getFecha() + "' AND codnomina='" + emple.getCodNomina() + "' AND funcion='" + emple.getFuncion() + "' AND descripcionpuesto='" + emple.getDescipcionPuesto() + "'";	
		
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
	 * 
	 * @author cesar
	 * @param emple
	 * @return
	 */
	
	// Pasamos un empleado con nombre apellidos y dni y nos devuelve su codigo
	public  String buscarEmpleado(Empleado emple){
		String cadena="SELECT codemple FROM empleado WHERE nombre='" + emple.getNombre()+"' AND apellidos='" + emple.getApellidos()+"' AND dni='" + emple.getDni()+"'";
		
		try{
			String t="";
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if ( reg.next())							
				t= reg.getString(1); // Devuelve el codigo del empleado							
			s.close();
			this.cerrar();
			return t;
		}
		catch ( SQLException e){
	
			return null;
			
		}
				
	
	
	}
	
}