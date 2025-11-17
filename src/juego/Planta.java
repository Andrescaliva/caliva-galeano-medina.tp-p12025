package juego;

import java.awt.*;

import entorno.Entorno;
import entorno.Herramientas;

public class Planta {
	private int x ;   //Fila 
	private int y ;  //Columna
	private int vida ;
	private int diametro ;
	private boolean seleccionada ;
	private boolean roseBlade;
	private int intervalosDisparos;
	private int contadorDisparos;
	
	public Planta(int x, int y,boolean roseBlade) {
		this.x = x;
		this.y = y;
		this.vida = roseBlade ?2:3;
		this.seleccionada=false;
		this.diametro= 37;
		this.roseBlade=roseBlade;
		this.intervalosDisparos=45; //ticks entre Disparos
		this.contadorDisparos=0;
		
	}
	
	
	public void dibujar(Entorno e) {
		Image roseblade=Herramientas.cargarImagen("rosa.png"); 
		e.dibujarImagen(roseblade, x, y, 0, 0.1);
		if(roseBlade) {
			e.dibujarImagen(roseblade, x, y, 0, 0.1);
		}
	}
	
	public Disparo disparar() {
		return new Disparo(this.x,this.y,5,5,Color.yellow,1);
	}
	
	public Disparo actualizarEstado() {
		if(!roseBlade){
			return null;
		}
		
		if(contadorDisparos>0) {
			contadorDisparos--;
			return null;
		}
		
		contadorDisparos=intervalosDisparos;
		return disparar();
			
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
