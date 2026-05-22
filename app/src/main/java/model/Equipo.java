package model;

import java.io.Serializable;
import java.util.ArrayList;




public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private ArrayList<Pokemon> listaPokemon;
    private static final int LIMITE_MAXIMO = 6;
    private boolean bloqueado = false;

    // Constructor: Inicializa un equipo vacío
    public Equipo() {
        this.listaPokemon = new ArrayList<>();
    }

    public boolean isBloqueado(){
        return bloqueado;
    }
    
    public void setBloqueado(boolean  bloqueado){
        this.bloqueado = bloqueado;
    }

    
    /**
     * Agrega un Pokémon al equipo respetando el límite máximo de 6 (Requisito del juego).
     * @return true si se agregó con éxito, false si el equipo ya estaba lleno.
     */
    public boolean agregarPokemon(Pokemon pokemon) {
        if (pokemon == null) return false;

        if (listaPokemon.size() < LIMITE_MAXIMO) {
            listaPokemon.add(pokemon);
            return true;
        } else {
            System.out.println("¡No puedes llevar más de " + LIMITE_MAXIMO + " Pokémon en tu equipo!");
            return false;
        }
    }

    /**
     * Requisito de Batalla: Verifica si todo el equipo está debilitado.
     * Si todos tienen 0 HP, el jugador pierde la batalla.
     */
    public boolean estaTodoElEquipoDebilitado() {
        if (listaPokemon.isEmpty()) return true;

        for (Pokemon p : listaPokemon) {
            if (!p.estaDebilitado()) {
                return false; // Encontró al menos uno vivo
            }
        }
        return true; // Todos están fuera de combate
    }

    /**
     * Devuelve el primer Pokémon vivo disponible para salir a combatir.
     */
    public Pokemon getPokemonActivo() {
        for (Pokemon p : listaPokemon) {
            if (!p.estaDebilitado()) {
                return p;
            }
        }
        return null; // No hay ninguno disponible
    }

    /**
      Permite intercambiar el orden de los Pokémones en el equipo.
     Muy útil para la interfaz gráfica cuando el usuario quiere reordenar sus posiciones.
     */
    public boolean intercambiarPosiciones(int indiceA, int indiceB) {
        if (indiceA >= 0 && indiceA < listaPokemon.size() && indiceB >= 0 && indiceB < listaPokemon.size()) {
            Pokemon temp = listaPokemon.get(indiceA);
            listaPokemon.set(indiceA, listaPokemon.get(indiceB));
            listaPokemon.set(indiceB, temp);
            return true;
        }
        return false;
    }

    //Metodo para limpiar el array equipo
    public void limpiarEquipo() {
    listaPokemon.clear();
}
    // Remover pokemon
    public boolean removerPokemon(int indice) {

    if (indice >= 0 && indice < listaPokemon.size()) {
        listaPokemon.remove(indice);
        return true;
    }

    return false;
}
    // --- GETTERS Y MÉTODOS DE UTILIDAD ---

    public ArrayList<Pokemon> getListaPokemon() {
        return listaPokemon;
    }

    public int getTamano() {
        return listaPokemon.size();
    }
}