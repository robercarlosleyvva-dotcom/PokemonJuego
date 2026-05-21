package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaPVP {

    public void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Modo PvP");
        
        // 1. BLOQUEAR TAMAÑO DE LA VENTANA
        stage.setResizable(false);

        // 2. CONTENEDOR PRINCIPAL POR COORDENADAS
        AnchorPane root = new AnchorPane();

        // 3. IMAGEN DE FONDO
        ImageView imgFondo = new ImageView();
        try {
            // Cambien "/pokemon/fondoPVP.jpg" por el nombre exacto de su imagen de batalla
            imgFondo.setImage(new Image(getClass().getResourceAsStream("/pokemon/FC.jpg")));
        } catch (Exception e) {
            System.out.println("No se encontro la imagen de fondo para PvP.");
        }
        // Ajustamos el fondo al tamaño de la ventana
        imgFondo.setFitWidth(400); 
        imgFondo.setFitHeight(500);
        
        // Clavamos el fondo en la esquina superior izquierda
        AnchorPane.setTopAnchor(imgFondo, 0.0);
        AnchorPane.setLeftAnchor(imgFondo, 0.0);

        // 4. TITULO DE LA VENTANA
        Label lblTitulo = new Label("JUGADOR VS JUGADOR");
        // Estilo con sombra oscura para que resalte sobre el fondo de batalla
        lblTitulo.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 5, 0, 0, 2);");
        
        // Coordenadas fijas para centrar el titulo arriba
        lblTitulo.setLayoutX(65); 
        lblTitulo.setLayoutY(30);

        // 5. BOTON REGRESAR INVISIBLE (Opcional por si usan una flecha en su fondo)
        // O si prefieren un boton normal que se vea, me avisan y lo cambiamos en un segundo
        Button btnRegresar = new Button("Regresar al Menu");
        btnRegresar.setStyle("-fx-font-weight: bold; -fx-background-color: #e74c3c; -fx-text-fill: white; -fx-background-radius: 10; -fx-cursor: hand;");
        btnRegresar.setPrefWidth(160);
        
        // Lo acomodamos hasta abajo en el centro
        btnRegresar.setLayoutX(120);
        btnRegresar.setLayoutY(430);
        
        btnRegresar.setOnAction(e -> stage.close());

        // 6. AGREGAR ELEMENTOS AL TABLERO (El fondo va primero para que quede abajo)
        root.getChildren().addAll(imgFondo, lblTitulo, btnRegresar);

        // Escena con las mismas medidas de la imagen
        Scene scene = new Scene(root, 400, 500);
        stage.setScene(scene);
        stage.show();
    }
     
}