package view;

import battle.gestorPartida;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Equipo;
import model.Jugador;
import model.JugadorHumano;
import model.Pokedex;
import model.Pokemon;

public class VentanaEquipo {

    private Equipo equipo;
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

        AnchorPane root = new AnchorPane();

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

        Label lblTitulo = new Label("SELECCIONA TU EQUIPO");
        String estiloTexto = "-fx-font-weight: bold; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 5, 0, 0, 0);";
        lblTitulo.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 22px; " + estiloTexto);
        lblTitulo.setLayoutX(110);
        lblTitulo.setLayoutY(25);

        Label lblContador = new Label("Seleccionados: " + equipo.getListaPokemon().size() + " / 6");
        lblContador.setStyle("-fx-font-size: 16px; -fx-text-fill: #f1c40f; " + estiloTexto);
        lblContador.setLayoutX(210);
        lblContador.setLayoutY(65);

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
            } catch (Exception e) {}

            Label lblNombre = new Label(p.getNombre());
            lblNombre.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: black;");

            tarjeta.getChildren().addAll(imgPoke, lblNombre);

            if (equipo.getListaPokemon().contains(p)) {
                tarjeta.setStyle("-fx-background-color: rgba(46, 204, 113, 0.4); -fx-background-radius: 10; -fx-border-color: #2ecc71; -fx-border-width: 2; -fx-border-radius: 10; -fx-cursor: hand;");
            }

            tarjeta.setOnMouseClicked(e -> {
                if (equipo.isBloqueado()) {
                    lblTitulo.setText("EQUIPO BLOQUEADO");
                    lblTitulo.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #e74c3c; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 5, 0, 0, 0);");
                    new javafx.animation.Timeline(new javafx.animation.KeyFrame(
                        javafx.util.Duration.seconds(1.5), 
                        ev -> {
                            lblTitulo.setText("SELECCIONA TU EQUIPO");
                            lblTitulo.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 22px; " + estiloTexto);
                        }
                    )).play();
                    return; 
                }

                if (equipo.getListaPokemon().contains(p)) {
                    equipo.getListaPokemon().remove(p);
                    tarjeta.setStyle("-fx-background-color: rgba(255, 255, 255, 0.2); -fx-background-radius: 10; -fx-cursor: hand;");
                } else {
                    if (equipo.getListaPokemon().size() < 6) {
                        equipo.getListaPokemon().add(p);
                        tarjeta.setStyle("-fx-background-color: rgba(46, 204, 113, 0.4); -fx-background-radius: 10; -fx-border-color: #2ecc71; -fx-border-width: 2; -fx-border-radius: 10; -fx-cursor: hand;");
                    } else {
                        lblTitulo.setText("EQUIPO LLENO ");
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

        Button btnConfirmar = new Button("Confirmar");
        btnConfirmar.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-color: #3498db; -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;");
        btnConfirmar.setPrefWidth(180);
        btnConfirmar.setPrefHeight(35);
        btnConfirmar.setLayoutX(185); 
        btnConfirmar.setLayoutY(515);

        btnConfirmar.setOnMouseEntered(e -> {
            if (!equipo.isBloqueado()) {
                btnConfirmar.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-color: #2ecc71; -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;");
            }
        });
        btnConfirmar.setOnMouseExited(e -> {
            if (!equipo.isBloqueado()) {
                btnConfirmar.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-color: #3498db; -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;");
            }
        });

        // === ACCIÓN ACTUALIZADA CON MENÚ FLOTANTE (OVERLAY) ===
        btnConfirmar.setOnAction(e -> {
            if (equipo.getListaPokemon().size() == 6 || equipo.isBloqueado()) {
                equipo.setBloqueado(true);
                
                // 1. Creamos la capa oscura que cubre toda la ventana
                StackPane overlay = new StackPane();
                overlay.setPrefSize(550, 650);
                overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.75);"); // Fondo negro al 75%

                // 2. Creamos la cajita del menú
                VBox menuFlotante = new VBox(15);
                menuFlotante.setAlignment(Pos.CENTER);
                menuFlotante.setMaxSize(300, 260);
                menuFlotante.setStyle("-fx-background-color: #2c3e50; -fx-background-radius: 15; -fx-border-color: #f1c40f; -fx-border-width: 3; -fx-border-radius: 15; -fx-padding: 20;");

                Label lblPregunta = new Label("Equipo Confirmado!\n Elije un modo de juego");
                lblPregunta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white; -fx-text-alignment: center;");

                // 3. Los tres botones con diseño de tu juego
                String estiloBtnOverlay = "-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: white; -fx-background-radius: 10; -fx-cursor: hand; -fx-pref-width: 200; -fx-pref-height: 35;";
                
                Button btnPvE = new Button("Aventura PvE");
                btnPvE.setStyle(estiloBtnOverlay + "-fx-background-color: #27ae60;");
                
                Button btnPvP = new Button("Batalla PvP");
                btnPvP.setStyle(estiloBtnOverlay + "-fx-background-color: #e74c3c;");
                
                Button btnMenu = new Button("Volver al Menu");
                btnMenu.setStyle(estiloBtnOverlay + "-fx-background-color: #7f8c8d;");

                // 4. Acciones de los nuevos botones
                btnPvE.setOnAction(ev -> {
                    stage.close();
                    new VentanaGameplay(this.equipo).mostrar();
                });

                btnPvP.setOnAction(ev -> {
                    stage.close();
                    iniciarPvPDirecto();
                });

                btnMenu.setOnAction(ev -> {
                    stage.close(); // Al cerrar esta ventana, la principal (que ya estaba atrás) se queda activa
                });

                // 5. Ensamblamos el menú flotante y lo mostramos
                menuFlotante.getChildren().addAll(lblPregunta, btnPvE, btnPvP, btnMenu);
                overlay.getChildren().add(menuFlotante);
                root.getChildren().add(overlay);

            } else {
                int faltantes = 6 - equipo.getListaPokemon().size();
                lblContador.setText("FALTAN " + faltantes + " POKEMON");
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

        Button btnRegresar = new Button();
        btnRegresar.setPrefWidth(50);
        btnRegresar.setPrefHeight(30);
        btnRegresar.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        btnRegresar.setLayoutX(250);
        btnRegresar.setLayoutY(575);
        btnRegresar.setOnAction(e -> stage.close());

        root.getChildren().addAll(imgFondo, lblTitulo, lblContador, cuadricula, btnConfirmar, btnRegresar);

        Scene scene = new Scene(root, 550, 650);
        stage.setScene(scene);
        stage.show();
    }

    // === MÉTODO PARA LA RETA RÁPIDA PVP ===
    private void iniciarPvPDirecto() {
        Jugador j1 = null;
        Jugador j2 = null;
        
        try {
            j1 = gestorPartida.cargarProgreso("partidaJ1.dat");
            j2 = gestorPartida.cargarProgreso("partidaJ2.dat");
        } catch (Exception e) {}

        if (j1 != null && j2 != null) {
            new VentanaPVP(j1, j2).mostrar();
        } else {
            j1 = new JugadorHumano("Joss");
            j1.getEquipo().addAll(this.equipo.getListaPokemon());
            
            j2 = new JugadorHumano("Rival");
            Pokemon pokeRival = Pokedex.buscarPorNombre("Charizard");
            if (pokeRival == null) pokeRival = Pokedex.buscarPorNombre("Pikachu");
            if (pokeRival != null) j2.getEquipo().add(pokeRival);
            
            new VentanaPVP(j1, j2).mostrar();
        }
    }
}