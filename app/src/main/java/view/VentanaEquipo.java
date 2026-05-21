package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Equipo;
import model.Pokedex;
import model.Pokemon;

public class VentanaEquipo {

    private Equipo equipo;
    // Lista fija con los 12 nombres exactos
    private final String[] disponibles = {
        "Bulbasaur", "Charmander", "Squirtle", "Cyndaquil", "Totodile", "Treecko",
        "Torchic", "Piplup", "Snivy", "Oshawott", "Rowlet", "Litten"
    };

    public VentanaEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Mi Equipo");
        stage.setResizable(false);

        // 1. CONTENEDOR PRINCIPAL
        AnchorPane root = new AnchorPane();

        // 2. IMAGEN DE FONDO
        ImageView imgFondo = new ImageView();
        try {
            imgFondo.setImage(new Image(getClass().getResourceAsStream("/pokemon/FE.jpg")));
        } catch (Exception e) {
            System.out.println("No se encontro la imagen de fondo para Equipo.");
        }
        imgFondo.setFitWidth(550);
        imgFondo.setFitHeight(650);
        AnchorPane.setTopAnchor(imgFondo, 0.0);
        AnchorPane.setLeftAnchor(imgFondo, 0.0);

        // 3. TITULO E INDICADOR DE CANTIDAD
        Label lblTitulo = new Label("SELECCIONA TU EQUIPO");
        String estiloTexto = "-fx-font-weight: bold; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 5, 0, 0, 0);";
        lblTitulo.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 22px; " + estiloTexto);
        lblTitulo.setLayoutX(110);
        lblTitulo.setLayoutY(25);

        Label lblContador = new Label("Seleccionados: " + equipo.getListaPokemon().size() + " / 6");
        lblContador.setStyle("-fx-font-size: 16px; -fx-text-fill: #f1c40f; " + estiloTexto);
        lblContador.setLayoutX(210);
        lblContador.setLayoutY(65);

        // 4. CUADRICULA PARA LOS 12 POKÉMON (GridPane)
        GridPane cuadricula = new GridPane();
        cuadricula.setHgap(20); 
        cuadricula.setVgap(15); 
        cuadricula.setAlignment(Pos.CENTER);
        
        cuadricula.setLayoutX(45);
        cuadricula.setLayoutY(110);

        int columna = 0;
        int fila = 0;

        for (String nombrePoke : disponibles) {
            Pokemon p = Pokedex.buscarPorNombre(nombrePoke);
            if (p == null) continue;

            VBox tarjeta = new VBox(5);
            tarjeta.setAlignment(Pos.CENTER);
            tarjeta.setPrefSize(100, 110);
            tarjeta.setStyle("-fx-background-color: rgba(255, 255, 255, 0.2); -fx-background-radius: 10; -fx-cursor: hand;");

            ImageView imgPoke = new ImageView();
            imgPoke.setFitWidth(70);
            imgPoke.setFitHeight(70);
            imgPoke.setPreserveRatio(true);
            try {
                imgPoke.setImage(new Image(getClass().getResourceAsStream(p.getImagen())));
            } catch (Exception e) {
                System.out.println("No se encontro la imagen de " + nombrePoke);
            }

            Label lblNombre = new Label(p.getNombre());
            lblNombre.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: black;");

            tarjeta.getChildren().addAll(imgPoke, lblNombre);

            if (equipo.getListaPokemon().contains(p)) {
                tarjeta.setStyle("-fx-background-color: rgba(46, 204, 113, 0.4); -fx-background-radius: 10; -fx-border-color: #2ecc71; -fx-border-width: 2; -fx-border-radius: 10; -fx-cursor: hand;");
            }

            tarjeta.setOnMouseClicked(e -> {
                if (equipo.getListaPokemon().contains(p)) {
                    equipo.getListaPokemon().remove(p);
                    tarjeta.setStyle("-fx-background-color: rgba(255, 255, 255, 0.2); -fx-background-radius: 10; -fx-cursor: hand;");
                } else {
                    if (equipo.getListaPokemon().size() < 6) {
                        equipo.getListaPokemon().add(p);
                        tarjeta.setStyle("-fx-background-color: rgba(46, 204, 113, 0.4); -fx-background-radius: 10; -fx-border-color: #2ecc71; -fx-border-width: 2; -fx-border-radius: 10; -fx-cursor: hand;");
                    } else {
                        lblTitulo.setText("¡EQUIPO LLENO (MAX 6)!");
                        new javafx.animation.Timeline(new javafx.animation.KeyFrame(
                            javafx.util.Duration.seconds(1.5), 
                            ev -> lblTitulo.setText("SELECCIONA TU EQUIPO")
                        )).play();
                    }
                }
                lblContador.setText("Seleccionados: " + equipo.getListaPokemon().size() + " / 6");
            });

            cuadricula.add(tarjeta, columna, fila);
            columna++;
            if (columna == 4) {
                columna = 0;
                fila++;
            }
        }

        // -------------------------------------------------------------
        // 5. BOTÓN DE CONFIRMAR EQUIPO -> ¡AHORA LANZA LA BATALLA REAL!
        Button btnConfirmar = new Button("Confirmar Equipo");
        btnConfirmar.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-color: #3498db; -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;");
        btnConfirmar.setPrefWidth(180);
        btnConfirmar.setPrefHeight(35);
        
        btnConfirmar.setLayoutX(185); 
        btnConfirmar.setLayoutY(515);

        btnConfirmar.setOnMouseEntered(e -> btnConfirmar.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-color: #2ecc71; -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;"));
        btnConfirmar.setOnMouseExited(e -> btnConfirmar.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-color: #3498db; -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;"));

        // Acción al confirmar
        btnConfirmar.setOnAction(e -> {
            if (equipo.getListaPokemon().size() == 6) {
                // 1. Cierra la ventana actual de seleccion
                stage.close();
                
                // 2. Abre la ventana de gameplay pasándole tu equipo seleccionado
                System.out.println("Equipo listo y confirmado. ¡Iniciando batalla PvE!");
                VentanaGameplay batalla = new VentanaGameplay(this.equipo); 
                batalla.mostrar();
            } else {
                int faltantes = 6 - equipo.getListaPokemon().size();
                lblContador.setText("¡FALTAN " + faltantes + " POKEMON!");
                lblContador.setStyle("-fx-font-size: 16px; -fx-text-fill: #e74c3c; " + estiloTexto);
                
                new javafx.animation.Timeline(new javafx.animation.KeyFrame(
                    javafx.util.Duration.seconds(1.5), 
                    ev -> {
                        lblContador.setText("Seleccionados: " + equipo.getListaPokemon().size() + " / 6");
                        lblContador.setStyle("-fx-font-size: 16px; -fx-text-fill: #f1c40f; " + estiloTexto);
                    }
                )).play();
            }
        });
        // -------------------------------------------------------------

        // 6. BOTON INVISIBLE SOBRE EL TRIANGULO NEGRO
        Button btnRegresar = new Button();
        btnRegresar.setPrefWidth(50);
        btnRegresar.setPrefHeight(30);
        btnRegresar.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        
        btnRegresar.setLayoutX(250);
        btnRegresar.setLayoutY(575);
        btnRegresar.setOnAction(e -> stage.close());

        // 7. ENSAMBLAR TODO
        root.getChildren().addAll(imgFondo, lblTitulo, lblContador, cuadricula, btnConfirmar, btnRegresar);

        Scene scene = new Scene(root, 550, 650);
        stage.setScene(scene);
        stage.show();
    }
}