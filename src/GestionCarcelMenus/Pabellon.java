package GestionCarcelMenus;

import PersonasEmpleadoUsuario.Genero;
import PersonasEmpleadoUsuario.Guardia;
import PersonasEmpleadoUsuario.Recluso;
import funcionalidad.JSONConvertible;
import funcionalidad.JsonManager;
import funcionalidad.WrongGenderException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class Pabellon implements JSONConvertible {

    private Sector sector;
    private Map<Integer, Recluso> presos;
    private LinkedHashSet<Guardia> guardias;
    private Genero genero;
    private int id;

    // Contador independiente por sector
    private static EnumMap<Sector, Integer> contadoresPorSector = new EnumMap<>(Sector.class);

    static {
        for (Sector s : Sector.values()) {
            contadoresPorSector.put(s, 1); // Inicializamos todos los contadores en 1
        }
    }

    // Constructores
    public Pabellon(Sector sector, Genero genero) {
        ColeccionManager<Guardia, Integer, Recluso> manager = new ColeccionManager<>();
        this.guardias = manager.crearLinkedHashSet();
        this.presos = manager.crearMapa();
        this.sector = sector;
        this.genero = genero;

        // Asignar id según contador del sector
        this.id = contadoresPorSector.get(sector);
        contadoresPorSector.put(sector, this.id + 1); // Incrementar contador
    }

    public Pabellon(JSONObject json) {
        this.sector = json.getEnum(Sector.class, "Sector");
        this.genero = json.getEnum(Genero.class, "Genero");

        // Leer ID desde JSON si existe, sino usar contador
        if (json.has("id")) {
            this.id = json.getInt("id");
            // Asegurarnos de que el contador por sector no sea menor que el id cargado
            contadoresPorSector.put(this.sector,
                    Math.max(contadoresPorSector.get(this.sector), this.id + 1));
        } else {
            // Asignar id según contador del sector
            this.id = contadoresPorSector.get(this.sector);
            contadoresPorSector.put(this.sector, this.id + 1);
        }

        // Inicializar presos
        this.presos = new HashMap<>();
        JSONObject presosJson = json.getJSONObject("Presos");
        for (String key : presosJson.keySet()) {
            Integer id = Integer.parseInt(key);
            Recluso r = new Recluso(presosJson.getJSONObject(key));
            this.presos.put(id, r);
        }

        // Inicializar guardias
        this.guardias = new LinkedHashSet<>();
        JSONArray guardiasArray = json.getJSONArray("Guardias");
        for (int i = 0; i < guardiasArray.length(); i++) {
            this.guardias.add(new Guardia(guardiasArray.getJSONObject(i)));
        }
    }

    // Getters y setters
    public Map<Integer, Recluso> getPresos() { return this.presos; }
    public void setPresos(Map<Integer, Recluso> presos) { this.presos = presos; }

    public LinkedHashSet<Guardia> getGuardias() { return guardias; }
    public void setGuardias(LinkedHashSet<Guardia> guardias) { this.guardias = guardias; }

    public Sector getSector() { return sector; }

    public String getClave (){
        return "" + id + sector;
    }

    public Genero getGenero() {
        return genero;
    }

    // Métodos
    public void mostrarReclusos() {
        ColeccionManager<Object, Integer, Recluso> manager = new ColeccionManager<>();
        manager.mostrarMapa(this.presos);
    }

    public void mostrarGuardias() {
        ColeccionManager<Guardia, Object, Object> manager = new ColeccionManager<>();
        manager.mostrarLinkedHashSet(this.guardias);
    }

    public void agregarRecluso(Recluso recluso) throws WrongGenderException {
        if(recluso.getGenero().equals(this.genero) || recluso.getGenero().equals(Genero.OTRO)) {
            this.presos.put(recluso.getPrisonerID(), recluso);
            System.out.println("Recluso encarcelado\n");
        } else {
            throw new WrongGenderException("El recluso no es "+this.genero+"\n");
        }
    }

    public void agregarGuardia(Guardia guardia) {
        this.guardias.add(guardia);
    }

    public void quitarRecluso(Recluso recluso) {
        this.presos.remove(recluso.getPrisonerID());
    }

    public void quitarGuardia(Guardia guardia) {
        this.guardias.remove(guardia);
    }

    public void moverRecluso(Recluso recluso, Pabellon otroPabellon) {
        quitarRecluso(recluso);

        try {
            otroPabellon.agregarRecluso(recluso);
        } catch (WrongGenderException e) {
            System.out.println(e.getMessage());
        }
    }

    public void moverGuardia(Guardia guardia, Pabellon otroPabellon) {
        quitarGuardia(guardia);
        otroPabellon.agregarGuardia(guardia);
    }

    public Recluso buscarRecluso(int id) {
        if (this.presos.containsKey(id)) {
            return this.presos.get(id);
        } else {
            System.out.println("Recluso no encontrado en este pabellon\n");
            return null;
        }
    }

    public Guardia buscarGuardia(String id) {
        for (Guardia g : guardias) {
            if (g.getPlacaPolicial().equals(id)) {
                return g;
            }
        }
        System.out.println("Guardia no encontrado en este pabellón");
        return null;
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
        json.put("Genero", genero);
        json.put("Sector", sector);

        // Guardamos el id del pabellón
        json.put("id", id);

        return json;
    }

    @Override
    public String toString() {
        return "Pabellon de " + genero + "S " + id + sector;
    }
}
