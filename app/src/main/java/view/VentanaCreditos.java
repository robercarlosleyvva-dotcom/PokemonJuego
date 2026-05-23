package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaCreditos {
    public void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Créditos");
        stage.setResizable(false);

        // 1. Fondo de imagen
        ImageView imgFondo = new ImageView();
        try {
            imgFondo.setImage(new Image(getClass().getResourceAsStream("/pokemon/FCD.jpg")));
        } catch (Exception e) {
            System.out.println("No se encontró la imagen de fondo para créditos.");
        }
        imgFondo.setFitWidth(300);
        imgFondo.setFitHeight(400);

        // 2. Contenedor principal
        VBox vBox = new VBox(15);
        vBox.setAlignment(Pos.CENTER);
        // Fondo semitransparente para que el texto se lea bien sobre cualquier imagen
        vBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-padding: 20; -fx-background-radius: 10;");

        Label titulo = new Label("--- INTEGRANTES ---");
        titulo.setStyle("-fx-text-fill: #f1c40f; -fx-font-size: 22px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 2, 1, 0, 0);");
        vBox.getChildren().add(titulo);

        String[] nombres = {
            "Leyva Roman Roberto Carlos",
            "Zamora Garcia Joselyne",
            "Casillas Acosta Daniela Yatziri", 
            "Juarez Cabrera Karol Alexis", 
            "Rios Rodriguez Emily", 
            "Rojas Rios Esteban"
        };

        for (String nombre : nombres) {
            Label lbl = new Label(nombre);
            lbl.setStyle("-fx-text-fill: white; -fx-font-size: 15px; -fx-font-family: 'Arial';");
            vBox.getChildren().add(lbl);
        }

        // 3. Unir todo
        StackPane root = new StackPane();
        root.getChildren().addAll(imgFondo, vBox);

        stage.setScene(new Scene(root, 300, 400));
        stage.show();
    }
}