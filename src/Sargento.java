public class Sargento extends Seguridad implements Reporteable {

    public Sargento(String Nombre, String Apellido, String DNI, int age, boolean bajaLogica, double Salario, int diasLibres, boolean activo, String placaPolicial) {
        super(Nombre, Apellido, DNI, age, bajaLogica, Salario, diasLibres, activo, placaPolicial);
    }

    ///===--- methods ---===///
    @Override
    public String generarReporte() {
        return "El sargento" + getApellido() + " genera un reporte del Cuerpo de Seguridad.";
    }

}