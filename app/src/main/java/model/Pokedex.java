package model;

import java.util.HashMap;
import java.util.Map;
public class Pokedex {
    //Un mapa para almacenar los "moldes" de los 20 pokemones usando un ID numerico
    private static final Map<Integer, Pokemon> catalogo = new HashMap<>();

    //Bloque Static para registrar los 20 pokemones
    
    static {
        //Estructura del constructor del pokemon
        //Pokemon(nombre, nivel, vida, vidaMaxima, ataque, defensa, tipo, nombreEvolucion, nivelEvolucion)
        // --- 1. TIPO PLANTA ---
catalogo.put(1, new Pokemon("Bulbasaur", 5, 20, 20, 12, 10, TipoPokemon.PLANTA, "Ivysaur", 6));
catalogo.put(2, new Pokemon("Ivysaur", 6, 30, 30, 18, 15, TipoPokemon.PLANTA, "Venusaur", 12));
catalogo.put(3, new Pokemon("Venusaur", 12, 45, 45, 25, 20, TipoPokemon.PLANTA, null, 0));

catalogo.put(4, new Pokemon("Treecko", 5, 18, 18, 14, 9, TipoPokemon.PLANTA, "Grovyle", 6));
catalogo.put(5, new Pokemon("Grovyle", 6, 28, 28, 19, 13, TipoPokemon.PLANTA, "Sceptile", 12));
catalogo.put(6, new Pokemon("Sceptile", 12, 42, 42, 26, 18, TipoPokemon.PLANTA, null, 0));

catalogo.put(7, new Pokemon("Snivy", 5, 19, 19, 11, 12, TipoPokemon.PLANTA, "Servine", 6));
catalogo.put(8, new Pokemon("Servine", 6, 28, 28, 17, 15, TipoPokemon.PLANTA, "Serperior", 12));
catalogo.put(9, new Pokemon("Serperior", 12, 40, 40, 24, 20, TipoPokemon.PLANTA, null, 0));

catalogo.put(10, new Pokemon("Rowlet", 5, 22, 22, 13, 10, TipoPokemon.PLANTA, "Dartrix", 6));
catalogo.put(11, new Pokemon("Dartrix", 6, 30, 30, 18, 14, TipoPokemon.PLANTA, "Decidueye", 12));
catalogo.put(12, new Pokemon("Decidueye", 12, 44, 44, 25, 19, TipoPokemon.PLANTA, null, 0));

catalogo.put(13, new Pokemon("Leafeon", 5, 22, 22, 15, 12, TipoPokemon.PLANTA, null, 0));


// --- 2. TIPO FUEGO ---
catalogo.put(14, new Pokemon("Charmander", 5, 19, 19, 14, 8, TipoPokemon.FUEGO, "Charmeleon", 6));
catalogo.put(15, new Pokemon("Charmeleon", 6, 29, 29, 20, 12, TipoPokemon.FUEGO, "Charizard", 12));
catalogo.put(16, new Pokemon("Charizard", 12, 45, 45, 28, 18, TipoPokemon.FUEGO, null, 0));

catalogo.put(17, new Pokemon("Cyndaquil", 5, 18, 18, 15, 7, TipoPokemon.FUEGO, "Quilava", 6));
catalogo.put(18, new Pokemon("Quilava", 6, 27, 27, 20, 11, TipoPokemon.FUEGO, "Typhlosion", 12));
catalogo.put(19, new Pokemon("Typhlosion", 12, 43, 43, 27, 17, TipoPokemon.FUEGO, null, 0));

catalogo.put(20, new Pokemon("Torchic", 5, 20, 20, 13, 9, TipoPokemon.FUEGO, "Combusken", 6));
catalogo.put(21, new Pokemon("Combusken", 6, 30, 30, 19, 13, TipoPokemon.FUEGO, "Blaziken", 12));
catalogo.put(22, new Pokemon("Blaziken", 12, 46, 46, 29, 18, TipoPokemon.FUEGO, null, 0));

catalogo.put(23, new Pokemon("Litten", 5, 21, 21, 14, 8, TipoPokemon.FUEGO, "Torracat", 6));
catalogo.put(24, new Pokemon("Torracat", 6, 31, 31, 20, 12, TipoPokemon.FUEGO, "Incineroar", 12));
catalogo.put(25, new Pokemon("Incineroar", 12, 48, 48, 30, 20, TipoPokemon.FUEGO, null, 0));

catalogo.put(26, new Pokemon("Flareon", 5, 24, 24, 16, 10, TipoPokemon.FUEGO, null, 0));


// --- 3. TIPO AGUA ---
catalogo.put(27, new Pokemon("Squirtle", 5, 22, 22, 10, 12, TipoPokemon.AGUA, "Wartortle", 6));
catalogo.put(28, new Pokemon("Wartortle", 6, 32, 32, 16, 18, TipoPokemon.AGUA, "Blastoise", 12));
catalogo.put(29, new Pokemon("Blastoise", 12, 48, 48, 24, 24, TipoPokemon.AGUA, null, 0));

catalogo.put(30, new Pokemon("Totodile", 5, 23, 23, 13, 11, TipoPokemon.AGUA, "Croconaw", 6));
catalogo.put(31, new Pokemon("Croconaw", 6, 33, 33, 19, 16, TipoPokemon.AGUA, "Feraligatr", 12));
catalogo.put(32, new Pokemon("Feraligatr", 12, 49, 49, 28, 21, TipoPokemon.AGUA, null, 0));

catalogo.put(33, new Pokemon("Piplup", 5, 20, 20, 11, 12, TipoPokemon.AGUA, "Prinplup", 6));
catalogo.put(34, new Pokemon("Prinplup", 6, 29, 29, 17, 17, TipoPokemon.AGUA, "Empoleon", 12));
catalogo.put(35, new Pokemon("Empoleon", 12, 45, 45, 25, 22, TipoPokemon.AGUA, null, 0));

catalogo.put(36, new Pokemon("Oshawott", 5, 21, 21, 12, 10, TipoPokemon.AGUA, "Dewott", 6));
catalogo.put(37, new Pokemon("Dewott", 6, 30, 30, 18, 15, TipoPokemon.AGUA, "Samurott", 12));
catalogo.put(38, new Pokemon("Samurott", 12, 46, 46, 27, 20, TipoPokemon.AGUA, null, 0));

catalogo.put(39, new Pokemon("Vaporeon", 5, 26, 26, 13, 14, TipoPokemon.AGUA, null, 0));


// --- 4. TIPO ELÉCTRICO ---
catalogo.put(40, new Pokemon("Pikachu", 5, 18, 18, 15, 8, TipoPokemon.ELECTRICO, null, 0));

catalogo.put(41, new Pokemon("Shinx", 5, 20, 20, 14, 9, TipoPokemon.ELECTRICO, "Luxio", 6));
catalogo.put(42, new Pokemon("Luxio", 6, 30, 30, 19, 13, TipoPokemon.ELECTRICO, "Luxray", 12));
catalogo.put(43, new Pokemon("Luxray", 12, 46, 46, 28, 18, TipoPokemon.ELECTRICO, null, 0));

// --- 5. TIPO ROCA ---
catalogo.put(44, new Pokemon("Larvitar", 5, 22, 22, 12, 12, TipoPokemon.ROCA, "Pupitar", 6));
catalogo.put(45, new Pokemon("Pupitar", 6, 32, 32, 18, 18, TipoPokemon.ROCA, "Tyranitar", 12));
catalogo.put(46, new Pokemon("Tyranitar", 12, 50, 50, 30, 24, TipoPokemon.ROCA, null, 0));

catalogo.put(47, new Pokemon("Rockruff", 5, 19, 19, 13, 8, TipoPokemon.ROCA, "Lycanroc", 6));
catalogo.put(48, new Pokemon("Lycanroc", 6, 34, 34, 22, 14, TipoPokemon.ROCA, null, 0));



        
       
    }
    //Instancia de un pokemon completamente nuevo basado en el molde del catalogo
    //esto evita que dos jugadores tengan el MISMO pokemon, y el mismo espacio de memoria.
    public static Pokemon obtenerPokemon(int id){
           Pokemon molde = catalogo.get(id);
           //Aca si valida, si se quiere agregar un pokemon con un ID que no esta en el catalogo, retorna un  null
           if (molde == null){
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
            
            
            //Atributos que controlan la evolucion a futuro
            molde.getNivel()>= 5? null : null , 0
           );


           //   Le agregamos un par de ataques
           asignarMovimientosIniciales(copia);
           return copia;
            }



            //Agrega ataques por defecto de forma automatica segun el tipo elemental del pokemon

            private static void asignarMovimientosIniciales(Pokemon p){
                //Todos los Pokemon conocen el ataque fisico placaje

                //Sgundo ataque basando en su tipo elemental
                switch (p.getTipo()) {
                    case PLANTA :
                        p.aprenderMovimiento(new Movimiento("Latigo Cepa",TipoPokemon.PLANTA,20));        
                        break;
                    case FUEGO :
                        p.aprenderMovimiento(new Movimiento("Ascuas", TipoPokemon.FUEGO, 20));
                    break;
                    case AGUA: 
                        p.aprenderMovimiento(new Movimiento("Pistola de agua",TipoPokemon.AGUA, 20));
                        break;
                    case ELECTRICO:
                        p.aprenderMovimiento(new Movimiento("Impactrueno",TipoPokemon.ELECTRICO,22));
                        break;
                    case ROCA: 
                        p.aprenderMovimiento(new Movimiento("Lanzarocas",TipoPokemon.ROCA, 19));
                        break;
                    
                
                }
            }
    }

    

