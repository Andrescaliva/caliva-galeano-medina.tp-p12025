package juego;

import java.awt.Color;

import entorno.Entorno;

public class Disparo {
	private double x;
	private double y;
	private int radio;
	private double velocidad;
	private boolean activado;
	private Color color;
	private int danio;
	
	public Disparo(double x, double y, int radio, double velocidad, Color color, int danio) {
		this.x=x;
		this.y=y;
		this.radio=radio;
		this.velocidad=velocidad;
		this.activado=true;
		this.color=color;
		this.danio=danio;
	}
	
	public void moverDerecha(){
		this.x+=this.velocidad;
	}
	
	public void dibujar(Entorno e) {
		if(activado) {
			e.dibujarCirculo(x, y, radio*2, Color.YELLOW);
		}
	}
	
	public void desactivar() {
		this.activado=false;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getRadio() {
		return radio;
	}

	public boolean isActivado() {
		return activado;
	}

	public Color getColor() {
		return this.color;
	}

	public int getDanio() {
		return danio;
	}
	
}
