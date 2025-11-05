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
	private int tickLento=0;
	private int filaObjetivo;
	private boolean generado=false;

		
	public Grinch(double x,double y, int filaObjetivo, int filaInicial) {
		// centro de las filas
		this.x=x;
		this.y=y;
		this.velocidad=1;
		this.vida=2;
		this.tamanio= 20;
		this.filaObjetivo=filaObjetivo;
	}
	

	public void dibujarGrinch(Entorno e) {
		e.dibujarCirculo(this.x, this.y, 30, Color.RED);
		e.dibujarCirculo(this.x, this.y, 25, Color.black);
		e.dibujarCirculo(this.x, this.y, 18, Color.red);
		e.dibujarCirculo(this.x, this.y, 12, Color.black);
		e.dibujarCirculo(this.x, this.y, 8, Color.red);

	}
	
	
	public void moverIzquierda() {   //Da el moviemiento de un zombie grinch
		if(tickLento>0) {
			tickLento--;
		}
		if(filaObjetivo!=-1&&(int)y!=filaObjetivo) {
			this.y+=(filaObjetivo>y) ? velocidad:--velocidad;
		}else {
			this.x-=velocidad;
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
	
	public void ralentizacion(int ticks) {
		tickLento=ticks;
		
	}
	
	public void recibirDanio(int danio) {
		this.vida-=danio;
	}
	
	
	public static void generacionZombiesAleatorios(Grinch[] zombieGrinch, Casillero[][] tablero, int fila, int columna, Entorno entorno, Random random) { //Pasar a grinch
		//Busca la primera posicion nula del arreglo para crear un nuevo zombie
		for(int i=0;i<zombieGrinch.length;i++) {
			if(zombieGrinch[i]==null) {
				
				int filaInicialValida=1; //La primera fila valida pra el juego
				if(fila<filaInicialValida) {
					return;
				}
				int numeroFilasValidas=fila-filaInicialValida;
				
				//Crea un nuevo zombie fuera de pantalla
				int filaAleatoria=random.nextInt(numeroFilasValidas);
				int yFila=tablero[filaAleatoria][columna-1].getY();
				zombieGrinch[i]=new Grinch(entorno.ancho()+50,yFila,-1,filaAleatoria);
				return;
			}
		}
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


	public boolean isGenerado() {
		return generado;
	}


	public void setGenerado(boolean generado) {
		this.generado = generado;
	}

}
