import funcionalidad.JSONConvertible;
import org.json.JSONObject;

public abstract class Persona implements JSONConvertible {

    private String Nombre;
    private String Apellido;
    private String DNI;
    private int Age;
    private Genero Genero;

    public Persona(String Nombre, String Apellido, String DNI, int age, Genero genero) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.DNI = DNI;
        this.Age = age;
        this.Genero = genero;
    }

    public Persona(JSONObject json) {
        this.Nombre = toJSONObject().getString("Nombre");
        this.Apellido = toJSONObject().getString("Apellido");
        this.DNI = toJSONObject().getString("DNI");
        this.Age = toJSONObject().getInt("Age");
        this.Genero = toJSONObject().getEnum(Genero.class, "Genero");


    }

    /// get and set
    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getDNI() {
        return DNI;
    }

    public int getAge() {
        return Age;
    }

    public Genero getGenero() {
        return Genero;
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("Nombre", Nombre);
        json.put("Apellido", Apellido);
        json.put("DNI", DNI);
        json.put("Age", Age);
        json.put("Genero", Genero);

        return json;
    }
}