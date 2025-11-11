package GestionCarcelMenus;
import PersonasEmpleadoUsuario.Recluso;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Map;

public class PabellonDB {

    private static ArrayList<Pabellon> p;

    public static void buscarPorSector(Sector sector) {
        int i;
        for (i=0;i<p.size();i++) {
            if (p.get(i).getSector().equals(sector)) {
                System.out.println(p.get(i).toString());
            }
        }
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

    public static JSONArray toJSONArray(ArrayList<Pabellon> p) {
        JSONArray jsonArray = new JSONArray();
        for (Pabellon pab : p) {
            jsonArray.put(pab.toJSONObject());
        }
        return jsonArray;
    }
}



