public class Guardia extends Empleado {

    private boolean activo;
    private String placaPolicial;
    // Constante de clase (final + static)
    private static final Cargo CARGO = Cargo.GUARDIA;

    public Guardia(String Nombre, String Apellido, String DNI, int age, double Salario, int diasLibres, Cargo cargo, boolean activo, String placaPolicial,Guardia guardia,Genero genero) {
        super(Nombre, Apellido, DNI, age, Salario, diasLibres, cargo,genero);
        this.activo = activo;
        this.placaPolicial = placaPolicial;
    }
}
