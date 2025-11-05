public class Guardia extends Seguridad implements Armado {

    public Guardia(String Nombre, String Apellido, String DNI, int age, boolean bajaLogica, double Salario, int diasLibres, boolean activo, String placaPolicial) {
        super(Nombre, Apellido, DNI, age, bajaLogica, Salario, diasLibres, activo, placaPolicial);
    }

    ///===--- methods ---===///
    @Override
    public String Apuntar() {
        return "El Oficial " + getApellido() + " apunta su pistola.";
    }

    @Override
    public String Disparar() {
        return "El Oficial " + getApellido() + "dispara su pistola.";
    }

    @Override
    public String Recargar() {
        return "El Oficial " + getApellido() + "recarga su pistola.";
    }

    @Override
    public String Seguro() {
        return "El Oficial" + getApellido() + "Pone el seguro.";
    }
}