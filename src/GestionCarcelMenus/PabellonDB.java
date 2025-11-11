package GestionCarcelMenus;
import PersonasEmpleadoUsuario.Recluso;
import funcionalidad.JSONConvertible;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PabellonDB implements JSONConvertible {

    private static  ArrayList<Pabellon> p= new ArrayList<>();


    //constructores
    public PabellonDB() {
    }

    public PabellonDB(JSONObject json) {
        JSONArray pabellonesArray = json.optJSONArray("Pabellones");
        if (pabellonesArray != null) {
            for (int i = 0; i < pabellonesArray.length(); i++) {
                JSONObject pabJson = pabellonesArray.getJSONObject(i);
                // Suponiendo que Pabellon tiene un constructor desde JSONObject
                Pabellon pab = new Pabellon(pabJson);
                this.agregarPabellon(pab);
            }
        }
    }


    //get/set
    public static ArrayList<Pabellon> getP() {
        return p;
    }

    public static void setP(ArrayList<Pabellon> p) {PabellonDB.p = p;
    }

    /// metodos
    public static void buscarPorSector(Sector sector) {
        int i;
        for (i=0;i<p.size();i++) {
            if (p.get(i).getSector().equals(sector)) {
                System.out.println(p.get(i).toString());
            }
        }
    }

    public void agregarPabellon(Pabellon pabellon) {
        ColeccionManager<Pabellon,Object,Object> cm = new ColeccionManager<>();
        cm.agregarALista(this.p,pabellon);
    }


    public static Pabellon buscarPabellon (String nom){
        int i;
        for (i=0;i<p.size();i++) {
            if(nom.equals(p.get(i).toString())){
                return p.get(i);
            }
        }
        System.out.println("No existe el pabellon con el sector "+nom);
        return null;
    }

    public static Recluso buscarPrisionero(int id) {
        int i;
        for (i=0;i<p.size();i++) {
            if(p.get(i).getPresos().containsKey(id)){
                return p.get(i).buscarRecluso(id);
            }
        }
        System.out.println("Prisionero no existe");
        return null;
    }
    public static Pabellon getPabellonDelPrisionero (int id)
    {
        int i;
        for (i=0;i<p.size();i++) {
            if(p.get(i).getPresos().containsKey(id)){
                return p.get(i);
            }
        }
        System.out.println("Prisionero no existe");
        return null;
    }

    public static void quitarPrisonero(int id) {
        PabellonDB.buscarPrisionero(id);

    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        JSONArray pabellonesArray = new JSONArray();

        if (p != null) {
            for (Pabellon pab : p) {
                pabellonesArray.put(pab.toJSONObject());
            }
        }

        json.put("Pabellones", pabellonesArray);
        return json;
    }

}



