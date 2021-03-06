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
	public boolean a�adirUsuario(Usuario usu){
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
	 * Funcion que nos elimina un usuario de la aplicacion a trav�s de su codigo
	 * @author cesar
	 * @param codigoUsuarioBorrar
	 * @return
	 */
	
	public int borrarUsuarioPorCodigoUsuario(String codigoUsuarioBorrar){
		
		String consulta = "DELETE FROM usuario WHERE codusuario='" +  codigoUsuarioBorrar + "'";	
		
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
			return t; // En caso de no encontrarlo, devolvera un String vacio
		}
		catch ( SQLException e){
	
			return null;
			
		}
	
	}
	
	/**
	 * Funcion para cambiar el codigo del cliente en la tabla usuario y asi al conectarse mostrar el menu correcto
	 * @author cesar
	 * @param codigoCliente
	 * @param codigoNuevo
	 * @return
	 */
	public int CambioTipoUsuario(String codigoCliente, String codigoNuevo){
		
		String consulta = "UPDATE usuario SET CodUsuario='" + codigoNuevo + "' WHERE Codusuario='" + codigoCliente +"'";
	
		
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
	 * Funcion que recibe la nueva contrase�a y la sustituye por la anterior
	 * @author Javier
	 * @param codigoCliente
	 * @param contr
	 * @return
	 */
	public int CambioContrasenia(String codigoCliente, String contr){
		
		String consulta = "UPDATE usuario SET Clave='" + contr + "' WHERE Codusuario='" + codigoCliente +"'";
	
		
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