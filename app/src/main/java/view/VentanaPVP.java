package view;

import battle.BatallaPVP;
import battle.gestorPartida; 
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
import model.Jugador;
import model.Pokemon;

public class VentanaPVP {

    private Jugador j1;
    private Jugador j2;
    private Pokemon pokemonJ1;
    private Pokemon pokemonJ2;
    private BatallaPVP logicaPVP;

    private int indiceJ1 = 0;
    private int indiceJ2 = 0;

    private AnchorPane root;
    private ImageView imgPokemonJ1;
    private ImageView imgPokemonJ2;
    private Label lblInfo;
    private ProgressBar barraVidaJ1;
    private ProgressBar barraVidaJ2; 
    private Label lblVidaJ1;
    private Label lblVidaJ2;

    private Button btnAtaque1;
    private Button btnAtaque2;
    private Button btnGuardar; 

    private double vidaJ1 = 1.0;
    private double vidaJ2 = 1.0; 

    private int faseTurno = 1; 
    private String movimientoSeleccionadoJ1 = "";
    private String movimientoSeleccionadoJ2 = "";

    private final double ANCHO = 500;
    private final double ALTO = 700;

    public VentanaPVP(Jugador j1, Jugador j2) {
        this.j1 = j1;
        this.j2 = j2;
        
        if (j1 != null && j1.getEquipo() != null && !j1.getEquipo().isEmpty()) {
            this.pokemonJ1 = j1.getEquipo().get(0);
        }
        if (j2 != null && j2.getEquipo() != null && !j2.getEquipo().isEmpty()) {
            this.pokemonJ2 = j2.getEquipo().get(0);
        }

        this.logicaPVP = new BatallaPVP(j1, j2);
        this.imgPokemonJ1 = new ImageView();
        this.imgPokemonJ2 = new ImageView(); 
    }

    public void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Arena PvP - Combate Local");
        stage.setResizable(false);
        root = new AnchorPane();

        ImageView imgFondo = new ImageView();
        try {
            imgFondo.setImage(new Image(getClass().getResourceAsStream("/pokemon/FC.jpeg")));
        } catch (Exception e) {}
        imgFondo.setFitWidth(ANCHO); 
        imgFondo.setFitHeight(ALTO);
        
        lblInfo = new Label();
        String estiloLetras = "-fx-font-weight: bold; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 5, 0, 0, 0);";
        lblInfo.setStyle("-fx-font-size: 18px; " + estiloLetras);
        lblInfo.setPrefWidth(460);
        lblInfo.setAlignment(javafx.geometry.Pos.CENTER);
        lblInfo.setLayoutX(20);
        lblInfo.setLayoutY(25);

        // UI J2
        lblVidaJ2 = new Label();
        lblVidaJ2.setStyle("-fx-font-size: 14px; " + estiloLetras);
        lblVidaJ2.setLayoutX(270);
        lblVidaJ2.setLayoutY(80);

        barraVidaJ2 = new ProgressBar(1.0);
        barraVidaJ2.setPrefWidth(180);
        barraVidaJ2.setStyle("-fx-accent: #2ecc71;");
        barraVidaJ2.setLayoutX(270);
        barraVidaJ2.setLayoutY(105);

        imgPokemonJ2.setFitWidth(150);
        imgPokemonJ2.setFitHeight(150);
        imgPokemonJ2.setPreserveRatio(false); 
        imgPokemonJ2.setLayoutX(280);
        imgPokemonJ2.setLayoutY(140);

        // UI J1
        lblVidaJ1 = new Label();
        lblVidaJ1.setStyle("-fx-font-size: 14px; " + estiloLetras);
        lblVidaJ1.setLayoutX(50);
        lblVidaJ1.setLayoutY(330);

        barraVidaJ1 = new ProgressBar(1.0);
        barraVidaJ1.setPrefWidth(180);
        barraVidaJ1.setStyle("-fx-accent: #2ecc71;");
        barraVidaJ1.setLayoutX(50);
        barraVidaJ1.setLayoutY(355);

