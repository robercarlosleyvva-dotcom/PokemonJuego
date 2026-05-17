package model;

import java.util.ArrayList;

public class Pokemon {

    // ATRIBUTOS
    private String nombre;
    private int nivel;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defensa;
    private TipoPokemon tipo;

    private ArrayList<Movimiento> movimientos; // Array con los movimientos del Pokémon (máximo 4)
    private int experiencia;                  // Progreso para subir de nivel
    private String nombreEvolucion;            // Destino de la evolución
    private int nivelEvolucion;                // Nivel requerido para evolucionar

    // CONSTRUCTOR
    public Pokemon(String nombre,
                   int nivel,
                   int vida,
                   int vidaMaxima,
                   int ataque,
                   int defensa,
                   TipoPokemon tipo,
                   String nombreEvolucion,
                   int nivelEvolucion) {

        this.nombre = nombre;
        this.nivel = nivel;
        this.vida = vida;
        this.vidaMaxima = vidaMaxima;
        this.ataque = ataque;
        this.defensa = defensa;
        this.tipo = tipo;
        this.movimientos = new ArrayList<>();
        this.experiencia = 0;
        this.nombreEvolucion = nombreEvolucion;
        this.nivelEvolucion = nivelEvolucion;
    }

    // GETTERS
    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public int getVida() {
        return vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public TipoPokemon getTipo() {
        return tipo;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    // MÉTODOS DE GESTIÓN

    public void aprenderMovimiento(Movimiento movimiento) {
        if (movimientos.size() < 4) {
            movimientos.add(movimiento);
        } else {
            System.out.println(nombre + " ya conoce 4 movimientos.");
        }
    }

    public void ganarExperiencia(int cantidad) {
        this.experiencia += cantidad;
        System.out.println(nombre + " ganó " + cantidad + " de experiencia.");

        int expNecesaria = this.nivel * 50;
        if (this.experiencia >= expNecesaria) {
            this.experiencia -= expNecesaria;
            subirNivel();
        }
    }

    private void verificarEvolucion() {
        if (nombreEvolucion != null && this.nivel >= nivelEvolucion) {
            System.out.println("¡Espera! " + this.nombre + " está evolucionando...");
            this.nombre = nombreEvolucion;
            this.nombreEvolucion = null; // Ya evolucionó, eliminamos el destino futuro
            System.out.println("¡Felicidades! Tu Pokémon ha evolucionado en " + this.nombre + ".");
        }
    }

    public void subirNivel() {
        nivel++;
        vidaMaxima += 10;
        ataque += 2;
        defensa += 2;
        vida = vidaMaxima; // Se cura al subir de nivel
        System.out.println(nombre + " subió al nivel " + nivel + "!");

        // Cada vez que sube de nivel, revisamos si ya puede evolucionar
        verificarEvolucion();
    }

    // MÉTODOS DE COMBATE

    public void recibirDanio(int danio) {
        vida -= danio;
        if (vida < 0) {
            vida = 0;
        }
    }

    public boolean estaDebilitado() {
        return vida <= 0;
    }

    public void curar(int cantidad) {
        vida += cantidad;
        if (vida > vidaMaxima) {
            vida = vidaMaxima;
        }
    }

    public int getHp() {
        return vida;
    }

    public void takeDamage(double danio) {
        recibirDanio((int) Math.round(danio));
    }
}

