package estrategias;

import logica.EstrategiaDeJuego;

public class JugadorTrivial implements EstrategiaDeJuego {
	private int intento = 41;

	@Override
	public int adivinarNumero(int nroAnterior, boolean buscadoEsMayor, int piso, int techo) {
		return intento++;
	}

}
