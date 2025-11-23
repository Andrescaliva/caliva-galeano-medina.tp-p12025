package juego;


import java.awt.Color;
import entorno.Entorno;


public class Casillero {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private boolean esVerde;
	private boolean ocupada;

	public Casillero(int x, int y, int ancho, int alto, boolean esVerde){
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.esVerde=esVerde;
		this.ocupada=false;
	}
	
	public void dibujar(Entorno entorno){
		if(esVerde){
			entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.GREEN);
		}else{
			entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.GREEN.darker());
		}
	}
	
	public boolean estaDisponible() {
		return !ocupada;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public int getAncho(){
		return ancho;
	}

	public int getAlto(){
		return alto;
	}
	public boolean isOcupada() {
		return ocupada;
	}
	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}
	
}

