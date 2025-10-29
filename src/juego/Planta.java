package juego;

import java.awt.Color;

import entorno.Entorno;

public class Planta {
	private int x ;   //Fila 
	private int y ;  //Columna
	private int vida ;
	private int diametro ;
	private boolean seleccionada ;
	
	public Planta(int x, int y) {
		this.x = x;
		this.y = y;
		this.vida = 3;
		this.seleccionada=false;
		this.diametro= 37;
	}
	
	
	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, diametro,Color.YELLOW);
	}
	
	public int getX() {
	    return x;
	}

	public int getY() {
	    return y;
	}

}
