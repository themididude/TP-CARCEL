package GestionCarcelMenus;

import funcionalidad.JSONConvertible;
import org.json.JSONObject;

public class Informe implements JSONConvertible {

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

    public JSONObject toJSONObject()
    {
        JSONObject json = new JSONObject();
        json.put("Asunto", Asunto);
        json.put("Descripcion", Descripcion);
        json.put("Fecha", Fecha);
        json.put("tipo", tipo.name());
        return json;
    }

    public static Informe fromJSON(JSONObject json) {
        String Asunto = json.getString("Asunto");
        String Descripcion = json.getString("Descripcion");
        String Fecha = json.getString("Fecha");
        Tipo tipo = Tipo.valueOf(json.getString("tipo"));

        return new Informe(Asunto, Descripcion, Fecha, tipo);
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
