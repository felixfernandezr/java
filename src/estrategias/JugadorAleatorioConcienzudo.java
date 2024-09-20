package estrategias;

import java.util.Random;

import logica.EstrategiaDeJuego;

public class JugadorAleatorioConcienzudo implements EstrategiaDeJuego {

	private Random r = new Random();
	
	@Override
	public int adivinarNumero(int nroAnterior, boolean buscadoEsMayor, int piso, int techo) {
		int nroPropuesto;
		
		if (buscadoEsMayor) {
			nroPropuesto = r.nextInt(techo-nroAnterior+1+1) + nroAnterior;
		} else {
			nroPropuesto = r.nextInt(nroAnterior-1-piso+1) + piso;
		}
		
		return nroPropuesto;
	}

}
