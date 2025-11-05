public class Guardia extends Seguridad implements Reporteable {

    public Guardia(String Nombre, String Apellido, String DNI, int age, boolean bajaLogica, double Salario, int diasLibres, boolean activo, String placaPolicial) {
        super(Nombre, Apellido, DNI, age, bajaLogica, Salario, diasLibres, activo, placaPolicial);
    }

    ///===--- methods ---===///
    @Override
    public String generarReporte() {
        return "El Guardia" + getApellido() + " genera un reporte de la Seguridad en el patio carcelario.";
    }

}