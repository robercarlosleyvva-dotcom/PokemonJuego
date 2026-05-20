package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Equipo;
import model.Pokemon;

public class VentanaEquipo {

    private Equipo equipo;

    public VentanaEquipo(Equipo equipo){
        this.equipo = equipo;   }

    public void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Mi Equipo");

        Label lblTitulo = new Label("Pokemon de tu equipo:");

        VBox contenedor = new VBox(10);

        for (Pokemon p : equipo.getListaPokemon()) {

            ImageView img = new ImageView(
                new Image(getClass()
                .getResource(p.getImagen())
                .toExternalForm())
            );

            img.setFitWidth(80);
            img.setFitHeight(80);

            Label nombre = new Label(p.getNombre());
            Label tipo = new Label("Tipo: " + p.getTipo());

            VBox card = new VBox(5, img, nombre, tipo);

            contenedor.getChildren().add(card);
        }

        VBox layout = new VBox(15, lblTitulo, contenedor);

        Scene scene = new Scene(layout, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
    }

