package battle;

import model.Jugador;
import model.Movimiento;
import model.Pokemon;
import model.TipoPokemon;

public class AccionAtacar extends AccionCombate { 
    
    private String nombreMovimientoSeleccionado;

    public AccionAtacar(String nombreMovimiento) {
        super(1); 
        this.nombreMovimientoSeleccionado = nombreMovimiento;
    }

    // === ¡AHORA SÍ! EL MÉTODO CORRECTO QUE LLAMA TU BATALLA ===
    @Override
    public void ejecutar(Jugador ejecutor, Jugador objetivo) {
        
        // Sacamos a los Pokémon vivos de cada jugador
        Pokemon atacante = ejecutor.getPokemonActivo();
        Pokemon defensor = objetivo.getPokemonActivo();

        if (atacante == null || defensor == null || atacante.estaDebilitado() || defensor.estaDebilitado()) {
            return;
        }

        // 1. Buscamos el objeto Movimiento real
        Movimiento movUsado = null;
        for (Movimiento m : atacante.getMovimientos()) {
            if (m.getNombre().equalsIgnoreCase(nombreMovimientoSeleccionado)) {
                movUsado = m;
                break;
            }
        }

        if (movUsado == null) {
            System.out.println(atacante.getNombre() + " intentó usar " + nombreMovimientoSeleccionado + ", ¡pero falló!");
            return;
        }

        // 2. Calculamos la ventaja de la tabla de tipos
        double multiplicador = calcularMultiplicador(movUsado.getTipoMov(), defensor.getTipo());

        // 3. Calculamos el daño base
        double danioBase = movUsado.getPotencia() + (atacante.getAtaque() * 0.5) - (defensor.getDefensa() * 0.3);
        if (danioBase < 1) {
            danioBase = 1; 
        }

        // 4. Aplicamos multiplicador
        double danioTotal = danioBase * multiplicador;

        // 5. ¡Trancazo aplicado al rival!
        defensor.takeDamage(danioTotal);

        // 6. Mensajes a consola (ahora sí van a aparecer)
        System.out.println("⚔️ " + atacante.getNombre() + " de " + ejecutor.getNombre() + " usó " + movUsado.getNombre() + "!");
        if (multiplicador > 1.0) {
            System.out.println("   💥 ¡Es súper eficaz!");
        } else if (multiplicador < 1.0) {
            System.out.println("   🛡️ No es muy eficaz...");
        }
        System.out.println("   -> Causó " + (int)danioTotal + " puntos de daño a " + defensor.getNombre() + ".\n");
    }

    // El de Pokémon lo dejamos vacío solo para que la clase padre no se queje
    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
    }

    // === LA TABLA DE TIPOS OFICIAL ===
    private double calcularMultiplicador(TipoPokemon tipoAtaque, TipoPokemon tipoDefensor) {
        
        switch (tipoAtaque) {
            case FUEGO:
                if (tipoDefensor == TipoPokemon.PLANTA) return 2.0; 
                if (tipoDefensor == TipoPokemon.AGUA || tipoDefensor == TipoPokemon.ROCA) return 0.5;
                break;
                
            case AGUA:
                if (tipoDefensor == TipoPokemon.FUEGO || tipoDefensor == TipoPokemon.ROCA) return 2.0; 
                if (tipoDefensor == TipoPokemon.PLANTA) return 0.5;
                break;
                
            case PLANTA:
                if (tipoDefensor == TipoPokemon.AGUA || tipoDefensor == TipoPokemon.ROCA) return 2.0; 
                if (tipoDefensor == TipoPokemon.FUEGO) return 0.5;
                break;
                
            case ELECTRICO:
                if (tipoDefensor == TipoPokemon.AGUA) return 2.0; 
                if (tipoDefensor == TipoPokemon.PLANTA || tipoDefensor == TipoPokemon.ROCA) return 0.5; 
                break;
                
            case ROCA:
                if (tipoDefensor == TipoPokemon.FUEGO || tipoDefensor == TipoPokemon.ELECTRICO) return 2.0; 
                if (tipoDefensor == TipoPokemon.AGUA || tipoDefensor == TipoPokemon.PLANTA) return 0.5; 
                break;
        }
        
        return 1.0; 
    }
}