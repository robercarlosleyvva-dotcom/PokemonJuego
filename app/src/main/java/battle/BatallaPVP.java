package battle;

import model.Jugador;

public class BatallaPVP extends Batalla {

    private final Jugador jugador1;
    private final Jugador jugador2;
    private int numeroRonda;

    public BatallaPVP(Jugador j1, Jugador j2) {
        super(j1, j2); // Arregla el error del constructor base
        this.jugador1 = j1;
        this.jugador2 = j2;
        this.numeroRonda = 1;
    }

    // Procesa una ronda completa de combate
    public void procesarRonda(String movJugador1, String movJugador2) {
        numeroRonda++;

        // Creamos los objetos de acción usando tus clases
        AccionCombate accionJ1 = new AccionAtacar(movJugador1);
        AccionCombate accionJ2 = new AccionAtacar(movJugador2);

        // Ejecuta el turno usando el procesador de la clase padre (Batalla)
        procesarTurno(accionJ1, accionJ2);
    }
}