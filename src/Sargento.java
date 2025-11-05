public class Sargento extends Empleado implements SeguridadReport {

    private boolean Activo;
    private String placaPolicial;


    public Sargento(String Nombre, String Apellido, String DNI, int age, boolean bajaLogica, double Salario, int diasLibres, boolean activo, String placaPolicial) {
        super(Nombre, Apellido, DNI, age, bajaLogica, Salario, diasLibres);
        this.Activo = activo;
        this.placaPolicial = placaPolicial;
    }

    ///===--- methods ---===///
    @Override
    public String generarReporte() {
        return "El Sargento" + getApellido() + " genera un reporte del Cuerpo de Seguridad, y de un incidente Carcelario.";
    }

    @Override
    public void switchActivo() {
        ///
    }
}