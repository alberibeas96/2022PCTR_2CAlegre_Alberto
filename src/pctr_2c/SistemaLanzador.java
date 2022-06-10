package pctr_2c;

public class SistemaLanzador {

	public static void main(String[] args) {
		
		IJuego juego= new Juego();
		int tipoEnemigo = 0;
		
		System.out.println("Empieza el juego");
		
		for (int i = 0; i < Integer.parseInt(args[0]); i++) {
			
			
			// Creación de hilos de entrada
			ActividadEnemiga enemigos = new ActividadEnemiga(tipoEnemigo,juego);
			new Thread (enemigos).start();
			
			// Creación de hilos de salida
			ActividadAliada aliados = new ActividadAliada(tipoEnemigo,juego);
			new Thread (aliados).start();	
		}
	}
}
