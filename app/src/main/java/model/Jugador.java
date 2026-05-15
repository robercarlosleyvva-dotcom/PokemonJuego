package model;
import java.util.ArrayList;

public class Jugador {
    //Atributos
    private String nombre;
    private ArrayList<Pokemon>  equipo;

    //Constructor

    public Jugador (String nombre){
        this.nombre = nombre;
        
        equipo = new ArrayList<>();
    }

    //Metodos
    public void agregarPokemon(Pokemon pokemon){
        equipo.add(pokemon);
    }

    //Getter
    public ArrayList<Pokemon> getEquipo() {

    return equipo;
}


    
}
