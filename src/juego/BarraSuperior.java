package juego;

import java.awt.Color;
import entorno.Entorno;


public class BarraSuperior {
	private int alturaBanda=90; //Altura
	
	public void dibujar(Entorno entorno, int zombiesEliminados, int zombiesRestantes, int tiempo) {
		//Dibuja el rectangulo
		entorno.dibujarRectangulo(entorno.ancho()/2,alturaBanda/2,entorno.ancho(),alturaBanda,0, Color.gray);
		entorno.cambiarFont("Arial", 18, Color.black);
		entorno.escribirTexto("Zombies eliminados: "+zombiesEliminados+"| Tiempo: "+tiempo+"s | Zombies Restantes: "+zombiesRestantes, 130, 40);
	}

	public int getAlturaBanda() {
		return alturaBanda;
	}

	public void setAlturaBanda(int alturaBanda) {
		this.alturaBanda = alturaBanda;
	}

}
