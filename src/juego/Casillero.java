package juego;
import java.awt.Color;
import entorno.Entorno;


public class Casillero {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private boolean esVerde;

	public Casillero(int x, int y, int ancho, int alto, boolean esVerde){
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.esVerde=esVerde;

	}
	public void dibujar(Entorno entorno){
		if(esVerde){
			entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.GREEN);
		}else{
			entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.GREEN.darker());
		}
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
}

