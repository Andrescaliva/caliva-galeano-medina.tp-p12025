package juego;

import java.awt.Color;
import entorno.Entorno;


public class BarraSuperior {
	private int alturaBanda=70; //Altura
	
	public void dibujar(Entorno entorno) {
		//Dibuja el rectangulo
		entorno.dibujarRectangulo(entorno.ancho()/2,alturaBanda/2,entorno.ancho(),alturaBanda,0, Color.gray);
	}

	public int getAlturaBanda() {
		return alturaBanda;
	}

	public void setAlturaBanda(int alturaBanda) {
		this.alturaBanda = alturaBanda;
	}

}
