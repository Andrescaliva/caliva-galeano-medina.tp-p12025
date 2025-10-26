package juego;

import java.awt.Color;
import entorno.Entorno;

public class Regalos {
	private double x;
	private double y;
	private int radio = 45;
	
	// Constructor que recibe la posición (x, y)
	public Regalos(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarCirculo(this.x, this.y, this.radio, Color.BLUE);
	}
		
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}