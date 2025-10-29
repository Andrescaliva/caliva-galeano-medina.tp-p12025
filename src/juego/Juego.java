package juego;


import java.awt.Color;
import java.util.Random;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	//Tamaño del tablero
	private int fila=5;
	private int columna=10;
	private int margenX=65;
    private int margenY=100;
	private Grinch[] zombieGrinch;
	private Planta[][] plantas;
	private Casillero[][] tablero;
	private Regalos[] regalos;
	private Disparo[] disparos;
	private Grinch grinch;
	private Planta planta;
	private int zombiesEliminados;
	private int zombiesRestantes;
	private int tiempo;
	private Random random;
	//Estado del juego
	private boolean juegoGanado;
	private boolean juegoPerdido;

	
	
	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Titulo de TP - Grupo N - Apellido1 - Apellido2 -Apellido3", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		
		this.zombieGrinch=new Grinch[15];   //arreglo con 15 zombies
		zombieGrinch[0] = new Grinch(900);  //En la posición 0 creo un zombie nuevo en la posición X = 900

		this.grinch=new Grinch(800);        //gricn en la pos 800
		
		this.regalos = new Regalos[fila];     //Crea un arrego de regaloscon tantas posiciones como filas hay en el tablero
		
		this.plantas = new Planta[fila][columna];  //Lo mismo, ppero con filas y columnas

		this.margenX=margenX;
		this.margenY=margenY;

		// Calculo dimensiones para ubicar el regalo
		int anchoCasillero = entorno.ancho() / columna; 
		int altoCasillero = entorno.alto() / fila; 

		// Posición X (Columna 0, centrada):
		int xInicialRegalo = anchoCasillero / 2; 

		// 5 regalos, uno por fila, centrados verticalmente.
		for (int i = 0; i < fila; i++) {
		    int yInicialRegalo =( i * altoCasillero) + altoCasillero / 2;    // calculo los centros de las filas
		    
		    this.regalos[i] = new Regalos(xInicialRegalo, yInicialRegalo); 
		}
		
		// Crear una planta enfrente de cada regalo en columna 1
		for (int i = 0; i < fila; i++) {
		    int xPlanta = (anchoCasillero * 1) + anchoCasillero / 2;
		    int yPlanta = (altoCasillero * i) + altoCasillero / 2;

		    plantas[i][1] = new Planta(xPlanta, yPlanta); //planta en la fila i y columna 1
		}

		
		
		// Inicia el juego!
		this.entorno.iniciar();
		
		//Creacion del tablero
		this.tablero = new Casillero[fila][columna];
		int anchocasillero=entorno.ancho()/columna;
		int altocasillero=entorno.alto()/fila;
		
		// Recorremos todas las filas.
		for (int i = 0; i < fila; i++) {
			
        // Dentro de cada fila, recorremos todas las columnas.
			for (int j = 0; j < columna; j++) {
				int x=j*anchoCasillero+anchoCasillero/2;
				int y=i*altoCasillero+altoCasillero/2;
				// La suma de la fila (i) y la columna (j) determina el color de la casilla.
				// Si la suma es par, es una casilla verde claro.
				if ((i + j) % 2 == 0) {
					tablero[i][j] = new Casillero(x, y, anchoCasillero, altoCasillero,true);
				} else {
                // Si la suma es impar, es una casilla verde oscuro.
					tablero[i][j] = new Casillero(x, y, anchoCasillero, altoCasillero, false);
				}
				      
			}
		}
		
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{	

		for (int i = 0; i < fila; i++) {
//        // Dentro de cada fila, recorremos todas las columnas.
			for (int j = 0; j < columna; j++) {
				tablero[i][j].dibujar(entorno);
			}
			

		}
				
		//Creacion de los zombies grinch dentro del tablero con sus respectivos moviementos
		for(int i=0;i<zombieGrinch.length;i++) {
			if(zombieGrinch[i]!=null) {
				Grinch g=zombieGrinch[i];
				zombieGrinch[i].moverIzquierda();
				zombieGrinch[i].dibujarGrinch(entorno);
				if(zombieGrinch[i].grinchMuerto()) {
					zombieGrinch[i]=null;
					zombiesEliminados++;
				}
			}
			
		}
	if(tiempo<=0) {
			grinch.dibujarGrinch(entorno);
			grinch.moverIzquierda();
			tiempo--;
		}
		
	//Dibujar regalos
	
	for (int i = 0; i < regalos.length; i++) {
	    if (regalos[i] != null) {
	        regalos[i].dibujar(entorno);
	    }
	}
	
	// Dibujar plantas
	for (int i = 0; i < fila; i++) {
	    if (plantas[i][1] != null) {
	        plantas[i][1].dibujar(entorno);
	    }
	}
		
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}



