package battle;

import model.Pokemon;

public class SistemaDeExp {

    public static int calcularExperienciaGanada(Pokemon oponenteDerrotado) {
        return oponenteDerrotado.getNivel() * 50;
    }

    public static String ganarExperiencia(Pokemon pokemon, int expGanada) {
        // 1. Sumamos la experiencia al Pokémon
        int expTotal = pokemon.getExperiencia() + expGanada;
        pokemon.setExperiencia(expTotal);
        
        // 2. Subimos de nivel (esto internamente llamará a verificarEvolucion)
        pokemon.subirNivel();
        
        // 3. Creamos el reporte de texto (todo al final)
        StringBuilder reporte = new StringBuilder();
        reporte.append(pokemon.getNombre())
               .append(" ganó ")
               .append(expGanada)
               .append(" puntos de EXP y subió de nivel.");

        // 4. Retornamos el texto al final de todo
        return reporte.toString();
    }
}