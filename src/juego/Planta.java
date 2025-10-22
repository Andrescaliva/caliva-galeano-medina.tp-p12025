package juego;

import java.awt.Color;

import entorno.Entorno;

public class Planta {
	private int x;//Fila 
	private int y;//Columna
	private int vida;
	private int diametro;
	private boolean seleccionada;
	
	public Planta(int x, int y) {
		this.x=8;
		this.y=4;
		this.vida=3;
		this.seleccionada=false;
	}
	
	
	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, 0.8,Color.RED);
	}
	
	public void danio(int danio) {// Daño que recibe de los zombie
		vida-=danio;
	}
	
	public boolean plantaDestruida() {
		if(vida<=0) {
			return true;
		}
		return false;
	}
	
	
	public void moverIzquierda(Entorno e) {
		if(x>0) {
			x--;
		}
	}
	
	
	public void moverDerecha(Entorno e) {
		if(x<8) {
			x++;
		}
	}
	
	
	public void moverArriba(Entorno e) {
		if(y>0) {
			y++;
		}
	}
	
	public void moverAbajo(Entorno e) {
		if(y<4) {
			y--;
		}
	}
	//Getters y setters

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public boolean isSeleccionada() {
		return seleccionada;
	}

	public void setSeleccionada(boolean seleccionada) {
		this.seleccionada = seleccionada;
	}

}
