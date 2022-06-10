package pctr_2c;

public class SistemaLanzador {

	public static void main(String[] args) {
		
		//IParque parque = new Parque();
		//char letra_puerta = 'A';
		
		//System.out.println("¡Parque abierto!");
		
		for (int i = 0; i < Integer.parseInt(args[0]); i++) {
			
			//String puerta = ""+((char) (letra_puerta++));
			
			// Creación de hilos de entrada
			ActividadEnemiga enemigos = new ActividadEnemiga(M, tipoEnemigo);
			new Thread (enemigos).start();
			
			// Creación de hilos de salida
			ActividadAliada aliados = new ActividadAliada(M, tipoenemigo);
			new Thread (aliados).start();	
		}
	}
}
