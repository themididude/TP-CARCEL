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

    private static int nextId = 0;
    private int informeID;
    private Tipo tipo;
    private String Asunto;
    private String Descripcion;
    private String Fecha;
    private boolean Activo;

    public Informe(String Asunto, String Descripcion, String Fecha, Tipo tipo) {
        this.informeID = nextId++;
        this.Asunto = Asunto;
        this.Descripcion = Descripcion;
        this.Fecha = Fecha;
        this.tipo = tipo;
        this.Activo = true;
    }

    public Informe(JSONObject json) {
        this.informeID = json.getInt("informeID");
        this.Asunto = json.getString("Asunto");
        this.Descripcion = json.getString("Descripcion");
        this.Fecha = json.getString("Fecha");
        this.tipo = Tipo.valueOf(json.getString("tipo"));
        this.Activo = json.getBoolean("Activo");

        if (this.informeID >= nextId) {
            nextId = this.informeID + 1;
        }
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
    /// get
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

    public int getInformeID() {return informeID;}
    public boolean isActivo() {return Activo;}

    ////set
    public void setInformeID(int informeID) {
        this.informeID = informeID;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setAsunto(String asunto) {
        Asunto = asunto;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public void setActivo(boolean activo) {
        Activo = activo;
    }
}
