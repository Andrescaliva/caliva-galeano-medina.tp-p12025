package juego;

import java.awt.Color;
import entorno.Entorno;

public class Grinch {
	private int x;//Fila
	private int y;//Columna
	private double velocidad;
	private double vida;
	//private Image imagenGrinch;
	private Color color;
	
	
	public Grinch(int x, int y) {
		this.x=5;
		this.y=8;
		this.velocidad=1;
		this.vida=2;
			
	}

	public void dibujarGrinch(Entorno e) {
		e.dibujarRectangulo(x, y, 0, 5, 0, color.green);
	}
	
	
	public void moverIzquierda(Entorno e) {//Da el moviemiento de un zombie grinch
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
