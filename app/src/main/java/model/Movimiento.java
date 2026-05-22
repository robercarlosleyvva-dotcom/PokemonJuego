package model;

import java.io.Serializable;

public class Movimiento implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String nombreMov;
    private TipoPokemon tipoMov;
    private int potencia;

    public Movimiento(String nombreMov, TipoPokemon tipoMov, int potencia){
        this.nombreMov = nombreMov;
        this.tipoMov = tipoMov;
        this.potencia = potencia;

    }

    //getters y setters

    public String getNombre(){
        return nombreMov;
    }
    public TipoPokemon getTipoMov(){
        return tipoMov;
    }
    public int getPotencia(){
        return potencia;
    }




}
