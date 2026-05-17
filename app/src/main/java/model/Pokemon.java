package model;

public class Pokemon {

    private String nombre;
    private int nivel;
    private int vida;
    private int ataque;
    private int defensa;

    private TipoPokemon tipo;

    private double hp = 100;

    //Metodo Constructor
    public Pokemon() {
    }

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

        //GETTERS
        public String getNombre() {
            return "Pokémon";
        }

        public int getNivel() {
            return 1;
        }

        public int getAtaque() {
            return 10;
        }

        public int getDefensa() {
            return 10;
        }

        public TipoPokemon getTipo() {
            return TipoPokemon.FUEGO;
        }

        //Sirven para ver si el Pokemon ya puede evolucionar o no
        public boolean canEvolve() {
            return false;
        }

        public Pokemon evolve() {
            return this;
        }

        public double getHp() {
            return hp;
        }

        public void takeDamage(double danio) {
            this.hp -= danio; if(this.hp < 0) this.hp = 0;
        }
}


