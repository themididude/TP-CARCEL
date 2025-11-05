public class Limpieza extends Empleado {

    private Sector sector;

    public Limpieza(String Nombre, String Apellido, String DNI, int age, boolean bajaLogica, double Salario, int diasLibres, Sector sector) {
        super(Nombre, Apellido, DNI, age, bajaLogica, Salario, diasLibres);
        this.sector = sector;
    }

    ///===--- GETTERS Y SETTERS ---===///
    public Sector getSector() {
        return sector;
    }
    public void setSector(Sector sector) {
        this.sector = sector;
    }


    ///===--- METODOS ---===///
}
