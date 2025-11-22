package PersonasEmpleadoUsuario;

import GestionCarcelMenus.Informe;
import org.json.JSONObject;

public class Empleado extends Persona {

    private static int nextID = 1;
    private int empleadoID;
    private double salario;
    private int diasLibres;
    protected Cargo cargo;
    /// La baja logica en este TP es en realidad la forma de "desactivar" un elemento cuando lo borramos. No deberiamos estar haciendo
    /// list.remove(item), sino que simplemente desactivarlo y que solamente se muestren los elementos activos.
    private boolean Activo;

    // constructor normal
    public Empleado(String nombre, String apellido, String DNI, int age,
                    double salario, int diasLibres, Cargo cargo, Genero genero, boolean Activo) {

        super(nombre, apellido, DNI, age, genero);
        this.empleadoID = nextID++;
        this.salario = salario;
        this.diasLibres = diasLibres;
        this.cargo = cargo;
        this.Activo = Activo;
    }
    public Empleado(JSONObject json) {
        super(json);
        if (json.has("EmpleadoID")) {
            this.empleadoID = json.getInt("EmpleadoID");

            if (empleadoID >= nextID) {
                nextID = empleadoID + 1;
            }
        }
        // si no tiene id generamos uno
        else {
            this.empleadoID = nextID++;
        }

        this.salario = json.getDouble("Salario");
        this.diasLibres = json.getInt("diasLibres");
        this.cargo = Cargo.valueOf(json.getString("Cargo").toUpperCase());
        this.Activo = json.getBoolean("Activo");
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = super.toJSONObject();

        json.put("EmpleadoID", empleadoID);
        json.put("Salario", salario);
        json.put("diasLibres", diasLibres);
        json.put("Cargo", cargo.name());
        json.put("Activo", Activo);

        return json;
    }

    public static Empleado fromJSON(JSONObject json) {
        return new Empleado(json);
    }

    // getters
    public int getEmpleadoID() { return empleadoID; }
    public double getSalario() { return salario; }
    public int getDiasLibres() { return diasLibres; }
    public Cargo getCargo() { return cargo; }
    public boolean isActivo() {return Activo;}

    //setters
    public void setActivo(boolean activo) {Activo = activo;}
    public void setSalario(double salario) {this.salario = salario;}
    public void setDiasLibres(int diasLibres) {this.diasLibres = diasLibres;}

    @Override
    public String toString() {
        return String.format(
                "Empleado ID: %d\n" +
                        "Nombre: %s %s\n" +
                        "DNI: %s\n" +
                        "Edad: %d años\n" +
                        "Cargo: %s\n" +
                        "Salario: $%.2f\n" +
                        "Días libres: %d\n" +
                        "Activo: %s\n",
                empleadoID,
                getNombre(),
                getApellido(),
                getDNI(),
                getAge(),
                cargo.name(),
                salario,
                diasLibres,
                Activo ? "Sí" : "No"
        );
    }
}
