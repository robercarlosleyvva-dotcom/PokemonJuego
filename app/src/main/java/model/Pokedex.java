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
        
        //Tipo Planta
        catalogo.put(1, new Pokemon("Bulbasaur", 5, 20, 20, 12, 10, TipoPokemon.PLANTA, "Ivysaur", 16));
        catalogo.put(2, new Pokemon("Treecko", 5, 18, 18, 14, 9, TipoPokemon.PLANTA, "Grovyle", 16));
        catalogo.put(3, new Pokemon("Snivy", 5, 19, 19, 11, 12, TipoPokemon.PLANTA, "Servine", 17));
        catalogo.put(4, new Pokemon("Rowlet", 5, 22, 22, 13, 10, TipoPokemon.PLANTA, "Dartrix", 17));

        // --- 2. TIPO FUEGO ---
        catalogo.put(5, new Pokemon("Charmander", 5, 19, 19, 14, 8, TipoPokemon.FUEGO, "Charmeleon", 16));
        catalogo.put(6, new Pokemon("Cyndaquil", 5, 18, 18, 15, 7, TipoPokemon.FUEGO, "Quilava", 14));
        catalogo.put(7, new Pokemon("Torchic", 5, 20, 20, 13, 9, TipoPokemon.FUEGO, "Combusken", 16));
        catalogo.put(8, new Pokemon("Litten", 5, 21, 21, 14, 8, TipoPokemon.FUEGO, "Torracat", 17));

        // --- 3. TIPO AGUA ---
        catalogo.put(9, new Pokemon("Squirtle", 5, 22, 22, 10, 12, TipoPokemon.AGUA, "Wartortle", 16));
        catalogo.put(10, new Pokemon("Totodile", 5, 23, 23, 13, 11, TipoPokemon.AGUA, "Croconaw", 18));
        catalogo.put(11, new Pokemon("Piplup", 5, 20, 20, 11, 12, TipoPokemon.AGUA, "Prinplup", 16));
        catalogo.put(12, new Pokemon("Oshawott", 5, 21, 21, 12, 10, TipoPokemon.AGUA, "Dewott", 17));

        // --- 4. TIPO ELÉCTRICO ---
        catalogo.put(13, new Pokemon("Pikachu", 5, 18, 18, 15, 8, TipoPokemon.ELECTRICO, "Raichu", 22));
        catalogo.put(14, new Pokemon("Shinx", 5, 20, 20, 14, 9, TipoPokemon.ELECTRICO, "Luxio", 15));
        catalogo.put(15, new Pokemon("Electrike", 5, 19, 19, 13, 8, TipoPokemon.ELECTRICO, "Manectric", 26));
        catalogo.put(16, new Pokemon("Elekid", 5, 17, 17, 14, 7, TipoPokemon.ELECTRICO, "Electabuzz", 30));

        // --- 5. TIPO ROCA ---
        catalogo.put(17, new Pokemon("Larvitar", 5, 22, 22, 12, 12, TipoPokemon.ROCA, "Pupitar", 30));
        catalogo.put(18, new Pokemon("Onix", 5, 28, 28, 9, 16, TipoPokemon.ROCA, null, 0)); // No evoluciona en este set
        catalogo.put(19, new Pokemon("Sudowoodo", 5, 25, 25, 14, 15, TipoPokemon.ROCA, null, 0)); // No evoluciona
        catalogo.put(20, new Pokemon("Rockruff", 5, 19, 19, 13, 8, TipoPokemon.ROCA, "Lycanroc", 25));        
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
                p.aprenderMovimiento( new Movimiento ("Placaje", TipoPokemon.NORMAL, 10));

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

    

