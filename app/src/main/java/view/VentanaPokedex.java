package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaPokedex {

    public void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Pokedex");

        Label lblTitulo = new Label("Pokémones disponibles");

        VBox layout = new VBox(10, lblTitulo);
        Scene scene = new Scene(layout, 400, 300);

        stage.setScene(scene);
        stage.show();
    }
}