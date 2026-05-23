package view;

import java.util.Random;

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
import model.InventarioCombate;
import model.Pokemon;

public class VentanaGameplay {
    

    private Equipo equipo; 
    private Pokemon miPokemonActual; 
    private Pokemon pokemonCPU; 
    private int indiceActual = 0; 

    // Contenedores globales de los sprites
    private ImageView imgPokemonCPU; 
    private ImageView imgPokemonJ1;
    private AnchorPane root; // Volvemos el panel global para poder quitar y poner componentes

    // Instancia de la mochila del jugador para esta batalla
    private InventarioCombate mochila = new InventarioCombate(); 

    // Variables de vida porcentual para las barras visuales (0.0 a 1.0)
    private ProgressBar barraVidaJ1;

    private double vidaJ1 = 1.0;
    private double vidaCPU = 1.0;

    private int rivalesDerrotados = 0; 
    private final int MAX_RIVALES = 2; 

    private final String[] jefesRival = {
        "Venusaur", "Sceptile", "Serperior", "Decidueye", "Leafeon",
        "Charizard", "Typhlosion", "Blaziken", "Incineroar", "Flareon",
        "Blastoise", "Feraligatr", "Empoleon", "Samurott", "Vaporeon",
        "Pikachu", "Luxray", "Tyranitar", "Lycanroc"
    };

    public VentanaGameplay(Equipo equipo) {
        this.equipo = equipo;
        if (equipo != null && !equipo.getListaPokemon().isEmpty()) {
            this.miPokemonActual = equipo.getListaPokemon().get(0);
        }
        
        // Inicializamos los contenedores por primera vez
        this.imgPokemonCPU = new ImageView();
        this.imgPokemonJ1 = new ImageView();
        
        aparecerSiguienteRival();
    }

    private void aparecerSiguienteRival() {
        Random rand = new Random();
        String nombreRivalAzar = jefesRival[rand.nextInt(jefesRival.length)];
        this.pokemonCPU = model.Pokedex.buscarPorNombre(nombreRivalAzar);
        if (this.pokemonCPU == null) {
            this.pokemonCPU = model.Pokedex.buscarPorNombre("Charizard");
        }
        this.vidaCPU = 1.0; 
    }

    public void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Gameplay - Batalla con Objetos");
        stage.setResizable(false);

        root = new AnchorPane();

        // Fondo
        ImageView imgFondo = new ImageView();
        try {
            imgFondo.setImage(new Image(getClass().getResourceAsStream("/pokemon/FC.jpeg")));
        } catch (Exception e) {
            System.out.println("No se encontro la imagen de fondo.");
        }
        imgFondo.setFitWidth(500); 
        imgFondo.setFitHeight(700);
        AnchorPane.setTopAnchor(imgFondo, 0.0);
        AnchorPane.setLeftAnchor(imgFondo, 0.0);

        // Consola de Texto
        Label lblInfo = new Label();
        String estiloLetras = "-fx-font-weight: bold; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 5, 0, 0, 0);";
        lblInfo.setStyle("-fx-font-size: 18px; " + estiloLetras);
        lblInfo.setPrefWidth(460);
        lblInfo.setAlignment(javafx.geometry.Pos.CENTER);
        lblInfo.setLayoutX(20);
        lblInfo.setLayoutY(25);

        if (miPokemonActual != null) {
            lblInfo.setText("Elije un movimiento" + miPokemonActual.getNombre() + "");
        }

        // RIVAL
        Label lblVidaRival = new Label("Vida de " + pokemonCPU.getNombre() + ":");
        lblVidaRival.setStyle("-fx-font-size: 14px; " + estiloLetras);
        lblVidaRival.setLayoutX(270);
        lblVidaRival.setLayoutY(80);

        ProgressBar barraVidaCPU = new ProgressBar(vidaCPU);
        barraVidaCPU.setPrefWidth(180);
        barraVidaCPU.setStyle("-fx-accent: #2ecc71;");
        barraVidaCPU.setLayoutX(270);
        barraVidaCPU.setLayoutY(105);

