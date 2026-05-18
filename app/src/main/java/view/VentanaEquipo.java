package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaEquipo {

    public void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Mi Equipo");

        Label lblTitulo = new Label("Pokémon de tu equipo:");

        VBox layout = new VBox(10, lblTitulo);
        Scene scene = new Scene(layout, 400, 300);

        stage.setScene(scene);
        stage.show();
    }
}
