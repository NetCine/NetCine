package proyectoCine;

/**
 * 
 */

import java.io.*;

import java.util.*;

import modelos.*; // Include para usar todas las clases de modelos (objetos)
import bbdd.*; // Para conectar a la bbdd

import java.time.LocalDate; // Import para localdate

// imports para fichero

import java.io.BufferedWriter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;

/**
 * Programa principal del proyecto
 * @author cesar
 *
 */

public class main {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		
		BD_Cliente bd = new BD_Cliente("mysql-properties.xml");	
		BD_Usuario bd2 = new BD_Usuario("mysql-properties.xml");
		
		int opcion1=0;
		
		System.out.println("|----------------------------------|");
		System.out.println("|------ BIENVENIDO A NETCINE ------|");
		System.out.println("|----------------------------------|\n\n");
		
		do{
			try{
				opcion1=0;
				System.out.println("¿Que desea hacer?");
				System.out.println("1.- Para registro en NetCine");
				System.out.println("2.- Para entrar en NetCine\n");
				System.out.print("--- Opcion: ");
				
				opcion1=sc.nextInt();
			
			}catch(InputMismatchException e){			
				opcion1=3; // Le asigno un valor mayor que el 2 y así imprimo el error abajo, mensaje común para todos los fallos
				sc.nextLine(); // Limpieza de buffer				
				
			}
			
			if(opcion1!=1 && opcion1!=2)
				System.out.println("\n--- ERROR. Opcion no valida, debe ser 1 para registo o 2 para entrar\n ");
			
		}while(opcion1!=1 && opcion1!=2);
		
