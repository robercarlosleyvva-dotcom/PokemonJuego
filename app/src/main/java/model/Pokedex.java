package model;

import java.util.HashMap;
import java.util.Map;

public class Pokedex {
    // Un mapa para almacenar los "moldes" de los pokemones usando un ID numerico
    private static final Map<Integer, Pokemon> catalogo = new HashMap<>();
    private String imagen;

    static {
        // Estructura del constructor del pokemon
        // Pokemon(nombre, nivel, vida, vidaMaxima, ataque, defensa, tipo, nombreEvolucion, nivelEvolucion, rutaImagen)
        
        // --- 1. TIPO PLANTA ---
        catalogo.put(1, new Pokemon("Bulbasaur", 5, 55, 55, 16, 15, TipoPokemon.PLANTA, "Ivysaur", 6, "/pokemon/bulbasaur.png"));
        catalogo.put(2, new Pokemon("Ivysaur", 6, 85, 85, 26, 24, TipoPokemon.PLANTA, "Venusaur", 12, "/pokemon/ivysaur.png"));
        catalogo.put(3, new Pokemon("Venusaur", 12, 180, 180, 52, 50, TipoPokemon.PLANTA, null, 0, "/pokemon/venusaur.png"));

        catalogo.put(4, new Pokemon("Treecko", 5, 50, 50, 18, 12, TipoPokemon.PLANTA, "Grovyle", 6, "/pokemon/treecko.png"));
        catalogo.put(5, new Pokemon("Grovyle", 6, 80, 80, 28, 20, TipoPokemon.PLANTA, "Sceptile", 12, "/pokemon/grovyle.png"));
        catalogo.put(6, new Pokemon("Sceptile", 12, 170, 170, 56, 45, TipoPokemon.PLANTA, null, 0, "/pokemon/sceptile.png"));

        catalogo.put(7, new Pokemon("Snivy", 5, 52, 52, 14, 18, TipoPokemon.PLANTA, "Servine", 6, "/pokemon/snivy.png"));
        catalogo.put(8, new Pokemon("Servine", 6, 82, 82, 22, 28, TipoPokemon.PLANTA, "Serperior", 12, "/pokemon/servine.png"));
        catalogo.put(9, new Pokemon("Serperior", 12, 175, 175, 48, 58, TipoPokemon.PLANTA, null, 0, "/pokemon/serperior.png"));

        catalogo.put(10, new Pokemon("Rowlet", 5, 58, 58, 17, 14, TipoPokemon.PLANTA, "Dartrix", 6, "/pokemon/rowlet.png"));
        catalogo.put(11, new Pokemon("Dartrix", 6, 88, 88, 25, 22, TipoPokemon.PLANTA, "Decidueye", 12, "/pokemon/dartrix.png"));
        catalogo.put(12, new Pokemon("Decidueye", 12, 185, 185, 54, 48, TipoPokemon.PLANTA, null, 0, "/pokemon/decidueye.png"));

        catalogo.put(13, new Pokemon("Leafeon", 5, 60, 60, 19, 16, TipoPokemon.PLANTA, null, 0, "/pokemon/leafeon.png"));

        // --- 2. TIPO FUEGO ---
        catalogo.put(14, new Pokemon("Charmander", 5, 52, 52, 18, 11, TipoPokemon.FUEGO, "Charmeleon", 6, "/pokemon/charmander.png"));
        catalogo.put(15, new Pokemon("Charmeleon", 6, 82, 82, 28, 19, TipoPokemon.FUEGO, "Charizard", 12, "/pokemon/charmeleon.png"));
        // ¡Charizard subió a 195 de vida máxima para aguantar los golpes sabroso!
        catalogo.put(16, new Pokemon("Charizard", 12, 195, 195, 58, 46, TipoPokemon.FUEGO, null, 0, "/pokemon/charizard.png"));

        catalogo.put(17, new Pokemon("Cyndaquil", 5, 50, 50, 19, 10, TipoPokemon.FUEGO, "Quilava", 6, "/pokemon/cyndaquil.png"));
        catalogo.put(18, new Pokemon("Quilava", 6, 80, 80, 29, 18, TipoPokemon.FUEGO, "Typhlosion", 12, "/pokemon/quilava.png"));
        catalogo.put(19, new Pokemon("Typhlosion", 12, 172, 172, 57, 44, TipoPokemon.FUEGO, null, 0, "/pokemon/typhlosion.png"));

        catalogo.put(20, new Pokemon("Torchic", 5, 54, 54, 17, 12, TipoPokemon.FUEGO, "Combusken", 6, "/pokemon/torchic.png"));
        catalogo.put(21, new Pokemon("Combusken", 6, 84, 84, 27, 20, TipoPokemon.FUEGO, "Blaziken", 12, "/pokemon/combusken.png"));
        catalogo.put(22, new Pokemon("Blaziken", 12, 182, 182, 58, 45, TipoPokemon.FUEGO, null, 0, "/pokemon/blaziken.png"));

        catalogo.put(23, new Pokemon("Litten", 5, 56, 56, 18, 11, TipoPokemon.FUEGO, "Torracat", 6, "/pokemon/litten.png"));
        catalogo.put(24, new Pokemon("Torracat", 6, 86, 86, 28, 19, TipoPokemon.FUEGO, "Incineroar", 12, "/pokemon/torracat.png"));
        catalogo.put(25, new Pokemon("Incineroar", 12, 190, 190, 59, 48, TipoPokemon.FUEGO, null, 0, "/pokemon/incineroar.png"));

        catalogo.put(26, new Pokemon("Flareon", 5, 62, 62, 20, 13, TipoPokemon.FUEGO, null, 0, "/pokemon/flareon.png"));

        // --- 3. TIPO AGUA ---
        catalogo.put(27, new Pokemon("Squirtle", 5, 58, 58, 13, 17, TipoPokemon.AGUA, "Wartortle", 6, "/pokemon/squirtle.png"));
        catalogo.put(28, new Pokemon("Wartortle", 6, 88, 88, 21, 27, TipoPokemon.AGUA, "Blastoise", 12, "/pokemon/wartortle.png"));
        catalogo.put(29, new Pokemon("Blastoise", 12, 190, 190, 48, 56, TipoPokemon.AGUA, null, 0, "/pokemon/blastoise.png"));

        catalogo.put(30, new Pokemon("Totodile", 5, 60, 60, 16, 14, TipoPokemon.AGUA, "Croconaw", 6, "/pokemon/totodile.png"));
        catalogo.put(31, new Pokemon("Croconaw", 6, 90, 90, 26, 22, TipoPokemon.AGUA, "Feraligatr", 12, "/pokemon/croconaw.png"));
        catalogo.put(32, new Pokemon("Feraligatr", 12, 192, 192, 55, 48, TipoPokemon.AGUA, null, 0, "/pokemon/feraligatr.png"));

        catalogo.put(33, new Pokemon("Piplup", 5, 56, 56, 14, 15, TipoPokemon.AGUA, "Prinplup", 6, "/pokemon/piplup.png"));
        catalogo.put(34, new Pokemon("Prinplup", 6, 86, 86, 22, 24, TipoPokemon.AGUA, "Empoleon", 12, "/pokemon/prinplup.png"));
        catalogo.put(35, new Pokemon("Empoleon", 12, 184, 184, 50, 54, TipoPokemon.AGUA, null, 0, "/pokemon/empoleon.png"));

        catalogo.put(36, new Pokemon("Oshawott", 5, 57, 57, 15, 13, TipoPokemon.AGUA, "Dewott", 6, "/pokemon/oshawott.png"));
        catalogo.put(37, new Pokemon("Dewott", 6, 87, 87, 24, 21, TipoPokemon.AGUA, "Samurott", 12, "/pokemon/dewott.png"));
        catalogo.put(38, new Pokemon("Samurott", 12, 186, 186, 53, 47, TipoPokemon.AGUA, null, 0, "/pokemon/samurott.png"));

        catalogo.put(39, new Pokemon("Vaporeon", 5, 65, 65, 15, 16, TipoPokemon.AGUA, null, 0, "/pokemon/vaporeon.png"));

        // --- 4. TIPO ELÉCTRICO ---
        catalogo.put(40, new Pokemon("Pikachu", 5, 50, 50, 18, 11, TipoPokemon.ELECTRICO, null, 0, "/pokemon/pikachu.png"));

        catalogo.put(41, new Pokemon("Shinx", 5, 52, 52, 17, 12, TipoPokemon.ELECTRICO, "Luxio", 6, "/pokemon/shinx.png"));
        catalogo.put(42, new Pokemon("Luxio", 6, 82, 82, 26, 20, TipoPokemon.ELECTRICO, "Luxray", 12, "/pokemon/luxio.png"));
        catalogo.put(43, new Pokemon("Luxray", 12, 180, 180, 56, 45, TipoPokemon.ELECTRICO, null, 0, "/pokemon/luxray.png"));

        // --- 5. TIPO ROCA ---
        catalogo.put(44, new Pokemon("Larvitar", 5, 58, 58, 15, 16, TipoPokemon.ROCA, "Pupitar", 6, "/pokemon/larvitar.png"));
        catalogo.put(45, new Pokemon("Pupitar", 6, 88, 88, 23, 25, TipoPokemon.ROCA, "Tyranitar", 12, "/pokemon/pupitar.png"));
        catalogo.put(46, new Pokemon("Tyranitar", 12, 200, 200, 58, 56, TipoPokemon.ROCA, null, 0, "/pokemon/tyranitar.png"));

        catalogo.put(47, new Pokemon("Rockruff", 5, 53, 53, 16, 11, TipoPokemon.ROCA, "Lycanroc", 6, "/pokemon/rockruff.png"));
        catalogo.put(48, new Pokemon("Lycanroc", 6, 83, 83, 29, 18, TipoPokemon.ROCA, null, 0, "/pokemon/lycanroc.png"));
    }

