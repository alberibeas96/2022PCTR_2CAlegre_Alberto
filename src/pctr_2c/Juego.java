package pctr_2c;

import java.util.Enumeration;
import java.util.Hashtable;

public class Juego  implements IJuego{
	
	private final int MAXENEMIGOS = 50;
	private final int MINENEMIGOS = 0;
	private Hashtable<Integer, Integer> contadoresEnemigosTipo;
	private Hashtable<Integer, Integer> contadoresEliminadosTipo;
	int contadorEnemigosTotales;
	
	public Juego() {
		contadorEnemigosTotales = 0;
		contadoresEnemigosTipo = new Hashtable<Integer, Integer>();
		contadoresEliminadosTipo = new Hashtable<Integer, Integer>();
	}

	
	

	@Override
	public synchronized void  generarEnemigo(Integer tipoEnemigo) {
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
	public synchronized void eliminarEnemigo(Integer tipoEnemigo) {
		// Si no hay enemigos de este tipo se generan.
		if (contadoresEliminadosTipo.get(tipoEnemigo) == null){
			contadoresEliminadosTipo.put(tipoEnemigo, 0);
		}
		
		// Comprobamos si podemos eliminar enemnigos
		comprobarAntesDeEliminar();
				
		
		// Decrementamos el contador total e incrementamos el individual
		contadorEnemigosTotales--;
		contadoresEnemigosTipo.put(tipoEnemigo, contadoresEnemigosTipo.get(tipoEnemigo)-1);
		contadoresEliminadosTipo.put(tipoEnemigo, contadoresEliminadosTipo.get(tipoEnemigo)+1);
		
		// Imprimimos el estado
		imprimirInfo(tipoEnemigo, "Eliminado");
		
		// Comprobamos las invariantes
		checkInvariante();
		
		// Notificamos al resto de hilos
		notifyAll();
		
	}
	
	private void imprimirInfo (int tipoEnemigo, String movimiento){
		System.out.println(movimiento + " enemigo tipo " + tipoEnemigo);
		System.out.println("--> Enemigos totales: " + contadorEnemigosTotales);
		
	
		
		for(int p=0; p < contadorEnemigosTotales; p++){
			System.out.println("----> Enemigos tipo " + p + ": " + contadoresEnemigosTipo.get(p)+" ----- [Eliminados:" + contadoresEliminadosTipo.get(p)+"]");
			
		}
		System.out.println(" ");
	}
	
	private int sumarContadores() {
		int sumaContadores = 0;
			Enumeration<Integer> iterEnemigos = contadoresEnemigosTipo.elements();
			while (iterEnemigos.hasMoreElements()) {
				sumaContadores += iterEnemigos.nextElement();
			}
		return sumaContadores;
	}
	
	protected void checkInvariante() {
		assert sumarContadores() == contadorEnemigosTotales : "INV: La suma de contadores de los tipos de enemigo debe ser igual al valor del contador de enemigos totales";
		assert contadorEnemigosTotales <= MAXENEMIGOS : "INV: No pueden haber más enemigos que lo que se permite";
		assert contadorEnemigosTotales >= MINENEMIGOS : "INV: No puede haber un numero negativo de enemigos";
	}
	
	protected void comprobarAntesDeGenerar(){
		while (contadorEnemigosTotales == MAXENEMIGOS) {
			try {
				wait();
			} catch (InterruptedException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	

	protected void comprobarAntesDeEliminar(){
		while (contadorEnemigosTotales == MINENEMIGOS) {
			try {
				wait();
			} catch (InterruptedException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}


