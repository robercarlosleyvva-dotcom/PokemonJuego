package model;

public class Pokemon {

    //ATRIBUTOS
    private String nombre;
    private int nivel;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defensa;

    private TipoPokemon tipo;

    // CONSTRUCTOR
    public Pokemon(String nombre,
                   int nivel,
                   int vida,
                   int vidaMaxima,
                   int ataque,
                   int defensa,
                   TipoPokemon tipo){

        this.nombre = nombre;
        this.nivel = nivel;
        this.vida = vida;
        this.vidaMaxima= vidaMaxima;
        this.ataque = ataque;
        this.defensa = defensa;
        this.tipo = tipo;
        }

    //Getters

    public String getNombre(){
        return nombre;
    }
    public int getNivel (){
        return nivel;
    }
    public int getVida(){
        return vida;
    }
    public int getAtaque(){
        return ataque;
    }
    public int getDefensa(){
        return defensa;
    }
    public TipoPokemon getTipo(){
        return tipo;
    }
    
   //METODOS
   // recibir danio
    public void recibirDanio(int danio) {

    vida -= danio;

    if (vida < 0) {
        vida = 0;
    }
    //Verificar si esta debilitado true o false
    }
    public boolean estaDebilitado (){
        return vida <=0;
    }
    //Curar al pokemon
    public void  curar(int cantidad){
        vida += cantidad;

        if (vida > vidaMaxima){
            vida = vidaMaxima;

        }
    }
    //Subir de Nivel, se mejoran las stats
    public void subirNivel(){
        nivel++;

        vidaMaxima +=10;
        ataque+=2;
        defensa +=2;
        vida +=vidaMaxima;
    }
}
