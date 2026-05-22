package battle;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Jugador;

public class gestorPartida {

    // Método para guardar un jugador y todo su equipo en un archivo .dat
    public static void guardarProgreso(Jugador jugador, String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(jugador);
            System.out.println("-> ¡Partida guardada para " + jugador.getNombre() + " en " + nombreArchivo + "!");
        } catch (Exception e) {
            System.out.println("Error al guardar el progreso: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para recuperar al jugador y su equipo desde el archivo .dat
    public static Jugador cargarProgreso(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            Jugador jugadorCargado = (Jugador) ois.readObject();
            System.out.println("-> ¡Partida cargada con éxito desde " + nombreArchivo + "!");
            return jugadorCargado;
        } catch (Exception e) {
            System.out.println("No se encontró archivo de guardado previo (" + nombreArchivo + "). Se iniciará una partida nueva.");
            return null;
        }
    }
}