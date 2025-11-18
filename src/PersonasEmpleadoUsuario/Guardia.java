package PersonasEmpleadoUsuario;

import funcionalidad.JSONConvertible;
import org.json.JSONObject;

import java.util.Objects;

public class Guardia extends Empleado implements JSONConvertible {

    private boolean activo;
    private String placaPolicial;
    private Cargo rango; // Jerarquía policial: GUARDIA, OFICIAL, etc.

    // Constructor desde JSON
    public Guardia(JSONObject json) {
        super(json);
        this.activo = json.optBoolean("activo");
        this.placaPolicial = json.optString("placaPolicial");
        this.rango = json.getEnum(Cargo.class, "Rango");  // Esto ya falla solo si no existe o es inválido
    }


    // Constructor normal
    public Guardia(String nombre, String apellido, String DNI, int age,
                   double salario, int diasLibres, boolean activo,
                   String placaPolicial, Genero genero, Cargo rango) {

        super(nombre, apellido, DNI, age, salario, diasLibres, Cargo.GUARDIA, genero);

        this.activo = activo;
        this.placaPolicial = placaPolicial;
        this.rango = rango;
    }

    public String getPlacaPolicial() {
        return placaPolicial;
    }

    public Cargo getRango() {
        return rango;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = super.toJSONObject();

        json.put("activo", activo);
        json.put("placaPolicial", placaPolicial);
        json.put("Rango", rango.name());

        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Guardia guardia)) return false;
        return activo == guardia.activo && Objects.equals(placaPolicial, guardia.placaPolicial) && rango == guardia.rango;
    }
    @Override
    public int hashCode() {
        return Objects.hash(activo, placaPolicial, rango);
    }
}
