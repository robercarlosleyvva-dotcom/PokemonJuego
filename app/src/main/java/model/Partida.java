package model;

public class Partida {
    //Atributos de estado global
    private JugadorHumano jugadorPrincipal;
    private int batallaActualPvE; //Controla el progreso
    private boolean partidaCompletada;

    //Constructor para una partida totalmente nueva
    public Partida(String nombreJugador){
        this.jugadorPrincipal = new JugadorHumano(nombreJugador);
        this.batallaActualPvE = 1; //inicia en la primera batalla obligatoria
        this.partidaCompletada = false;
    //Al inicio le damos su primer Pokemon para iniciar
    //usando el ID de nuestra pokedex como inicial por defecto

    Pokemon inicial = Pokedex.obtenerPokemon(1);//Por ejemplo usando el ID 1 , se le da a bulbasaur
    if(inicial != null){
        this.jugadorPrincipal.agregarPokemon(inicial);
        System.out.println("Elegiste de Pokemon a "+  inicial.getNombre());
    }
    }

    //Incremendo de dificultad segun  el número de batalla actual
     public JugadorCPU generarSiguienteOponenteCPU(){
        JugadorCPU rival;

        switch (batallaActualPvE){
            case 1: 
                rival = new JugadorCPU("Entrenador Azul");
                rival.agregarPokemon(Pokedex.obtenerPokemon(15));
            break;
            case 2: 
                rival = new JugadorCPU("Entrenador Verde");
                rival.agregarPokemon(Pokedex.obtenerPokemon(13));
                rival.agregarPokemon(Pokedex.obtenerPokemon(11));
            break;
            case 3:
                rival = new JugadorCPU("Entrenador Rojo");
                rival.agregarPokemon(Pokedex.obtenerPokemon(26));
                rival.agregarPokemon(Pokedex.obtenerPokemon(18));
                rival.agregarPokemon(Pokedex.obtenerPokemon(20));
            break;
            default :
            rival = new JugadorCPU("Campeon");
            rival.agregarPokemon(Pokedex.obtenerPokemon(40));
            rival.agregarPokemon(Pokedex.obtenerPokemon(48));
            rival.agregarPokemon(Pokedex.obtenerPokemon(16));
            rival.agregarPokemon(Pokedex.obtenerPokemon(38));
            rival.agregarPokemon(Pokedex.obtenerPokemon(46));
             rival.agregarPokemon(Pokedex.obtenerPokemon(6));

            break;
            
        }
        return rival;
        
     }
     //Registra el resultado de una victoria en el modo PvE.
     //Avanza el progreso del jugador y premia a su equipo con experiencia.

     public void registrarVictoriaPvE(){
        System.out.println("¡Victoria registrada para " + jugadorPrincipal.getNombre() +"!");

        //Repartir experiencia a los pokemon
        for(Pokemon p : jugadorPrincipal.getEquipo()){
            if(!p.estaDebilitado()){
                p.ganarExperiencia(100*batallaActualPvE);
            }
        }

        //Avanzar en la historia de las 3 batallas
        if (batallaActualPvE <3){
            batallaActualPvE++;
            System.err.println("Has desbloqueado la Batalla PvE numero " + batallaActualPvE + "!");

        }
     }
     //Restablecer la salud de todo el equipo del jugador.
     public void curarEquipo(){
        for (Pokemon p : jugadorPrincipal.getEquipo()){
            p.curar(p.getVidaMaxima());
        }
        System.out.print("Todo tu equipo ha restaurado el 100% de sus HP. ");

     }
     // --- GETTERS Y SETTERS ---
    // Vitales para que el módulo de persistencia pueda leer y sobreescribir el estado al cargar archivo
    
    public JugadorHumano getJugadorPrincipal() {
        return jugadorPrincipal;
    }

    public void setJugadorPrincipal(JugadorHumano jugadorPrincipal) {
        this.jugadorPrincipal = jugadorPrincipal;
    }

    public int getBatallaActualPvE() {
        return batallaActualPvE;
    }

    public void setBatallaActualPvE(int batallaActualPvE) {
        this.batallaActualPvE = batallaActualPvE;
    }

    public boolean isPartidaCompletada() {
        return partidaCompletada;
    }

    public void setPartidaCompletada(boolean partidaCompletada) {
        this.partidaCompletada = partidaCompletada;
    }
}

    

