package GestionCarcelMenus;
import funcionalidad.JSONConvertible;
import funcionalidad.JsonManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static GestionCarcelMenus.Funcion.pedirCambios;


public class Paperwork implements JSONConvertible {

    private static Paperwork instancia;
    ArrayList<Informe> informes = new ArrayList<>();

    ///construct
    private Paperwork() {
    }
    /// singleton
    public static Paperwork getInstancia() {
        if (instancia == null) {
            instancia = new Paperwork();
        }
        return instancia;
    }

    public JSONObject toJSONObject()
    {
        JSONObject json = new JSONObject();
        JSONArray arrayInformes = new JSONArray();

        for(Informe inf : informes)
        {
            arrayInformes.put(inf.toJSONObject());
        }

        json.put("Informes", arrayInformes);
        return json;
    }

    public void fromJSON(JSONObject json) {
        informes.clear();
        JSONArray array = json.getJSONArray("Informes");

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            informes.add(Informe.fromJSON(obj));
        }
    }

    ///===-------------------- OPERACIONES --------------------===///
    public static Informe generarInforme(Scanner sc, Informe.Tipo tipo)
    {
        String Asunto;
        String Descripcion;
        String Fecha;

        System.out.println("Ingrese una fecha para el informe! (DIA/MES/AÑO) FORMAT\n");
        Fecha = sc.nextLine();

        System.out.println("Ingrese un Asunto: ");
        Asunto = sc.nextLine();

        System.out.println("Ingrese una descripcion: ");
        Descripcion = sc.nextLine();

        Informe informe = new Informe(Asunto,Descripcion,Fecha,tipo);
        return informe;
    }

    public void agregarInforme(Informe informe) {
        if (informe != null) {
            informes.add(informe);
            System.out.println("Informe agregado con Exito!");
        } else {
            System.out.println("Error: El informe a agregar no puede ser nulo!");
        }
    }

    public void mostrarInforme(Informe inf)
    {
        System.out.println("\n--- INFORME ENCONTRADO ---");
        System.out.println("ID: " + inf.getInformeID());
        System.out.println("Tipo: " + inf.getTipo());
        System.out.println("Asunto: " + inf.getAsunto());
        System.out.println("Fecha: " + inf.getFecha());
        System.out.println("Descripción: " + inf.getDescripcion());
        System.out.println("ESTADO:" +inf.isActivo());
        System.out.println("--------------------------");

    }
    public void mostrarTodosLosInformes() {
        if (informes.isEmpty()) {
            System.out.println("ERROR: No hay informes!");
            return;
        }

        System.out.println("\n--- LISTADO DE TODOS LOS INFORMES (" + informes.size() + ") ---");
        for (int i = 0; i < informes.size(); i++) {
            Informe inf = informes.get(i);
            if(inf.isActivo()) {
                System.out.println("-------------------------------------");
                System.out.println("ID: " + (i + 1));
                System.out.println("Tipo: " + inf.getTipo());
                System.out.println("Asunto: " + inf.getAsunto());
                System.out.println("Fecha: " + inf.getFecha());
                System.out.println("Descripción: " + inf.getDescripcion().substring(0, Math.min(inf.getDescripcion().length(), 50)) + "...");
            }
        }
        System.out.println("-------------------------------------");
    }

    public void mostrarInformesPorTipo(Informe.Tipo tipo) {
        if (tipo == null) {
            System.out.println("ERROR: tipo especificado es Nulo!");
            return;
        }

        ArrayList<Informe> informesFiltrados = new ArrayList<>();
        for (Informe inf : informes) {
            if (inf.getTipo().equals(tipo) && inf.isActivo()) {
                informesFiltrados.add(inf);
            }
        }

        if (informesFiltrados.isEmpty()) {
            System.out.println("No se encontraron informes del tipo  " + tipo + ".");
            return;
        }

        System.out.println("\n--- INFORMES DE TIPO: " + tipo + " (" + informesFiltrados.size() + ") ---");
        for (Informe inf : informesFiltrados) {
            System.out.println("-------------------------------------");
            System.out.println("Asunto: " + inf.getAsunto());
            System.out.println("Fecha: " + inf.getFecha());
            System.out.println("Descripción: " + inf.getDescripcion().substring(0, Math.min(inf.getDescripcion().length(), 50)) + "...");
        }
        System.out.println("-------------------------------------");
    }

    public Informe buscarInforme(int id) {
        for (Informe inf : informes) {
            if (inf != null && inf.getInformeID() == id) {
                return inf;
            }
        }
        System.out.println("No se encontró ningún informe con ID " + id + ".");
        return null;
    }

    public boolean eliminarInforme(int id) {
        for (Informe inf : informes) {
            if (inf != null && inf.getInformeID() == id) {
                try {
                    inf.setActivo(false);
                    System.out.println("Informe con ID " + id + " ha sido eliminado.");
                    /// JSON save
                    JsonManager.guardarLista("informes.json", Paperwork.getInstancia().informes);
                    return true;
                } catch(IOException e){
                    System.out.println("No se pudo guardar el cambio en informes.json");
                    return false;
                } catch(JSONException e){
                    System.out.println("Error al guardar en JSON");
                    return false;
                }
            }
        }
        System.out.println("No se encontró ningún informe con ID " + id + ".");
        return false;
    }

    public Informe modificarInforme(Scanner sc, int id)
    {
        Informe inf = buscarInforme(id);
        if (inf == null) return null;

        /// PEDIR ENUM TIPO (quilombete)
        String tipoStr = pedirCambios(sc, "Tipo (GENERAL, POLCIAL, FINANCIERO...)", inf.getTipo().name());
        try {
            if (!tipoStr.isEmpty()) {
                inf.setTipo(Enum.valueOf(Informe.Tipo.class, tipoStr.toUpperCase()));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Rango invalido. Se mantiene el rango actual: " + inf.getTipo());
        }

        inf.setAsunto(pedirCambios(sc, "Asunto", inf.getAsunto()));
        inf.setDescripcion(pedirCambios(sc, "Descripcion", inf.getDescripcion()));
        inf.setFecha(pedirCambios(sc, "Fecha (formato 00/00/00)", inf.getFecha()));

        String activoStr = pedirCambios(sc, "Activo? (true/false)", String.valueOf(inf.isActivo()));
        inf.setActivo(Boolean.parseBoolean(activoStr));

        System.out.println("Informe actualizado correctamente.\n");
        return inf;
    }
}


