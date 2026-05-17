package battle;

import model.TipoPokemon;

public class SistemaTipos {

    //Use una matriz y por si ocupa agregar algún otro Pokemos
    //Filas = Atacante, Columnas = Defensor
    // 1.0 = Daño normal, 2.0 = Súper eficaz, 0.5 = Poco eficaz

    private static final double[][] matrizDeEfectividad = {
            // FUEGO  AGUA   PLANTA  ELECTRICO    ROCA  Defensores
            {  0.5,   0.5,    2.0,      1.0,       1.0  }, //FUEGO Atacante
            {  2.0,   0.5,    0.5,      1.0,       1.0  }, //AGUA  Atacante
            {  0.5,   2.0,    0.5,      1.0,       1.0  }, //PLANTA   Atacante
            {  1.0,   2.0,    0.5,      0.5,       1.0  }, //ELECTRICO   Atacante
            {  1.0,   1.0,    1.0,      1.0,       0.5  }  //ROCA   Atacante
    };

    private static int getIndice(TipoPokemon tipo) {
        switch (tipo) {
            case FUEGO:
                return 0;
            case AGUA:
                return 1;
            case PLANTA:
                return 2;
            case ELECTRICO:
                return 3;
            case ROCA:
                return 4;
            default:
                return -1;
        }
    }

    //Metodo para calcular el daño segun su poder

    public static double obtenerMultiplicador(TipoPokemon tipoAtaque, TipoPokemon tipoDefensor) {

        int fila = getIndice(tipoAtaque);
        int columna = getIndice(tipoDefensor);

        if (fila == -1 || columna == -1) {
            return 1.0;
        }

        return matrizDeEfectividad[fila][columna];
    }
}
