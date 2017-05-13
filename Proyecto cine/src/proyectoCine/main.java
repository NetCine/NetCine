package proyectoCine;

/**
 * 
 */


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
import java.time.*;
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
		
		int opcion1=0, opcionUsuario;
		
		System.out.println("|----------------------------------|");
		System.out.println("|------ BIENVENIDO A NETCINE ------|");
		System.out.println("|----------------------------------|\n\n");
		
		do{
			do{
				try{
					opcion1=0;
					System.out.println("\n�Que desea hacer?");
					System.out.println("1.- Para registro en NetCine");
					System.out.println("2.- Para entrar en NetCine");
					System.out.println("3.- Para salir\n");
					System.out.print("--- Opcion: ");
					
					opcion1=sc.nextInt();
				
				}catch(InputMismatchException e){			
					opcion1=4; // Le asigno un valor mayor que el 2 y as� imprimo el error abajo, mensaje com�n para todos los fallos
					sc.nextLine(); // Limpieza de buffer		
				}
				
				if(opcion1!=1 && opcion1!=2 && opcion1!=3)
					System.out.println("\n--- ERROR. Opcion no valida, debe ser 1 para registo o 2 para entrar\n ");
				
			}while(opcion1!=1 && opcion1!=2 && opcion1!=3);
			
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
							
						
						/*A�ADIR METER CODIGO PROMOCIONAL PARA PODER REGISTRARSE COMO ESPECIAL*/
						if(tipoCliente==2){
							int correcto=0;
							sc.nextLine();
							do{
								System.out.println("\nPara poder darte de alta como usuario especal necesitas un codigo promocional.\nIntroduce un codigo promocional valido para poder registrarte como usuario especial:\n ");
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
						System.out.print("Introduce a�o de nacimiento: ");
						anyo=sc.nextInt();					
						
						fechaNacimiento=(LocalDate.of(anyo,mes,dia)); // Se genera la fecha
												
						Period tiempo = Period.between(fechaNacimiento,LocalDate.now()); // Comprobamos la difrencia de fechas para ver si es o no mayor de edad
						
						System.out.print(tiempo.getYears());
						
						if(tiempo.getYears()<18){ // En caso de ser menor de edad no dejamos que se registre.
							System.out.println("\n--- Lo sentimos no eres a�n mayor de 18 a�os y por lo tanto no puedes registrarte en NetCine\n");
							tipoCliente=0;
							break;
						}
						
						System.out.print("Introduce numero de tarjeta de credito: ");
						tarjeta=sc.nextInt();
						sc.nextLine(); // Limpiamos el buffer					
						
						int correoValido;
						/* Al a�adir el mail, verificamos que sea correcto comprobando que tenga una @*/
						do{	
							System.out.print("Introduce correo: ");
							correo=sc.nextLine();
							
							correoValido=1;
							if(correo.indexOf("@")==-1){							
								System.out.println("\n--- Correo no v�lido, inserte de nuevo.");
								correoValido=0;
							}
						}while(correoValido==0);	
						
						System.out.print("Introduce contrase�a: ");
						clave=sc.nextLine();
						
						
						}catch(InputMismatchException e){
							sc.nextLine(); // Limpieza buffer
										
							error++;
							System.out.println("\n--- ERROR, datos erroneos, empiece de nuevo\n");
						}	
										
					}while(error!=0); // Si error se incrementa repetimos el proceso ya que ha saltado una excepcion
					
					/*Vamos a a�adir el cliente normal a la tabla cliente*/
					if(tipoCliente==1){
						String codigoClienteNormal=crearCodigoClienteNormal(bd);
						
						// Le creamos sin descuento por ser cliente normal
						Cliente cl= (new Cliente(codigoClienteNormal,nombre,apellidos,dni,telefono,correo,clave,tarjeta,0,fechaNacimiento));// Creamos el objeto que vamos a insertar en la bbdd para dar el alta
						
						if ( bd.a�adirCliente(cl)) // Insertamos en la bbdd
							System.out.println("\nRegistro completado.\nA�adido en la tabla clientes");		
						else
							System.out.println("\n--- No se ha podido a�adir en la tabla clientes");
						
						/*Vamos a a�adirlo en la tabla usuarios*/				
						Usuario usu = (new Usuario(codigoClienteNormal,correo,clave)); // Generamos el usuario para insertarle
						
						if(bd2.a�adirUsuario(usu))
							System.out.println("A�adido en la tabla usuarios");
						else
							System.out.println("--- No se ha podido a�adir a la tabla usuarios");
					}
					
					/*Vamos a a�adir el cliente especial a la tabla cliente*/
					if(tipoCliente==2){
																	
						String codigoClienteEspecial=crearCodigoClienteEspecial(bd);
						
						// Le creamos con el descuento de cliente especial (30%)
						Cliente cl= (new Cliente(codigoClienteEspecial,nombre,apellidos,dni,telefono,correo,clave,tarjeta,30,fechaNacimiento));// Creamos el objeto que vamos a insertar en la bbdd para dar el alta
						if ( bd.a�adirCliente(cl)) // Insertamos en la bbdd
							System.out.println("\nRegistro completado.\nA�adido en la tabla clientes");		
						else
							System.out.println("--- No se ha podido a�adir en la tabla clientes");
						
						/*Vamos a a�adirlo en la tabla usuarios*/
						
						Usuario usu = (new Usuario(codigoClienteEspecial,correo,clave)); // Generamos el usuario para insertarle
						
						if(bd2.a�adirUsuario(usu))
							System.out.println("A�adido en la tabla usuarios");
						else
							System.out.println("--- No se ha podido a�adir a la tabla usuarios");
					
					
				}
				
			}
			
			
			/*CONECTARSE*/
			if(opcion1==2){ // Vamos a conectarnos y en caso de conectarse, mostramos las opciones correctas para ese usuario
				
				sc.nextLine();
				System.out.print("\nIntroduce correo: ");
				String correo=sc.nextLine();
				System.out.print("Introduce contrase�a: ");
				String clave=sc.nextLine();
							
				Usuario usuario=new Usuario(correo,clave); // Creamos el usuario para poder buscarle
					
				String resultadoBusqueda= bd2.buscarUsuario(usuario); // Buscamos el usuario
				
				if (resultadoBusqueda==null)
					System.out.println("Por motivos t�cnicos no podemos obtener la informaci�n en este momento");
				else
					if (resultadoBusqueda.equals(""))
						System.out.println("LO SENTIMOS PERO ESE USUARIO NO FIGURA COMO REGISTRADO O HA INTRODUCIDO MAL LA CLAVE DE ACCESO");
				
					else{
						
						String cadena = correo+"\t"+clave+"\t"+LocalDate.now();
						
						escribirConexionFichero(cadena);
						
						String codigo = resultadoBusqueda.substring(0, resultadoBusqueda.length()-4); // Eliminamos los 4 ultimos caracteres para quedarnos con las dos letras y con ellas saber el tipo de usuario.
						
												
						/*Segun el usuario conectado sacamos un menu u otro*/
						if(codigo.indexOf("CN")!=-1){
							
							// resultadoBusqueda contiene el codigo del usuario que se ha conectado
							
							/* APARTADO DEL CLIENTE NORMAL*/
							System.out.println("\n-- SE HA CONECTADO COMO USUARIO CLIENTE NORMAL, BIENVENIDO --");
							System.out.println("\nElija una opcion:");
							System.out.println("1.- Editar sus datos");
							System.out.println("2.- Editar compra");
							System.out.println("3.- Realizar compra");
							System.out.println("4.- Consultar cual es la pel�cula mas vista");
							System.out.println("5.- Desconectarse");
							System.out.print("\n--- Opcion: ");
							opcionUsuario=sc.nextInt();
							
							if(opcionUsuario==5)
								System.out.print("\n--- HASTA PRONTO ---\n");
							
						}
						
						if(codigo.indexOf("CE")!=-1){
						
							/*APARTADO CLIENTE ESPECIAL*/
							System.out.println("\n-- SE HA CONECTADO COMO USUARIO CLIENTE ESPECIAL, BIENVENIDO --");
							System.out.println("\n-- LE RECORDAMOS QUE TIENE UN 30% DE DESCUENTO EN SUS COMPRAS --");
							System.out.println("\nElija una opcion:");
							System.out.println("1.- Editar sus datos");
							System.out.println("2.- Editar compra");
							System.out.println("3.- Realizar compra");
							System.out.println("4.- Consultar cual es la pel�cula mas vista");
							System.out.println("5.- Desconectarse");
							System.out.print("\n--- Opcion: ");
							opcionUsuario=sc.nextInt();
							
							if(opcionUsuario==5)
								System.out.print("\n--- HASTA PRONTO ---\n");
								
								
							}
							
						
						
						
						if(codigo.indexOf("JF")!=-1){
							
							// Hacer funcion para que jefe del cine pueda a�adir nuevo cupon para clientes especial
							
							/*METODO PARA EL JEFE DEL CINE*/
							System.out.println("\n-- SE HA CONECTADO COMO JEFE DEL CINE, BIENVENIDO --");
							System.out.println("\nElija una opcion:");
							System.out.println("1.- Modificar cupones registro para cliente especial");
							System.out.println("2.- Dar de alta un empleado");
							System.out.println("3.- Dar de baja un empleado");
							System.out.println("4.- Cambiar la cartelera");
							System.out.println("5.- Modificar el tipo de alg�n usuario registrado");
							System.out.println("6.- Revisar peticiones pentides de empleados");
							System.out.println("7.- Enviar las nominas del mes");
							System.out.println("8.- Desconectarse");
							System.out.print("\n--- Opcion: ");
							opcionUsuario=sc.nextInt();
							
							
							
							if(opcionUsuario==8)
								System.out.print("\n--- HASTA PRONTO ---\n");
						}
						
						
						if(codigo.indexOf("EM")!=-1){
							
							
							/*METODO PARA EL EMPLEADO DEL CINE*/
							
							System.out.println("\n-- SE HA CONECTADO COMO EMPLEADO, BIENVENIDO --");
							System.out.println("\nElija una opcion:");
							System.out.println("1.- Consultar cual es mi jornada de trabajo");
							System.out.println("2.- Solicitar un cambio");
							System.out.println("3.- Desconectarse");
							System.out.print("\n--- Opcion: ");
							opcionUsuario=sc.nextInt();
						
							
							if(opcionUsuario==3)
								System.out.print("\n--- HASTA PRONTO ---\n");
						}
					
							
					}
			
				}
			
			if(opcion1==3)
				System.out.println("\n--- HA CERRADO LA APLICACION ---");
		
			
		}while(opcion1!=3);
		
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
            //Escribimos nueva l�nea para separarlas
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
			codigo="CE"+"000"+numeroCodigo;
		
		if(numeroCodigo>=10 && numeroCodigo<=99)
			codigo = "CE"+"00"+numeroCodigo;
		
		if(numeroCodigo>99)
			codigo = "CE"+"0"+numeroCodigo;
		
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
			codigo="CN"+"000"+numeroCodigo;
		
		if(numeroCodigo>=10 && numeroCodigo<=99)
			codigo = "CN"+"00"+numeroCodigo;
		
		if(numeroCodigo>99)
			codigo = "CN"+"0"+numeroCodigo;
		
		if(numeroCodigo>999)
			codigo = "CN"+numeroCodigo;
		
		return codigo;
		
	}

}
