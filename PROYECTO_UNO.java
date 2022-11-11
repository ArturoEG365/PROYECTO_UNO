import java.util.*;  // cd C:\Users\ArtuEG\Desktop
public class PROYECTO_UNO { // CLASE PRINCIPAL
	public static void main(String[] args) { //MAIN
		int Baraja_En_Juego[];
		Baraja_En_Juego = new int[130]; //defino el arreglo con 130 elementos para la baraja
		Scanner leer_datos = new Scanner(System.in);//defino el scanner jaja    *** int $VARIABLE = leer_datos.nextInt();;  <-- ASI SE USA 
		llenar_Baraja(Baraja_En_Juego); // lleno la baraja con numeros de 0 a 9
		boolean intentos = true;
		do{						//Leo los jugadore. No puede salir del ciclo hasta que ponga bien la validacion
			System.out.println("¿Cuantos humanos van a jugar?        Min: 1  Max: 4");
			int jugadores_humanos = leer_datos.nextInt();
			System.out.println("¿Cuantos bots van a jugar?   Min: 1  Max: 3");
			int jugadores_bots = leer_datos.nextInt();
			if (jugadores_humanos >= 1 && jugadores_humanos <= 4) 
				if (jugadores_bots >= 0 && jugadores_bots <=3 ) {
					comenzar_juego(Baraja_En_Juego, jugadores_bots, jugadores_humanos);//comienza el juego
					intentos = false;
				}
				if (intentos)		
					System.out.println("-_-_-_Intenta de nuevo_-_-_-");
		}while(intentos);  //Termina el ciclo de validacion de jugadores 
	}//TERMINA MAIN
//*********** ZONA DE FUNCIONES **************
	public static void llenar_Baraja(int [] Baraja_En_Juego){ // lleno la baraja con numeros de 0 a 9
		int aux=0;
		for (int numero_carta = 1; numero_carta <= 120 ; numero_carta++ ){
			Baraja_En_Juego[numero_carta] = aux;
			if (aux==9) {
				aux = 0;
			}else{
				aux++;
			}
		}
		for (aux=121;aux<=124 ;aux++ ) 
			Baraja_En_Juego[aux] = 10;
		for (aux=125;aux<=128 ;aux++ ) 
			Baraja_En_Juego[aux] = 11;
	}
	public static void comenzar_juego(int [] Baraja_En_Juego, int jugadores_bots, int jugadores_humanos){   //AQUI LO QUE HAGO ES VER EL ESTADO DE LAS CARTAS. SI ESTA OCUPADA O NO
		int aux;
		int Jugadores[][] ; //Declaro la matriz con las cartas
		Jugadores = new int[8][15];
		int aux_total = jugadores_humanos + jugadores_bots;
		System.out.println("\n\n\nVoy a generar " + aux_total + " Barajas\n");
		for(int control_jugadores = 1; control_jugadores <= aux_total; control_jugadores++){  // aqui me llena las barajas con numeros aleatorios
			for (int control_cartas = 1; control_cartas <= 7 ; control_cartas++ ) {
				int cara_carta = (int)((Math.random()*128 + 1));
				if(Baraja_En_Juego[cara_carta]!=90){
					Jugadores[control_jugadores][control_cartas] = Baraja_En_Juego[cara_carta];
					Baraja_En_Juego[cara_carta] = 90;
					control_cartas--;
				}
			}
			for (int y = 8; y<=14 ; y++)
				Jugadores[control_jugadores][y] = 95;   // identifico con 95 que la carta esta libre 
		}
		int separar_bots_de_humanos=0;
		for(separar_bots_de_humanos = 1; separar_bots_de_humanos <= jugadores_bots; separar_bots_de_humanos++){			//SEPARO LOS BOTS DE LOS HUMANOS
			System.out.println("Bot: JUGADOR #" + separar_bots_de_humanos);
			Jugadores[separar_bots_de_humanos][0]=98;
		}
		for(int separar_aux = separar_bots_de_humanos; separar_aux <= aux_total;separar_aux++){
			System.out.println("Humano: JUGADOR# " + separar_aux);
			Jugadores[separar_aux][0]=99;
		}
		if(aux_total!=7)
			for(int vacios = aux_total+1; vacios<=7; vacios++)
				Jugadores[vacios][0]=100;
		Boolean ganador = true;
		int turnos = 1, girando=0, carta_en_juego=0;
		System.out.println("\n\nCARTA R = REVERSA,    CARTA B = BLOQUEO, CARTA () = ESPACIO LIBRE\n\nCOMENZAMOS EL JUEGOOOOO!!!");
		int carta_aleatoria = (int)((Math.random()*9 + 1));
		System.out.println("\nTIRO #: "+turnos + " Se juega carta: " + carta_aleatoria);    //CARTA ALEATORIA
		carta_en_juego = carta_aleatoria;
		while(ganador){
			if(Jugadores[turnos][0] != 100){
				System.out.println("\n\nLe toca tirar al jugador #" + turnos  + "    ...:::CARTA EN JUEGO: "+ carta_en_juego +":::...");
				System.out.print("Sus cartas:");
				for (int controlador_cartas = 1;controlador_cartas <= 11 ;controlador_cartas++) {
					if(Jugadores[turnos][controlador_cartas] == 10){
						System.out.print(" *B* ");
					}else{
						if (Jugadores[turnos][controlador_cartas] == 11) {
							System.out.print(" *R* ");
						}else{
							if (Jugadores[turnos][controlador_cartas] == 95) {
								System.out.print(" () ");
							}else{
								System.out.print(" *"+Jugadores[turnos][controlador_cartas]+"* ");
							}
						}
					}
				}
				if(Jugadores[turnos][0] == 98){
					System.out.println("\nVa a tirar la IA xs");
					turnos++;
					girando++;
				}else{
					if (Jugadores[turnos][0] == 99) {
						System.out.println("\nVa a tirar un Humano xs");	
						turnos++;		
						girando++;			
					}else{
						if (Jugadores[turnos][0] == 100) {
							turnos++;
							girando++;
						}
					}
				}
				if(turnos==aux_total+1)
					turnos=1;
				if(girando == 10)
				ganador = false;   // para terminar el programa
			}
		}
	}
}//ESTA DEBE SER LA ULTIMA LLAVE XD