    public static Pokemon obtenerPokemon(int id) {
        Pokemon molde = catalogo.get(id);
        if (molde == null) {
            return null;
        }

        Pokemon copia = new Pokemon(
            molde.getNombre(),
            molde.getNivel(),
            molde.getVida(),
            molde.getVidaMaxima(),
            molde.getAtaque(),
            molde.getDefensa(),
            molde.getTipo(),
            molde.getNombreEvolucion(), // <--- Usa el nombre del molde
         molde.getNivelEvolucion(), // Atributos de evolucion limpios
            molde.getImagen()
        );

        asignarMovimientosIniciales(copia);
        return copia;
    }

    public static int obtenerIdPorNombre(String nombre) {
        for (Map.Entry<Integer, Pokemon> entry : catalogo.entrySet()) {
            Pokemon p = entry.getValue();
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return entry.getKey();
            }
        }
        return -1;
    }           

  public static Pokemon buscarPorNombre(String nombre) {
        // En lugar de devolver el molde, buscamos su ID y devolvemos una COPIA fresca con ataques
        int id = obtenerIdPorNombre(nombre);
        if (id != -1) {
            return obtenerPokemon(id); 
        }
        return null;
    }

    private static void asignarMovimientosIniciales(Pokemon p) {
        switch (p.getTipo()) {
            case PLANTA :
                p.aprenderMovimiento(new Movimiento("Latigazo", TipoPokemon.PLANTA, 20));        
                break;
            case FUEGO :
                p.aprenderMovimiento(new Movimiento("Ascuas", TipoPokemon.FUEGO, 20));
                break;
            case AGUA: 
                p.aprenderMovimiento(new Movimiento("Acuajet", TipoPokemon.AGUA, 20));
                break;
            case ELECTRICO:
                p.aprenderMovimiento(new Movimiento("Impactrueno", TipoPokemon.ELECTRICO, 22));
                break;
            case ROCA: 
                p.aprenderMovimiento(new Movimiento("Lanzarocas", TipoPokemon.ROCA, 19));
                break;
        }
    }
}