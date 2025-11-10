import funcionalidad.JSONConvertible;
import org.json.JSONObject;

public class Guardia extends Empleado implements JSONConvertible {

    private boolean activo;
    private String placaPolicial;
    // Constante de clase (final + static)
    private static final Cargo CARGO = Cargo.GUARDIA;
    private Cargo rango;

    public Guardia(JSONObject json) {
        super(json);
       this.activo = json.optBoolean("activo");
       this.placaPolicial = json.optString("placaPolicial");
    }

    public Guardia(String Nombre, String Apellido, String DNI, int age, double Salario, int diasLibres, boolean activo, String placaPolicial, Genero genero, Cargo rango) {
        super(Nombre, Apellido, DNI, age, Salario, diasLibres, genero);
        this.activo = activo;
        this.placaPolicial = placaPolicial;
        this.rango = rango;
    }


    @Override
    public JSONObject toJSONObject() {
        JSONObject json = super.toJSONObject();

        json.put("activo", this.activo);
        json.put("placaPolicial", this.placaPolicial);
        json.put("rango", this.rango);
        return json;
    }
}
