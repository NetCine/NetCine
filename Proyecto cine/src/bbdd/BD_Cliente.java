package bbdd;

import java.sql.*;
import modelos.*;

/**
 * 
 * @author cesar
 *
 */

public class BD_Cliente extends BD_Conector {
		private static Statement s;		
		private static ResultSet reg;
	
	/**
	 * 
	 * @author cesar
	 * @param file
	 */
		
	public BD_Cliente(String file){
		super (file);
	}

	/**
	 * 
	 * @author cesar
	 * @param cl
	 * @return
	 */
	
	public  boolean añadirCliente(Cliente cl){
	
		String consulta = "INSERT INTO cliente VALUES('" + cl.getCodCLiente() + "','" + cl.getNombre()+"','"  + cl.getApellidos() + "','" + cl.getDni() + "','" + cl.getTelefono() + "','" + cl.getCorreo() + "','" + cl.getFechaNacimiento() + "','" + cl.getNumeroTarjeta() + "','" + cl.getDescuento() + "','" + cl.getClave() + "')";
		
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
	
	public int borrarCliente(Cliente cl){
		
		String consulta = "DELETE FROM cliente WHERE codcliente='" +  cl.getCodCLiente() + "' AND nombre='" + cl.getNombre()+"' AND apellidos='" + cl.getApellidos()+"' AND dni='" + cl.getDni()+"' AND telefono='" + cl.getTelefono()+"' AND correo='" + cl.getCorreo()+"' AND fechanacimiento='" + cl.getFechaNacimiento()+"' AND numtarjeta='" + cl.getNumeroTarjeta()+"' AND Descuento='" + cl.getDescuento()+"' AND clave='" + cl.getClave() + "'";	
		
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
	 * Buscamos un cliente mediante su nombre, apellidos y dni y devolvemos su codigo de cliente
	 * @author cesar
	 * @param cl
	 * @return
	 */
	
	public String buscarCliente(Cliente cl){ // Devolvemos el codigo del cliente
	
		String consulta = "SELECT codcliente FROM cliente WHERE  nombre='" + cl.getNombre()+"' AND apellidos='" + cl.getApellidos()+"' AND dni='" + cl.getDni() + "'";
		
		try{
			String t="";
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(consulta);
			if ( reg.next())							
				t= reg.getString(1); //cogemos el codigo del cliente para devolverle							
			s.close();
			this.cerrar();
			return t;
		}
		catch ( SQLException e){
	
			return null;
			
		}
				
	
	
	}
	/**
	 * Funcion que nos devuelve el numero de clientes para poder generar en el main un codigo correcto
	 * @author cesar
	 * @return
	 */
	
	
	public int contadorClientes(){
		
		String consulta = "SELECT COUNT(codcliente) FROM cliente";
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