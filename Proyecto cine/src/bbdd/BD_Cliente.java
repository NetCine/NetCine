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
	
	/**
	 * Funcion que pasa el cliente de cliente normal a especial o al revés.
	 * @author cesar
	 * @param codigoCliente
	 * @param codigoNuevo
	 * @return
	 */
	public int CambioTipoCliente(String codigoCliente, String codigoNuevo){
		
		int descuento;
		
		if(codigoCliente.indexOf("CE")!=-1) // Sera de tipo cliente especial y pasara a normal
			descuento = 0;
		else // sera de tipo normal y pasara a especial
			descuento = 30; // El descuento para un cliente especial es del 30%
		
		String consulta = "UPDATE cliente SET CodCliente='" + codigoNuevo +"', descuento=" + descuento + " WHERE CodCliente='" + codigoCliente + "'";
		
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
	 * Funcion que recibe el numero de tarjeta nuevo y el numero de cliente que solicita el cambio y se realiza el update actualizando los datos
	 * @author Javier
	 * @param numTarjeta
	 * @param codCliente
	 * @return
	 */
	public int CambioNumTarjeta(int numTarjeta, String codCliente){
		String consulta = "UPDATE cliente SET numtarjeta = '"+numTarjeta+"' WHERE codcliente = '"+codCliente+"'";
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
	 * Funcion que recibe el numero de telefono nuevo y el numero de cliente que solicita el cambio y se realiza el update actualizando los datos
	 * @author Javier
	 * @param telf
	 * @param codCliente
	 * @return
	 */
	public int CambioTelfContacto(int telf, String codCliente){
		String consulta = "UPDATE cliente SET numtarjeta = '"+telf+"' WHERE codcliente = '"+codCliente+"'";
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