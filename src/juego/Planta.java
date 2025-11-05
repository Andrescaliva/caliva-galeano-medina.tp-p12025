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
					this.contadorDisparos=intervalosDisparos;
				}
			}
		}
	}
	
	public boolean contienePunto(int px, int py) {
		return Math.hypot(px-x, py-y)<diametro/2.0;
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
	
	
	
	public void moverArriba(int desplazamiento, Casillero[][] tablero, Planta[][] plantas, int fila, int columna) {
		
	     int nuevoY=this.y -= desplazamiento;
	     if(nuevoY-diametro/2>90&&!casillaOcupada(x,nuevoY,tablero,plantas, fila,columna)) {
				this.y=nuevoY;
			}
	 }

	 public void moverAbajo(int desplazamiento, Casillero[][] tablero, Planta[][] plantas, int fila, int columna) {
		  
	     int nuevoY=this.y += desplazamiento;
	     if(nuevoY-diametro/2<600&&!casillaOcupada(x,nuevoY,tablero,plantas,fila,columna)) {
	    	 this.y=nuevoY;
	     }
	 }

	 public void moverIzquierda(int desplazamiento, Casillero[][] tablero, Planta[][] plantas, int fila, int columna) {
	     int nuevoX=this.x -= desplazamiento;
	     if(nuevoX-diametro/2>0&&!casillaOcupada(nuevoX,y,tablero,plantas,fila,columna)) {
	    	 this.x=nuevoX;
	     }
	 }

	 public void moverDerecha(int desplazamiento, Casillero[][] tablero, Planta[][] plantas, int fila, int columna) {
		 int nuevoX=this.x += desplazamiento;
		 if(nuevoX-diametro/2<800&&!casillaOcupada(nuevoX,y,tablero,plantas,fila,columna)) {
			 this.x=nuevoX;
		 }
	 }
	 
	 
	 public boolean casillaOcupada(int px, int py, Casillero[][] tablero, Planta[][] plantas, int fila, int columna) {
		for(int i=0;i<fila;i++) {
			for(int j=0;j<columna;j++) {
				Casillero c=tablero[i][j];
				//Verfiicacion si la posicion esta dentro de la casilla
				if(Math.abs(px-c.getX())<=c.getAncho()/2 && Math.abs(py-c.getY())<=c.getAlto()/2) {
					return plantas[i][j] != null && plantas[i][j]!=this;// Devuelve true si hay una planta dentro de la casilla actual y no en la actual
				}
			}
		}
		return false; //Indica que la casilla no esta ocupada
	}
		


	public void setX(int x) {
		this.x = x;
	}


	public void setY(int y) {
		this.y = y;
	}

}
