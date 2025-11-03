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
	private int fila=5;
	private int columna=10;
	private Casillero[][] tablero;
	private Grinch[] zombieGrinch;//Zombies grinch
	private Planta[][] plantas;//Matriz de plantas
	private Regalos[] regalos;
	private Disparo[] disparos;
	private int minimioTiempoRegeneracion=60;
	private int maximoTiemporRegernacion=180;
	private int totalEnemigos=60;
	private int enemigosGenerados=0;
	private int maxZombiesSimultaneos=10;
	private int zombiesEliminados=0;
	private int zombiesRestantes=totalEnemigos;
	private int tiempo;
	private Random random;
	private BarraSuperior barraSuperior;

	//Carta de planatas para el HUD
	private boolean arrastrando=false;
	private Planta plantaPrevizualizada=null;
	private int cartaRoseX=80;
	private int cartaRoseY=30;
	private int cartaAncho=60;
	private int cartaAlto=40;


	private boolean juegoTerminado=false;
	private boolean juegoGanado=false;


	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Titulo de TP - Grupo N - Apellido1 - Apellido2 -Apellido3", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		
		this.zombieGrinch=new Grinch[15];   //arreglo con 15 zombies
		//zombieGrinch[0] = new Grinch(900);  //En la posición 0 creo un zombie nuevo en la posición X = 900

		//this.grinch=new Grinch(800);        //gricn en la pos 800
		this.plantas=new Planta[fila][columna];  //Matriz de plantas
		this.regalos = new Regalos[fila];     //Crea un arrego de regaloscon tantas posiciones como filas hay en el tablero
		this.disparos=new Disparo[50];	  //arreglo para los disparos
		this.barraSuperior = new BarraSuperior();
		this.random=new Random();
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
		this.plantas[testFila][testColumna]=new Planta(px,py,true,this);
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
		this.barraSuperior.dibujar(entorno);
		entorno.dibujarRectangulo(cartaRoseX,cartaRoseY,cartaAncho,cartaAlto,0,Color.PINK);
		entorno.cambiarFont("Arial", 18, Color.black);
		entorno.escribirTexto("Rose Blade", (double)cartaRoseX, (double)cartaRoseY);
        
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
		if(!arrastrando && entorno.estaPresionado(entorno.BOTON_IZQUIERDO)){
			int mouseX=entorno.mouseX();
			int mouseY=entorno.mouseY();
			if(mouseX>=cartaRoseX-cartaAncho/2 && mouseX<=cartaRoseX+cartaAncho/2 && mouseY>=cartaRoseY-cartaAlto/2 && mouseY<=cartaRoseY+cartaAlto/2){
				arrastrando=true;
				plantaPrevizualizada=new Planta(mouseX,mouseY,true,this);
			} else{
				//click fuera de la carta
				int mouseX2=mouseX;
				int mouseY2=mouseY;
				for(int i=0;i<fila;i++){
					for(int j=0;j<columna;j++){
						Planta p=plantas[i][j];
						if(p!=null&&p.contienePunto(mouseX2, mouseY2)){
						//sellecciona unicamente esa planta para el tablero
						deseleccionarTodas();
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
					plantas[fi][co]=new Planta(px,py,true,this);//Roseblade por defecto
				}
			}
			plantaPrevizualizada=null;
			arrastrando=false;
		}


		
		if(enemigosGenerados<totalEnemigos){
			tiempo--;
			//Cuenta cuantos zombies activos hay en el tablero
			int activos=0;
			for(int i=0;i<zombieGrinch.length;i++) {
				if(zombieGrinch[i]!=null) {
					activos++;
				}
			}
			if(tiempo<=0 && activos<maxZombiesSimultaneos) {
				//Busca la primera posicion nula del arreglo para crear un nuevo zombie
				for(int i=0;i<zombieGrinch.length;i++) {
					if(zombieGrinch[i]==null) {
						//Crea un nuevo zombie fuera de pantalla
						int filaAleatoria=random.nextInt(fila);
						int yFila=tablero[filaAleatoria][columna-1].getY();
						zombieGrinch[i]=new Grinch(entorno.ancho()+50,yFila);
						enemigosGenerados++;
						//Reduce la cantidad de zombies restantes
						zombiesRestantes=Math.max(0, totalEnemigos-enemigosGenerados);
						//reinicia el tiempo de regeneracion
						tiempo=random.nextInt(maximoTiemporRegernacion-minimioTiempoRegeneracion+1)+minimioTiempoRegeneracion;
						break;
					}
				}
			}
		}
		//Creacion de los zombies grinch dentro del tablero con sus respectivos moviementos
		for(int i=0;i<zombieGrinch.length;i++) {
			if(zombieGrinch[i]!=null) {
				zombieGrinch[i].moverIzquierda();
				zombieGrinch[i].dibujarGrinch(entorno);
				//Si el grinch se sale de la pantalla
				if(zombieGrinch[i].getX()<0) {
					zombieGrinch[i]=null;
					continue;
				}
				//Si el grinch se muere
				if(zombieGrinch[i].grinchMuerto()) {
					zombieGrinch[i]=null;
					zombiesEliminados++;
				}
				//Si el grinch entra en contacto con el regalo
				for(int j=0;j<regalos.length;j++) {
					if(regalos[j]!=null&&zombieGrinch[i].chocaConRegalo(regalos[j])) {
						juegoTerminado=true;
						juegoGanado=false;
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
			if(d.getX()>entorno.ancho()+100){
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
				p.actualizarEstado();
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
			     
			     if(entorno.estaPresionada(entorno.TECLA_ARRIBA)&&planta.getY()-velocidad-planta.getDiametro()>barraSuperior.getAlturaBanda()) {
			         planta.moverArriba(velocidad);
			     }
			     if(entorno.estaPresionada(entorno.TECLA_ABAJO)) {
			         planta.moverAbajo(velocidad);
			     }
			     if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			         planta.moverIzquierda(velocidad);
			     }
			     if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			         planta.moverDerecha(velocidad);
			     } 
			     planta.dibujar(entorno);
			 }
		}
	}
	  
	
	//Verificacion de victoria o derrota
	if(zombiesRestantes==0&&zombiesEliminados>=totalEnemigos) {
		juegoTerminado=true;
		juegoGanado=true;
	}
	
	entorno.cambiarFont("Arial", 20, Color.RED);
	if(juegoTerminado) {
		if(juegoGanado) {
			entorno.escribirTexto("¡Has ganado!", entorno.ancho()/2, entorno.alto()/2);
		}else {
			entorno.escribirTexto("¡Has perdido!", entorno.ancho()/2, entorno.alto()/2);
		}
	}
	
}
	
	
	
	
	//Metodo auxiliar para el control de los disparos
	public void disparar(double x, double y){
		for(int i=0;i<disparos.length;i++){
			if(disparos[i]==null){
				disparos[i]=new Disparo(x, y, 5, 5, Color.YELLOW, 1);
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

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}