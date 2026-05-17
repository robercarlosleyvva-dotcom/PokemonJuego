package battle;

public class ResultadoRonda {
    private final ResultadoAccion resultadoJugador1;
    private final ResultadoAccion resultadoJugador2;
    private final boolean batallaTerminada;
    private final String ganador;

    //Meodo constuctor
    public ResultadoRonda(ResultadoAccion r1, ResultadoAccion r2, boolean batallaTerminada, String ganador) {
        this.resultadoJugador1 = r1;
        this.resultadoJugador2 = r2;
        this.batallaTerminada = batallaTerminada;
        this.ganador = ganador;
    }

    // Getters
    public ResultadoAccion getResultadoJugador1() {
        return resultadoJugador1;
    }

    public ResultadoAccion getResultadoJugador2() {
        return resultadoJugador2;
    }

    public boolean isBatallaTerminada() {
        return batallaTerminada;
    }

    public String getGanador() {
        return ganador;
    }
}