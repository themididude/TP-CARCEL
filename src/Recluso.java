public class Recluso extends Persona{

    private int nextID = 0;
    private final int PrisonerID;
    private int sentencia;

    public Recluso(String Nombre, String Apellido, String DNI, int age, boolean BajaLogica, int sentencia) {
        super(Nombre, Apellido, DNI, age, BajaLogica);
        this.PrisonerID = nextID++;
        this.sentencia = sentencia;
    }

    ///===---       get set           ---===///
    public int getPrisonerID() {
        return PrisonerID;
    }
}