package pctr_2c;

import java.util.Hashtable;

public class Juego  implements IJuego{
	
	private final int MAXENEMIGOS = 50;
	private final int MINENEMIGOS = 0;
	private Hashtable<Integer, Integer> contadoresEnemigosTipo;
	private Hashtable<Integer, Integer> contadoresEliminadosTipo;
	int contadorEnemigosTotales;
	
	

	@Override
	public void generarEnemigo(String tipoEnemigo) {
		// Si no hay enemigos de este tipo se generan.
		if (contadoresEnemigosTipo.get(tipoEnemigo) == null){
			contadoresEnemigosTipo.put(tipoEnemigo, 0);
		}
		
		// Comprobamos si podemos generar enemnigos
		comprobarAntesDeGenerar();
				
		
		// Aumentamos el contador total y el individual
		contadorEnemigosTotales++;		
		contadoresEnemigosTipo.put(tipoEnemigo, contadoresEnemigosTipo.get(tipoEnemigo)+1);
		
		// Imprimimos el estado
		imprimirInfo(tipoEnemigo, "Generado");
		
		// Comprobamos las invariantes
		checkInvariante();
		
		// Notificamos al resto de hilos
		notifyAll();
		
	}

	@Override
	public void eliminarEnemigo(String tipoEnemigo) {
		// Si no hay enemigos de este tipo se generan.
		if (contadoresEliminadosTipo.get(tipoEnemigo) == null){
			contadoresEliminadosTipo.put(tipoEnemigo, 0);
		}
		
		// Comprobamos si podemos eliminar enemnigos
		comprobarAntesDeEliminar();
				
		
		// Aumentamos el contador total y el individual
		contadorEnemigosTotales--;		
		contadoresEliminadosTipo.put(tipoEnemigo, contadoresEliminadosTipo.get(tipoEnemigo)+1);
		
		// Imprimimos el estado
		imprimirInfo(tipoEnemigo, "Eliminado");
		
		// Comprobamos las invariantes
		checkInvariante();
		
		// Notificamos al resto de hilos
		notifyAll();
		
	}
	
	private void imprimirInfo (String tipoEnemigo, String movimiento){
		System.out.println(movimiento + " enemigo tipo " + tipoEnemigo);
		System.out.println("--> Enemigos totales: " + contadorEnemigosTotales);
		
	
		
		for(int p=0; p < contadorEnemigosTotales; p++){
			System.out.printf("----> Enemigos tipo " + p + ": " + contadoresEnemigosTipo.get(p));
			System.out.println("----- [Eliminados:" + contadoresEliminadosTipo.get(p)+"]");
		}
		System.out.println(" ");
	}
	
	

}
