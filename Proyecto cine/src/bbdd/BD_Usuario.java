package bbdd;

import java.sql.*;
import modelos.*;

/**
 * 
 * @author cesar
 *
 */

public class BD_Usuario extends BD_Conector {
		private static Statement s;		
		private static ResultSet reg;
		
		
	/**
	 * 
	 * @author cesar	
	 * @param file
	 */
		
	public BD_Usuario(String file){
		super (file);
	}

	/**
	 * 
	 * @author cesar
	 * @param usu
	 * @return
	 */
	public boolean añadirUsuario(Usuario usu){
		String consulta = "INSERT INTO usuario VALUES('" + usu.getCodUsuario() + "','" + usu.getCorreo() + "','" + usu.getClave() + "')";
	
	
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
	 * @param usu
	 * @return
	 */
	public int borrarUsuario(Usuario usu){
		
		String consulta = "DELETE FROM usuario WHERE codusuario='" +  usu.getCodUsuario() + "' AND correo='" + usu.getCorreo() +"' AND clave='" + usu.getClave() + "'";	
		
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
	 * @param usu
	 * @return
	 */
	
	// Pasamos un usuario con correo y clave y nos devuelve su codigo
	public String buscarUsuario(Usuario usu){ // Devolvemos el codigo del usuario
		
		String consulta = "SELECT codusuario FROM usuario WHERE correo='" + usu.getCorreo() +"' AND clave='" + usu.getClave() + "'";	
		
		try{
			String t="";
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(consulta);
			if ( reg.next())							
				t= reg.getString(1); // Cogemos el codigo del usuario para devolverle							
			s.close();
			this.cerrar();
			return t;
		}
		catch ( SQLException e){
	
			return null;
			
		}
	
	}
}