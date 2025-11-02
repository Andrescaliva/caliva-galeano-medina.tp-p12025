package juego;

import java.awt.Color;
import java.util.Random;

import entorno.Entorno;

public class Grinch {
	private double x;  //Fila
	private double y;  //Columna
	private double velocidad;
	private double vida;
	private int tamanio;

		
	public Grinch(double x) {
		Random orden=new Random();
		int[] opciones ={60,180,300,420,540}; // centro de las filas
		this.x=x;
		this.y=opciones[orden.nextInt(5)];
		this.velocidad=1;
		this.vida=2;
		this.tamanio= 20;
		orden.nextInt(5);
	}
	

	public void dibujarGrinch(Entorno e) {
		e.dibujarCirculo(this.x, this.y, 30, Color.RED);
		e.dibujarCirculo(this.x, this.y, 25, Color.black);
		e.dibujarCirculo(this.x, this.y, 18, Color.red);
		e.dibujarCirculo(this.x, this.y, 12, Color.black);
		e.dibujarCirculo(this.x, this.y, 8, Color.red);

	}
	
	
	public void moverIzquierda() {   //Da el moviemiento de un zombie grinch
		if(x > 0) {
			this.x-=this.velocidad;
		}
	}
		
	public boolean grinchMuerto() {//Indica cuando un grinch zombie esta muerto
		if(vida<=0) {
			return true;
		}
		return false;
	}
	
	public boolean chocaConRegalo(Regalos r) {
		if(r==null) {
			return false;
		}
		double semiladoRegalo=20.0;
		
		double semiradioGrinch=this.tamanio/2.0;
		double dx=this.x-r.getX();
		double dy=this.y-r.getY();
		double distancia=Math.sqrt(dx*dx+dy*dy);
		
		return distancia<=(semiradioGrinch+semiladoRegalo);
	}
	
	public void recibirDanio(int danio) {
		this.vida-=danio;
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


	public int getTamanio() {
		return tamanio;
	}

}
