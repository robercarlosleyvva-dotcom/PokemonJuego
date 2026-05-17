package battle;

import model.Pokemon;

public class SistemaDeExp {

    //Metodo pra calcula la experiencia ganada al derrotar al contrario
    public static int calcularExperienciaGanada(Pokemon oponenteDerrotado) {
        return oponenteDerrotado.getNivel() * 50;
    }

    //Aplica la experiencia ganada a un Pokémon y maneja la subida de nivel
    public static String ganarExperiencia(Pokemon pokemon, int expGanada) {
        StringBuilder reporte = new StringBuilder();
        reporte.append(pokemon.getNombre()).append(" ganó ").append(expGanada).append(" puntos de EXP.\n");

        boolean subioDeNivel = false;

        if (subioDeNivel) {
            reporte.append("¡FELICIDADES! ").append(pokemon.getNombre()).append(" SUBIO DE NIVEL ").append(pokemon.getNivel()).append(".\n");

            // Verificar si cumple la condición de evolución
            if (pokemon.canEvolve()) {
                Pokemon evolucionado = pokemon.evolve();
                reporte.append("¡¿QUÉ?! ").append(pokemon.getNombre()).append(" ESTA EVOLUCINANDO...\n");
                reporte.append("¡FELICIDADES! TU POKÉMON A EVOLUCIONA EN ").append(evolucionado.getNombre()).append(".\n");
            }
        }

        return reporte.toString();
    }
}
