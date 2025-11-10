import org.json.JSONObject;

public class Empleado extends Persona{

    private int NextID = 0;
    private int EmpleadoID;
    private double Salario;
    private int diasLibres;

    protected Cargo cargo;

    public Empleado(String Nombre, String Apellido, String DNI, int age, double Salario, int diasLibres, Cargo cargo, Genero genero,int EmpleadoID) {
        super(Nombre, Apellido, DNI, age, genero);
        EmpleadoID = NextID++;
        this.Salario = Salario;
        this.diasLibres = diasLibres;
        this.cargo = cargo;
    }

    public Empleado (JSONObject json) {
        super(json);
        NextID = json.getInt("NextID");
        this.Salario = json.getDouble("Salario");
        this.diasLibres = json.getInt("diasLibres");
        this.EmpleadoID = json.getInt("EmpleadoID");
    }

    public Empleado(String nombre, String apellido, String dni, int age, double salario, int diasLibres, Genero genero) {
        super(nombre,apellido,dni,age, genero);
        EmpleadoID = NextID++;
        this.Salario = salario;
        this.diasLibres = diasLibres;
    }


    public void aumentarSalario(double Salario, double porcentaje) {
        this.Salario = Salario+Salario*porcentaje;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = super.toJSONObject();

        json.put("NextID", NextID );
        json.put("Salario", Salario);
        json.put("diasLibres", diasLibres);
        json.put("EmpleadoID", EmpleadoID);
        return json;
    }
}