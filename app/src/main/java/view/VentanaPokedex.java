package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Pokedex;
import model.Pokemon;

public class VentanaPokedex {

    public void mostrar() {

        Stage stage = new Stage();
        stage.setTitle("Pokedex");

        Label lblTitulo = new Label("POKEDEX");
        lblTitulo.setStyle(
    "-fx-font-size: 28px;" +
    "-fx-font-weight: bold;" +
    "-fx-text-fill: white;"
        );
        

        

        // Lista de pokemon
        ListView<String> listaPokemon = new ListView<>();
        listaPokemon.setPrefHeight(150);
        listaPokemon.getItems().addAll(
            
"Bulbasaur",
"Ivysaur",
"Venusaur",
"Charmander",
"Charmeleon",
"Charizard",
"Squirtle",
"Wartortle",
"Blastoise",
"Pikachu",
"Leafeon",
"Flareon",
"Vaporeon",
"Cyndaquil",
"Quilava",
"Typhlosion",
"Totodile",
"Croconaw",
"Feraligatr",
"Larvitar",
"Pupitar",
"Tyranitar",
"Treecko",
"Grovyle",
"Sceptile",
"Torchic",
"Combusken",
"Blaziken",
"Shinx",
"Luxio",
"Luxray",
"Piplup",
"Prinplup",
"Empoleon",
"Snivy",
"Servine",
"Serperior",
"Oshawott",
"Dewott",
"Samurott",
"Rowlet",
"Dartrix",
"Decidueye",
"Litten",
"Torracat",
"Incineroar",
"Rockruff",
"Lycanroc"
        );
        

        // Imagen
        ImageView imageView = new ImageView();

        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);

        // Nombre
        Label lblNombre = new Label();

        //Numero de pokedex 
        Label lblNumero = new Label();

        //Tipo elemental
        Label lblTipo = new Label();

        lblNumero.setStyle(
    "-fx-font-size: 16px;" +
    "-fx-text-fill: white;" +
    "-fx-font-weight: bold;"
);

lblNombre.setStyle(
    "-fx-font-size: 20px;" +
    "-fx-text-fill: white;" +
    "-fx-font-weight: bold;"
);

lblTipo.setStyle(
    "-fx-font-size: 16px;" +
    "-fx-text-fill: yellow;" +
    "-fx-font-weight: bold;"
);

        // Evento cuando seleccionan pokemon
        listaPokemon.getSelectionModel()
        
            .selectedItemProperty()
            
            .addListener((obs, viejo, nuevo) -> {

                Pokemon p = Pokedex.buscarPorNombre(nuevo);
                int id = Pokedex.obtenerIdPorNombre(nuevo);


                if(p != null){

                    Image imagen = new Image(
                        getClass()
                        .getResource(p.getImagen())
                        .toExternalForm()
                    );

                    lblNumero.setText(
                        
                        "ID: " + id );

                    imageView.setImage(imagen);

                    lblNombre.setText(
                        "Nombre: " + p.getNombre()
                    );
                    lblTipo.setText(
                        "Tipo : " + p.getTipo()
                    );

                    
                }
            });

        VBox layout = new VBox(
            10,
            lblTitulo,
            listaPokemon,
            imageView,
            lblNumero,
            lblNombre,
            lblTipo
        );
        layout.setStyle(
    "-fx-background-color: linear-gradient(to bottom, #2fa2d3, #85baf7);" +
    "-fx-padding: 20;" +
    "-fx-alignment: center;"
);

        Scene scene = new Scene(layout, 400, 500);

        stage.setScene(scene);
        stage.show();
    }
    
}