		/*REGISTRARSE*/
		if(opcion1==1){ // Vamos al registro en la aplicacion
			
			/*Desde la ventana de usuario solo se puede dar de alta un cliente nuevo, recogemos sus datos
			 el empleado solo lo puede dar de alta el jefe de cine por lo que aqui solo se registran clientes*/
			
			int error=0; // variable que se va a encargar de comenzar de nuevo a pedir datos en caso de que sale la excepcion
			
			String nombre="",apellidos="",dni="",telefono="",correo="",clave="";
			int tarjeta=0, dia=0,mes=0,anyo=0, tipoCliente=0;
			LocalDate fechaNacimiento=null;
			
			String [] codigosPromocionales = new String [10]; // Array donde tenemos los codigos promocionales para poder registrarse como usuario especial
			int contadorCodigos=0;
			
			codigosPromocionales[0] = "CUPON1";
			contadorCodigos++;
			codigosPromocionales[1] = "PROMOCIONESPECIAL";
			contadorCodigos++;
			
			do{
				try{
					
					do{ // Recogemos el tipo de cliente que va a darse de alta
						System.out.print("\nIntroduce tipo de cliente \n1.- Normal\n2.- Especial\nOpcion: ");
						tipoCliente=sc.nextInt();
						
						if(tipoCliente!=1 && tipoCliente!=2)
							System.out.println("\n--- ERROR, tipo de cliente no valido, inserte de nuevo");
						
					}while(tipoCliente!=1 && tipoCliente!=2);
						
					
					/*AÑADIR METER CODIGO PROMOCIONAL PARA PODER REGISTRARSE COMO ESPECIAL*/
					if(tipoCliente==2){
						int correcto=0;
						sc.nextLine();
						do{
							System.out.println("\nPara poder conectarte como usuario especal necesitas un codigo promocional, Introduce un codigo promocional valido para poder registrarte como usuario especial:\n ");
							String leerCodigo=sc.nextLine();
							
							for(int i=0;i<contadorCodigos;i++){ // Buscamos si el codigo es correcto
								if(codigosPromocionales[i].equals(leerCodigo.toUpperCase())){
									correcto++;									
									
								}
									
							}
							if(correcto==0)
								System.out.print("\n--- ERROR, codigo no valido, inserta un codigo promocional valido para registrarte como usuario especial ");
							
						}while(correcto==0);	
						
					}
					
					if(tipoCliente==1)// Vendremos de leer un int por lo que estamos obligados a limpiar el buffer
						sc.nextLine();
					
					error=0;
					
					System.out.print("\nIntroduce nombre: ");
					nombre=sc.nextLine();
					System.out.print("Introduce apellidos: ");
					apellidos=sc.nextLine();
					System.out.print("Introduce DNI: ");
					dni=sc.nextLine(); // comprobar que sea un dni valido.
					System.out.print("Introduce telefono de contacto: ");
				    telefono=sc.nextLine();
				    System.out.print("Introduce dia de nacimiento: ");
					dia=sc.nextInt();					
					System.out.print("Introduce mes de nacimiento: ");
					mes=sc.nextInt();
					System.out.print("Introduce año de nacimiento: ");
					anyo=sc.nextInt();					
					fechaNacimiento=(LocalDate.of(anyo,mes,dia)); // Se genera la fecha
					System.out.print("Introduce numero de tarjeta de credito: ");
					tarjeta=sc.nextInt();
					sc.nextLine(); // Limpiamos el buffer
					System.out.print("Introduce correo: ");
					correo=sc.nextLine();
					System.out.print("Introduce contraseña: ");
					clave=sc.nextLine();
				
				}catch(InputMismatchException e){
					sc.nextLine(); // Limpieza buffer
								
					error++;
					System.out.println("\n--- ERROR, datos erroneos, empiece de nuevo\n");
				}	
								
			}while(error!=0); // Si error se incrementa repetimos el proceso ya que ha saltado una excepcion
			
			/*Vamos a añadir el cliente normal a la tabla cliente*/
			if(tipoCliente==1){
				String codigoClienteNormal=crearCodigoClienteNormal(bd);
				
				// Le creamos sin descuento por ser cliente normal
				Cliente cl= (new Cliente(codigoClienteNormal,nombre,apellidos,dni,telefono,correo,clave,tarjeta,0,fechaNacimiento));// Creamos el objeto que vamos a insertar en la bbdd para dar el alta
				
				if ( bd.añadirCliente(cl)) // Insertamos en la bbdd
					System.out.println("Añadido en la tabla clientes");		
				else
					System.out.println("No se ha podido añadir");
				
				/*Vamos a añadirlo en la tabla usuarios*/
				
				Usuario usu = (new Usuario(codigoClienteNormal,correo,clave)); // Generamos el usuario para insertarle
				
				if(bd2.añadirUsuario(usu))
					System.out.println("Añadido en la tabla usuarios");
				else
					System.out.println("Nos se ha podido añadir a la tabla usuarios");
			}
			
			/*Vamos a añadir el cliente especial a la tabla cliente*/
			if(tipoCliente==2){
															
				String codigoClienteEspecial=crearCodigoClienteEspecial(bd);
				
				// Le creamos con el descuento de cliente especial (30%)
				Cliente cl= (new Cliente(codigoClienteEspecial,nombre,apellidos,dni,telefono,correo,clave,tarjeta,30,fechaNacimiento));// Creamos el objeto que vamos a insertar en la bbdd para dar el alta
				if ( bd.añadirCliente(cl)) // Insertamos en la bbdd
					System.out.println("Añadido");		
				else
					System.out.println("No se ha podido añadir");
				
				/*Vamos a añadirlo en la tabla usuarios*/
				
				Usuario usu = (new Usuario(codigoClienteEspecial,correo,clave)); // Generamos el usuario para insertarle
				
				if(bd2.añadirUsuario(usu))
					System.out.println("Añadido en la tabla usuarios");
				else
					System.out.println("Nos se ha podido añadir a la tabla usuarios");
				
				
			}
		}
		
