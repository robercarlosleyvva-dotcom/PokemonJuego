package battle;
import model.TipoPokemon;

public class SistemaDeTipos {

    public double obtenerMultiplicador(
        TipoPokemon atacante,
        TipoPokemon defensor
     )
     {

    //VENTAJA EL DAÑO AUMENTA 2.0
        //Fuego > Planta 
        if(atacante ==TipoPokemon.FUEGO && 
            defensor == TipoPokemon.PLANTA){
                return 2.0;
            }
        // FUEGO > PLANTA
        // AGUA > FUEGO
        // PLANTA > AGUA

        // ELECTRICO > AGUA
        // ROCA > ELECTRICO
        // ROCA > FUEGO
        // PLANTA > ROCA
        // AGUA > ROCA
    
    //DESVENTAJA EL DAÑO DISMINUYE 0.5

        // PLANTA < FUEGO
        // FUEGO < AGUA
        // AGUA < PLANTA

        // AGUA < ELECTRICO
        // ELECTRICO < ROCA

        // FUEGO < ROCA
        // ROCA < PLANTA

        // ROCA < AGUA
        
            //else
           return 1.0;
     }
    
}