        imgPokemonJ1.setFitWidth(150);
        imgPokemonJ1.setFitHeight(150);
        imgPokemonJ1.setPreserveRatio(false); 
        imgPokemonJ1.setLayoutX(60);  
        imgPokemonJ1.setLayoutY(410); 

        actualizarDatosPantalla();

        // === BOTONERA INFERIOR COMPLETA Y ALINEADA EN EL MISMO RENGLÓN Y ALTURA ===
        String estiloAtaques = "-fx-font-weight: bold; -fx-font-size: 13px; -fx-text-fill: white; -fx-background-radius: 15; -fx-cursor: hand;";
        
        btnAtaque1 = new Button();
        btnAtaque1.setStyle(estiloAtaques + " -fx-background-color: #e74c3c;");
        btnAtaque1.setPrefWidth(100);
        btnAtaque1.setLayoutX(20); 
        btnAtaque1.setLayoutY(610); 

        btnAtaque2 = new Button();
        btnAtaque2.setStyle(estiloAtaques + " -fx-background-color: #3498db;");
        btnAtaque2.setPrefWidth(100);
        btnAtaque2.setLayoutX(135);
        btnAtaque2.setLayoutY(610);

        btnGuardar = new Button("Guardar");
        btnGuardar.setStyle(estiloAtaques + " -fx-background-color: #2ecc71;");
        btnGuardar.setPrefWidth(100);
        btnGuardar.setLayoutX(250);
        btnGuardar.setLayoutY(610);

        Button btnHuir = new Button("Huir");
        btnHuir.setStyle(estiloAtaques + " -fx-background-color: #7f8c8d;");
        btnHuir.setPrefWidth(100);
        btnHuir.setLayoutX(365); 
        btnHuir.setLayoutY(610); 

        // Acción de Huir: Guarda de respaldo automáticamente antes de cerrar
        btnHuir.setOnAction(e -> {
            gestorPartida.guardarProgreso(j1, "partidaJ1.dat");
            gestorPartida.guardarProgreso(j2, "partidaJ2.dat");
            stage.close(); 
        });

        // Acción de Guardar: Guarda a mitad de turno con feedback estético
        btnGuardar.setOnAction(e -> {
            desactivarControles();
            btnGuardar.setDisable(true);

            gestorPartida.guardarProgreso(j1, "partidaJ1.dat");
            gestorPartida.guardarProgreso(j2, "partidaJ2.dat");

            String textoOriginal = lblInfo.getText();
            lblInfo.setText("💾 Progreso de batalla guardado con exito!");

            new Timeline(new KeyFrame(Duration.seconds(1.2), ev -> {
                lblInfo.setText(textoOriginal);
                activarControles();
                btnGuardar.setDisable(false);
            })).play();
        });

        configurarTurnoVisual();

        btnAtaque1.setOnAction(e -> procesarSeleccionMovimiento(btnAtaque1.getText()));
        btnAtaque2.setOnAction(e -> procesarSeleccionMovimiento(btnAtaque2.getText()));

        root.getChildren().addAll(
            imgFondo, lblInfo, 
            lblVidaJ2, barraVidaJ2, imgPokemonJ2, 
            lblVidaJ1, barraVidaJ1, imgPokemonJ1, 
            btnAtaque1, btnAtaque2, btnGuardar, btnHuir
        );

