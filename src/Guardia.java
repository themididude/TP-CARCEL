public class Guardia extends Empleado {

    private boolean activo;
    private String placaPolicial;
    private static RangoPolicial rango;

    // Constante de clase (final + static)
    private static final Cargo CARGO = Cargo.GUARDIA;

    public Guardia(String nombre, String apellido, String DNI, int edad, boolean bajaLogica,
                   double salario, int diasLibres, boolean activo, String placaPolicial) {

        super(nombre, apellido, DNI, edad, bajaLogica, salario, diasLibres);
        this.activo = activo;
        this.placaPolicial = placaPolicial;

        // si Empleado tiene un atributo cargo, lo asignas
        super.cargo = CARGO;
    }
}
