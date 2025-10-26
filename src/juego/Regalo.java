package juego;

import java.awt.Color;

import entorno.Entorno;

public class Regalo {
	private double x;
	private double y;
	private int ancho;
	private int alto;
	private boolean visible;
	
	public Regalo(double x, double y, int ancho, int alto) {
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.visible=true;
	}
	
	public void dibujar(Entorno e) { //Dibuja el regalo en el tablero
		if(!visible) {
			return;
		}
		e.dibujarRectangulo(x, y, ancho, alto, 0, Color.MAGENTA);
	}
	
	public void contacto() { //Condicion en caso de que un grinch zombie haga contacto con el regalo
		this.visible=false;
	}

	
	//Getters y setters
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getAlto() {
		return alto;
	}

	public boolean isVisible() {
		return visible;
	}
	
	

}
