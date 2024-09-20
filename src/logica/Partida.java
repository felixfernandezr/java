package logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import estrategias.*;
import excepciones.DemasiadosIntentosException;
import excepciones.NoHayUnGanadorException;
import helpers.Resultado;

public class Partida {
	
	private Jugador[] jugadores = null;
	private int nroPiso;
	private int nroTecho;
	private int nroBuscado;
	private Random r = new Random();
	
	public static final int CANT_MAX_INTENTOS = 7;
	
	public Partida() {
		configurarPartida();
	}

	private void configurarPartida() {
		nroPiso = 0;
		nroTecho = 100;
		nroBuscado = r.nextInt(nroTecho-nroPiso+1);
		
		System.out.println("Configurando la cosa... El número a adivinar es el " + nroBuscado + "\n");
		
		cargarJugadores();
	}
	
	private void cargarJugadores() {
		jugadores = new Jugador[3];
				
		jugadores[0] = new Jugador("Felix", new FernandezRolon());
		jugadores[1] = new Jugador("Lenny", new JugadorAleatorio());
		jugadores[2] = new Jugador("Tolkien", new JugadorAleatorioConcienzudo());
	}

	public void iniciarNuevaPartida() {
		System.out.println("   >> ¡Adivina competencia... ya! <<\n");
		
		Resultado[] resultados = generarResultados();
		
		try {
			procesarResultados(resultados);
			System.out.println("\n        El ganador es... " + resultados[0].getJugador().getNombre() + "!");
		} catch (NoHayUnGanadorException e) {
			System.out.println("\n        La competencia quedó desierta...");
		}
	}

	private Resultado[] generarResultados() {
		Jugador jugador;
		int cantIntentos = 0;
		Resultado[] resultados = new Resultado[jugadores.length]; 
		
		for (int i = 0; i < jugadores.length; i++) {
			jugador = jugadores[i];
			
			try {
				cantIntentos = intentarHastaAdivinar(jugador);
			}
			catch (DemasiadosIntentosException e) {
				System.out.println("\n        " + jugador.getNombre() + " se ha quedado sin intentos... \n\n");
				cantIntentos = Partida.CANT_MAX_INTENTOS + 1;
			}
			
			if (cantIntentos <= Partida.CANT_MAX_INTENTOS) {
				resultados[i] = new Resultado(jugador, cantIntentos);	
			} else {
				resultados[i] = null;
			}
		}

		Resultado[] resultadosValidos = eliminarResultadosInvalidos(resultados);
		
		return resultadosValidos;
	}

	private Resultado[] eliminarResultadosInvalidos(Resultado[] resultados) {
		List<Resultado> resultadosProcesados = new ArrayList<Resultado>(Arrays.asList(resultados));
		resultadosProcesados.removeIf(Predicate.isEqual(null));
		
		Resultado[] resultadosValidos = new Resultado[resultadosProcesados.size()];
		int i = 0;
		for (Resultado resultado : resultadosProcesados) {
			resultadosValidos[i] = resultado;
			i++;
		}
		
		return resultadosValidos;
	}

	private int intentarHastaAdivinar(Jugador jugador) throws DemasiadosIntentosException {
		int cantIntentos = 1;
		int nroPropuesto;
		int nroAnterior = nroPiso-1;
		boolean esMayor = true;
		
		System.out.println("      > Juega " + jugador.getNombre() + "\n");
		
		do {
			nroPropuesto = jugador.proponerNumero(nroAnterior, esMayor, nroPiso, nroTecho);
			System.out.println("        - Adivino que es el " + nroPropuesto);
			
			if (nroPropuesto != nroBuscado) {
				System.out.println("          mmm... nop!");
				cantIntentos++;
				esMayor = nroBuscado > nroPropuesto;
				nroAnterior = nroPropuesto;
				
				if (cantIntentos == Partida.CANT_MAX_INTENTOS+1) {
					System.out.println("          ouch!");					
					throw new DemasiadosIntentosException();
				}

			}
		} while (nroPropuesto != nroBuscado);
		
		System.out.println("          oh yeah!\n");
		
		return cantIntentos;
	}	

	private void procesarResultados(Resultado[] resultados) throws NoHayUnGanadorException {
		if (resultados.length > 0) {
			// hay jugadores que adivinaron el número -> identificar al ganador
			System.out.println("\n      > Los resultados fueron:");
			
			for (Resultado resultado : resultados) {
				System.out.println("        " + resultado);
			}
			
			Arrays.sort(resultados);
		} else {
			throw new NoHayUnGanadorException();
		}
	}

}