		/*CONECTARSE*/
		if(opcion1==2){ // Vamos a conectarnos y en caso de conectarse, mostramos las opciones correctas para ese usuario
			
			sc.nextLine();
			System.out.print("\nIntroduce correo: ");
			String correo=sc.nextLine();
			System.out.print("Introduce contraseña: ");
			String clave=sc.nextLine();
						
			Usuario usuario=new Usuario(correo,clave); // Creamos el usuario para poder buscarle
				
			String resultadoBusqueda= bd2.buscarUsuario(usuario); // Buscamos el usuario
			
			if (resultadoBusqueda==null)
				System.out.println("Por motivos técnicos no podemos obtener la información en este momento");
			else
				if (resultadoBusqueda.equals(""))
					System.out.println("LO SENTIMOS PERO ESE USUARIO NO ESTA REGISTRADO");
			
				else{
					
					String cadena = correo+"\t"+clave+"\t"+LocalDate.now();
					
					escribirConexionFichero(cadena);
					
					String codigo = resultadoBusqueda.substring(0, resultadoBusqueda.length()-4); // Eliminamos los 4 ultimos caracteres para quedarnos con las dos letras y con ellas saber el tipo de usuario.
					
					/*Segun el usuario conectado sacamos un menu u otro*/
					if(codigo.indexOf("CN")!=-1){
						
						System.out.println("SE HA CONECTADO COMO USUARIO CLIENTE NORMAL, BIENVENIDO");
						
						/* APARTADO DEL CLIENTE NORMAL*/
						
					}
					
					if(codigo.indexOf("CE")!=-1){
					
						System.out.println("SE HA CONECTADO COMO USUARIO CLIENTE ESPECIAL, BIENVENIDO");
						/*APARTADO CLIENTE ESPECIAL*/
						
					}
					
					
					if(codigo.indexOf("JF")!=-1){
						
						// Hacer funcion para que jefe del cine pueda añadir nuevo cupon para clientes especial
						
						/*METODO PARA EL JEFE DEL CINE*/
						System.out.println("\nSE HA CONECTADO COMO JEFE CINE, BIENVENIDO");
						
					}
					
					
					if(codigo.indexOf("EM")!=-1){
						
						/*METODO PARA EL EMPLEADO DEL CINE*/
						
						System.out.println("SE HA CONECTADO COMO USUARIO EMPLEADO NORMAL, BIENVENIDO");
					}
				
						
				}
			}
	
	}
	
	
	/**
	 * Funcion para escribir cada conexion correcta en el fichero logConexiones.txt que esta en la raiz del proyecto
	 * @author cesar
	 */
	
	public static void escribirConexionFichero(String cadena) {	
		
	
        Path file= Paths.get("logConexiones.txt");
        Charset charset = Charset.forName("UTF-8");
        //Creamos un BufferedWriter de java.io de forma eficiente utilizando Files de java.nio
        try (BufferedWriter writer = Files.newBufferedWriter(file, charset,APPEND)) {

            Scanner sc=new Scanner(System.in);            

            writer.newLine(); // Metemos un salto de linea
            
            writer.write(cadena); // Copiamos la cadena con correco clave y fecha de conexion que hemos generado antes
            //Escribimos nueva línea para separarlas
            writer.newLine();
            
            writer.close();
            
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
	
	}	
	
	
	/**
	 * Funciones para generar el codCliente normal.
	 * @author cesar
	 * @return
	 */
	
	public static String crearCodigoClienteEspecial(BD_Cliente bd){
		
		String codigo="";
		int numeroCodigo = bd.contadorClientes();
		numeroCodigo++;
		
		// El codigo para un cliente especial se genera con las siglas CN mas los ceros que necesite segun los usuarios que ya haya
		
		if(numeroCodigo<10)
			codigo="CE"+000+numeroCodigo;
		
		if(numeroCodigo>=10 && numeroCodigo<=99)
			codigo = "CE"+00+numeroCodigo;
		
		if(numeroCodigo>99)
			codigo = "CE"+0+numeroCodigo;
		
		if(numeroCodigo>999)
			codigo = "CE"+numeroCodigo;
		
		return codigo;
	}
	
	/**
	 * Funciones para generar el codCliente especial.
	 * @author cesar
	 * @return
	 */
	
	public static String crearCodigoClienteNormal(BD_Cliente bd){
		
		String codigo="";
		int numeroCodigo = bd.contadorClientes();
		numeroCodigo++;

		// El codigo para un cliente especial se genera con las siglas CN mas los ceros que necesite segun los usuarios que ya haya
		
		if(numeroCodigo<10)
			codigo="CE"+000+numeroCodigo;
		
		if(numeroCodigo>=10 && numeroCodigo<=99)
			codigo = "CE"+00+numeroCodigo;
		
		if(numeroCodigo>99)
			codigo = "CE"+0+numeroCodigo;
		
		if(numeroCodigo>999)
			codigo = "CE"+numeroCodigo;
		
		return codigo;
		
	}

}
