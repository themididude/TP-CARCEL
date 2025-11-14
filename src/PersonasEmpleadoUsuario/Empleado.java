package PersonasEmpleadoUsuario;

import org.json.JSONObject;

public class Empleado extends Persona {

    private static int nextID = 1;
    private int empleadoID;
    private double salario;
    private int diasLibres;
    protected Cargo cargo;

    // constructor normal
    public Empleado(String nombre, String apellido, String DNI, int age,
                    double salario, int diasLibres, Cargo cargo, Genero genero) {

        super(nombre, apellido, DNI, age, genero);
        this.empleadoID = nextID++;
        this.salario = salario;
        this.diasLibres = diasLibres;
        this.cargo = cargo;
    }

    // constructor desde JSON
    public Empleado(JSONObject json) {
        super(json);

        // si el JSON trae ID → usarlo
        if (json.has("EmpleadoID")) {
            this.empleadoID = json.getInt("EmpleadoID");

            if (empleadoID >= nextID) {
                nextID = empleadoID + 1;
            }
        }
        // si NO trae ID → generar uno nuevo
        else {
            this.empleadoID = nextID++;
        }

        this.salario = json.getDouble("Salario");
        this.diasLibres = json.getInt("diasLibres");
        this.cargo = Cargo.valueOf(json.getString("Cargo").toUpperCase());
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = super.toJSONObject();

        json.put("EmpleadoID", empleadoID);
        json.put("Salario", salario);
        json.put("diasLibres", diasLibres);
        json.put("Cargo", cargo.name());

        return json;
    }

    // getters
    public int getEmpleadoID() { return empleadoID; }
    public double getSalario() { return salario; }
    public int getDiasLibres() { return diasLibres; }
    public Cargo getCargo() { return cargo; }
}
