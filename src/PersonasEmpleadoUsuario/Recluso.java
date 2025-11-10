package PersonasEmpleadoUsuario;

import funcionalidad.JSONConvertible;
import org.json.JSONObject;

public class Recluso extends Persona implements JSONConvertible {

    private static int nextId = 0;
    private int PrisonerID;
    private int sentencia;

    public Recluso(String Nombre, String Apellido, String DNI, int age,  int sentencia,Genero genero) {
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
        sb.append("Sentencia: "+sentencia+"\n");

        return sb.toString();
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = super.toJSONObject();
        json.put("prisonerID", this.PrisonerID);
        json.put("sentencia", this.sentencia);
        return json;
    }

}