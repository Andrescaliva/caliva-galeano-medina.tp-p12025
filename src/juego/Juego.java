package juego;


import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	//Tamaño del tablero
	private int fila=5;
	private int columna=10;
	private Grinch[] zombieGrinch;
	private Planta[] plantas;
	private Planta planta;
	private int zombiesEliminados;
	private int zombiesRestantes;
	
	
	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Titulo de TP - Grupo N - Apellido1 - Apellido2 -Apellido3", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		
		this.zombieGrinch=new Grinch[15];
		this.plantas=new Planta[5];
		this.planta=new Planta(entorno.ancho()/2, entorno.alto()/2);
		this.zombiesEliminados=0;
		this.zombiesRestantes=100;
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{	
		int anchoCasillero=entorno.ancho()/columna;
		int altoCasillero=entorno.alto()/fila;
		int diametroCirculo = (int)(Math.min(anchoCasillero, altoCasillero) * 0.8); // circuloo
		// Recorremos todas las filas.
		for (int i = 0; i < fila; i++) {
        // Dentro de cada fila, recorremos todas las columnas.
			for (int j = 0; j < columna; j++) {
				int x=j*anchoCasillero+anchoCasillero/2;
				int y=i*altoCasillero+altoCasillero/2;
				// La suma de la fila (i) y la columna (j) determina el color de la casilla.
				// Si la suma es par, es una casilla verde claro.
				if ((i + j) % 2 == 0) {
					entorno.dibujarRectangulo(x, y, anchoCasillero, altoCasillero, 0, Color.GREEN);
				} else {
                // Si la suma es impar, es una casilla verde oscuro.
					entorno.dibujarRectangulo(x, y, anchoCasillero, altoCasillero, 0, Color.GREEN.darker());
				}
				
				if (j == 0) {
		              entorno.dibujarCirculo(x, y, diametroCirculo, Color.RED); // dibujar circuloss
				}       
			}
		}
		
		
		//Creacion de los zombies grinch dentro del tablero con sus respectivos moviementos
		for(int i=0;i<zombieGrinch.length;i++) {
			if(zombieGrinch[i]!=null) {
				zombieGrinch[i].moverIzquierda();
				zombieGrinch[i].dibujarGrinch(entorno);
				if(zombieGrinch[i].grinchMuerto()) {
					zombieGrinch[i]=null;
					zombiesEliminados++;
				}
			}
		}
		
		//Posicion de plantas
		/*planta.dibujar(entorno);
		for(int i=0;i<plantas.length;i++) {
			if(plantas[i]!=null) {
				plantas[i].dibujar(entorno);
			}
		}*/
		
		
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
