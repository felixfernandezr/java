package estrategias;

import java.util.Random;

import logica.EstrategiaDeJuego;

public class JugadorAleatorio implements EstrategiaDeJuego {

	private Random r = new Random();
	
	@Override
	public int adivinarNumero(int nroAnterior, boolean buscadoEsMayor, int piso, int techo) {
		return r.nextInt(techo - piso + 1);
	}

}
