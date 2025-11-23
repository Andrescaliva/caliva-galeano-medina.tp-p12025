package juego;


import java.awt.Color;
import java.util.Random;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	//Tamaño del tablero
	private int fila=5;  // usar dentro los metodos
	private int columna=10; // usar dentro los metodos
	private Casillero[][] tablero;
	private Grinch[] zombieGrinch;//Zombies grinch
	private Planta[][] plantas;//Matriz de plantas
	private Regalos[] regalos;
	private Disparo[] disparos;
	private int minimioTiempoRegeneracion;
	private int maximoTiemporRegernacion;
	private int totalEnemigos;
	private int enemigosGenerados;
	private int maxZombiesSimultaneos;
	private int zombiesEliminados;
	private int zombiesRestantes;
	private int tiempo;
	private Random random;// usar dentro los metodos
	private BarraSuperior barraSuperior;

	//Carta de planatas para el HUD
	private boolean arrastrando=false;
	private Planta plantaPrevizualizada=null;
	private CartaPlanta carta;
	private int tiempoTranscurrido=0;
	//private int cartaRoseX=80;  //Clase carta
	//private int cartaRoseY=30; //Clase carta
	//private int cartaAncho=60; //Clase carta
	//private int cartaAlto=40; //Clase carta
	int tiempoDisp = 500;//Clase carta
	


	private boolean juegoTerminado=false;
	private boolean juegoGanado=false;


	public Juego() {
		
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "La inavasion de los zombies Grinch - Grupo 6 - Caliva - Galeano -Medina", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		
		this.zombieGrinch=new Grinch[15];   //arreglo con 15 zombies
		//zombieGrinch[0] = new Grinch(900);  //En la posición 0 creo un zombie nuevo en la posición X = 900
   
		//this.grinch=new Grinch(800);        //gricn en la pos 800
		this.random=new Random();
		this.plantas=new Planta[fila][columna];  //Matriz de plantas
		this.regalos = new Regalos[fila];     //Crea un arrego de regaloscon tantas posiciones como filas hay en el tablero
		this.disparos=new Disparo[50];	  //arreglo para los disparos
		this.carta=new CartaPlanta(80,30,60,40,500);
		this.barraSuperior = new BarraSuperior();
		this.minimioTiempoRegeneracion=60;
		this.maximoTiemporRegernacion=180;
		this.totalEnemigos=60;
		this.enemigosGenerados=0;
		this.maxZombiesSimultaneos=10;
		this.zombiesEliminados=0;
		this.zombiesRestantes=totalEnemigos;
		
		this.tiempo=random.nextInt(maximoTiemporRegernacion-minimioTiempoRegeneracion+1)+minimioTiempoRegeneracion; 


		// Define el desplazamiento (70pixeles)
		int yInicialDesplazamiento = 70; 

		// Nuevo Alto del área de juego (600 - 70 = 530)
		int altoAreaJuego = entorno.alto() - yInicialDesplazamiento; 

		// Nuevo altoCasillero (530 / 5 = 106)
		int anchoCasillero = entorno.ancho() / columna; 
		int altoCasillero = altoAreaJuego / fila;   // 106 de alto cada casillero  (106*5 = 530)

		// Posición X (Columna 0, centrada):
		int xInicialRegalo = anchoCasillero / 2;
		
		
		// 5 regalos, uno por fila
		for (int i = 0; i < fila; i++) {
		    int yInicialRegalo = yInicialDesplazamiento + (i * altoCasillero) + (altoCasillero / 2);    // centro y del regalo , el 70 mueve todo hacia abajo 
		    this.regalos[i] = new Regalos(xInicialRegalo, yInicialRegalo); 
		}

    //Creacion del tablero
	  this.tablero = new Casillero[fila][columna];

		
		// Recorremos todas las filas.
		for (int i = 0; i < fila; i++) {
			
        // Dentro de cada fila, recorremos todas las columnas.
			for (int j = 0; j < columna; j++) {
				int x=j*anchoCasillero+anchoCasillero/2;
				
                // AHORA SUMA EL DESPLAZAMIENTO
				int y= yInicialDesplazamiento + i*altoCasillero + altoCasillero /2;
				
				// La suma de la fila (i) y la columna (j) determina el color de la casilla.
				// Si la suma es par, es una casilla verde claro.
				if ((i + j) % 2 == 0) {
					tablero[i][j] = new Casillero(x, y, anchoCasillero, altoCasillero,true);
				} else {
                // Si la suma es impar, es una casilla verde oscuro.
					tablero[i][j] = new Casillero(x, y, anchoCasillero, altoCasillero, false);
				}
				      
			}
		}

		int testFila=2;
		int testColumna=1;
		int px=tablero[testFila][testColumna].getX();
		int py=tablero[testFila][testColumna].getY();	
		this.plantas[testFila][testColumna]=new Planta(px,py,true);
		this.plantas[testFila][testColumna].setSeleccionada(true);
		
		// Inicia el juego!
		this.entorno.iniciar();
		
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{	
		tiempoTranscurrido++;
		carta.actualizarTicks();//actualiza la carga de disponibildad planta
		
		
		this.barraSuperior.dibujar(entorno, zombiesEliminados, zombiesRestantes, tiempoTranscurrido/60);
		this.carta.Dibujar(entorno);
		
		//Verificacion de victoria o derrota
		if(zombiesEliminados>=totalEnemigos) {
			juegoTerminado=true;
			juegoGanado=true;
		 }
		
		entorno.cambiarFont("Arial", 20, Color.RED);
		if(juegoTerminado) {
			if(juegoGanado) {
				entorno.escribirTexto("¡Has ganado!", entorno.ancho()/2, entorno.alto()/2);
				return;
			}else{
				entorno.escribirTexto("Game over", entorno.ancho()/2, entorno.alto()/2);
				return;
			}
		}
		
        //Dibujo del tablero
		for (int i = 0; i < fila; i++) {
//        // Dentro de cada fila, recorremos todas las columnas.
			for (int j = 0; j < columna; j++) {
				if(tablero[i][j]!=null){
					tablero[i][j].dibujar(entorno);
				}
			}

		}
        
		//Manjeo de arrastre de la carta de planta
		arrastrePlanta();


		
		if(enemigosGenerados<totalEnemigos){
			tiempo--;
			//Cuenta cuantos zombies activos hay en el tablero
			int activos=contadorActivos(zombieGrinch);
			if(tiempo<=0 && activos<maxZombiesSimultaneos) {
				
				int zombiesAntes=activos;
				
				Grinch.generacionZombiesAleatorios(zombieGrinch, tablero, fila, columna, entorno, random);
				int zombiesDespues=contadorActivos(zombieGrinch);
				if(zombiesAntes>zombiesDespues) {
					enemigosGenerados++;
					zombiesRestantes=Math.max(0, totalEnemigos);
					tiempo=random.nextInt(maximoTiemporRegernacion-minimioTiempoRegeneracion+1)+minimioTiempoRegeneracion;
				}
			}
		}
		
		//Creacion de los zombies grinch dentro del tablero con sus respectivos moviementos
		for(int i=0;i<zombieGrinch.length;i++) {
			if(zombieGrinch[i]!=null) {
				zombieGrinch[i].moverIzquierda();
				zombieGrinch[i].dibujarGrinch(entorno);
				
				boolean eliminado=false;
				//Si el grinch se sale de la pantalla
				if(zombieGrinch[i].getX()<0) {
					zombieGrinch[i]=null;
					eliminado=true;
				} else if(zombieGrinch[i].grinchMuerto()) {
					zombieGrinch[i]=null;
					zombiesEliminados++;
					eliminado=true;
				}
				
				if(eliminado) {
					continue;
				}
				Planta planta=obtenerPlantaEnPosicion(zombieGrinch[i].getX(),zombieGrinch[i].getY());
				if(planta!=null) {
					quitarPlanta(planta); //Elimina planta 
					zombieGrinch[i].ralentizacion(60);//
					
				}
				
				//Si el grinch entra en contacto con el regalo
				for(int j=0;j<regalos.length;j++) {
					if(regalos[j]!=null&&zombieGrinch[i].chocaConRegalo(regalos[j])) {
						juegoTerminado=true;
						juegoGanado=false;
						break;
					}
				}
			}
			
		}
		
		
		//Control y dibujo de los disparos, movimiento, dibujo y colision de los grinch
		for(int i=0;i<disparos.length;i++){
			Disparo d=disparos[i];
			if(d==null) continue;
			if(!d.isActivado()){
				disparos[i]=null;
				continue;
			}

			d.moverDerecha();
			d.dibujar(entorno);
            //Fuera de pantalla: eliminar zombie
			if(d.getX()>entorno.ancho()){
				disparos[i]=null;
				continue;
			}
            //Varifica colision con grinch
			boolean impacto=false;
			for(int j=0;j<zombieGrinch.length;j++){
				Grinch g=zombieGrinch[j];
				if(g==null) continue;

				double dx=d.getX()-g.getX();
				double dy=d.getY()-g.getY();
				double distancia=Math.sqrt(dx*dx+dy*dy);
				double alcance=d.getRadio()+g.getTamanio()/2.0;

				if(distancia<alcance){
					//Hay impacto
					g.recibirDanio(d.getDanio());
					disparos[i]=null;
					impacto=true;
					// Verificar si el grinch murio y aumerntar el contador de zombies eliminados
					if(g.grinchMuerto()) {
						zombieGrinch[j]=null;
						zombiesEliminados++;
					}
					break;
				}
			}
			if(impacto) continue;
		}
		
	//Dibujar regalos
	
	for (int i = 0; i < regalos.length; i++) {
	    if (regalos[i] != null) {
	        regalos[i].dibujar(entorno);
	    }
	}
	// dibujar plantas 
	for(int i=0;i<fila;i++){
		for(int j=0;j<columna;j++){
			Planta p = plantas[i][j];
			if(p!=null){
				p.dibujar(entorno);
				Disparo nuevo =p.actualizarEstado();
				if(nuevo!=null) {
					disparo(nuevo);
				}
			}
		}
	}
	if(plantaPrevizualizada!=null){
		plantaPrevizualizada.dibujar(entorno);
	}
	
	
	//Movimiento de la planta selecccionada
	
	for(int i=0;i<fila;i++) {
		for(int j=0;j<columna;j++) {
			Planta planta=plantas[i][j];
			if(planta != null && planta.isSeleccionada()) {
			     int velocidad = 5;  // pixeles por tick
			     
			     if(entorno.estaPresionada(entorno.TECLA_ARRIBA)&&planta.getY()-velocidad-planta.getDiametro()>barraSuperior.getAlturaBanda()) { // && !planta.tocaCasillaocupada(tablero)) {
//			         planta.moverArriba(velocidad);
			         planta.moverArriba(velocidad, tablero,plantas,fila,columna);
			     }
			     if(entorno.estaPresionada(entorno.TECLA_ABAJO)&&planta.getY()+velocidad+planta.getDiametro()<entorno.alto()) {
			         planta.moverAbajo(velocidad,tablero,plantas,fila,columna);
			     }
			     if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)&&planta.getX()-velocidad-planta.getDiametro()>0) {
			         planta.moverIzquierda(velocidad,tablero,plantas,fila,columna);
			     }
			     if(entorno.estaPresionada(entorno.TECLA_DERECHA) && planta.getX()+velocidad+planta.getDiametro() <entorno.ancho()) {
			         planta.moverDerecha(velocidad,tablero,plantas,fila,columna);
			     } 
			     planta.dibujar(entorno);
			 }
		}
	}
	  
	
	
}
	
	
	
	
	//Metodo auxiliar para el control de los disparos
	public void disparo(Disparo d){
		for(int i=0;i<disparos.length;i++){
			if(disparos[i]==null){
				disparos[i]=d;
				break;
			}
		}
	}

	//Metodo auxiliar para obtener las coordenadas del casillero en base a las coordenadas X e Y
	public int[] cooodenadasCasillero(int x, int y){
		for(int i=0;i<fila;i++){
			for(int j=0;j<columna;j++){
				Casillero c=tablero[i][j];
				if(Math.abs(x-c.getX())<=c.getAncho()/2 && Math.abs(y-c.getY())<=c.getAlto()/2){
					return new int[] {i,j};
				}
			}
		}
		return null;
	}
    
	//Metodo auxiliar para deseleccionar todas las plantas del tablero
	public void deseleccionarTodas(){
		for(int i=0;i<fila;i++){
			for(int j=0;j<columna;j++){
				if(plantas[i][j]!=null){
					plantas[i][j].setSeleccionada(false);
				}
			}
		}
	}
	
	private Planta obtenerPlantaEnPosicion(double x,double y) {
		for(int i=0;i<fila;i++) {
			for(int j=0;j<columna;j++) {
				Planta p= plantas[i][j];
				if(p!=null&&Math.abs(p.getX()-x)<p.getDiametro()/2 && Math.abs(p.getY()-y)<p.getDiametro()/2) {
					return p;
				}
			}
		}
		return null;
	}
	
	public void quitarPlanta(Planta p) {
		for(int i=0;i<fila;i++) {
			for(int j=0;j<columna;j++) {
				if(plantas[i][j]==p) {
					plantas[i][j]=null;
					return;
				}
			}
		}
	}
	
	public int contadorActivos(Grinch[] a) {
		int contador=0;
		for(int i=0;i<a.length;i++) {
			if(a[i]!=null) {
				contador++;
			}
		}
		return contador;
	}
	
	public void arrastrePlanta() {
		//Manjeo de arrastre de la carta de planta
			if(!arrastrando && entorno.estaPresionado(entorno.BOTON_IZQUIERDO)){
				int mouseX=entorno.mouseX();
				int mouseY=entorno.mouseY();
				if(carta.contienePunto(mouseX, mouseY)&&carta.puedeUsar()){
					arrastrando=true;
					plantaPrevizualizada=new Planta(mouseX,mouseY,true);
				} else{
						//click fuera de la carta
					deseleccionarTodas();
					for(int i=0;i<fila;i++){
						for(int j=0;j<columna;j++){
							Planta p=plantas[i][j];
							if(p!=null&&p.contienePunto(mouseX, mouseY)){
							//sellecciona unicamente esa planta para el tablero
								p.setSeleccionada(true);	
							}
						}
					}
				}
			}

			//mientras mantenga presionado, actualiza la vista previa del mouse
			if(arrastrando && entorno.estaPresionado(entorno.BOTON_IZQUIERDO)){
				int mouseX=entorno.mouseX();
				int mouseY=entorno.mouseY();
				if(plantaPrevizualizada!=null){
						plantaPrevizualizada.moverPlanta(mouseX-plantaPrevizualizada.getX(), mouseY-plantaPrevizualizada.getY());
				}
			}

			//soltar la planta en el tablero y deteccion de casilletro libre u ocupado
			if(arrastrando && !entorno.estaPresionado(entorno.BOTON_IZQUIERDO)){
				int mouseX=entorno.mouseX();
				int mouseY=entorno.mouseY();
				int[] ij= cooodenadasCasillero(mouseX,mouseY);
				if(ij!=null){
					int fi=ij[0];
					int co=ij[1];
					//Si hay un casillero libre,  se coloca la planta
					if(plantas[fi][co]==null){
						int px=tablero[fi][co].getX();
						int py=tablero[fi][co].getY();
						plantas[fi][co]=new Planta(px,py,true);//Roseblade por defecto
						carta.reiniciarCarga();
					}
				}
				plantaPrevizualizada=null;
				arrastrando=false;
		}
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}