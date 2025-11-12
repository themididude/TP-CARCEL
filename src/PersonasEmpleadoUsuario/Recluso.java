package PersonasEmpleadoUsuario;

import funcionalidad.JSONConvertible;
import org.json.JSONObject;

public class Recluso extends Persona implements JSONConvertible {

    private static int nextId = 0;
    private int PrisonerID;
    private int sentencia;


    public Recluso(String Nombre, String Apellido, String DNI, int age,  int sentencia,Genero genero, int Celda) {
        super(Nombre, Apellido, DNI, age, genero);
        nextId++;
        this.PrisonerID = nextId;
        this.sentencia = sentencia;
    }
    public Recluso(JSONObject json) {
        super(json);
        nextId++;
        this.PrisonerID = nextId;
        this.sentencia = json.getInt("sentencia");

    }

    public Recluso() {
    }

    ///===---       get set           ---===///
    public int getPrisonerID() {
        return PrisonerID;
    }


    //otros metodos
    public String toString (){
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre y apellido: "+getNombre()+" "+getApellido()+"\n");
        sb.append("DNI: "+getDNI()+"\n");
        sb.append("Prisoner ID: "+PrisonerID+"\n");
        sb.append("Sentencia: "+sentencia+" Años\n");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // Si es el mismo objeto
        if (!(o instanceof Recluso)) return false;  // Si no es de la misma clase
        Recluso recluso = (Recluso) o;
        return PrisonerID == recluso.PrisonerID;  // Compara por ID único
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(PrisonerID);
    }


    @Override
    public JSONObject toJSONObject() {
        JSONObject json = super.toJSONObject();
        json.put("prisonerID", this.PrisonerID);
        json.put("sentencia", this.sentencia);

        return json;
    }

}