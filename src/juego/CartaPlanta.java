package juego;

public class CartaPlanta {
	private int cartaRoseX;
	private int cartaRoseY;
	private int cartaAncho;
	private int cartaAlto;
	private int tiempo;
	
	public CartaPlanta(int x, int y, int alto, int ancho) {
		this.cartaRoseX=x;
		this.cartaRoseY=y;
		this.cartaAlto=alto;
		this.cartaAncho=ancho;
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
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	
	
	
	

}
