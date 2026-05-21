package view;

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

public class VentanaPrincipal extends Application {

    private Equipo equipo = new Equipo();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Pokemon Juego - Principal");

        // -------------------------------------------------------------
        // 1. BLOQUEAR EL TAMAÑO DE LA VENTANA (No se puede maximizar ni estirar)
        stage.setResizable(false);
        // -------------------------------------------------------------

        // 2. IMAGEN DE FONDO (Medidas fijas adaptadas a la escena)
        ImageView imgFondo = new ImageView();
        try {
            // Cambien "/pokemon/fondoPrincipal.jpg" por la ruta exacta de su fondo
            imgFondo.setImage(new Image(getClass().getResourceAsStream("/pokemon/FP.jpg")));
        } catch (Exception e) {
            System.out.println("No se encontro la imagen de fondo principal.");
        }
        imgFondo.setFitWidth(400);
        imgFondo.setFitHeight(500);

        // 3. BOTONES
        Button btnEquipo = new Button("Mi Equipo");
        Button btnPVE = new Button("Modo PvE");
        Button btnPVP = new Button("Modo PvP");
        Button btnPokedex = new Button("Pokedex");
        Button btnSalir = new Button("Salir");

        // Estilo estetico para los botones: fondo semitransparente o estilizado para que resalten
        String estiloBoton = "-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-color: rgba(255, 255, 255, 0.85); -fx-text-fill: #2c3e50; -fx-background-radius: 15; -fx-cursor: hand;";
        btnEquipo.setStyle(estiloBoton);
        btnPVE.setStyle(estiloBoton);
        btnPVP.setStyle(estiloBoton);
        btnPokedex.setStyle(estiloBoton);
        btnSalir.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-color: #e74c3c; -fx-text-fill: white; -fx-background-radius: 15; -fx-cursor: hand;");

        // Ajustamos un ancho fijo para que todos los botones midan lo mismo de largo
        btnEquipo.setPrefWidth(180);
        btnPVE.setPrefWidth(180);
        btnPVP.setPrefWidth(180);
        btnPokedex.setPrefWidth(180);
        btnSalir.setPrefWidth(180);

        // Acciones
        btnEquipo.setOnAction(e -> {
            new VentanaEquipo(equipo).mostrar();
        });
        btnPVE.setOnAction(e -> new VentanaGameplay(equipo).mostrar());
        btnPVP.setOnAction(e -> new VentanaPVP().mostrar());
        btnPokedex.setOnAction(e -> new VentanaPokedex().mostrar());
        btnSalir.setOnAction(e -> stage.close());

        // 4. ACOMODO EN CAPAS
        // Contenedor de botones (Fondo transparente para que se vea la imagen de atras)
        VBox contenido = new VBox();
        contenido.setAlignment(Pos.CENTER);
        contenido.setSpacing(18);
        contenido.setPadding(new Insets(20));
        contenido.setStyle("-fx-background-color: transparent;");

        contenido.getChildren().addAll(
            btnEquipo,
            btnPVE,
            btnPVP,
            btnPokedex,
            btnSalir
        );

        // Capa base (Fondo) + Capa superior (Botones)
        StackPane root = new StackPane();
        root.getChildren().addAll(imgFondo, contenido);

        // Escena con las mismas medidas exactas que la imagen
        Scene scene = new Scene(root, 400, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}