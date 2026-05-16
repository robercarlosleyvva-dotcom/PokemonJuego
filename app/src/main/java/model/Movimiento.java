package model;

public class Movimiento {
    private String nombreMov;
    private TipoPokemon tipoMov;
    private int potencia;

    public Movimiento(String nombreMov, TipoPokemon tipoMov, int potencia){
        this.nombreMov = nombreMov;
        this.tipoMov = tipoMov;
        this.potencia = potencia;

    }

    //getters y setters

    public String getNombreMov(){
        return nombreMov;
    }
    public TipoPokemon getTipoMov(){
        return tipoMov;
    }
    public int getPotencia(){
        return potencia;
    }



    
}
