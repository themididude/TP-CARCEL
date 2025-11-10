package GestionCarcelMenus;

import PersonasEmpleadoUsuario.Genero;
import PersonasEmpleadoUsuario.Guardia;
import PersonasEmpleadoUsuario.Recluso;
import funcionalidad.JSONConvertible;
import funcionalidad.JsonManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Pabellon implements JSONConvertible {

    private Sector sector;
    private Map <Integer,Recluso> presos;
    private LinkedList<Guardia> guardias;
    private Genero genero;


    //Constructores
    public Pabellon(Sector sector, Genero genero) {
        ColeccionManager<Guardia,Integer,Recluso> manager = new ColeccionManager<>();
        this.guardias=manager.crearLinkedList();
        this.presos=manager.crearMapa();
        this.sector = sector;
        this.genero = genero;
    }

    public Pabellon(JSONObject json) {

        this.sector = json.getEnum(Sector.class,"GestionCarcelMenus.Sector");
        this.genero = json.getEnum(Genero.class,"PersonasEmpleadoUsuario.Genero");

        this.presos = new HashMap<>();
        JSONObject presosJson = json.getJSONObject("Presos");
        for (String key : presosJson.keySet()) {
            Integer id = Integer.parseInt(key);                     // Claves Integer
            Recluso r = new Recluso(presosJson.getJSONObject(key)); // Crear PersonasEmpleadoUsuario.Recluso desde JSON
            this.presos.put(id, r);
        }

        this.guardias = new LinkedList<>();
        JSONArray guardiasArray = json.getJSONArray("Guardias");
        for (int i = 0; i < guardiasArray.length(); i++) {
            this.guardias.add(new Guardia(guardiasArray.getJSONObject(i))); // Crear PersonasEmpleadoUsuario.Guardia desde JSON
        }
    }



    //getters y setters

    public Map<Integer,Recluso> getPresos() {
        return this.presos;
    }

    public void setPresos(Map<Integer, Recluso> presos) {
        this.presos = presos;
    }

    public LinkedList<Guardia> getGuardias() {

        return guardias;
    }


    //otros metodos
    public void mostrarReclusos (Map<Recluso, Integer> reclusos){
        ColeccionManager <Object,Recluso,Integer> manager = new ColeccionManager<>();
       manager.mostrarMapa(reclusos);
    }
    public void mostrarGuardias (Map<Recluso, Integer> reclusos){
        ColeccionManager <Guardia,Object,Object> manager = new ColeccionManager<>();
        manager.mostrarLinkedList(this.guardias);
    }

    public void agregarRecluso(Recluso recluso) {
        if (recluso.getGenero()!=this.genero){
            System.out.println("Este recluso no es "+ this.genero);
        }
        else{
            this.presos.put(recluso.getPrisonerID(),recluso);
            System.out.println("recluso encarcelado\n");

        }

    }
    public void agregarGuardia(Guardia guardia) {

        this.guardias.add(guardia);
    }

    public void quitarRecluso(Recluso recluso) {
        this.presos.remove(recluso);
    }
    public void quitarGuardia(Guardia guardia) {
        this.guardias.remove(guardia);
    }

    public void moverRecluso(Recluso recluso, Pabellon otroPabellon) {
        quitarRecluso(recluso);
        otroPabellon.agregarRecluso(recluso);
    }


    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();

        json.put("Presos", JsonManager.mapDeObjetosAJSONObjectInt(presos));

        JSONArray guardiasArray = new JSONArray();
        for (Guardia g : guardias) {
            guardiasArray.put(g.toJSONObject());
        }
        json.put("Guardias", guardiasArray);
        json.put("PersonasEmpleadoUsuario.Genero", genero);
        json.put("GestionCarcelMenus.Sector", sector);

        return json;
    }
}

