package battle;

import model.Pokemon;
import model.Movimiento;
import java.util.Random;

public class CalculadoraDanio {

    private static final Random random = new Random();

    public static ResultadoAccion calcularDanio(Pokemon atacante, Pokemon defensor, Movimiento movimiento) {
        int nivel = atacante.getNivel();
        int ataque = atacante.getAtaque();
        int defensa = defensor.getDefensa();

        // Ajustado a los nombres de tus compañeros
        int poderMovimiento = movimiento.getPotencia();

        boolean esCritico = random.nextDouble() < 0.10;
        double factorCritico = esCritico ? 2.0 : 1.0;

        // Ajustado a getTipoMov()
        double multiplicadorTipos = SistemaTipos.obtenerMultiplicador(movimiento.getTipoMov(), defensor.getTipo());

        double factorAleatorio = 0.85 + (1.00 - 0.85) * random.nextDouble();
        double danioBase = (((2 * nivel / 5.0) + 2) * poderMovimiento * ((double) ataque / defensa) / 50.0) + 2;
        double danioFinal = danioBase * factorCritico * multiplicadorTipos * factorAleatorio;

        int danioRedondeado = (int) Math.round(danioFinal);

        // Ajustado a getNombreMov()
        String mensaje = atacante.getNombre() + " usó " + movimiento.getNombreMov() + ".";
        if (multiplicadorTipos > 1.0) {
            mensaje += " ¡Es súper eficaz!";
        } else if (multiplicadorTipos < 1.0 && multiplicadorTipos > 0) {
            mensaje += " No es muy eficaz...";
        }
        if (esCritico) {
            mensaje += " ¡Un golpe crítico!";
        }

        return new ResultadoAccion(mensaje, danioRedondeado, esCritico, multiplicadorTipos);
    }
}
