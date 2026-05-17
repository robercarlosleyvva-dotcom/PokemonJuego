package model;
import java.util.ArrayList;
import java.util.Random;

public class JugadorCPU  extends Jugador{
    private Random random;

    public JugadorCPU(String nombre){
        super(nombre);
        this.random = new Random();

    }

    @Override
    public Movimiento elegirMovimiento(Pokemon pokemonActivo) {
//Validacion de seguridad, si el pokemon es nulo o esta debilitado, no hay movimiento
        if(pokemonActivo== null || pokemonActivo.estaDebilitado()){
            return null;
        }
        //accedemos a la lista de movimientos del pokemon activo
        ArrayList<Movimiento> movimientosDisponibles = pokemonActivo.getMovimientos();

        //Si el pokemon no tiene movimientos disponibles 

        if(movimientosDisponibles.isEmpty()){
            System.out.println("Alerta: " + pokemonActivo.getNombre() + " no tiene movimientos aprendidos.");
            return null;
        }
        //generamos un indice aleatorio entre 1 y los movimientos aprendidos

        int indiceAleatorio = random.nextInt(movimientosDisponibles.size());

        //retorna un movimiento al azar

    



         return movimientosDisponibles.get(indiceAleatorio);
    }
   

    
}