        Scene scene = new Scene(root, ANCHO, ALTO);
        stage.setScene(scene);
        stage.show();
    }private void actualizarBotonesMovimientos(Pokemon pokemonActual) {
        // Por seguridad, apagamos los botones primero
        btnAtaque1.setDisable(true);
        btnAtaque2.setDisable(true);
        btnAtaque1.setText("-");
        btnAtaque2.setText("-");

        // Leemos tu ArrayList de movimientos real
        if (pokemonActual != null && pokemonActual.getMovimientos() != null) {
            int totalMovimientos = pokemonActual.getMovimientos().size();
            
            if (totalMovimientos > 0) {
                // Suponiendo que tu clase Movimiento tiene un metodo getNombre()
                btnAtaque1.setText(pokemonActual.getMovimientos().get(0).getNombre());
                btnAtaque1.setDisable(false);
            }
            if (totalMovimientos > 1) {
                btnAtaque2.setText(pokemonActual.getMovimientos().get(1).getNombre());
                btnAtaque2.setDisable(false);
            }
        }
    }

    private void configurarTurnoVisual() {
      if (faseTurno == 1) {
            lblInfo.setText("Turno de " + j1.getNombre() + ": Elige movimiento");
            actualizarBotonesMovimientos(pokemonJ1);
        } else if (faseTurno == 2) {
            lblInfo.setText("Turno de " + j2.getNombre() + ": Elige movimiento");
            actualizarBotonesMovimientos(pokemonJ2);
        }
    }

    private void procesarSeleccionMovimiento(String mov) {
        if (faseTurno == 1) {
            movimientoSeleccionadoJ1 = mov;
            faseTurno = 2;
            configurarTurnoVisual();
        } else if (faseTurno == 2) {
            movimientoSeleccionadoJ2 = mov;
            faseTurno = 3;
            desactivarControles();
            btnGuardar.setDisable(true);
            lblInfo.setText("Resolviendo combate!");
            
            logicaPVP.procesarRonda(movimientoSeleccionadoJ1, movimientoSeleccionadoJ2);
            ejecutarFaseResolucionGrafica();
        }
    }

    // === AQUÍ SE CORRIGIÓ EL BUG: Se rellenó el método para actualizar las barras ===
    private void ejecutarFaseResolucionGrafica() {
        AnimadorPokemon.animarAtaque(imgPokemonJ1, true);
        AnimadorPokemon.animarAtaque(imgPokemonJ2, false);
        AnimadorPokemon.animarRecibirDanio(imgPokemonJ1);
        AnimadorPokemon.animarRecibirDanio(imgPokemonJ2);

        new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
            
            // 1. Sincronizamos a los Pokémon con el daño real que les hizo la lógica
            if (j1 != null && !j1.getEquipo().isEmpty()) {
                this.pokemonJ1 = j1.getEquipo().get(indiceJ1);
            }
            if (j2 != null && !j2.getEquipo().isEmpty()) {
                this.pokemonJ2 = j2.getEquipo().get(indiceJ2);
            }

            // 2. Calculamos la nueva vida matemática y se la asignamos a la barra visual
            if (pokemonJ1 != null && pokemonJ2 != null) {
                vidaJ1 = (double) pokemonJ1.getVida() / pokemonJ1.getVidaMaxima();
                vidaJ2 = (double) pokemonJ2.getVida() / pokemonJ2.getVidaMaxima();
            }

            // 3. Evitamos que la barra colapse con números negativos
            if (vidaJ1 < 0) vidaJ1 = 0;
            if (vidaJ2 < 0) vidaJ2 = 0;

            barraVidaJ1.setProgress(vidaJ1);
            barraVidaJ2.setProgress(vidaJ2);

            // 4. Cambiamos las barras a color rojo si la vida baja del 30%
            if (vidaJ1 <= 0.3) barraVidaJ1.setStyle("-fx-accent: #e74c3c;");
            if (vidaJ2 <= 0.3) barraVidaJ2.setStyle("-fx-accent: #e74c3c;");

            // 5. Validamos si alguien murió y cambiamos el turno
            evaluarEstatusPostCombate();
        })).play();
    }

    private void evaluarEstatusPostCombate() {
        if (pokemonJ1 != null && pokemonJ1.estaDebilitado()) {
            lblInfo.setText( pokemonJ1.getNombre() + " de " + j1.getNombre() + " cayo!");
            AnimadorPokemon.animarDebilitado(imgPokemonJ1);
            procesarRelevoJugador1();
        } else if (pokemonJ2 != null && pokemonJ2.estaDebilitado()) {
            lblInfo.setText( pokemonJ2.getNombre() + " de " + j2.getNombre() + " cayo!");
            AnimadorPokemon.animarDebilitado(imgPokemonJ2);
            procesarRelevoJugador2();
        } else {
            faseTurno = 1;
            activarControles();
            btnGuardar.setDisable(false);
            configurarTurnoVisual();
        }
    }

    private void procesarRelevoJugador1() {
        new Timeline(new javafx.animation.KeyFrame(Duration.seconds(1.2), e -> {
            if (j1 != null && j1.getEquipo() != null && (indiceJ1 + 1) < j1.getEquipo().size()) {
                indiceJ1++;
                pokemonJ1 = j1.getEquipo().get(indiceJ1); 
                
                root.getChildren().remove(imgPokemonJ1);
                imgPokemonJ1 = new ImageView();
                imgPokemonJ1.setFitWidth(150); imgPokemonJ1.setFitHeight(150);
                imgPokemonJ1.setLayoutX(60); imgPokemonJ1.setLayoutY(410);
                root.getChildren().add(imgPokemonJ1);

                vidaJ1 = (double) pokemonJ1.getVida() / pokemonJ1.getVidaMaxima();
                barraVidaJ1.setProgress(vidaJ1);
                barraVidaJ1.setStyle("-fx-accent: #2ecc71;");
                actualizarDatosPantalla();
                
                faseTurno = 1;
                activarControles();
                btnGuardar.setDisable(false);
                configurarTurnoVisual();
            } else {
                lblInfo.setText(j1.getNombre() + " no tiene mas Pokémon! Ganador: " + j2.getNombre());
            }
        })).play();
    }

    private void procesarRelevoJugador2() {
        new Timeline(new javafx.animation.KeyFrame(Duration.seconds(1.2), e -> {
            if (j2 != null && j2.getEquipo() != null && (indiceJ2 + 1) < j2.getEquipo().size()) {
                indiceJ2++;
                pokemonJ2 = j2.getEquipo().get(indiceJ2); 
                
                root.getChildren().remove(imgPokemonJ2);
                imgPokemonJ2 = new ImageView();
                imgPokemonJ2.setFitWidth(150); imgPokemonJ2.setFitHeight(150);
                imgPokemonJ2.setLayoutX(280); imgPokemonJ2.setLayoutY(140);
                root.getChildren().add(imgPokemonJ2);

                vidaJ2 = (double) pokemonJ2.getVida() / pokemonJ2.getVidaMaxima();
                barraVidaJ2.setProgress(vidaJ2);
                barraVidaJ2.setStyle("-fx-accent: #2ecc71;");
                actualizarDatosPantalla();
                
                faseTurno = 1;
                activarControles();
                btnGuardar.setDisable(false);
                configurarTurnoVisual();
            } else {
                lblInfo.setText( j2.getNombre() + " no tiene mas Pokémon el ganador es " + j1.getNombre());
            }
        })).play();
    }

    private void actualizarDatosPantalla() {
        try {
            if (pokemonJ1 != null) {
                lblVidaJ1.setText("Vida de " + pokemonJ1.getNombre() + " (J1)");
                imgPokemonJ1.setImage(new Image(getClass().getResourceAsStream(pokemonJ1.getImagen())));
            }
            if (pokemonJ2 != null) {
                lblVidaJ2.setText("Vida de " + pokemonJ2.getNombre() + " (J2)");
                imgPokemonJ2.setImage(new Image(getClass().getResourceAsStream(pokemonJ2.getImagen())));
            }
        } catch (Exception e) {}
    }

    private void desactivarControles() {
        btnAtaque1.setDisable(true); btnAtaque2.setDisable(true);
    }

    private void activarControles() {
        btnAtaque1.setDisable(false); btnAtaque2.setDisable(false);
    }
}