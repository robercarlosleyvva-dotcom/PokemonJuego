package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaPrincipal extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Pokemon Juego - Principal");

        Button btnEquipo = new Button("Mi Equipo");
        Button btnPVE = new Button("Modo PvE");
        Button btnPVP = new Button("Modo PvP");
        Button btnPokedex = new Button("Pokedex");
        Button btnSalir = new Button("Salir");


        btnEquipo.setOnAction(e -> new VentanaEquipo(null).mostrar());
        btnPVE.setOnAction(e -> new VentanaGameplay().mostrar());
        btnPVP.setOnAction(e -> new VentanaPVP().mostrar());
        btnPokedex.setOnAction(e -> new VentanaPokedex().mostrar());
        btnSalir.setOnAction(e -> stage.close());

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(20);
        layout.setPadding(new Insets(15, 20, 15, 20));

        layout.getChildren().addAll(btnEquipo, btnPVE, btnPVP, btnPokedex, btnSalir);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
