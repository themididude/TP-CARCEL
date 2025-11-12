package GestionCarcelMenus;

public class Informe {

    public enum Tipo
    {
        FINANCIERO,
        POLICIAL,
        GENERAL
    }

    private Tipo tipo;
    private String Asunto;
    private String Descripcion;
    private String Fecha;

    public Informe(String Asunto, String Descripcion, String Fecha, Tipo tipo) {
        this.Asunto = Asunto;
        this.Descripcion = Descripcion;
        this.Fecha = Fecha;
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }
    public String getAsunto() {
        return Asunto;
    }
    public String getDescripcion() {
        return Descripcion;
    }
    public String getFecha() {
        return Fecha;
    }
}
