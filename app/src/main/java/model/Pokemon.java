package model;

import java.util.ArrayList;

public class Pokemon {

    //ATRIBUTOS
    private String nombre;
    private int nivel;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defensa;
    private TipoPokemon tipo;


    private ArrayList<Movimiento> movimientos; //Array con los movimientos de los Pokemon (4)
    private int experiencia ;                  //Progeso para subir de nivel;
    private String nombreEvolucion;            //Destino de la evolucion
    private int nivelEvolucion;                //Nivel requerido para evolucionar

   

    // CONSTRUCTOR
    public Pokemon(String nombre,
                   int nivel,
                   int vida,
                   int vidaMaxima,
                   int ataque,
                   int defensa,
                   TipoPokemon tipo,
                   String nombreEvolucion,
                   int nivelEvolucion
                     ){

        this.nombre = nombre;
        this.nivel = nivel;
        this.vida = vida;
        this.vidaMaxima= vidaMaxima;
        this.ataque = ataque;
        this.defensa = defensa;
        this.tipo = tipo;
        this.movimientos = new ArrayList<>();
        this.experiencia =0;
        this.nombreEvolucion =nombreEvolucion;
        this.nivelEvolucion = nivelEvolucion;

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
    public int getVidaMaxima(){
        return vidaMaxima;
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
     //Getter para movimientos
   public ArrayList<Movimiento> getMovimientos(){
    return movimientos;
   }
   //METODOS

   //Metodos para aprender movimientos 
   public void aprenderMovimiento (Movimiento movimiento){
    if (movimientos.size() <4){
        movimientos.add(movimiento);
    } else{
        System.out.println(nombre + " ya conoce 4 movimientos.");
    }
   }

  
   
   // metodo para Ganar experiencia y evolucionar 
   public void ganarExperiencia(int cantidad){
    this.experiencia += cantidad;
    System.out.println(nombre + " gano "+ cantidad + " de experiencia");
    // Si acomula suficiente experiencia ( por ejemplo: nivel *50), sube de nivel
    int expNecesaria = this.nivel*50;
    if (this.experiencia >= expNecesaria){
        this.experiencia -= expNecesaria;
        subirNivel();
    }
}

//Metodo para verificar Evolución
private void verificarEvolucion(){
    if(nombreEvolucion != null && this.nivel >= nivelEvolucion){
        System.out.println("¡Espera! "+ this.nombre + "esta evolucionando...");
        this.nombre = nombreEvolucion;
        this.nombreEvolucion = null; //ya evoluciono , eliminamos el destino futuro
        System.out.println("¡Felicidades! Tu pokemon ha evolucionado en " + this.nombre + ".");
    
    }
    }

//metodo para subir de nivel
    
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
        System.out.println(nombre + " subio al nivel " + nivel + "!");
    }
}
