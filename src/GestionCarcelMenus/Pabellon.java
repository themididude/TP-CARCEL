package GestionCarcelMenus;

import PersonasEmpleadoUsuario.Genero;
import PersonasEmpleadoUsuario.Guardia;
import PersonasEmpleadoUsuario.Recluso;
import funcionalidad.JSONConvertible;
import funcionalidad.JsonManager;
import funcionalidad.WrongGenderException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static GestionCarcelMenus.Funcion.pedirCambios;

public class Pabellon implements JSONConvertible {

    private Sector sector;
    private Map<Integer, Recluso> presos;
    private HashSet<Guardia> guardias;
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
        this.guardias = manager.crearHashSet();
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
        this.guardias = new HashSet<>();
        JSONArray guardiasArray = json.getJSONArray("Guardias");
        for (int i = 0; i < guardiasArray.length(); i++) {
            this.guardias.add(new Guardia(guardiasArray.getJSONObject(i)));
        }
    }

    ///===--------------------------- GETTERS / SETTERS ---------------------------===///
    public Map<Integer, Recluso> getPresos() { return this.presos; }
    public void setPresos(Map<Integer, Recluso> presos) { this.presos = presos; }
    public HashSet<Guardia> getGuardias() { return guardias; }
    public void setGuardias(HashSet<Guardia> guardias) { this.guardias = guardias; }
    public Sector getSector() { return sector; }
    public String getClave (){
        return "" + id + sector;
    }
    public Genero getGenero() {
        return genero;
    }

    ///===--------------------------- METODOS ---------------------------===///

    ///lil helper ----->

    ///==================================================================///
    ///===------------- AGREGAR ------------===///
    public void agregarRecluso(Recluso recluso) throws WrongGenderException {
        if (recluso == null) {
            throw new IllegalArgumentException("Recluso nulo");
        }

        Genero generoRecluso = recluso.getGenero();
        if (generoRecluso == null) {
            throw new IllegalArgumentException("Genero del recluso nulo");
        }

        if (generoRecluso == this.genero || generoRecluso == Genero.OTRO) {
            this.presos.put(recluso.getPrisonerID(), recluso);
            System.out.println("Recluso encarcelado\n");
        } else {
            throw new WrongGenderException("El recluso no es " + this.genero + "\n");
        }
    }
    public void agregarGuardia(Guardia guardia) {
        this.guardias.add(guardia);
    }
    ///==================================================================///
    ///===------------ MODIFICAR ------------===///
    public Recluso modificarRecluso(Scanner sc, Recluso recluso) {
        System.out.println("────────── EDITAR RECLUSO ──────────");
        System.out.println("ID: " + recluso.getPrisonerID());

        /// press enter para mantener !!!!!
        recluso.setNombre(pedirCambios(sc, "Nombre", recluso.getNombre()));
        recluso.setApellido(pedirCambios(sc, "Apellido", recluso.getApellido()));
        recluso.setDNI(pedirCambios(sc, "DNI", recluso.getDNI()));

        // validando edad. . . ..
        String edadStr = pedirCambios(sc, "Edad", String.valueOf(recluso.getAge()));
        try {
            recluso.setAge(Integer.parseInt(edadStr));
        } catch (NumberFormatException ignored) {            // si no es num, mantenemos nomas
        }

        // sentencia en anios, validar num
        String sentStr = pedirCambios(sc, "Sentencia (años)", String.valueOf(recluso.getSentencia()));
        try {
            recluso.setSentencia(Integer.parseInt(sentStr));
        } catch (NumberFormatException ignored) {            // lo mismo
        }

        // Activo / baja lógica
        String activoStr = pedirCambios(sc, "Activo (true/false)", String.valueOf(recluso.isActivo()));
        recluso.setActivo(Boolean.parseBoolean(activoStr));

        System.out.println("Recluso actualizado correctamente.\n");
        return recluso;
    }

    public Guardia modificarGuardia(Scanner sc, Guardia guardia) {
        System.out.println("────────── EDITAR GUARDIA ──────────");
        System.out.println("Placa: " + guardia.getPlacaPolicial());

        /// basic stuffffffff
        guardia.setNombre(pedirCambios(sc, "Nombre", guardia.getNombre()));
        guardia.setApellido(pedirCambios(sc, "Apellido", guardia.getApellido()));
        guardia.setDNI(pedirCambios(sc, "DNI", guardia.getDNI()));

        /// edad
        String edadStr = pedirCambios(sc, "Edad", String.valueOf(guardia.getAge()));
        try {
            guardia.setAge(Integer.parseInt(edadStr));
        } catch (NumberFormatException ignored) {
            /// lo mismo de recluso, si no es num, mantenemos
        }

        // Salario (double)
        String salarioStr = pedirCambios(sc, "Salario", String.valueOf(guardia.getSalario()));
        try {
            guardia.setSalario(Double.parseDouble(salarioStr));
        } catch (NumberFormatException ignored) {
            /// mantenemos . . . . .
        }

        /// diaslibres
        String diasStr = pedirCambios(sc, "Dias libres", String.valueOf(guardia.getDiasLibres()));
        try {
            guardia.setDiasLibres(Integer.parseInt(diasStr));
        } catch (NumberFormatException ignored) {
            // mantener valor actual
        }

        /// placa
        guardia.setPlacaPolicial(pedirCambios(sc, "Placa policial", guardia.getPlacaPolicial()));

        // rango (del enum cargou)
        String rangoStr = pedirCambios(sc, "Rango (ej: GUARDIA, OFICIAL...)", guardia.getRango().name());
        try {
            if (!rangoStr.isEmpty()) {
                guardia.setRango(Enum.valueOf(PersonasEmpleadoUsuario.Cargo.class, rangoStr.toUpperCase()));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Rango invalido. Se mantiene el rango actual: " + guardia.getRango());
        }

        // activo?
        String activoStr = pedirCambios(sc, "Activo (true/false)", String.valueOf(guardia.isActivo()));
        guardia.setActivo(Boolean.parseBoolean(activoStr));

        System.out.println("Guardia actualizado correctamente.\n");
        return guardia;
    }

    public void moverGuardia(Guardia guardia, Pabellon otroPabellon) {
        realRemove(guardia);
        otroPabellon.agregarGuardia(guardia);
    }

    ///==================================================================///
    ///===------------ MOSTRAR ------------===///

    public void mostrarReclusos() {
        ColeccionManager<Object, Integer, Recluso> manager = new ColeccionManager<>();
        manager.mostrarMapa(this.presos);
    }

    public void mostrarGuardias() {
        ColeccionManager<Guardia, Object, Object> manager = new ColeccionManager<>();
        manager.mostrarHashSet(this.guardias);
    }

    public void moverRecluso(Recluso recluso, Pabellon otroPabellon) {
        try {
            quitarRecluso(recluso);
            otroPabellon.agregarRecluso(recluso);
        } catch (WrongGenderException e) {
            System.out.println(e.getMessage());
        }
    }

    ///==================================================================///
    ///===------------ QUITAR ------------===///

    public void quitarRecluso(Recluso recluso) {

        recluso.setActivo(false); ///<- baja logica/soft delete
    }

    public void quitarGuardia(Guardia guardia) {
        guardia.setActivo(false);
    }

    public void realRemove(Guardia guardia)
    {
        guardias.remove(guardia);
    }

    ///==================================================================///
    ///===------------ BUSQUEDA ------------===///

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
        System.out.println("Guardia no encontrado en este pabellon");
        return null;
    }
    ///==================================================================///

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
