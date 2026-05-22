package battle;

public class ResultadoAccion {
    private final String mensaje;
    private final double danioInfligido;
    private final boolean esCritico;
    private final double multiplicadorTipos;

    //Metodo constructor
    public ResultadoAccion(String mensaje, double danioInfligido, boolean esCritico, double multiplicadorTipos) {
        this.mensaje = mensaje;
        this.danioInfligido = danioInfligido;
        this.esCritico = esCritico;
        this.multiplicadorTipos = multiplicadorTipos;
    }

    public String getMensaje() { return mensaje; }
    public double getDanioInfligido() { return danioInfligido; }
    public boolean esCritico() { return esCritico; }
    public double getMultiplicadorTipos() { return multiplicadorTipos; }
}
