package battle;

import model.Jugador;

public class BatallaPVE extends Batalla {

    // Constructor que hereda los dos jugadores de la clase Batalla
    public BatallaPVE(Jugador jugador1, Jugador jugador2) {
        super(jugador1, jugador2);
    }

    /**
     * Simula el turno contra la Inteligencia Artificial (la máquina).
     * Recibe la acción que eligió el jugador humano y procesa todo.
     */
    public void jugarTurnoPVE(AccionCombate accionHumano) {
        // Por ahora creamos una acción simple para la CPU (un ataque genérico)
        // Esto evita que el código se rompa mientras tus amigos programan la IA en JugadorCPU
        AccionCombate accionCPU = new AccionAtacar("Ataque Computadora");

        // Ejecuta el flujo de turnos que programamos en la clase padre (Batalla)
        procesarTurno(accionHumano, accionCPU);
    }
}