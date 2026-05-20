package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaInfoPartida {

    public void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Informacion de la Partida");

        Label lblInfo = new Label("Datos de la partida actual...");

        VBox layout = new VBox(10, lblInfo);
        Scene scene = new Scene(layout, 300, 200);

        stage.setScene(scene);
        stage.show();
    }
}