package PersonasEmpleadoUsuario;

import funcionalidad.JSONConvertible;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static GestionCarcelMenus.Funcion.pedirCambios;

public class EmpleadoDB implements JSONConvertible {

    private static final List<Empleado> empleados = new ArrayList<>();

    static {

    }

    private static String leerString(Scanner sc, String mensaje) {
        String input;
        do {
            System.out.print(mensaje);
            input = sc.nextLine();
            if (input.isBlank()) System.out.println("No puede estar vacío.");
        } while (input.isBlank());
        return input;
    }

    public static Empleado crearEmpleadoDesdeConsola(Scanner sc) {

        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            if (nombre.isBlank()) throw new IllegalArgumentException("El nombre no puede estar vacío");

            System.out.print("Apellido: ");
            String apellido = sc.nextLine();
            if (apellido.isBlank()) throw new IllegalArgumentException("El apellido no puede estar vacío");

            System.out.print("DNI: ");
            String dni = sc.nextLine();
            if (dni.isBlank()) throw new IllegalArgumentException("El DNI no puede estar vacío");

            System.out.print("Edad: ");
            int edad = sc.nextInt();
            sc.nextLine();

            System.out.print("Salario: ");
            double salario = sc.nextDouble();
            sc.nextLine();

            System.out.print("Días libres: ");
            int diasLibres = sc.nextInt();
            sc.nextLine();

            // ----- CARGO -----
            System.out.println("Cargos disponibles:");
            for (Cargo c : Cargo.values()) {
                System.out.println("- " + c);
            }
            System.out.print("Elige un cargo: ");
            String cargoStr = sc.nextLine().toUpperCase();
            Cargo cargo = Cargo.valueOf(cargoStr);

            // ----- GENERO -----
            System.out.println("Géneros disponibles:");
            for (Genero g : Genero.values()) {
                System.out.println("- " + g);
            }
            System.out.print("Elige un género: ");
            String generoStr = sc.nextLine().toUpperCase();
            Genero genero = Genero.valueOf(generoStr);
            Empleado empleado = new Empleado(nombre, apellido, dni, edad, salario, diasLibres, cargo, genero, true);
            return empleado;
        } catch(Exception e){
            System.out.println("Error al crear el empleado!" + e.getMessage());
            return null;
        }

    }


    public static void agregarEmpleado(Empleado emp) {
        if(emp != null){
        empleados.add(emp);
        } else {
            System.out.println("Error: empleado null.");
        }
    }

    public static boolean eliminarEmpleado(int id)
    {
        /// LA BAJA LOGICA HACE QUE NO HAYA QUE ELIMINARLOS EN SI.
        boolean found = false;
        for(Empleado e: empleados)
        {
            if(e.getEmpleadoID() == id)
            {
                e.setActivo(false);
                return found = true;
            }
        }
        return found;

        /*
        for (int i = 0; i < empleados.size(); i++) {
            Empleado e = empleados.get(i);
            if (e.getEmpleadoID() == id) {
                empleados.remove(i);
                return true;
            }
        }
        System.out.println("ERROR: No existe el empleado con el id " + id + ".");
        return false; // no sa encontrau
        */
    }

    public static Empleado buscarEmpleadoPorId(int id) {
        if(empleados.isEmpty()) {
            System.out.println("ERROR: No hay empleados cargados.");
            return null;
        }

        for (Empleado e : empleados) {
            if (e.getEmpleadoID() == id) return e;
        }
        return null;
    }

    public static void mostrarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("ERROR: No hay empleados cargados.");
            return;
        }

        System.out.println("==----------- LISTA DE EMPLEADOS -----------==");
        for (Empleado e : empleados) {
            if(e != null && e.isActivo()){
                System.out.println("==================");
                System.out.println(e.toString());
                System.out.println("==================");
            }
        }
        System.out.println("==-----------....................-----------==");
    }

    public static void mostrarEmpleadosPorCargo(Scanner sc) {
        try {
            System.out.println("Ingrese el cargo a filtrar (ej... COCINERO, BASURERO, RECEPCIONISTA): ");
            String cargoStr = sc.nextLine().toUpperCase();
            Cargo cargo = Enum.valueOf(Cargo.class, cargoStr);

            System.out.println("==----------- LISTA DE EMPLEADOS CON CARGO: " + cargo + " -----------==");
            for (Empleado e : empleados) {
                if (e != null && e.isActivo() && e.getCargo() == cargo) {
                    System.out.println("==================");
                    System.out.println(e.toString());
                    System.out.println("==================");
                }
            }
            System.out.println("==-----------....................-----------==");
        } catch(IllegalArgumentException e) {
            System.out.println("Cargo inválido." + e.getMessage());
        } catch(Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }


    public JSONObject toJSONObject()
    {
        JSONObject json = new JSONObject();
        JSONArray arrayEmpleados = new JSONArray();

        for(Empleado emp : empleados)
        {
            arrayEmpleados.put(emp.toJSONObject());
        }

        json.put("Empleados", arrayEmpleados);
        return json;
    }

    public void fromJSON(JSONObject json) {
        empleados.clear();
        JSONArray array = json.getJSONArray("Empleados");

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            empleados.add(Empleado.fromJSON(obj));
        }
    }

    public static List<Empleado> getEmpleados() {
        return empleados;
    }


    public static Empleado modificarEmpleado(Scanner sc, int id)
    {
        Empleado emp = buscarEmpleadoPorId(id);
        if (emp == null) return null;

        emp.setNombre(pedirCambios(sc, "Nombre", emp.getNombre()));
        emp.setApellido(pedirCambios(sc, "Apellido", emp.getApellido()));
        emp.setDNI(pedirCambios(sc, "DNI", emp.getDNI()));
        emp.setSalario(Double.parseDouble(pedirCambios(sc, "Salario", String.valueOf(emp.getSalario()))));
        emp.setDiasLibres(Integer.parseInt(pedirCambios(sc, "Dias Libres", String.valueOf(emp.getDiasLibres()))));
        /// PEDIR ENUM CARGO (quilombete)
        String cargoStr = pedirCambios(sc, "Rango (ej... COCINERO, BASURERO, RECEPCIONISTA)", emp.getCargo().name());
        try {
            if (!cargoStr.isEmpty()) {
                emp.setCargo(Enum.valueOf(PersonasEmpleadoUsuario.Cargo.class, cargoStr.toUpperCase()));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Rango invalido. Se mantiene el rango actual: " + emp.getCargo());
        }
        /// ACTIVO?
        String activoStr = pedirCambios(sc, "Activo? (true/false)", String.valueOf(emp.isActivo()));
        emp.setActivo(Boolean.parseBoolean(activoStr));

        System.out.println("Empleado actualizado correctamente.\n");
        return emp;
    }

}