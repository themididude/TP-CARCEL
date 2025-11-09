public abstract class Persona {

    private String Nombre;
    private String Apellido;
    private String DNI;
    private int Age;
    private Genero Genero;

    public Persona(String Nombre, String Apellido, String DNI, int age, Genero genero) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.DNI = DNI;
        this.Age = age;
        this.Genero = genero;
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
    public Genero getGenero() { return Genero;}
}