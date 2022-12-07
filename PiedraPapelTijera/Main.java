import java.util.*; //Importamos el scanner

public class Main {

	public static void main(String[] args) {
		
		//Iniciamos el scanner
		
		Scanner entrada = new Scanner (System.in);
		
		//Creamos un array con las opciones disponibles
		
		String arrayLetras [] = new String[] {"P","L","T","G","K"};
		
		//Creamos una matriz con los resultados de los duelos
		
		iniciarResultadosCombates();

		//Hacemos variables, los contadores para las stats y la opción del ordenador
		
		int contWins = 0;
		int contLose = 0;
		int contEmpates = 0;
		int letraPC;

		//Mostramos el menú inicial, incluye la explicación y reglas del juego
		
		System.out.println("**JUEGO DEL PIEDRA PAPEL TIJERA**");
		System.out.println("En este juego, te vas a enfrentar al ordenador en el juego del piedra papel tijera lagarto spock");
		System.out.println("Tendrás que insertar tu opción");
		System.out.println("El ordenador escogerá aleatoriamente entre los 5, y comparará el resultado, para ver si ganaste, empataste o perdiste");
		System.out.println("Puedes insertar las opciones en mayúsculas o minúsculas, da igual, se reconocen ambas");
		System.out.println("**INICIO DEL JUEGO**");
		
		//Creamos la variable opcion del jugador
		
		String opcion;
		
		//Iniciamos el bucle, queremos que se ejecute al menos una vez
		
		do {
			
			//Mostramos el menú que se repetirá cada ronda
			
			System.out.println("************************************************************************************");
			System.out.println("Escoge: Piedra (P), papel (L), tijera(T), Lagarto (G) o Spock (K) si no quieres jugar, puedes salir con (S)");
			System.out.println("Para ver las estadísticas, pulse (Z)");
			
			//Pedimos la opción del jugador por pantalla
			
			opcion = entrada.nextLine();
			
			//Lo convertimos a mayúsculas, por si el ju7gador inserta minúsculas
			
			opcion = opcion.toUpperCase();
			
			//Hacemos la jugada del ordenador
			
			letraPC = generaJugadaPC(arrayLetras);
			
			//Condicional switch que depende de la entrada del jugador
			
			switch (opcion) {
			
			//Si la entrada es una opcion jugable de entre las 5
			
			case "P", "L", "T", "G", "K":
				
				//Cada letra tiene un numero asignado, nos devuelve el numero
				
				int numLetraJugador = devuelveNumero(opcion);
			
				//Compara la opcion del jugador y la del ordenador
			
				int finalPartida = comparaJugadas(letraPC, numLetraJugador);
				
				//Muestra lo que escogió el ordenador
				
				System.out.println("El ordenador ha escogido: " + obtenOpcionPC(arrayLetras[letraPC]));
				
				//Compara lo que devuelve la funcion comparaJugadas(), para ver si se gana, se empata, o se pierde
				//Suma 1 al contador correspondiente y devuelve el resultado del duelo por pantalla
				
				if (finalPartida == 1) {
					contWins++;
					System.out.println("Has ganado");
				}
				else if (finalPartida == -1) {
					contLose++;
					System.out.println("Has perdido");
				}
				else {
					contEmpates++;
					System.out.println("Has empatado");
				}
				break;
				
			//Si el jugador mete la letra Z
				
			case "Z":
				
				//Muestra las estadisticas, usando los contadores anteriores
				
				System.out.println("ESTADISTICAS:");
				System.out.println(contWins + " ganadas, "+ contLose + " perdidas y " + contEmpates + " empates");
				break;
				
			//Si el jugador quiere salir del juego, inserta S, no es necesario jugar ninguna partida
				
			case "S":
				
				//Muestra un mensaje por pantalla
				
				System.out.println("Cerrando programa..."); 
				break;
				
			//Si la opcion insertada no es ninguna de las anteriores, muestra un mensaje de error por pantalla
				
			default:
				System.out.println("Error de entrada, vuelva a intentarlo...");
			} //Bucle switch
			
			//Repite el bucle si la opcion NO es igual a S, o si la longitud de la opcion es mayor a 1
			
		} while (opcion.matches("[^S]") || opcion.length() > 1 || opcion.length() == 0); // do-while
		
		//Cierra el scanner
		
		entrada.close();
	} //Metodo main
	
	
	/**
	 * Genera la jugada del ordenador
	 * @param arrayLetras el array de letras que contiene las opciones
	 * @return letraPC El indice de la letra del array
	 */
	
	public static int generaJugadaPC(String arrayLetras[]) {
		int letraPC = (int)(Math.random() * arrayLetras.length);
		return letraPC;
	} //generaJugadaPC()
	
	/**
	 * Compara las jugadas del ordenador y el jugador
	 * @param letraPC la jugada del ordenador
	 * @param opcion la jugada del jugador
	 * @return resultadosCombates[opcion][letraPC] El valor de la matriz de resultados
	 */
	
	public static int comparaJugadas(int letraPC, int opcion) {
		int [][] resultadosCombates = iniciarResultadosCombates();
		return resultadosCombates[opcion][letraPC];
	} //comparaJugadas()
	
	/**
	 * Inicia los resultados de los duelos
	 * @return resultadosCombates la matriz con los resultados de los duelos, el valor de cada casilla es 1 si ganas, 0 si empatas, -1 si pierdes
	 */
	
	public static int[][] iniciarResultadosCombates() {
		int [][] resultadosCombates = {{0, -1, 1, 1, -1},{1, 0, -1, -1, 1},{-1, 1, 0, 1, -1},{-1, 1, -1, 0, 1},{1, -1, 1, -1, 0}};
		return resultadosCombates;
	} //iniciarResultadosCombates()
	
	/**
	 * Nos saca el indice de cada letra
	 * @param opcion La opcion del jugador
	 * @return numero el indice de la letra
	 */
	
	public static int devuelveNumero(String opcion) {
		int numero = 0;
		if (opcion.equals("P")) {
			numero = 0;
		}
		else if (opcion.equals("L")) {
			numero = 1;
		}
		else if (opcion.equals("T")) {
			numero = 2;
		}
		else if (opcion.equals("G")) {
			numero = 3;
		}
		else if (opcion.equals("K")) {
			numero = 4;
		}
		return numero;
	} //devuelveNumero()
	
	/**
	 * Obtiene la opcion del ordenador en forma de texto
	 * @param letraPC El indice de la opcion del ordenador
	 * @return sailda El texto descriptivo de cada opcion
	 */
	
	public static String obtenOpcionPC(String letraPC) {
		String salida = "";
		if (letraPC.equals("P")) {
			salida = "Piedra";
		}
		else if (letraPC.equals("L")) {
			salida = "Papel";		
			}
		else if (letraPC.equals("T")) {
			salida = "Tijera";		
			}
		else if (letraPC.equals("G")) {
			salida = "Lagarto";		
			}
		else if (letraPC.equals("K")) {
			salida = "Spock";		
			}
		return salida;
	} //obtenOpcionPC()
	
} //Clase
