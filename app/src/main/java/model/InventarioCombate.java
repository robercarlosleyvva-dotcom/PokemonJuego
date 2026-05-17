package model;

import java.util.HashMap;
import java.util.Map;


public class InventarioCombate {
    //Almacena el item como clave y la cantidad disponible como valor
    private Map<String, Integer> items;
    
    //Guardamos las instancias de los objetos curativos disponibles en el juego
    private Map<String, ItemCurativo> catalogoItems;
    
    //Constructor, inicializa el inventario con algunos objetos basicos apra la aventura
    public InventarioCombate(){
        this.items = new HashMap<>();
        this.catalogoItems = new HashMap<>();

        //Registramos los dos items del juego en el catalogo
        catalogoItems.put("Pocion", new ItemCurativo("Pocion", 20));
        catalogoItems.put("SuperPocion", new ItemCurativo("SuperPocion",50));

        // Le otorgamos una cantidad inicial al jugador al empezar la partida
        items.put("Pocion", 3); //el jugador inicia con 3 pociones y 1 superpocion
        items.put("Superpocion",1);
    }

    //Devuelve la cantidad disponible de un item especifico
    public int obtenerCantidad(String claveItem){
        return items.getOrDefault(claveItem,0);
    }

    //Aplica el efecto de curacion sobre un pokemon y descuenta 1 de la mochila.
    //retorna true si el item se aplico con exito , false si no quedan existencias.

    public boolean usarItem(String claveItem, Pokemon pokemon){
        int cantidadActual =obtenerCantidad(claveItem);

        if (cantidadActual <=0){
            System.out.println("No te quedan unidades de este objeto");
            return false;
        }

        //Si el pokemon ya tiene la vida al maximo , evitamos desperdiciar el objeto
        if(pokemon.getVida() >= pokemon.getVidaMaxima()){
            System.err.println(pokemon.getNombre() + " ya tiene la salud al maximo. ");
    
            return false;

        }
        ItemCurativo item = catalogoItems.get(claveItem);
        if (item != null) {
            item.usarEn(pokemon);
            // Restamos uno de la cantidad en el inventario
            items.put(claveItem, cantidadActual - 1);
            return true;
        }
        return false;
    }

    
}
