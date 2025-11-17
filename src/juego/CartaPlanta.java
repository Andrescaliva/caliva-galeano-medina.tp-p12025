package juego;

import java.awt.Color;

import entorno.Entorno;

public class CartaPlanta {
	private int cartaRoseX;
	private int cartaRoseY;
	private int cartaAncho;
	private int cartaAlto;
	private int tiempoCarga=150; //5 segs a 60 FPS
    private int tiempoDisp=tiempoCarga; 
	
	public CartaPlanta(int x, int y, int alto, int ancho, int tiempoDisp) {
		this.cartaRoseX=x;
		this.cartaRoseY=y;
		this.cartaAlto=alto;
		this.cartaAncho=ancho;
		this.tiempoDisp=tiempoDisp;
	}
	
	public void Dibujar(Entorno e) {
		e.dibujarRectangulo(cartaRoseX,cartaRoseY,cartaAncho,cartaAlto,0,Color.PINK);
		e.cambiarFont("Arial", 12, Color.black);
		e.escribirTexto("Rose Blade" + tiempoDisp, 20, 30);
		int barraAncho=(int)(cartaAncho*(1.0-(double)tiempoDisp/tiempoCarga));
		e.dibujarRectangulo(cartaRoseX-cartaAncho/2+barraAncho,cartaRoseY+cartaAlto/2-5,barraAncho,10,0,Color.GREEN);
	}
	
	public boolean puedeUsar() {
		return tiempoDisp>=tiempoCarga;
	}
	
	public void reiniciarCarga() {
		tiempoDisp=0;//Pone el tiempo a 0
	}
	
	public void actualizarTicks() {
		if(tiempoDisp<tiempoCarga) {
			tiempoDisp++;//Incrementa ticks en cada fotograma
		}
	}
	
	public boolean contienePunto(int px, int py) {
		return px>=cartaRoseX-cartaAncho/2 && px<=cartaRoseX+cartaAncho/2 && py>=cartaRoseY-cartaAlto/2 && py<=cartaRoseY+cartaAlto/2;
	}

	public int getCartaRoseX() {
		return cartaRoseX;
	}

	public void setCartaRoseX(int cartaRoseX) {
		this.cartaRoseX = cartaRoseX;
	}

	public int getCartaRoseY() {
		return cartaRoseY;
	}

	public void setCartaRoseY(int cartaRoseY) {
		this.cartaRoseY = cartaRoseY;
	}

	public int getCartaAncho() {
		return cartaAncho;
	}

	public void setCartaAncho(int cartaAncho) {
		this.cartaAncho = cartaAncho;
	}

	public int getCartaAlto() {
		return cartaAlto;
	}

	public void setCartaAlto(int cartaAlto) {
		this.cartaAlto = cartaAlto;
	}

	public int getTiempo() {
		return tiempoDisp;
	}

	public void setTiempo(int tiempo) {
		this.tiempoDisp = tiempo;
	}
	
	
	
	

}
