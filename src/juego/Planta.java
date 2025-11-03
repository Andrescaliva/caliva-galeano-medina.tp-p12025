package juego;

import java.awt.Color;

import entorno.Entorno;

public class Planta {
	private int x ;   //Fila 
	private int y ;  //Columna
	private int vida ;
	private int diametro ;
	private boolean seleccionada ;
	private boolean roseBlade;
	private int intervalosDisparos;
	private int contadorDisparos;
	private Juego juego;
	
	public Planta(int x, int y,boolean roseBlade, Juego juego) {
		this.x = x;
		this.y = y;
		this.vida = roseBlade ?2:3;
		this.seleccionada=false;
		this.diametro= 37;
		this.roseBlade=roseBlade;
		this.juego=juego;
		this.intervalosDisparos=45; //ticks entre Disparos
		this.contadorDisparos=0;
		
	}
	
	
	public void dibujar(Entorno e) {
		Color colorBase=seleccionada?Color.BLUE:Color.YELLOW; 
		e.dibujarCirculo(x, y, diametro,colorBase);
		if(roseBlade) {
			e.dibujarCirculo(x, y, diametro/2, Color.RED);
		}
	}
	
	
	public void actualizarEstado() {
		if(roseBlade) {
			if(contadorDisparos>0) {
				contadorDisparos--;
			}else {
				//dispara hacia la derecha desde el borde de la planata
				if(juego!=null) {
					double origenX=this.x+this.diametro/2.0+4;
					double origenY=this.y;
					juego.disparar(origenX,origenY);
				}
				this.contadorDisparos=intervalosDisparos;
			}
		}
	}
	
	public boolean contienePunto(int px, int py) {
		double distancia=Math.sqrt(Math.pow(px-x, 2)+Math.pow(py-y, 2));
		return distancia<diametro/2.0;
	}
	
	
	public void moverPlanta(int dx, int dy) {
		if(seleccionada) {
			this.x+=dx;
			this.y+=dy;
		}else {
			this.x+=dx;
			this.y+=dy;
		}
	}
	
	
	
	 
	
	
	public void recicbirDanio(int danio) {
		this.vida-=danio;
	}
	
	public boolean plantaMuerta() {
		return vida<=0;
	}
	
	
	//Getters corrresponidentes a planta
	public int getX() {
	    return x;
	}

	public int getY() {
	    return y;
	}


	public int getDiametro() {
		return diametro;
	}


	public boolean isSeleccionada() {
		return seleccionada;
	}


	public boolean isRoseBlade() {
		return roseBlade;
	}


	public void setSeleccionada(boolean seleccionada) {
		this.seleccionada = seleccionada;
	}
	
	
	
	public void moverArriba(int desplazamiento) {
	     this.y -= desplazamiento;
	 }

	 public void moverAbajo(int desplazamiento) {
	     this.y += desplazamiento;
	 }

	 public void moverIzquierda(int desplazamiento) {
	     this.x -= desplazamiento;
	 }

	 public void moverDerecha(int desplazamiento) {
	     this.x += desplazamiento;
	 }


	public void setX(int x) {
		this.x = x;
	}


	public void setY(int y) {
		this.y = y;
	}

}
