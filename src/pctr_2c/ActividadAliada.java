package pctr_2c;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActividadAliada implements Runnable {
	
	private static final int M = 20; //maximo de enemigos
	int tipoEnemigo;
	private IJuego juego;
	
	public ActividadAliada(int tipoEnemigo,IJuego juego) {
		this.tipoEnemigo = tipoEnemigo;
		this.juego = juego;
		
	}
	
	@Override
	public void run() {
		for (int i = 0; i < M; i ++) {
			try {
				juego.eliminarEnemigo(tipoEnemigo);
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5)*1000);
			} catch (InterruptedException e) {
				Logger.getGlobal().log(Level.INFO, "Enemigo NO eliminado");
				Logger.getGlobal().log(Level.INFO, e.toString());
				return;
			}
		}
		
	}

}
