package bbdd;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

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
	public boolean añadirEmpleado(Empleado emple){
		
		String consulta = "INSERT INTO empleado VALUES('" + emple.getCodEmple() + "','" + emple.getNombre() + "','" + emple.getApellidos() + "','" + emple.getDni() + "','" + emple.getTelefono() + "','" + emple.getCorreo() + "','" + emple.getClave() + "','" + emple.getFecha() +"','" + emple.getFuncion() + "','" + emple.getDescipcionPuesto() + "')";
		
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
	 * Funcion para borrar el empleado que tiene un determinado codigo
	 * @author cesar
	 * @param cl
	 * @return
	 */
	public int borrarEmpleadoPorCodigoEmple(String codigoEmpleadoBorrar){
		
		String consulta = "DELETE FROM empleado WHERE codemple='" + codigoEmpleadoBorrar + "'";	
		
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
	 * @param cl
	 * @return
	 */
	public int borrarEmpleado(Empleado emple){
		
		String consulta = "DELETE FROM empleado WHERE codemple='" +  emple.getCodEmple() + "' AND nombre='" + emple.getNombre()+"' AND apellidos='" + emple.getApellidos()+"' AND dni='" + emple.getDni()+"' AND telefono='" + emple.getTelefono()+"' AND correo='" + emple.getCorreo()+"' AND clave='" + emple.getClave() +"' AND jornadas='" + emple.getFecha() +"' AND funcion='" + emple.getFuncion() + "' AND descripcionpuesto='" + emple.getDescipcionPuesto() + "'";	
		
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
	/**
	 * Funcion que nos cuenta el numero de empleados que tenemos para así poder generar el codEmple
	 * @author cesar
	 * @return
	 */
	public int contadorEmpleados(){
		
		String consulta = "SELECT COUNT(codEmple) FROM empleado";
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
	 * Funcion que nos permite mostrar todos los empleados que tiene nuestra aplicacion
	 * @author cesar
	 * @param bd
	 * @return
	 */
	
	
	public  Vector <Empleado> listaEmpleadosCine(){
		String cadenaSQL="SELECT * from empleado";
		
		Vector <Empleado> listaEmpleados = new Vector <Empleado> ();
		
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			while ( reg.next()){
				
				java.sql.Date f=reg.getDate("jornadas"); // Cogemos la fecha que tenemos en la bbdd en el registro cuyo nombre es jornadas
				LocalDate fBuena=f.toLocalDate(); // Pasamos esa ficha a LocalDate (que es lo que necesitamos en nuestro constructor)
				
				listaEmpleados.add(new Empleado(reg.getString("codemple"),reg.getString("nombre"),reg.getString("apellidos"),reg.getString("dni"),reg.getString("telefono"),reg.getString("correo"),reg.getString("clave"),reg.getString("funcion"),reg.getString("descripcionpuesto"),fBuena));
				
			}
			s.close();
			this.cerrar();
			return listaEmpleados;
		}
		catch ( SQLException e){		
			return null;			
		}
	}
	/**
	 * Pasamos un empleado y nos devuelve la fecha de la jornada en la que ha inicado el trabajo
	 * @author diego
	 * @param emple
	 * @return
	 */
	
	public LocalDate buscarJornada(String codemple ){
		LocalDate fbuena=null;
	String cadena="SELECT Jornadas FROM empleado where codemple='" + codemple+"' ";
		try{
			
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if (reg.next()){
			
			java.sql.Date f=reg.getDate("jornadas"); // Cogemos la fecha que tenemos en la bbdd en el registro cuyo nombre es jornadas
			fbuena=f.toLocalDate(); // Pasamos esa ficha a LocalDate (que es lo que necesitamos en nuestro constructor)	
			}
			
			s.close();
			this.cerrar();
			return fbuena;
		}
		catch ( SQLException e){

			this.cerrar();
			return null;
		}
	}
	
}