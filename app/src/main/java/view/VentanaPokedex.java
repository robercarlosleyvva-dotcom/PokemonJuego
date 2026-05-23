package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Pokedex;
import model.Pokemon;

public class VentanaPokedex {

    public void mostrar() {

        Stage stage = new Stage();
        stage.setTitle("Pokedex");
        stage.setResizable(false);

        // 1. CONTENEDOR PRINCIPAL FIJO (Tablero de coordenadas)
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: #2c3e50;"); // Por si acaso

        // 2. IMAGEN DE FONDO
        ImageView imgFondo = new ImageView();
        try {
            imgFondo.setImage(new Image(getClass().getResourceAsStream("/pokemon/fondoPokedex.jpg")));
        } catch (Exception e) {
            System.out.println("No se encontro la imagen de fondo.");
        }
        imgFondo.setFitWidth(400); 
        imgFondo.setFitHeight(600);
        AnchorPane.setTopAnchor(imgFondo, 0.0);
        AnchorPane.setLeftAnchor(imgFondo, 0.0);

        // 3. TITULO POKEDEX (Bajado a la franja roja exacta)
        Label lblTitulo = new Label("POKEDEX");
        // Le agregue una tipografia mas estilizada, negrita y sombra limpia
        lblTitulo.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(167, 0, 0, 0.76), 4, 0, 0, 2);");
        lblTitulo.setLayoutX(125); // Centrado horizontal manual
        lblTitulo.setLayoutY(35);  // Bajado exacto a la zona roja de su imagen

        // 4. CAJA DE LISTA SUPER BELLA (Redondeada y traslucida)
        ListView<String> listaPokemon = new ListView<>();
        listaPokemon.setPrefHeight(115); 
        listaPokemon.setPrefWidth(240);  
        
        // Coordenadas para que encaje perfecto en el cuadro blanco de su fondo
        listaPokemon.setLayoutX(80);   
        listaPokemon.setLayoutY(115);  

        // CSS Avanzado para que la caja exterior se vea premium (bordes bien curvados)
        listaPokemon.setStyle(
            "-fx-background-color: rgba(255, 255, 255, 0.65);" + // Fondo blanco tipo cristal
            "-fx-background-radius: 15;" + 
            "-fx-border-radius: 15;" + 
            "-fx-border-color: rgba(255, 255, 255, 0.8);" +
            "-fx-border-width: 1.5;" +
            "-fx-padding: 5;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 5, 0, 0, 2);"
        );

        // Volver bellos y limpios los renglones internos de la lista
        listaPokemon.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("-fx-background-color: transparent;");
                } else {
                    setText(item);
                    // Texto elegante para cada Pokemon
                    setStyle(
                        "-fx-text-fill: #2c3e50;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-color: transparent;" +
                        "-fx-padding: 3 10 3 10;"
                    );
                }
            }
        });

       listaPokemon.getItems().addAll(
            // === GENERACIÓN 1 ===
            "Bulbasaur", "Ivysaur", "Venusaur", "Charmander", "Charmeleon", "Charizard",
            "Squirtle", "Wartortle", "Blastoise", "Pikachu", "Flareon","Vaporeon",
            
            // === GENERACIÓN 2 ===
            "Cyndaquil", "Quilava", "Typhlosion", "Totodile", "Croconaw", "Feraligatr","Leafeon",
            "Larvitar", "Pupitar", "Tyranitar",
            
            // === GENERACIÓN 3 ===
            "Treecko", "Grovyle", "Sceptile", "Torchic", "Combusken", "Blaziken",
            
            // === GENERACIÓN 4 ===
            "Piplup", "Prinplup", "Empoleon", "Shinx", "Luxio", "Luxray",
            
            // === GENERACIÓN 5 ===
            "Snivy", "Servine", "Serperior", "Oshawott", "Dewott", "Samurott",
            
            // === GENERACIÓN 7 ===
            "Rowlet", "Dartrix", "Decidueye", "Litten", "Torracat", "Incineroar", 
            "Rockruff", "Lycanroc"
        );

        // 5. COMPONENTES DEL POKEMON (Imagen y textos)
        ImageView imageView = new ImageView();
        imageView.setFitWidth(130);
        imageView.setFitHeight(130);
        imageView.setPreserveRatio(true);

        Label lblNumero = new Label();
        Label lblNombre = new Label();
        Label lblTipo = new Label();

        // Estilos Pro con sombras oscuras para que se lean perfecto
        String estiloInfo = "-fx-font-weight: bold; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(155, 0, 0, 0.89), 6, 0, 0, 1);";
        lblNumero.setStyle("-fx-font-size: 16px; " + estiloInfo);
        lblNombre.setStyle("-fx-font-size: 20px; " + estiloInfo);
        lblTipo.setStyle("-fx-font-size: 17px; -fx-text-fill: #f1c40f; " + estiloInfo); // Tipo en amarillo brillante

        // --- EL TRUCO PARA QUE NO SE AMONTONEN ABAJO ---
        // Metemos la foto y los textos en un VBox ordenado, y fijamos ESE VBox abajo de la linea de puntos
        VBox infoPokemon = new VBox(8, imageView, lblNumero, lblNombre, lblTipo);
        infoPokemon.setAlignment(javafx.geometry.Pos.CENTER);
        infoPokemon.setPrefWidth(300);
        
        // Colocamos el bloque completo justo abajo de los puntos negros
        infoPokemon.setLayoutX(50);  // Centrado en la ventana
        infoPokemon.setLayoutY(300); // Altura perfecta abajo de la linea de puntos

        // 6. BOTON REGRESAR INVISIBLE (Clavado sobre el triangulo negro)
        Button btnRegresar = new Button(); 
        btnRegresar.setPrefWidth(60);
        btnRegresar.setPrefHeight(40);
        btnRegresar.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        
        btnRegresar.setLayoutX(170); // Justo en medio
        btnRegresar.setLayoutY(535); // Encima de la flecha negra

        btnRegresar.setOnAction(e -> stage.close());

        // Evento de seleccion
        listaPokemon.getSelectionModel().selectedItemProperty().addListener((obs, viejo, nuevo) -> {
            if (nuevo != null) {
                Pokemon p = Pokedex.buscarPorNombre(nuevo);
                int id = Pokedex.obtenerIdPorNombre(nuevo);

                if (p != null) {
                    lblNumero.setText("ID: " + id);
                    lblNombre.setText("Nombre: " + p.getNombre());
                    lblTipo.setText("Tipo: " + p.getTipo());

                    try {
                        imageView.setImage(new Image(getClass().getResourceAsStream(p.getImagen())));
                    } catch (Exception e) {
                        System.out.println("No se pudo cargar la imagen de " + nuevo);
                        imageView.setImage(null);
                    }
                }
            }
        });

        // 7. AGREGAR AL CONTENEDOR (Fondo -> Titulo -> Lista -> Bloque Info -> Boton Clic)
        root.getChildren().addAll(
            imgFondo, 
            lblTitulo, 
            listaPokemon, 
            infoPokemon, 
            btnRegresar
        );

        Scene scene = new Scene(root, 400, 600);
        stage.setScene(scene);
        stage.show();
    }
}