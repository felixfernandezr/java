package helpers;

import logica.Jugador;

public class Resultado implements Comparable<Resultado> {
	private Jugador jugador;
	private int cantIntentos;
	
	public Resultado(Jugador jugador, int cantIntentos) {
		this.jugador = jugador;
		this.cantIntentos = cantIntentos;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public int getCantIntentos() {
		return cantIntentos;
	}

	@Override
	public String toString() {
		return "El jugador " + jugador.getNombre() + " adivinó en " + cantIntentos + " intentos.";
	}

	@Override
	public int compareTo(Resultado otroResultado) {
		return Integer.valueOf(cantIntentos).compareTo(Integer.valueOf(otroResultado.getCantIntentos()));
	}
	
}
