package battle;

import model.Jugador;
import model.Pokemon;

public class AccionAtacar extends AccionCombate {
    private String movimiento;

    public AccionAtacar(String movimiento) {
        // Prioridad 1 para los ataques estándares (van después de los cambios)
        super(1);
        this.movimiento = movimiento;
    }

    @Override
    public void ejecutar(Jugador ejecutor, Jugador objetivo) {
        if (ejecutor.getPokemonActivo() != null) {
            System.out.println(ejecutor.getPokemonActivo().getNombre() + " usa " + movimiento);
        }
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }
}