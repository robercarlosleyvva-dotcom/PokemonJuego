package model;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Jugador implements Serializable{

    private static final long serialVersionUID = 1L;
    
    //Atributos encapsulados
    private String nombre;
    private ArrayList<Pokemon>  equipo;

    //Constructor

    public Jugador (String nombre){
        this.nombre = nombre;
        equipo = new ArrayList<>();
    }

    //Metodos de gestion del equipo
    public void agregarPokemon(Pokemon pokemon){
        //Limitamos el equipo a un maximo de 6
        if(equipo.size() <6){
        equipo.add(pokemon);
        System.out.println(pokemon.getNombre() + " fue añadido al equipo de " + this.nombre);
    } else{
        System.out.println("¡El equipo de " + this.nombre + "ya esta lleno¡");
    }
    }

    public void mostrarEquipo() {
    for (Pokemon pokemon : equipo) {

        System.out.println(pokemon.getNombre());
    }
}
    //Metodo de utilidad para el sistema de batalla
    public Pokemon getPokemonActivo(){
        for (Pokemon p : equipo){
            if (!p.estaDebilitado()){
                return p;
        }

    }
    return null;}

    //Se declara el metodo abstracto
    public abstract Movimiento elegirMovimiento(Pokemon pokemonActivo);

    //Getter

    public String getNombre(){
        return nombre;
    }
    public ArrayList<Pokemon> getEquipo() {

    return equipo;

}


    
}
