package PersonasEmpleadoUsuario;

import funcionalidad.JSONConvertible;
import funcionalidad.Activable;
import org.json.JSONObject;
import java.util.Objects;

public class Guardia extends Empleado implements JSONConvertible, Activable {

    private String placaPolicial;
    private Cargo rango; // Jerarquía policial: GUARDIA, OFICIAL, etc.
    private boolean Activo;


    // Constructor desde JSON
    public Guardia(JSONObject json) {
        super(json);
        this.Activo = json.optBoolean("Activo");
        this.placaPolicial = json.optString("placaPolicial");
        this.rango = json.getEnum(Cargo.class, "Rango");  // Esto ya falla solo si no existe o es inválido
    }
    // Constructor normal
    public Guardia(String nombre, String apellido, String DNI, int age,
                   double salario, int diasLibres, boolean activo,
                   String placaPolicial, Genero genero, Cargo rango) {

        super(nombre, apellido, DNI, age, salario, diasLibres, Cargo.GUARDIA, genero, activo);
        this.Activo = activo;
        this.placaPolicial = placaPolicial;
        this.rango = rango;
    }

    public String getPlacaPolicial() {
        return placaPolicial;
    }
    public Cargo getRango() {
        return rango;
    }

    public void setPlacaPolicial(String placaPolicial) {
        this.placaPolicial = placaPolicial;
    }
    public void setRango(Cargo rango) {
        this.rango = rango;
    }
    @Override
    public void setActivo(boolean activo) {
        Activo = activo;
    }
    @Override
    public JSONObject toJSONObject() {
        JSONObject json = super.toJSONObject();
        json.put("placaPolicial", placaPolicial);
        json.put("Rango", rango.name());
        json.put("Activo", Activo);
        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Guardia guardia)) return false;
        return Activo == guardia.Activo && Objects.equals(placaPolicial, guardia.placaPolicial) && rango == guardia.rango;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Activo, placaPolicial, rango);
    }
}
