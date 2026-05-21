package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Equipo;
import model.Pokemon;

public class VentanaGameplay {

    private Equipo equipo; // Guardará los 6 Pokémon elegidos
    private Pokemon miPokemonActual; // El Pokémon que sale a pelear primero
    private int indiceActual = 0; // Controla qué número de compañero está peleando (0 al 5)

    // Variables de prueba para la vida (Empiezan al 100%)
    private double vidaJ1 = 1.0;
    private double vidaCPU = 1.0;

    public VentanaGameplay(Equipo equipo) {
        this.equipo = equipo;
        if (equipo != null && !equipo.getListaPokemon().isEmpty()) {
            this.miPokemonActual = equipo.getListaPokemon().get(0);
        }
    }

    public void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Gameplay-Batalla");
        stage.setResizable(false);

        AnchorPane root = new AnchorPane();

        // Fondo
        ImageView imgFondo = new ImageView();
        try {
            imgFondo.setImage(new Image(getClass().getResourceAsStream("/pokemon/FC.jpeg")));
        } catch (Exception e) {
            System.out.println("No se encontro la imagen de fondo para el gameplay.");
        }
        imgFondo.setFitWidth(500); 
        imgFondo.setFitHeight(700);
        AnchorPane.setTopAnchor(imgFondo, 0.0);
        AnchorPane.setLeftAnchor(imgFondo, 0.0);

        // Texto de información
        Label lblInfo = new Label();
        String estiloLetras = "-fx-font-weight: bold; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 5, 0, 0, 0);";
        lblInfo.setStyle("-fx-font-size: 18px; " + estiloLetras);
        lblInfo.setPrefWidth(460);
        lblInfo.setAlignment(javafx.geometry.Pos.CENTER);
        lblInfo.setLayoutX(20);
        lblInfo.setLayoutY(25);

        if (miPokemonActual != null) {
            lblInfo.setText("¿Que deberia hacer " + miPokemonActual.getNombre() + "?");
        } else {
            lblInfo.setText("Aqui se mostrara la batalla");
        }

        // RIVAL
        Label lblVidaRival = new Label("Vida Rival:");
        lblVidaRival.setStyle("-fx-font-size: 14px; " + estiloLetras);
        lblVidaRival.setLayoutX(270);
        lblVidaRival.setLayoutY(80);

        ProgressBar barraVidaCPU = new ProgressBar(vidaCPU);
        barraVidaCPU.setPrefWidth(180);
        barraVidaCPU.setStyle("-fx-accent: #2ecc71;");
        barraVidaCPU.setLayoutX(270);
        barraVidaCPU.setLayoutY(105);

        ImageView imgPokemonCPU = new ImageView();
        imgPokemonCPU.setFitWidth(150);
        imgPokemonCPU.setFitHeight(150);
        imgPokemonCPU.setPreserveRatio(false); // Evita que el rival baile por proporciones
        imgPokemonCPU.setLayoutX(280);
        imgPokemonCPU.setLayoutY(140);

        // JUGADOR 1 (Coordenadas blindadas y corregidas)
        Label lblTuVida = new Label();
        if (miPokemonActual != null) {
            lblTuVida.setText("Vida de " + miPokemonActual.getNombre() + ":");
        } else {
            lblTuVida.setText("Tu Vida:");
        }
        lblTuVida.setStyle("-fx-font-size: 14px; " + estiloLetras);
        lblTuVida.setLayoutX(50);
        lblTuVida.setLayoutY(330);

        ProgressBar barraVidaJ1 = new ProgressBar(vidaJ1);
        barraVidaJ1.setPrefWidth(180);
        barraVidaJ1.setStyle("-fx-accent: #2ecc71;");
        barraVidaJ1.setLayoutX(50);
        barraVidaJ1.setLayoutY(355);

        // El contenedor del Pokémon se crea UNA SOLA VEZ con su tamaño y posición fija
        ImageView imgPokemonJ1 = new ImageView();
        imgPokemonJ1.setFitWidth(150);
        imgPokemonJ1.setFitHeight(150);
        
