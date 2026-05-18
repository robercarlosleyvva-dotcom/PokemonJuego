package battle;

import model.Jugador;

public class AccionUsarObjeto extends AccionCombate {
    // Usamos un String para el nombre del objeto para mantenerlo simple por ahora
    private String objeto;

    public AccionUsarObjeto(String objeto) {
        // Prioridad 2: Al igual que los cambios de Pokémon,
        // usar un objeto de curación va ANTES que los ataques en el turno.
        super(2);
        this.objeto = objeto;
    }

    @Override
    public void ejecutar(Jugador ejecutor, Jugador objetivo) {
        System.out.println(ejecutor.getNombre() + " usó " + objeto);

        // Aquí es donde conectarás el efecto en el futuro con tus amigos, por ejemplo:
        // ejecutor.getPokemonActivo().curar(50);
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }
}