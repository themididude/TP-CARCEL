package PersonasEmpleadoUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmpleadoDB {

    private static final List<Empleado> empleados = new ArrayList<>();

    static {
        /// para precargar
    }

    public static Empleado crearEmpleadoDesdeConsola(Scanner sc) {

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("DNI: ");
        String dni = sc.nextLine();

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
        return null;
    }

    public static void agregarEmpleado(Empleado emp) {
        empleados.add(emp);
    }

    public static boolean eliminarEmpleado(int id) {
        for (int i = 0; i < empleados.size(); i++) {
            Empleado e = empleados.get(i);
            if (e.getEmpleadoID() == id) {
                empleados.remove(i);
                return true;
            }
        }
        System.out.println("ERROR: No existe el empleado con el id " + id + ".");
        return false; // no sa encontrau
    }

    public static Empleado buscarEmpleadoPorId(int id) {
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
            if(e.isActivo())    System.out.println(e.toString());
        }
        System.out.println("==-----------....................-----------==");

    }
}