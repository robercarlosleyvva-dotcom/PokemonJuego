package battle;

import model.Jugador;
import model.Pokemon;
import model.Movimiento;

public class BatallaPVP extends Batalla {

    private final Jugador jugador1;
    private final Jugador jugador2;
    private int numeroRonda;

    public BatallaPVP(Jugador j1, Jugador j2) {
        this.jugador1 = j1;
        this.jugador2 = j2;
        this.numeroRonda = 1;
    }

    //Procesa una ronda completa de combate donde ambos jugadores eligieron un movimiento.
    //Procesa una ronda completa de combate donde ambos jugadores eligieron un movimiento.
    public ResultadoRonda procesarRonda(Movimiento movJugador1, Movimiento movJugador2) {

        Pokemon p1 = jugador1.getEquipo().getPokemonActivo();
        Pokemon p2 = jugador2.getEquipo().getPokemonActivo();
        ResultadoAccion r1 = null;
        ResultadoAccion r2 = null;

        boolean p1AtacaPrimero = true;

        if (p1AtacaPrimero) {
            // Turno del Jugador 1
            r1 = CalculadoraDanio.calcularDanio(p1, p2, movJugador1);
            p2.takeDamage(r1.getDanioInfligido());

            // Si el enemigo no se muere,responde
            if (p2.getHp() > 0) {
                r2 = CalculadoraDanio.calcularDanio(p2, p1, movJugador2);
                p1.takeDamage(r2.getDanioInfligido());
            } else {
                r2 = new ResultadoAccion(p2.getNombre() + " se ha debilitado y no puede atacar.", 0, false, 1.0);
            }
        } else {
            // Turno del Jugador 2
            r2 = CalculadoraDanio.calcularDanio(p2, p1, movJugador2);
            p1.takeDamage(r2.getDanioInfligido());

            if (p1.getHp() > 0) {
                r1 = CalculadoraDanio.calcularDanio(p1, p2, movJugador1);
                p2.takeDamage(r1.getDanioInfligido());
            } else {
                r1 = new ResultadoAccion(p1.getNombre() + " se ha debilitado y no puede atacar.", 0, false, 1.0);
            }
        }

        numeroRonda++;

        boolean batallaTerminada = verificarFinBatalla();
        String ganador = "";
        if (batallaTerminada) {
            ganador = jugador1.getEquipo().tienePokemonesVivos() ? jugador1.getNombre() : jugador2.getNombre();
        }

        return new ResultadoRonda(r1, r2, batallaTerminada, ganador);
    }

    private boolean verificarFinBatalla() {
        return !jugador1.getEquipo().tienePokemonesVivos() || !jugador2.getEquipo().tienePokemonesVivos();
    }
}