        imgPokemonCPU.setFitWidth(150);
        imgPokemonCPU.setFitHeight(150);
        imgPokemonCPU.setPreserveRatio(false); 
        imgPokemonCPU.setLayoutX(280);
        imgPokemonCPU.setLayoutY(140);

        // JUGADOR 1
        Label lblTuVida = new Label();
        if (miPokemonActual != null) {
            lblTuVida.setText("Vida de " + miPokemonActual.getNombre() + ":");
        }
        lblTuVida.setStyle("-fx-font-size: 14px; " + estiloLetras);
        lblTuVida.setLayoutX(50);
        lblTuVida.setLayoutY(330);

        barraVidaJ1 = new ProgressBar(vidaJ1); // <-- Debe quedar así
        barraVidaJ1.setPrefWidth(180);
        barraVidaJ1.setStyle("-fx-accent: #2ecc71;");
        barraVidaJ1.setLayoutX(50);
        barraVidaJ1.setLayoutY(355);

        imgPokemonJ1.setFitWidth(150);
        imgPokemonJ1.setFitHeight(150);
        imgPokemonJ1.setPreserveRatio(false); 
        imgPokemonJ1.setLayoutX(60);  
        imgPokemonJ1.setLayoutY(410); 

        try {
            if (miPokemonActual != null) {
                imgPokemonJ1.setImage(new Image(getClass().getResourceAsStream(miPokemonActual.getImagen())));
            }
            if (pokemonCPU != null) {
                imgPokemonCPU.setImage(new Image(getClass().getResourceAsStream(pokemonCPU.getImagen())));
            }
        } catch (Exception e) {
            System.out.println("No se encontraron las imagenes.");
        }

        // Botones de control principales
        Button btnAtacar = new Button("Atacar");
        btnAtacar.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-color: #e74c3c; -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;");
        btnAtacar.setPrefWidth(120);
        btnAtacar.setLayoutX(40);
        btnAtacar.setLayoutY(600);

        Button btnMochila = new Button("Mochila");
        btnMochila.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-color: #f39c12; -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;");
        btnMochila.setPrefWidth(120);
        btnMochila.setLayoutX(190);
        btnMochila.setLayoutY(600);

        Button btnRegresar = new Button("Huir");
        btnRegresar.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-color: #7f8c8d; -fx-text-fill: white; -fx-background-radius: 12; -fx-cursor: hand;");
        btnRegresar.setPrefWidth(120);
        btnRegresar.setLayoutX(340);
        btnRegresar.setLayoutY(600);
        btnRegresar.setOnAction(e -> stage.close());

        // Componentes Mochila
        Button btnUsaPocion = new Button("Pocion (" + mochila.obtenerCantidad("Pocion") + ")");
        btnUsaPocion.setStyle("-fx-font-weight: bold; -fx-font-size: 13px; -fx-background-color: #3498db; -fx-text-fill: white; -fx-background-radius: 10; -fx-cursor: hand;");
        btnUsaPocion.setPrefWidth(130);
        btnUsaPocion.setLayoutX(80);
        btnUsaPocion.setLayoutY(600);
        btnUsaPocion.setVisible(false);

        Button btnUsaSuperPocion = new Button("SuperPocion (" + mochila.obtenerCantidad("SuperPocion") + ")");
        btnUsaSuperPocion.setStyle("-fx-font-weight: bold; -fx-font-size: 13px; -fx-background-color: #9b59b6; -fx-text-fill: white; -fx-background-radius: 10; -fx-cursor: hand;");
        btnUsaSuperPocion.setPrefWidth(140);
        btnUsaSuperPocion.setLayoutX(220);
        btnUsaSuperPocion.setLayoutY(600);
        btnUsaSuperPocion.setVisible(false);

        Button btnAtrasMochila = new Button("X");
        btnAtrasMochila.setStyle("-fx-font-weight: bold; -fx-font-size: 13px; -fx-background-color: #d35400; -fx-text-fill: white; -fx-background-radius: 50; -fx-cursor: hand;");
        btnAtrasMochila.setPrefWidth(40);
        btnAtrasMochila.setLayoutX(380);
        btnAtrasMochila.setLayoutY(600);
        btnAtrasMochila.setVisible(false);

