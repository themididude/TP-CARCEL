public class Sargento extends Seguridad implements Armado {

    public Sargento(String Nombre, String Apellido, String DNI, int age, boolean bajaLogica, double Salario, int diasLibres, boolean activo, String placaPolicial) {
        super(Nombre, Apellido, DNI, age, bajaLogica, Salario, diasLibres, activo, placaPolicial);
    }

    ///===--- methods ---===///
    @Override
    public String Apuntar() {
        return "El sargento" + getApellido() + " apunta su Escopeta.";
    }

    @Override
    public String Disparar() {
        return "El sargento" + getApellido() + "Dispara su Escopeta. BOOM";
    }

    @Override
    public String Recargar() {
        return "CHK-CHK. El sargento" + getApellido() + "Recarga." ;
    }

    @Override
    public String Seguro() {
        return "CLICK. " + getApellido() + " pone el seguro.";
    }
}