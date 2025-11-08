public abstract class Empleado extends Persona{

    private int NextID = 0;
    private final int EmpleadoID;
    private double Salario;
    private int diasLibres;
    protected Cargo cargo;

    public Empleado(String Nombre, String Apellido, String DNI, int age, boolean bajaLogica, double Salario, int diasLibres) {
        super(Nombre, Apellido, DNI, age, bajaLogica);
        EmpleadoID = NextID++;
        this.Salario = Salario;
        this.diasLibres = diasLibres;
    }

}