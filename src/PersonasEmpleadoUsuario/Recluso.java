package PersonasEmpleadoUsuario;

import funcionalidad.JSONConvertible;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Recluso extends Persona implements JSONConvertible {

    private static int nextId = 0;
    private int PrisonerID;
    private int sentencia;
    private final static int MAX_VISITAS_MENSUALES=3;
    private int visitasRestantes;
    private String ultimoMesVisita;



    public Recluso(String Nombre, String Apellido, String DNI, int age,  int sentencia,Genero genero) {
        super(Nombre, Apellido, DNI, age, genero);
        nextId++;
        this.PrisonerID = nextId;
        this.sentencia = sentencia;
        this.visitasRestantes = MAX_VISITAS_MENSUALES;
        this.ultimoMesVisita = obtenerMesActual();
    }
    public Recluso(JSONObject json) {
        super(json);
        nextId++;
        this.PrisonerID = nextId;
        this.sentencia = json.getInt("sentencia");
         this.visitasRestantes = json.optInt("visitasRestantes", MAX_VISITAS_MENSUALES);
         this.ultimoMesVisita = json.optString("ultimoMesVisita", obtenerMesActual());
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
        if (this == o) return true;
        if (!(o instanceof Recluso)) return false;
        Recluso recluso = (Recluso) o;
        return PrisonerID == recluso.PrisonerID;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(PrisonerID);
    }


    @Override
    public JSONObject toJSONObject() {
        JSONObject json = super.toJSONObject();

        json.put("prisonerID", PrisonerID);
        json.put("sentencia", sentencia);
        // Atributos de visitas
        json.put("visitasRestantes", visitasRestantes);
        json.put("ultimoMesVisita", ultimoMesVisita);
        return json;
    }


    ///  METODOS VISITA ///

    public int getVisitasRestantes(){
      actualizarVisitaSiesNuevoMes();
      return visitasRestantes;
    }

    public boolean registrarVisita(){
    if(visitasRestantes  > 0){
    visitasRestantes--;
        System.out.println("Visita registada. Visitas restantes:" +visitasRestantes);
        return true;
    }else{
        System.out.println("AVISO: El recluso" + getNombre() + "no tiene visitas disponibles por el resto del mes.") ;
        return false;
    }
    }

    private void actualizarVisitaSiesNuevoMes(){
    String mesActual = obtenerMesActual();
    if(!mesActual.equals(this.ultimoMesVisita)){
        this.visitasRestantes = MAX_VISITAS_MENSUALES;
        this.ultimoMesVisita = mesActual;
        System.out.println("Visitas mensuales reiniciadas para "+ getNombre());
    }
    }

    private String obtenerMesActual(){
                       return DateTimeFormatter.ofPattern("MM/yyyy").format(LocalDateTime.now());
    }

    ////////////////////////////////



}