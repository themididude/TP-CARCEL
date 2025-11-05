public class Seguridad extends Empleado{

    private boolean Activo;
    private String placaPolicial;

    public Seguridad(String Nombre, String Apellido, String DNI, int age, boolean bajaLogica, double Salario, int diasLibres, boolean activo, String placaPolicial) {
        super(Nombre, Apellido, DNI, age, bajaLogica, Salario, diasLibres);
        Activo = activo;
        this.placaPolicial = placaPolicial;
    }
}