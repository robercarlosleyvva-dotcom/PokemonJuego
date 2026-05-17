package model;

public class ItemCurativo {
    private String nombre;
    private int cantidadCuracion;

    public ItemCurativo(String nombre, int cantidadCuracion){
        this.nombre = nombre;
        this.cantidadCuracion = cantidadCuracion;


    }
    //El item aplica su logica directamente sobre el pokemon objetivo
    public void usarEn(Pokemon pokemon){
            if(pokemon != null && !pokemon.estaDebilitado()){
                pokemon.curar(cantidadCuracion);
                System.out.println("Se uso  " + nombre + " en "+  pokemon.getNombre() + ". Recupero " + cantidadCuracion + "de HP.");
               }else{
                System.out.println("No se puede usar " + nombre);
               }
   }

   //Getters
   public String getNombre(){
    return  nombre;
   }
   
   public int getCantidadCuracion(){
    return cantidadCuracion;
   }
    

}
