package juego;

import java.awt.Color;
import entorno.Entorno;

public class Regalos {
	private double x;
	private double y;
	
	// Constructor que recibe la posición (x, y)
	public Regalos(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void dibujar(Entorno e) {
	    int lado = 40; // Tamaño
	    e.dibujarRectangulo(x, y, lado, lado, 0, Color.BLUE);
	}


		
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}