package juego;

import java.awt.Color;
import java.util.Random;

import entorno.Entorno;

public class Grinch {
	private double x;//Fila
	private double y;//Columna
	private double velocidad;
	private double vida;
	private int tamanio;

	//private Image imagenGrinch;
	
	
	
	public Grinch(double x) {
		Random orden=new Random();
		int[] opciones ={60,180,300,420,540}; // centro de las filas
		this.x=x;
		this.y=opciones[orden.nextInt(5)];
		this.velocidad=1;
		this.vida=2;
		this.tamanio= 40;
		orden.nextInt(5);
	}
	

	public void dibujarGrinch(Entorno e) {
		e.dibujarRectangulo(x, y, 20, 10, 0, Color.RED);
		//dibujarRectangulo(x, 20, 20, 10, 0, Color.RED);
	}
	
	
	public void moverIzquierda() {//Da el moviemiento de un zombie grinch
		if(x>0) {
			x--;
		}
	}
	
	public void impactoPlanta() {//Cuando el zombie grinch recibe daño de una planta
		vida--;
	}
	
	public boolean grinchMuerto() {//Indica cuando un grinch zombie esta muerto
		if(vida<=0) {
			return true;
		}
		return false;
	}
	
	public boolean chocaConRegalo(Regalos r) {
		return x+tamanio/2>=r.getX()&&y+tamanio>=r.getY();
	}


	public double getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}

}
