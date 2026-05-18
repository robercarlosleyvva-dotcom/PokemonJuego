package battle;

import model.Jugador;

public class AccionCombate {
    private int prioridad;

    // Constructor base
    public AccionCombate(int prioridad) {
        this.prioridad = prioridad;
    }

    // Método que heredan las demás acciones
    public void ejecutar(Jugador ejecutor, Jugador objetivo) {
        // Se sobrescribe en las clases hijas
    }

    // Métodos de acceso
    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
}