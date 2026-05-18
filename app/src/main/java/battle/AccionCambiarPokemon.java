package battle;

import model.Jugador;
import model.Pokemon;

public class AccionCambiarPokemon extends AccionCombate {
    private Pokemon nuevoPokemon;

    public AccionCambiarPokemon(Pokemon nuevoPokemon) {
        super(2); // Prioridad alta para cambios
        this.nuevoPokemon = nuevoPokemon;
    }

    @Override
    public void ejecutar(Jugador ejecutor, Jugador objetivo) {
        Pokemon viejo = ejecutor.getPokemonActivo();
        if (viejo != null) {
            System.out.println(ejecutor.getNombre() + " retira a " + viejo.getNombre());
        }

        // Movemos al nuevo pokemon a la posición 0 del equipo de tus amigos
        if (ejecutor.getEquipo().contains(nuevoPokemon)) {
            ejecutor.getEquipo().remove(nuevoPokemon);
            ejecutor.getEquipo().add(0, nuevoPokemon);
        }

        System.out.println("¡Adelante, " + nuevoPokemon.getNombre() + "!");
    }

    public Pokemon getNuevoPokemon() {
        return nuevoPokemon;
    }

    public void setNuevoPokemon(Pokemon nuevoPokemon) {
        this.nuevoPokemon = nuevoPokemon;
    }
}