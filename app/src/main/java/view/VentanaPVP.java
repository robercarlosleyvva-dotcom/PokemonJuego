package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaPVP {

    public void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Modo PvP");

        Label lblTitulo = new Label("Jugador vs Jugador");

        VBox layout = new VBox(10, lblTitulo);
        Scene scene = new Scene(layout, 400, 300);

        stage.setScene(scene);
        stage.show();
    }
}