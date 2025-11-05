public class Guardia extends Empleado implements SeguridadReport {

    private boolean Activo;
    private String placaPolicial;


    public Guardia(String Nombre, String Apellido, String DNI, int age, boolean bajaLogica, double Salario, int diasLibres, boolean activo, String placaPolicial) {
        super(Nombre, Apellido, DNI, age, bajaLogica, Salario, diasLibres);
        this.Activo = activo;
        this.placaPolicial = placaPolicial;
    }



    ///===--- METHODS ---===///
    @Override
    public String generarReporte() {
        return "El Guardia" + getApellido() + " genera un reporte de un incidente Carcelario.";
    }

    @Override
    public void switchActivo() {


    }
}