        // --- SOLUCIÓN: Forzamos a que llene el espacio asignado sin moverse por diferencias de ratio ---
        imgPokemonJ1.setPreserveRatio(false); 
        imgPokemonJ1.setLayoutX(60);  // Posición X fija
        imgPokemonJ1.setLayoutY(410); // Ajustado a 410 para dejar espacio libre debajo de la barra de vida

        try {
            if (miPokemonActual != null) {
                imgPokemonJ1.setImage(new Image(getClass().getResourceAsStream(miPokemonActual.getImagen())));
            } else {
                imgPokemonJ1.setImage(new Image(getClass().getResourceAsStream("/pokemon/pikachu.png")));
            }
            imgPokemonCPU.setImage(new Image(getClass().getResourceAsStream("/pokemon/charizard.png")));
        } catch (Exception e) {
            System.out.println("No se encontraron las imagenes iniciales de prueba.");
        }

        // Botones
        Button btnAtacar = new Button("Atacar");
        btnAtacar.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-background-color: #e74c3c; -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;");
        btnAtacar.setPrefWidth(140);
        btnAtacar.setLayoutX(90);
        btnAtacar.setLayoutY(590);

        Button btnRegresar = new Button("Huir");
        btnRegresar.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-background-color: #7f8c8d; -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;");
        btnRegresar.setPrefWidth(140);
        btnRegresar.setLayoutX(270);
        btnRegresar.setLayoutY(590);
        btnRegresar.setOnAction(e -> stage.close());

