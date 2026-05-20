package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaGameplay {

    public void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Gameplay-Batalla");

        Label lblInfo = new Label("Aqui se mostrara la batalla");
        Button btnAtacar = new Button("Atacar");
        Button btnDefender = new Button("Defender");

        VBox layout = new VBox(10, lblInfo, btnAtacar, btnDefender);
        Scene scene = new Scene(layout, 500, 400);

        stage.setScene(scene);
        stage.show();
    }
}
