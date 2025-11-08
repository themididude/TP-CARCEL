public class Empleado extends Persona{

    private int NextID = 0;
    private final int EmpleadoID;
    private double Salario;
    private int diasLibres;

    protected Cargo cargo;

    public Empleado(String Nombre, String Apellido, String DNI, int age, double Salario, int diasLibres, Cargo cargo) {
        super(Nombre, Apellido, DNI, age);
        EmpleadoID = NextID++;
        this.Salario = Salario;
        this.diasLibres = diasLibres;
        this.cargo = cargo;
    }

}