        // 8. LOGICA DE TURNOS ACTUALIZADA
        btnAtacar.setOnAction(event -> {
            btnAtacar.setDisable(true);
            
            String nombreMio = (miPokemonActual != null) ? miPokemonActual.getNombre() : "Pikachu";
            lblInfo.setText("¡" + nombreMio + " usa un ataque fulminante!");

            AnimadorPokemon.animarAtaque(imgPokemonJ1, true);
            AnimadorPokemon.animarRecibirDanio(imgPokemonCPU);

            if (vidaCPU > 0) {
                vidaCPU -= 0.2;
                if (vidaCPU < 0) vidaCPU = 0;
                barraVidaCPU.setProgress(vidaCPU);
                if (vidaCPU <= 0.3) {
                    barraVidaCPU.setStyle("-fx-accent: #e74c3c;");
                }
            }

            // RESETEO TRAS ATAQUE: Mantiene la misma caja estática
            KeyFrame resetearImagen = new KeyFrame(
                Duration.millis(300), 
                e -> {
                    try {
                        if (miPokemonActual != null && vidaJ1 > 0) {
                            imgPokemonJ1.setImage(new Image(getClass().getResourceAsStream(miPokemonActual.getImagen())));
                        }
                    } catch (Exception ex) {
                        System.out.println("No se pudo regresar a la imagen normal.");
                    }
                }
            );
            new Timeline(resetearImagen).play();

            // === CASO 1: ¡GANASTE LA BATALLA! ===
            if (vidaCPU <= 0) {
                AnimadorPokemon.animarDebilitado(imgPokemonCPU);
                
                if (miPokemonActual != null) {
                    String nombreViejo = miPokemonActual.getNombre();
                    
                    Pokemon rivalDerrotado = model.Pokedex.buscarPorNombre("Charizard");
                    int expCalculada = battle.SistemaDeExp.calcularExperienciaGanada(rivalDerrotado);
                    
                    String reporteExp = battle.SistemaDeExp.ganarExperiencia(miPokemonActual, expCalculada);
                    System.out.print(reporteExp); 
                    
                    if (!miPokemonActual.getNombre().equalsIgnoreCase(nombreViejo)) {
                        lblInfo.setText("¡Felicidades! ¡Tu " + nombreViejo + " evoluciono en " + miPokemonActual.getNombre() + "!");
                        try {
                            imgPokemonJ1.setImage(new Image(getClass().getResourceAsStream(miPokemonActual.getImagen())));
                            lblTuVida.setText("Vida de " + miPokemonActual.getNombre() + ":");
                        } catch (Exception ex) {
                            System.out.println("Error al cargar foto de la evolucion.");
                        }
                    } else {
                        lblInfo.setText("¡Ganaste! " + miPokemonActual.getNombre() + " subio de nivel/experiencia.");
                    }
                } else {
                    lblInfo.setText("El Charizard enemigo se ha debilitado. ¡Ganaste!");
                }
                return; 
            }

            // VENGANZA DEL CPU
            KeyFrame ataqueEnemigo = new KeyFrame(
                Duration.seconds(1.0), 
                e -> {
                    lblInfo.setText("¡Charizard enemigo usa Lanzallamas!");

                    AnimadorPokemon.animarAtaque(imgPokemonCPU, false);
                    AnimadorPokemon.animarRecibirDanio(imgPokemonJ1);

                    if (vidaJ1 > 0) {
                        vidaJ1 -= 0.5;
                        if (vidaJ1 < 0) vidaJ1 = 0;
                        barraVidaJ1.setProgress(vidaJ1);
                        if (vidaJ1 <= 0.3) {
                            barraVidaJ1.setStyle("-fx-accent: #e74c3c;");
                        }
                    }

                    KeyFrame reactivarTurno = new KeyFrame(
                        Duration.millis(500),
                        ev -> {
                            // === CASO 2: SE DEBILITA TU POKÉMON (SISTEMA DE RELEVO OFICIAL) ===
                            if (vidaJ1 <= 0) {
                                AnimadorPokemon.animarDebilitado(imgPokemonJ1);
                                String nombreDebilitado = miPokemonActual.getNombre();
                                
                                // ¿Quedan más compañeros en la lista de 6?
                                if (equipo != null && indiceActual + 1 < equipo.getListaPokemon().size()) {
                                    indiceActual++; 
                                    miPokemonActual = equipo.getListaPokemon().get(indiceActual);
                                    
                                    // Reseteamos la barra al 100% en verde
                                    vidaJ1 = 1.0;
                                    barraVidaJ1.setProgress(vidaJ1);
                                    barraVidaJ1.setStyle("-fx-accent: #2ecc71;");
                                    
                                    // REAPARICIÓN PERFECTA: Conserva dimensiones sin distorsionar coordenadas
                                    lblTuVida.setText("Vida de " + miPokemonActual.getNombre() + ":");
                                    try {
                                        imgPokemonJ1.setImage(new Image(getClass().getResourceAsStream(miPokemonActual.getImagen())));
                                    } catch (Exception ex) {
                                        System.out.println("Error al cargar la imagen del relevo.");
                                    }
                                    
                                    lblInfo.setText("¡" + nombreDebilitado + " cayo! ¡Entra " + miPokemonActual.getNombre() + "!");
                                    btnAtacar.setDisable(false); 
                                    
                                } else {
                                    lblInfo.setText("Todo tu equipo se ha debilitado. Fin de la partida.");
                                }
                            } else {
                                lblInfo.setText("¿Que deberia hacer " + miPokemonActual.getNombre() + "?");
                                btnAtacar.setDisable(false);
                            }
                        }
                    );
                    new Timeline(reactivarTurno).play();
                }
            );
            
            Timeline temporizadorCPU = new Timeline(ataqueEnemigo);
            temporizadorCPU.play();
        });

        // 9. AGREGAR TODO AL TABLERO EN ORDEN DE CAPAS
        root.getChildren().addAll(
            imgFondo, lblInfo,
            lblVidaRival, barraVidaCPU, imgPokemonCPU,
            lblTuVida, barraVidaJ1, imgPokemonJ1,
            btnAtacar, btnRegresar
        );

        Scene scene = new Scene(root, 500, 700);
        stage.setScene(scene);
        stage.show();
    }
}