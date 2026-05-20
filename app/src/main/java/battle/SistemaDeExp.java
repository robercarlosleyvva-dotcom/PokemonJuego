package battle;

import model.Pokemon;

public class SistemaDeExp {

    public static int calcularExperienciaGanada(Pokemon oponenteDerrotado) {
        return oponenteDerrotado.getNivel() * 50;
    }

    public static String ganarExperiencia(Pokemon pokemon, int expGanada) {
        StringBuilder reporte = new StringBuilder();
        reporte.append(pokemon.getNombre()).append(" ganó ").append(expGanada).append(" puntos de EXP.\n");

        // En lugar de inventar lógica que choque con Carlos, llamamos directamente
        // a su método original que ya calcula la exp, sube de nivel y evoluciona solo.
        pokemon.ganarExperiencia(expGanada);

        return reporte.toString();
    }
}