        // Menú Mochila acciones
        btnMochila.setOnAction(e -> {
            btnAtacar.setVisible(false); btnMochila.setVisible(false); btnRegresar.setVisible(false);
            btnUsaPocion.setVisible(true); btnUsaSuperPocion.setVisible(true); btnAtrasMochila.setVisible(true);
            lblInfo.setText("Selecciona un objeto para usar en tu Pokemon.");
        });

        btnAtrasMochila.setOnAction(e -> {
            btnUsaPocion.setVisible(false); btnUsaSuperPocion.setVisible(false); btnAtrasMochila.setVisible(false);
            btnAtacar.setVisible(true); btnMochila.setVisible(true); btnRegresar.setVisible(true);
            lblInfo.setText("Que deberia hacer " + miPokemonActual.getNombre() + "?");
        });

        // Turnos de Batalla
        btnAtacar.setOnAction(event -> {
    desactivarControles(btnAtacar, btnMochila, btnRegresar);
    
    // 1. Preparamos las rutas de forma segura
    String rutaNormal = miPokemonActual.getImagen();
    // Usamos substring para asegurar que solo cambiamos la extensión, evita el error de "png2.png"
    String rutaAtaque = rutaNormal.substring(0, rutaNormal.lastIndexOf(".")) + "2.png";

    // 2. Ejecutamos las animaciones UNA sola vez
    AnimadorPokemon.animarAtaque(imgPokemonJ1, true); 
    AnimadorPokemon.animarCambioImagen(imgPokemonJ1, rutaNormal, rutaAtaque);
    AnimadorPokemon.animarRecibirDanio(imgPokemonCPU);
    
    // 3. Actualizamos la información visual
    String nombreMio = (miPokemonActual != null) ? miPokemonActual.getNombre() : "Pokemon";
    lblInfo.setText(nombreMio + " usa un ataque fulminante!");

    // 4. Lógica de daño
    if (vidaCPU > 0) {
        vidaCPU -= 0.2;
        if (vidaCPU < 0) vidaCPU = 0;
        barraVidaCPU.setProgress(vidaCPU);
        if (vidaCPU <= 0.3) barraVidaCPU.setStyle("-fx-accent: #e74c3c;");
    }

    // 5. Decisión de qué sigue (Victoria o Contraataque)
    // Eliminamos el KeyFrame manual de reset aquí porque AnimadorPokemon.cambiarImagen ya lo hace por ti
    if (vidaCPU <= 0) {
        procesarVictoriaRival(lblInfo, lblVidaRival, barraVidaCPU, lblTuVida, btnAtacar, btnMochila, btnRegresar);
    } else {
        ejecutarContraataqueRival(lblInfo, lblTuVida, barraVidaJ1, btnAtacar, btnMochila, btnRegresar);
    }
});

        btnUsaPocion.setOnAction(e -> {
            procesarCuracion("Pocion", btnUsaPocion, btnUsaSuperPocion, btnAtrasMochila, lblInfo, barraVidaJ1, lblTuVida, btnAtacar, btnMochila, btnRegresar);
        });

        btnUsaSuperPocion.setOnAction(e -> {
            procesarCuracion("SuperPocion", btnUsaPocion, btnUsaSuperPocion, btnAtrasMochila, lblInfo, barraVidaJ1, lblTuVida, btnAtacar, btnMochila, btnRegresar);
        });

        root.getChildren().addAll(
            imgFondo, lblInfo,
            lblVidaRival, barraVidaCPU, imgPokemonCPU,
            lblTuVida, barraVidaJ1, imgPokemonJ1,
            btnAtacar, btnMochila, btnRegresar, 
            btnUsaPocion, btnUsaSuperPocion, btnAtrasMochila 
        );

