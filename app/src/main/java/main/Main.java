package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        System.out.println("PROBANDO");
        
        Label nombre = new Label("EQUIPO CHILES EN NOGADA");
        Label etiqueta = new Label("Hola mundo");
        
        Button boton = new Button("Presioname");

         boton.setOnAction(e ->
                etiqueta.setText("JavaFX funciona correctamente ")
        );

        VBox root = new VBox(10);

        root.getChildren().addAll(nombre,etiqueta,boton);


        Scene scene = new Scene(root, 400, 200);

        stage.setTitle("Ventana");

        stage.setScene(scene);

        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) {
        
        launch();
    }
}