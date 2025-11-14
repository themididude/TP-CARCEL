package GestionCarcelMenus;
import PersonasEmpleadoUsuario.Guardia;
import PersonasEmpleadoUsuario.Recluso;
import funcionalidad.JSONConvertible;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PabellonDB implements JSONConvertible {

    private  ArrayList<Pabellon> p= new ArrayList<>();


    //constructores
    public PabellonDB() {
    }

    public PabellonDB(JSONObject json) {
        JSONArray pabellonesArray = json.optJSONArray("Pabellones");
        if (pabellonesArray != null) {
            for (int i = 0; i < pabellonesArray.length(); i++) {
                JSONObject pabJson = pabellonesArray.getJSONObject(i);
                Pabellon pab = new Pabellon(pabJson);
                this.agregarPabellon(pab);
            }
        }
    }


    //get/set
    public ArrayList<Pabellon> getP() {
        return p;
    }

    public void setP(ArrayList<Pabellon> p) {this.p = p;
    }

    /// metodos
    public void buscarPorSector(Sector sector) {
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


    public  Pabellon buscarPabellon (String nom){
        int i;
        for (i=0;i<p.size();i++) {
            if(nom.equals(p.get(i).getClave())){
                return p.get(i);
            }
        }
        System.out.println("No existe el pabellon "+nom);
        return null;
    }

    //metodos de prisionero
    public Recluso buscarReclusoDB(int id) {
        int i;
        for (i=0;i<p.size();i++) {
            if(p.get(i).getPresos().containsKey(id)){
                return p.get(i).buscarRecluso(id);
            }
        }
        System.out.println("Prisionero no existe");
        return null;
    }
    public  Pabellon getPabellonDelRecluso(int id)
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


    //metodos de guardia
    public Guardia buscarGuardiaDB(String id) {
        for (Pabellon pab : p) {
            Guardia g = pab.buscarGuardia(id);
            if (g != null) {
                return g;
            }
        }

        System.out.println("Guardia no existe");
        return null;
    }

    public Pabellon getPabellonDelGuardia(String id) {
        for (Pabellon pab : p) {
            for (Guardia g : pab.getGuardias()) {
                if (g.getPlacaPolicial().equals(id)) {
                    return pab; // ← este es el pabellón del guardia
                }
            }
        }

        System.out.println("Guardia no existe");
        return null;
    }



    public String toString() {
        return "Pabellones{" + "Pabellones=" + p + '}';
    }

    //toJson
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



