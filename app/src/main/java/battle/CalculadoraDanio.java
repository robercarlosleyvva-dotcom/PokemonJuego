package battle;

import model.Pokemon;
import model.Movimiento;
import java.util.Random;

public class CalculadoraDanio {

    //Numero Random para el daño
    private static final Random random = new Random();

    //Metodo para calcula el daño final
    public static ResultadoAccion calcularDanio(Pokemon atacante, Pokemon defensor, Movimiento movimiento) {
        int nivel = atacante.getNivel();
        int ataque = atacante.getAtaque();
        int defensa = defensor.getDefensa();
        int poderMovimiento = movimiento.getPoder();

        //Verificar si es Golpe Crítico
        boolean esCritico = random.nextDouble() < 0.10;
        double factorCritico = esCritico ? 2.0 : 1.0;

        //Obtener multiplicador de tipos
        double multiplicadorTipos = SistemaTipos.obtenerMultiplicador(movimiento.getTipo(), defensor.getTipo());

        //Factor aleatorio para que varíe el daño
        double factorAleatorio = 0.85 + (1.00 - 0.85) * random.nextDouble();

        //Fórmula base de daño
        // Usamos la regla de tres simple con ataque/defensa y escalado por nivel
        double danioBase = (((2 * nivel / 5.0) + 2) * poderMovimiento * ((double) ataque / defensa) / 50.0) + 2;

        //Aplicar todos los modificadores
        double danioFinal = danioBase * factorCritico * multiplicadorTipos * factorAleatorio;

        // Redondeamos para no tener decimales feos en las barras de vida
        int danioRedondeado = (int) Math.round(danioFinal);

        //Mensaje que se mostrará en la interfaz gráfica
        String mensaje = atacante.getNombre() + " usó " + movimiento.getNombre() + ".";

        if (multiplicadorTipos > 1.0) {
            mensaje += " ¡ES SÚPER EFICAZ! ";
        } else if (multiplicadorTipos < 1.0 && multiplicadorTipos > 0) {
            mensaje += " NO ES MUY EFICAZ ";
        }
        if (esCritico) {
            mensaje += " ¡UN GOLPE CRÍTICO!";
        }

        // Retornamos el reporte completo
        return new ResultadoAccion(mensaje, danioRedondeado, esCritico, multiplicadorTipos);
    }
}
