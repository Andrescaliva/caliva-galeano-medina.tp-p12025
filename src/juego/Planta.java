package juego;

import java.awt.Color;

import entorno.Entorno;

public class Planta {
	private int x ;   //Fila 
	private int y ;  //Columna
	private int vida ;
	private int diametro ;
	private boolean seleccionada ;
	private boolean disparoListo;
	private String tipoPlanta;
	
	public Planta(int x, int y) {
		this.x = x;
		this.y = y;
		this.vida = 3;
		this.seleccionada=false;
		this.diametro= 37;
		this.tipoPlanta="RoseBlade";
		this.disparoListo=false;
		
	}
	
	
	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, diametro,Color.YELLOW);
	}
	
	public boolean disparoPlanta(Disparo d) {
		if(tipoPlanta.equals("RoseBlade")&&disparoListo){
			disparoListo=false;
			return true;
		}
		return false;
	}
	
	public int getX() {
	    return x;
	}

	public int getY() {
	    return y;
	}


	public String getTipoPlanta() {
		return tipoPlanta;
	}


	public void setTipoPlanta(String tipoPlanta) {
		this.tipoPlanta = tipoPlanta;
	}


	public int getDiametro() {
		return diametro;
	}


	public boolean isSeleccionada() {
		return seleccionada;
	}


	public boolean isDisparoListo() {
		return disparoListo;
	}

}
