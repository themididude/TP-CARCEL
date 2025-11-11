package PersonasEmpleadoUsuario;

import funcionalidad.JSONConvertible;
import org.json.JSONObject;

public class Recluso extends Persona implements JSONConvertible {

    private static int nextId = 0;
    private int PrisonerID;
    private int sentencia;
    private int Celda;
    private String causaSimple; ///<--- una descripcion simple de lo que hizo, de forma que salga al consultar el expediente.

    public Recluso(String Nombre, String Apellido, String DNI, int age,  int sentencia,Genero genero, int Celda) {
        super(Nombre, Apellido, DNI, age, genero);
        nextId++;
        this.PrisonerID = nextId;
        this.sentencia = sentencia;
        this.Celda = Celda;
    }
    public Recluso(JSONObject json) {
        super(json);
        nextId++;
        this.PrisonerID = nextId;
        this.sentencia = json.getInt("sentencia");
        this.Celda = json.getInt("Celda");

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
        sb.append("Celda: "+Celda+"\n");

        return sb.toString();
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = super.toJSONObject();
        json.put("prisonerID", this.PrisonerID);
        json.put("sentencia", this.sentencia);
        json.put("Celda", this.Celda);
        return json;
    }

}