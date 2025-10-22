package juego;

import java.awt.Color;
import entorno.Entorno;

public class Grinch {
	private int x;//Fila
	private int y;//Columna
	private double velocidad;
	private double vida;
	//private Image imagenGrinch;
	
	
	
	public Grinch(int x) {
		this.x=x;
		this.y=8;
		this.velocidad=1;
		this.vida=2;
			
	}
	

	public void dibujarGrinch(Entorno e) {
		e.dibujarRectangulo(x, y, 0, 5, 0, Color.GREEN);
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

}
