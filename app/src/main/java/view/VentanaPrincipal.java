package view;

import battle.gestorPartida;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Equipo;
import model.Jugador;
import model.JugadorHumano;
import model.Pokemon; // === NUEVO IMPORT: Para poder guardar y cargar ===

public class VentanaPrincipal extends Application {

    private Equipo equipo = new Equipo();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Pokemon Juego - Principal");

        // -------------------------------------------------------------
        // === INTENTAR CARGAR EL PROGRESO AL INICIAR EL JUEGO ===
        // Intentamos cargar los datos guardados del Jugador 1
        Jugador jugadorGuardado = gestorPartida.cargarProgreso("partidaJ1.dat");
        
        // Si encontramos una partida guardada, le restauramos los Pokémon a nuestro objeto global "equipo"
        if (jugadorGuardado != null && jugadorGuardado.getEquipo() != null && !jugadorGuardado.getEquipo().isEmpty()) {
            this.equipo.getListaPokemon().clear();
            this.equipo.getListaPokemon().addAll(jugadorGuardado.getEquipo());
            this.equipo.setBloqueado(true); // Bloqueamos el candado para que mantenga el equipo fijo
            System.out.println("-> [ÉXITO] Se restauró el equipo de la partida guardada anterior.");
        }
        // -------------------------------------------------------------

        // 1. BLOQUEAR EL TAMAÑO DE LA VENTANA
        stage.setResizable(false);

        // 2. IMAGEN DE FONDO
        ImageView imgFondo = new ImageView();
        try {
            imgFondo.setImage(new Image(getClass().getResourceAsStream("/pokemon/FP.jpg")));
        } catch (Exception e) {
            System.out.println("No se encontro la imagen de fondo principal.");
        }
        imgFondo.setFitWidth(400);
        imgFondo.setFitHeight(550); 

        // 3. BOTONES
        Button btnEquipo = new Button("Mi Equipo");
        Button btnPVE = new Button("Modo PvE");
        Button btnPVP = new Button("Modo PvP");
        Button btnPokedex = new Button("Pokedex");
        Button btnBorrarProgreso = new Button("Borrar Progreso");
        Button btnSalir = new Button("Salir");
        Button btnCreditos = new Button("Creditos");
        

        // Estilos estéticos de los botones
        String estiloBoton = "-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-color: rgba(255, 255, 255, 0.85); -fx-text-fill: #2c3e50; -fx-background-radius: 15; -fx-cursor: hand;";
        btnEquipo.setStyle(estiloBoton);
        btnPVE.setStyle(estiloBoton);
        btnPVP.setStyle(estiloBoton);
        btnPokedex.setStyle(estiloBoton);
        btnCreditos.setStyle(estiloBoton);
        
        btnBorrarProgreso.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-color: #e67e22; -fx-text-fill: white; -fx-background-radius: 15; -fx-cursor: hand;");
        btnSalir.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-color: #e74c3c; -fx-text-fill: white; -fx-background-radius: 15; -fx-cursor: hand;");

        // Ajustamos el tamaño uniforme
        btnEquipo.setPrefWidth(180);
        btnPVE.setPrefWidth(180);
        btnPVP.setPrefWidth(180);
        btnPokedex.setPrefWidth(180);
        btnBorrarProgreso.setPrefWidth(180); 
        btnSalir.setPrefWidth(180);
        btnCreditos.setPrefWidth(180);

        // Acciones de los botones
        btnEquipo.setOnAction(e -> {
            new VentanaEquipo(equipo).mostrar();
        });
        
        btnPVE.setOnAction(e -> new VentanaGameplay(equipo).mostrar());

        // === ACCIÓN MODIFICADA: Ahora jala el progreso guardado o crea uno nuevo ===
        btnPVP.setOnAction(e -> {
            // 1. Intentamos cargar los dos archivos guardados
            Jugador jugador1 = gestorPartida.cargarProgreso("partidaJ1.dat");
            Jugador jugador2 = gestorPartida.cargarProgreso("partidaJ2.dat");

            // Si no hay partida previa para el J1, lo creamos con el equipo actual de la ventana
            if (jugador1 == null) {
                jugador1 = new JugadorHumano("Joss");
                if (equipo != null && !equipo.getListaPokemon().isEmpty()) {
                    jugador1.getEquipo().addAll(equipo.getListaPokemon());
                }
            }

            // Si no hay partida previa para el J2, creamos al rival desde cero con un Pokémon por defecto
            if (jugador2 == null) {
                jugador2 = new JugadorHumano("Rival");
                Pokemon pokeRival = model.Pokedex.buscarPorNombre("Charizard");
                if (pokeRival == null) {
                    pokeRival = model.Pokedex.buscarPorNombre("Pikachu");
                }
                if (pokeRival != null) {
                    jugador2.getEquipo().add(pokeRival);
                }
            }

            // 3. Abrimos la ventana PvP con los jugadores listos
            new VentanaPVP(jugador1, jugador2).mostrar();
        });

        btnPokedex.setOnAction(e -> new VentanaPokedex().mostrar());

        // === ACCIÓN DE BORRAR PROGRESO MODIFICADA: También destruye físicamente los archivos guardados ===
        btnBorrarProgreso.setOnAction(e -> {
            if (!equipo.getListaPokemon().isEmpty() || equipo.isBloqueado()) {
                equipo.getListaPokemon().clear(); 
                equipo.setBloqueado(false);       
                System.out.println("Progreso eliminado con exito! El candado de equipo se ha liberado.");
                
                // Borramos los archivos .dat para que la próxima vez inicie limpio
                try {
                    java.io.File archivoJ1 = new java.io.File("partidaJ1.dat");
                    java.io.File archivoJ2 = new java.io.File("partidaJ2.dat");
                    if(archivoJ1.exists()) archivoJ1.delete();
                    if(archivoJ2.exists()) archivoJ2.delete();
                } catch(Exception ex) {
                    System.out.println("No se pudieron borrar los archivos físicos.");
                }

                btnBorrarProgreso.setText("Progreso Borrado!");
                btnBorrarProgreso.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-color: #2ecc71; -fx-text-fill: white; -fx-background-radius: 15;");
                
                new javafx.animation.Timeline(new javafx.animation.KeyFrame(
                    javafx.util.Duration.seconds(1.5), 
                    ev -> {
                        btnBorrarProgreso.setText("Borrar Progreso");
                        btnBorrarProgreso.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-color: #e67e22; -fx-text-fill: white; -fx-background-radius: 15; -fx-cursor: hand;");
                    }
                )).play();
            }
        });

        btnSalir.setOnAction(e -> stage.close());

       btnCreditos.setOnAction(e -> new VentanaCreditos().mostrar());

        // 4. ACOMODO EN CAPAS
        VBox contenido = new VBox();
        contenido.setAlignment(Pos.CENTER);
        contenido.setSpacing(15); 
        contenido.setPadding(new Insets(20));
        contenido.setStyle("-fx-background-color: transparent;");

        contenido.getChildren().addAll(
            btnEquipo,
            btnPVE,
            btnPVP,
            btnPokedex,
            btnBorrarProgreso, 
            btnSalir,
            btnCreditos
        );

        StackPane root = new StackPane();
        root.getChildren().addAll(imgFondo, contenido);

        Scene scene = new Scene(root, 400, 550);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}