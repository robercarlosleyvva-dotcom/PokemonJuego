package model;

import java.io.Serializable;

public class JugadorHumano extends Jugador implements Serializable{

    private static final long serialVersionUID = 1L;


    //Almacena el movimiento que el usuario elija en la interfaz grafica
    private Movimiento movimientoSeleccionado;

    public JugadorHumano(String nombre){
        super(nombre);
    }

    //Este metodo lo llamará el Controller de JavaFX cuando el jugador presione un boton de ataque
    public void setMovimientoSeleccionado(Movimiento mov){
        this.movimientoSeleccionado = mov;
    }
    
    @Override
    public Movimiento elegirMovimiento(Pokemon pokemonActivo) {
        // Retorna la acción que el usuario seleccionó manualmente
        return movimientoSeleccionado;
    }


    
}