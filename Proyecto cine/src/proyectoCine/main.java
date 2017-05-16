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
		BD_Empleado bd3 = new BD_Empleado("mysql-properties.xml");
		BD_Nomina bd4 = new BD_Nomina("mysql-properties.xml");
		
		int opcion1=0, opcionUsuario, opcionCupon=0;
		
		Vector <String> codigosPromocionales = new Vector <String> (); // Array donde tenemos los codigos promocionales para poder registrarse como usuario especial		
		
		System.out.println("|----------------------------------|");
		System.out.println("|------ BIENVENIDO A NETCINE ------|");
		System.out.println("|----------------------------------|\n\n");
		
		do{
			do{
				try{
					opcion1=0;
					System.out.println("\n¿Que desea hacer?");
					System.out.println("1.- Para registro en NetCine");
					System.out.println("2.- Para entrar en NetCine");
					System.out.println("3.- Para salir\n");
					System.out.print("--- Opcion: ");
					
					opcion1=sc.nextInt();
				
				}catch(InputMismatchException e){			
					opcion1=4; // Le asigno un valor mayor que el 2 y así imprimo el error abajo, mensaje común para todos los fallos
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
								
				codigosPromocionales.add("CUPON1");
				codigosPromocionales.add("PROMOCIONESPECIAL");
				
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
								System.out.println("\nPara poder darte de alta como usuario especal necesitas un codigo promocional.\nIntroduce un codigo promocional valido para poder registrarte como usuario especial:\n ");
								String leerCodigo=sc.nextLine();
								
								for(int i=0;i<codigosPromocionales.size();i++){ // Buscamos si el codigo es correcto
									if(codigosPromocionales.get(i).equals(leerCodigo.toUpperCase())){
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
												
						Period tiempo = Period.between(fechaNacimiento,LocalDate.now()); // Comprobamos la difrencia de fechas para ver si es o no mayor de edad
						
						System.out.print(tiempo.getYears());
						
						if(tiempo.getYears()<18){ // En caso de ser menor de edad no dejamos que se registre.
							System.out.println("\n--- Lo sentimos no eres aún mayor de 18 años y por lo tanto no puedes registrarte en NetCine\n");
							tipoCliente=0;
							break;
						}
						
						System.out.print("Introduce numero de tarjeta de credito: ");
						tarjeta=sc.nextInt();
						sc.nextLine(); // Limpiamos el buffer					
						
						int correoValido;
						/* Al añadir el mail, verificamos que sea correcto comprobando que tenga una @*/
						do{	
							System.out.print("Introduce correo: ");
							correo=sc.nextLine();
							
							correoValido=1;
							if(correo.indexOf("@")==-1){							
								System.out.println("\n--- Correo no válido, inserte de nuevo.");
								correoValido=0;
							}
						}while(correoValido==0);	
						
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
							System.out.println("\nRegistro completado.\nAñadido en la tabla clientes");		
						else
							System.out.println("\n--- No se ha podido añadir en la tabla clientes");
						
						/*Vamos a añadirlo en la tabla usuarios*/				
						Usuario usu = (new Usuario(codigoClienteNormal,correo,clave)); // Generamos el usuario para insertarle
						
						if(bd2.añadirUsuario(usu))
							System.out.println("Añadido en la tabla usuarios");
						else
							System.out.println("--- No se ha podido añadir a la tabla usuarios");
					}
					
					/*Vamos a añadir el cliente especial a la tabla cliente*/
					if(tipoCliente==2){
																	
						String codigoClienteEspecial=crearCodigoClienteEspecial(bd);
						
						// Le creamos con el descuento de cliente especial (30%)
						Cliente cl= (new Cliente(codigoClienteEspecial,nombre,apellidos,dni,telefono,correo,clave,tarjeta,30,fechaNacimiento));// Creamos el objeto que vamos a insertar en la bbdd para dar el alta
						if ( bd.añadirCliente(cl)) // Insertamos en la bbdd
							System.out.println("\nRegistro completado.\nAñadido en la tabla clientes");		
						else
							System.out.println("--- No se ha podido añadir en la tabla clientes");
						
						/*Vamos a añadirlo en la tabla usuarios*/
						
						Usuario usu = (new Usuario(codigoClienteEspecial,correo,clave)); // Generamos el usuario para insertarle
						
						if(bd2.añadirUsuario(usu))
							System.out.println("Añadido en la tabla usuarios");
						else
							System.out.println("--- No se ha podido añadir a la tabla usuarios");
					
					
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
						System.out.println("LO SENTIMOS PERO ESE USUARIO NO FIGURA COMO REGISTRADO O HA INTRODUCIDO MAL LA CLAVE DE ACCESO");
				
					else{
						
						String cadena = correo+"\t"+clave+"\t"+LocalDate.now();
						
						escribirConexionFichero(cadena);
						
						String codigo = resultadoBusqueda.substring(0, resultadoBusqueda.length()-4); // Eliminamos los 4 ultimos caracteres para quedarnos con las dos letras y con ellas saber el tipo de usuario.
						
												
						/*Segun el usuario conectado sacamos un menu u otro*/
						if(codigo.indexOf("CN")!=-1 || codigo.indexOf("CE")!=-1){
							
							// la variable resultadoBusqueda contiene el codigo del usuario que se ha conectado
							
							/* APARTADO DEL CLIENTE NORMAL*/
							if(codigo.indexOf("CN")!=-1)	
								System.out.println("\n-- SE HA CONECTADO COMO USUARIO CLIENTE NORMAL, BIENVENIDO --");
							if(codigo.indexOf("CE")!=-1)
								System.out.println("\n-- SE HA CONECTADO COMO USUARIO CLIENTE ESPECIAL, BIENVENIDO --");
							
							System.out.println("\nElija una opcion:");							
							System.out.println("1.- Editar sus datos");
							System.out.println("2.- Editar compra");
							System.out.println("3.- Realizar compra");
							System.out.println("4.- Consultar cual es la película mas vista");
							System.out.println("5.- Desconectarse");
							System.out.print("\n--- Opcion: ");
							opcionUsuario=sc.nextInt();
							
							if(opcionUsuario==5)
								System.out.print("\n--- HASTA PRONTO ---\n");
							
						}
				
						if(codigo.indexOf("JF")!=-1){
							
							do{
							
								System.out.println("\n-- SE HA CONECTADO COMO JEFE DEL CINE, BIENVENIDO --");
								
								do{	
									try{
										/*METODO PARA EL JEFE DEL CINE*/
										
										System.out.println("\nElija una opcion:");
										System.out.println("1.- Modificar cupones registro para cliente especial");
										System.out.println("2.- Dar de alta un empleado");
										System.out.println("3.- Dar de baja un empleado");
										System.out.println("4.- Cambiar la cartelera");
										System.out.println("5.- Modificar el tipo de algún usuario registrado");
										System.out.println("6.- Revisar peticiones pendientes de empleados");
										System.out.println("7.- Enviar las nominas del mes");
										System.out.println("8.- Desconectarse");
										System.out.print("\n--- Opcion: ");
										opcionUsuario=sc.nextInt();
										
									}catch(InputMismatchException e){
										
										opcionUsuario=9; // Le asigno el 9 para que entre a mostrar el mensaje de error
										sc.nextLine(); // Limpieza de buffer
									}
										
									if(opcionUsuario==1){ // Opcion modificar cupones de registro
										
										do{
											System.out.print("\n1.- Para quitar cupon\n2.- Para añadir nuevo cupón");
											opcionCupon=sc.nextInt();
											
											if(opcionCupon==1){ // En caso de querer quitar un cupon
												System.out.print("\nInserte el nuevo cupon que quiere añadir: ");
												sc.nextLine(); // Limpieza buffer
												codigosPromocionales.add(sc.nextLine());
												System.out.print("\nCupon añadido");
											}
											
											else if(opcionCupon==2){ // En caso de querer añadir un cupon
												sc.nextLine(); // Limpieza de buffer
												System.out.print("\nInserte el cupon que quiere eliminar: ");
												String cuponEliminar=sc.nextLine();
												int eliminado=0; // Variable para saber si lo ha eliminado o no existia
												for(int i=0;i<codigosPromocionales.size();i++){
													if(cuponEliminar.toUpperCase().equals(codigosPromocionales.get(i))){
														codigosPromocionales.remove(i);
														System.out.print("\n-- El cupon "+cuponEliminar+" ha sido eliminado");
														eliminado++;
													}
													
												}
												if(eliminado==0) // En caso de no existir dicho cupon
													System.out.print("\n-- El cupon "+cuponEliminar+" no se encuentra entre los validos por lo que no se puede eliminar");
												
											}
											
											else
												System.out.print("\n--- ERROR, OPCION NO VALIDA\n");
										
										}while(opcionCupon!=1 && opcionCupon!=2);
										//SALIDA opcionCupon==1 || opcionCupon==2		
										
										
									} // Fin opcion 1
									
									if(opcionUsuario==2){ // Dar de alta un empleado
										
										System.out.println("\n-- Vamos a dar de alta un nuevo empleado");
										
										/*AL DAR DE ALTA EL EMPLEADO SE DEBE DE DAR DE ALTA COMO USUARIO Y SE DEBE DE DAR DE ALTA SU NOMINA*/
										
										sc.nextLine(); // Limpiamos el buffer
										
										// Variables para dar de alta el nuevo empleado
										String nombreEmple,apellidosEmple,dniEmple,telefonoEmple,correoEmple,claveEmple, funcion, puesto;
										LocalDate fecha;
										double salario;
										
										System.out.print("Introduce nombre del nuevo empleado: ");
										nombreEmple=sc.nextLine();
										System.out.print("Introduce apellidos del nuevo empleado: ");
										apellidosEmple=sc.nextLine();
										System.out.print("Introduce DNI del nuevo empleado: ");
										dniEmple=sc.nextLine();
										System.out.print("Introduce telefono del nuevo empleado: ");
										telefonoEmple=sc.nextLine();
										System.out.print("Introduce correo del nuevo empleado: ");
										correoEmple=sc.nextLine();
										System.out.print("Introduce clave de acceso del nuevo empleado: ");
										claveEmple=sc.nextLine();
										System.out.print("Introduce jornada del nuevo empleado: ");
										fecha=LocalDate.now(); // Metemos la fecha en la que se le da de alta
										System.out.print("Introduce funcion del nuevo empleado: ");
										funcion=sc.nextLine();
										System.out.print("Introduce puesto del nuevo empleado: ");
										puesto=sc.nextLine();
										System.out.print("Introduce salario para el nuevo empleado: ");
										salario=sc.nextDouble();
										// Objetos para añadir a la bbdd
										Empleado emple;
										Nomina nom;
										Usuario usuEmpleado;
										
										// Generamos ambos codigos el del empleado y el de la nomina
										String codEmple = crearCodigoEmpleado(bd3);
										String codNomina = crearCodigoNomina (bd4); 
										
										// Creo los objetos
										emple = new Empleado(codEmple, nombreEmple, apellidosEmple, dniEmple, telefonoEmple, correoEmple,	claveEmple, codNomina, funcion, puesto, fecha);
										nom = new Nomina(salario, dniEmple, codNomina);
										usuEmpleado = new Usuario(codEmple, correoEmple, clave);
										
										// damos de alta en tabla empleados
										if ( bd3.añadirEmpleado(emple)) // Insertamos en la bbdd
											System.out.println("\nEmpleado dado de alta.\nAñadido en la tabla empledos");		
										else
											System.out.println("--- No se ha podido añadir en la tabla empleados");
										
										// damos de alta en tabla nominas
										if ( bd4.añadirNomina(nom)) // Insertamos en la bbdd
											System.out.println("\nNomina dada de alta.\nAñadido en la tabla nomina");		
										else
											System.out.println("--- No se ha podido añadir en la tabla nomina");
										
										// damos de alta en tabla usuario
										if ( bd2.añadirUsuario(usuEmpleado)) // Insertamos en la bbdd
											System.out.println("\nNuevo usuario dado de alta.\nAñadido en la tabla usuario");		
										else
											System.out.println("--- No se ha podido añadir en la tabla usuario");										
										
									}
									
									
									
									
									if(opcionUsuario==8)
										System.out.print("\n--- HASTA PRONTO ---\n");
								
									if(opcionUsuario>8){
										
										System.out.print("\n--- ERROR, OPCION NO VALIDA\n");
										
									}
								
								
								}while(opcionUsuario>8); // Control de errores para que introduzca el jefe de empleado una opcion válida
								
							}while(opcionUsuario!=8);
							// salimos cuando opcionUsuario==8
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
            //Escribimos nueva línea para separarlas
            writer.newLine();
            
            writer.close();
            
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
	
	}	
	
	/**
	 * Funcion para generar el codigo de una nueva nomina
	 * @author cesar
	 * @param bd
	 * @return
	 */
	public static String crearCodigoNomina (BD_Nomina bd){
		
		String codigo="";
		int numeroCodigo = bd.contadorNominas();
		numeroCodigo++; // El codigo será las siglas mas el numero de nominas +1
		
		// El codigo para una nomina se genera con las siglas NM mas los ceros que necesite segun los usuarios que ya haya
		
		if(numeroCodigo<10)
			codigo="NM"+"000"+numeroCodigo;
		
		if(numeroCodigo>=10 && numeroCodigo<=99)
			codigo = "NM"+"00"+numeroCodigo;
		
		if(numeroCodigo>99)
			codigo = "NM"+"0"+numeroCodigo;
		
		if(numeroCodigo>999)
			codigo = "NM"+numeroCodigo;
		
		return codigo;
	}
	
	
	/**
	 * Funciones para generar el condEmple (codigo de empleado)
	 * @author cesar
	 * @return
	 */
	
	public static String crearCodigoEmpleado(BD_Empleado bd){
		
		String codigo="";
		int numeroCodigo = bd.contadorEmpleados();
		numeroCodigo++;
		
		// El codigo para un cliente especial se genera con las siglas EM mas los ceros que necesite segun los usuarios que ya haya
		
		if(numeroCodigo<10)
			codigo="EM"+"000"+numeroCodigo;
		
		if(numeroCodigo>=10 && numeroCodigo<=99)
			codigo = "EM"+"00"+numeroCodigo;
		
		if(numeroCodigo>99)
			codigo = "EM"+"0"+numeroCodigo;
		
		if(numeroCodigo>999)
			codigo = "EM"+numeroCodigo;
		
		return codigo;
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
