package model;

public class Pokemon {

    private String nombre;
    private int nivel;
    private int vida;
    private int ataque;
    private int defensa;

    private TipoPokemon tipo;

    // CONSTRUCTOR
    public Pokemon(String nombre,
                   int nivel,
                   int vida,
                   int ataque,
                   int defensa,
                   TipoPokemon tipo) {

        this.nombre = nombre;
        this.nivel = nivel;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.tipo = tipo;
    }
    
}
