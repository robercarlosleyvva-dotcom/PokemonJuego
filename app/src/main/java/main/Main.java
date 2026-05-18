package main;

import battle.AccionAtacar;
import battle.Batalla;
import model.JugadorHumano;
import model.Pokemon;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE BATALLA ===");

        Pokemon p1 = new Pokemon("Pikachu", 5, 100, 100, 55, 40, null, "Raichu", 16);
        Pokemon p2 = new Pokemon("Charmander", 5, 120, 120, 52, 43, null, "Charmeleon", 16);

        JugadorHumano j1 = new JugadorHumano("Esteban");
        JugadorHumano j2 = new JugadorHumano("Carlos");

        j1.agregarPokemon(p1);
        j2.agregarPokemon(p2);

        Batalla batalla = new Batalla(j1, j2);

        System.out.println("\n--- Turno 1: Ambos Atacan ---");
        batalla.procesarTurno(new AccionAtacar("Impactrueno"), new AccionAtacar("Ascuas"));
    }
}