        Scene scene = new Scene(root, 500, 700);
        stage.setScene(scene);
        stage.show();
    }

    private void procesarCuracion(String itemClave, Button b1, Button b2, Button b3, Label lblInfo, ProgressBar barraJ1, Label lblTuVida, Button act, Button moch, Button huir) {
        if (miPokemonActual == null) return;

        boolean exito = mochila.usarItem(itemClave, miPokemonActual);

        if (exito) {
            b1.setText("Pocion (" + mochila.obtenerCantidad("Pocion") + ")");
            b2.setText("SuperPocion (" + mochila.obtenerCantidad("SuperPocion") + ")");

            this.vidaJ1 = (double) miPokemonActual.getVida() / miPokemonActual.getVidaMaxima();
            barraJ1.setProgress(this.vidaJ1);
            
            if (this.vidaJ1 > 0.3) {
                barraJ1.setStyle("-fx-accent: #2ecc71;"); 
            }

            lblInfo.setText("¡Se uso " + itemClave + "! " + miPokemonActual.getNombre() + " recupero salud.");

            b1.setVisible(false); b2.setVisible(false); b3.setVisible(false);
            act.setVisible(true); moch.setVisible(true); huir.setVisible(true);
            desactivarControles(act, moch, huir);

            ejecutarContraataqueRival(lblInfo, lblTuVida, barraJ1, act, moch, huir);

        } else {
            if (miPokemonActual.getVida() >= miPokemonActual.getVidaMaxima()) {
                lblInfo.setText("¡" + miPokemonActual.getNombre() + " ya tiene la vida al maximo!");
            } else {
                lblInfo.setText("No te quedan unidades de " + itemClave + "!");
            }
        }
    }

    private void procesarVictoriaRival(Label lblInfo, Label lblVRival, ProgressBar bCPU, Label lblTVida, Button act, Button moch, Button huir) {
        rivalesDerrotados++;
        if (miPokemonActual != null) {
            String nombreViejo = miPokemonActual.getNombre();
            int expCalculada = battle.SistemaDeExp.calcularExperienciaGanada(pokemonCPU);
            String reporteExp = battle.SistemaDeExp.ganarExperiencia(miPokemonActual, expCalculada);
            System.out.print(reporteExp); 
            
            if (!miPokemonActual.getNombre().equalsIgnoreCase(nombreViejo)) {

                lblInfo.setText("¡Tu " + nombreViejo + " evoluciono en " + miPokemonActual.getNombre() + "!");
                try {
                    imgPokemonJ1.setImage(new Image(getClass().getResourceAsStream(miPokemonActual.getImagen())));
                    lblTVida.setText("Vida de " + miPokemonActual.getNombre() + ":");
                    this.vidaJ1 = 1.0; // Como está curado, es 100% de vida
                    barraVidaJ1.setProgress(this.vidaJ1);
                    barraVidaJ1.setStyle("-fx-accent: #2ecc71;");
                
                    
                }
                 catch (Exception ex) {}
            }
        }

        if (rivalesDerrotados < MAX_RIVALES) {
            KeyFrame relevoRival = new KeyFrame(Duration.seconds(1.5), ev -> {
                aparecerSiguienteRival();
                
                // === SOLUCIÓN DETONACIÓN CPU: Borramos el ImageView maldito y creamos uno limpio ===
                root.getChildren().remove(imgPokemonCPU);
                imgPokemonCPU = new ImageView();
                imgPokemonCPU.setFitWidth(150);
                imgPokemonCPU.setFitHeight(150);
                imgPokemonCPU.setPreserveRatio(false); 
                imgPokemonCPU.setLayoutX(280);
                imgPokemonCPU.setLayoutY(140);
                root.getChildren().add(imgPokemonCPU); // Se reinserta virgen de animaciones
                
                lblVRival.setText("Vida de " + pokemonCPU.getNombre() + ":");
                bCPU.setProgress(vidaCPU);
                bCPU.setStyle("-fx-accent: #2ecc71;");
                try {
                    imgPokemonCPU.setImage(new Image(getClass().getResourceAsStream(pokemonCPU.getImagen())));
                } catch (Exception ex) {}
                lblInfo.setText("Aparece un " + pokemonCPU.getNombre() + " listo para pelear!");
                activarControles(act, moch, huir);
            });
            new Timeline(relevoRival).play();
        } else {
            lblInfo.setText("Derrotaste a todos los jefes! ERES EL CAMPEON!");
        }
    }

    private void ejecutarContraataqueRival(Label lblInfo, Label lblTuVida, ProgressBar barraJ1, Button act, Button moch, Button huir) {
        
        KeyFrame ataqueEnemigo = new KeyFrame(Duration.seconds(1.2), e -> {
    lblInfo.setText(pokemonCPU.getNombre() + " enemigo usa un ataque critico!");
    
    // --- NUEVO: Animación de cambio de sprite del rival ---
    String rutaNormalCPU = pokemonCPU.getImagen();
    String rutaAtaqueCPU = rutaNormalCPU.substring(0, rutaNormalCPU.lastIndexOf(".")) + "2.png";
    AnimadorPokemon.animarCambioImagen(imgPokemonCPU, rutaNormalCPU, rutaAtaqueCPU);
    
    // Animaciones de acción
    AnimadorPokemon.animarAtaque(imgPokemonCPU, false);
    AnimadorPokemon.animarRecibirDanio(imgPokemonJ1);

    if (miPokemonActual != null && !miPokemonActual.estaDebilitado()) {
        // EL DAÑO QUE CAUSARA EL CPU SERA 1/3 DE LA VIDA DEL POKEMON
        int danioReal = miPokemonActual.getVidaMaxima() / 3;
        miPokemonActual.recibirDanio(danioReal);
        
        this.vidaJ1 = (double) miPokemonActual.getVida() / miPokemonActual.getVidaMaxima();
        barraJ1.setProgress(this.vidaJ1);
        
        if (this.vidaJ1 <= 0.3) {
            barraJ1.setStyle("-fx-accent: #e74c3c;"); 
        }
    }

    KeyFrame reactivarTurno = new KeyFrame(Duration.millis(500), ev -> {
        
        if (miPokemonActual == null || miPokemonActual.estaDebilitado()) {
            AnimadorPokemon.animarDebilitado(imgPokemonJ1);
            String nombreDebilitado = (miPokemonActual != null) ? miPokemonActual.getNombre() : "Pokemon";
            
            if (equipo != null && indiceActual + 1 < equipo.getListaPokemon().size()) {
                indiceActual++; 
                miPokemonActual = equipo.getListaPokemon().get(indiceActual);
                
                root.getChildren().remove(imgPokemonJ1);
                imgPokemonJ1 = new ImageView();
                imgPokemonJ1.setFitWidth(150);
                imgPokemonJ1.setFitHeight(150);
                imgPokemonJ1.setPreserveRatio(false); 
                imgPokemonJ1.setLayoutX(60);  
                imgPokemonJ1.setLayoutY(410);
                root.getChildren().add(imgPokemonJ1);
                
                this.vidaJ1 = (double) miPokemonActual.getVida() / miPokemonActual.getVidaMaxima();
                barraJ1.setProgress(this.vidaJ1);
                barraJ1.setStyle("-fx-accent: #2ecc71;");
                
                lblTuVida.setText("Vida de " + miPokemonActual.getNombre() + ":");
                try {
                    imgPokemonJ1.setImage(new Image(getClass().getResourceAsStream(miPokemonActual.getImagen())));
                } catch (Exception ex) {}
                lblInfo.setText("¡" + nombreDebilitado + " cayo! ¡Entra " + miPokemonActual.getNombre() + "!");
                activarControles(act, moch, huir);
            } else {
                lblInfo.setText("Todo tu equipo se ha debilitado. Fin de la partida.");
            }
        } else {
            lblInfo.setText("Que deberia hacer " + miPokemonActual.getNombre() + "?");
            activarControles(act, moch, huir);
        }
    });
    new Timeline(reactivarTurno).play();
});
new Timeline(ataqueEnemigo).play();
    }

    private void desactivarControles(Button a, Button b, Button c) {
        a.setDisable(true); b.setDisable(true); c.setDisable(true);
    }

    private void activarControles(Button a, Button b, Button c) {
        a.setDisable(false); b.setDisable(false); c.setDisable(false);
    }
}