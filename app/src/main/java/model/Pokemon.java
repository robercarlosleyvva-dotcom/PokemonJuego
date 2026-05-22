package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Pokemon  implements Serializable{

    private static final long serialVersionUID = 1L;

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
    private String imagen;                     // Guarda una imagen del pokemon

    // CONSTRUCTOR
    public Pokemon(
                   String nombre,
                   int nivel,
                   int vida,
                   int vidaMaxima,
                   int ataque,
                   int defensa,
                   TipoPokemon tipo,
                   String nombreEvolucion,
                   int nivelEvolucion,
                   String imagen) {
        
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
        this.imagen = imagen;
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

    public String getImagen() {
        return imagen;
    }
     
                
    public String getNombreEvolucion(){
        return nombreEvolucion;
    }
    public int getNivelEvolucion(){
        return nivelEvolucion;
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
        System.out.println(nombre + " gano " + cantidad + " de experiencia.");

        int expNecesaria = this.nivel * 50;
        if (this.experiencia >= expNecesaria) {
            this.experiencia -= expNecesaria;
            subirNivel();
        }
    }

    // === MODIFICADO: Ahora busca en la Pokedex para mutar la imagen y los stats base ===
    private void verificarEvolucion() {
        if (nombreEvolucion != null && this.nivel >= nivelEvolucion) {
            System.out.println("¡Espera! " + this.nombre + " esta evolucionando...");
            
            // Buscamos el molde oficial de la evolución en el catálogo estático
            Pokemon evolucionMolde = Pokedex.buscarPorNombre(nombreEvolucion);
            
            if (evolucionMolde != null) {
                String nombreAnterior = this.nombre;
                
                // Actualizamos los datos de identidad e IMAGEN
                this.nombre = evolucionMolde.getNombre();
                this.imagen = evolucionMolde.getImagen(); // <--- ¡Aquí cambia la foto!
                
                // Actualizamos a los stats base de la evolución de forma proporcional
                this.vidaMaxima = evolucionMolde.getVidaMaxima();
                this.vida = this.vidaMaxima; // Se cura por completo
                this.ataque = evolucionMolde.getAtaque();
                this.defensa = evolucionMolde.getDefensa();
                
                // Preparamos los datos por si este nuevo Pokémon tiene otra evolución más adelante
                this.nombreEvolucion = evolucionMolde.getNombreEvolucion();
                this.nivelEvolucion = evolucionMolde.getNivelEvolucion();
                
                System.out.println("¡Felicidades! Tu " + nombreAnterior + " ha evolucionado en " + this.nombre + ".");
            }
        }
    }

    public void subirNivel() {
        nivel++;
        vidaMaxima += 10;
        ataque += 2;
        defensa += 2;
        vida = vidaMaxima; // Se cura al subir de nivel
        System.out.println(nombre + " subio al nivel " + nivel + "!");

        // Cada vez que sube de nivel, revisamos si ya cumple con los requisitos de la Pokedex
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