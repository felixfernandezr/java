package estrategias;

import logica.EstrategiaDeJuego;

public class FernandezRolon implements EstrategiaDeJuego {
	
	private int miPiso = 0;
	private int miTecho = 0;
	private boolean primera = true;
	
	@Override
	public int adivinarNumero(int nroAnterior, boolean buscadoEsMayor, int piso, int techo) {
		int nroPropuesto = 0;
		
		if(buscadoEsMayor && !primera) {
			miPiso = nroAnterior;
	    }
		else {
			miTecho = nroAnterior;
		}
		
		if(primera) {
			miTecho = techo;
			miPiso = piso;
			primera = false;
		}

		nroPropuesto = miPiso + ((miTecho - miPiso) / 2);
		
		return (int)nroPropuesto;
	}
}