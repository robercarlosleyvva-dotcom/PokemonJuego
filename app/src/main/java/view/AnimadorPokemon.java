package view;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AnimadorPokemon {

    // 1. EL BRINCO DE ATAQUE (CORREGIDO PARA QUE NO SE ESCAPE)
    public static void animarAtaque(ImageView pokemon, boolean haciaLaDerecha) {
    TranslateTransition brinco = new TranslateTransition(Duration.millis(120), pokemon);
    int distancia = haciaLaDerecha ? 60 : -60; 
    
    brinco.setFromX(0);          // Fuerza a que empiece SIEMPRE en su posición original
    brinco.setToX(distancia);    // Va hacia el enemigo
    brinco.setAutoReverse(true); // Activa el regreso automático
    brinco.setCycleCount(2);     // Va (1) y regresa (2)
    
    brinco.play();
}

    // 2. EL PARPADEO POR DAÑO
    public static void animarRecibirDanio(ImageView pokemon) {
        FadeTransition parpadeo = new FadeTransition(Duration.millis(100), pokemon);
        parpadeo.setFromValue(1.0); 
        parpadeo.setToValue(0.2);   
        parpadeo.setAutoReverse(true);
        parpadeo.setCycleCount(6);  
        parpadeo.play();
    }

    // 3. LA CAÍDA CUANDO SE DEBILITA
    public static void animarDebilitado(ImageView pokemon) {
        TranslateTransition sacudida = new TranslateTransition(Duration.millis(50), pokemon);
        sacudida.setByX(10);
        sacudida.setAutoReverse(true);
        sacudida.setCycleCount(4);

        sacudida.setOnFinished(event -> {
            TranslateTransition caida = new TranslateTransition(Duration.millis(400), pokemon);
            caida.setByY(150); 
            
            FadeTransition desvanecer = new FadeTransition(Duration.millis(400), pokemon);
            desvanecer.setToValue(0.0); 
            
            caida.play();
            desvanecer.play();
        });

        sacudida.play();
    }
}