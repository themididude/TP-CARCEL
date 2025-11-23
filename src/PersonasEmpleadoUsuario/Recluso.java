package PersonasEmpleadoUsuario;

import funcionalidad.JSONConvertible;
import funcionalidad.Activable;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Recluso extends Persona implements JSONConvertible, Activable {

    private static int nextId = 0;
    private int PrisonerID;
    private int sentencia;
    private final static int MAX_VISITAS_MENSUALES=3;
    private int visitasRestantes;
    private String ultimoMesVisita;

    private boolean Activo; ///baja logica

    public Recluso(String nombre, String apellido, String DNI, int age, int sentencia, Genero genero, Boolean Activo) {
        super(nombre, apellido, DNI, age, genero);
        this.PrisonerID = nextId++;   // ASÍ NO CAMBIAN LOS ID VIEJOS
        this.sentencia = sentencia;
        this.visitasRestantes = MAX_VISITAS_MENSUALES;
        this.ultimoMesVisita = obtenerMesActual();
        this.Activo = Activo;
    }

    public Recluso(JSONObject json) {
        super(json);
        // Recuperamos el ID original del JSON
        this.PrisonerID = json.getInt("prisonerID");
        // Actualizamos nextId solo si este ID es mayor
        if (this.PrisonerID >= nextId) {
            nextId = this.PrisonerID + 1;
        }

        this.sentencia = json.getInt("sentencia");
        this.visitasRestantes = json.optInt("visitasRestantes", MAX_VISITAS_MENSUALES);
        this.ultimoMesVisita = json.optString("ultimoMesVisita", obtenerMesActual());
        this.Activo = json.getBoolean("Activo");
    }

    ///===---       get set           ---===///
    public int getPrisonerID() {
        return PrisonerID;
    }
    public int getSentencia() {return sentencia;}
    public String getUltimoMesVisita() {return ultimoMesVisita;}
    public static int getNextId() {return nextId;}

    @Override
    public boolean isActivo() {
        return Activo;
    }

    @Override
    public void setActivo(boolean Activo) {
    this.Activo = Activo;
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
        json.put("Activo", Activo);
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

    public boolean registrarVisita() {
        if (visitasRestantes > 0) {
            visitasRestantes--;
            System.out.println("Visita registada. Visitas restantes:" + visitasRestantes);
            return true;
        } else {
            System.out.println("AVISO: El recluso" + getNombre() + "no tiene visitas disponibles por el resto del mes.");
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