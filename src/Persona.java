public abstract class Persona {

    private String Nombre;
    private String Apellido;
    private String DNI;
    private int Age;
    boolean BajaLogica;

    public Persona(String Nombre, String Apellido, String DNI, int age, boolean bajaLogica) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.DNI = DNI;
        this.Age = age;
        this.BajaLogica = bajaLogica;
    }

    /// get and set
    public String getNombre() {
        return Nombre;
    }
    public String getApellido()
    {
        return Apellido;
    }
    public String getDNI() {
        return DNI;
    }
    public int getAge() {
        return Age;
    }
    public boolean isBajaLogica() {
        return BajaLogica;
    }
    ///===---                         ---===///
    public void setBajaLogica(boolean bajaLogica) {
        this.BajaLogica = bajaLogica;
    }
}