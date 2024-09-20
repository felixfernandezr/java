package logica;

public class Jugador {
	private String nombre;
	private EstrategiaDeJuego estrategia;
	
	public Jugador(String nombre, EstrategiaDeJuego estrategia) {
		this.nombre = nombre;
		this.estrategia = estrategia;
	}

	public String getNombre() {
		return nombre;
	}
	
	public int proponerNumero(int nroAnterior, boolean esMayor, int piso, int techo) {
		return estrategia.adivinarNumero(nroAnterior, esMayor, piso, techo);
	}
	
}
