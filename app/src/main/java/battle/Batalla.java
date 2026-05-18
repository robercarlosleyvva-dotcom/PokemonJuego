package battle;

import model.Jugador;

public class Batalla {
    protected Jugador jugador1;
    protected Jugador jugador2;
    protected int numeroTurno;

    public Batalla(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.numeroTurno = 1;
    }

    public void procesarTurno(AccionCombate accionJ1, AccionCombate accionJ2) {
        System.out.println("--- Turno " + numeroTurno + " ---");

        // Compara las prioridades de las acciones
        if (accionJ1.getPrioridad() > accionJ2.getPrioridad()) {
            ejecutarAcciones(accionJ1, jugador1, jugador2, accionJ2, jugador2, jugador1);
        } else if (accionJ2.getPrioridad() > accionJ1.getPrioridad()) {
            ejecutarAcciones(accionJ2, jugador2, jugador1, accionJ1, jugador1, jugador2);
        } else {
            // Desempate por defecto si las prioridades son iguales
            ejecutarAcciones(accionJ1, jugador1, jugador2, accionJ2, jugador2, jugador1);
        }
        numeroTurno++;
    }

    private void ejecutarAcciones(AccionCombate primera, Jugador ejecutor1, Jugador obj1,
                                  AccionCombate segunda, Jugador ejecutor2, Jugador obj2) {

        // Valida que el equipo tenga elementos antes de actuar
        if (ejecutor1.getEquipo() != null && !ejecutor1.getEquipo().isEmpty()) {
            primera.ejecutar(ejecutor1, obj1);
        }

        if (ejecutor2.getEquipo() != null && !ejecutor2.getEquipo().isEmpty()) {
            segunda.ejecutar(ejecutor2, obj2);
        }
    }
}