package GestionCarcelMenus;

import PersonasEmpleadoUsuario.Genero;
import PersonasEmpleadoUsuario.Recluso;
import funcionalidad.JsonManager;

import java.io.IOException;
import java.util.Scanner;

public class Funcion {


    public static void MoverPreso(Scanner sc) {
        System.out.println("Ingrese el id del prisionero");
        int id = sc.nextInt();
        sc.nextLine();

        Pabellon p = Carcel.pdb.getPabellonDelRecluso(id);
        Recluso r = Carcel.pdb.buscarReclusoDB(id);
        System.out.println(r.toString() + "del " + p.toString());

        System.out.println("a que pabellon se traslada?");
        String nom = sc.nextLine();
        Pabellon p2 = Carcel.pdb.buscarPabellon(nom);
        p.moverRecluso(r, p2);

        try {
            JsonManager.guardarLista("Pabellones.json", Carcel.pdb.getP());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    public static Pabellon BuscarPab(Scanner sc) {
        System.out.println("que pabellon desea mostrar?");
        String nom = sc.nextLine();
        return Carcel.pdb.buscarPabellon(nom);
    }

    public static void MostrarPresos(Scanner sc) {
        Pabellon p = Funcion.BuscarPab(sc);
        p.mostrarReclusos();
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();

    }

    public static void MostrarGuardias(Scanner sc) {
        Pabellon p = Funcion.BuscarPab(sc);
        p.mostrarGuardias();
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    public static void MostrarPreso(Scanner sc) {
        System.out.println("Ingrese el id del prisionero que busca");
        int id = sc.nextInt();
        sc.nextLine();
        Pabellon p = Carcel.pdb.getPabellonDelRecluso(id);
        Recluso r = Carcel.pdb.buscarReclusoDB(id);
        System.out.println(r.toString() + "del pabellon " + p.toString());
        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();

    }

    public static void registrarVisita(Scanner sc) {
        System.out.println("--- REGISTRO DE VISITA ---");
        System.out.println("Ingrese el id del prisionero:");
        int id = -1;
        if (sc.hasNextInt()) {
            id = sc.nextInt();
        }
        sc.nextLine();

        if (id == -1) {
            System.out.println("Id de prisionero invalido");
            System.out.println("\n Presiona ENTER para continuar...");
            sc.nextLine();
            return;
        }

        Recluso recluso = Carcel.pdb.buscarReclusoDB(id);
        if (recluso != null) {
            System.out.println("Recluso encontrado: " + recluso.getNombre() + " " + recluso.getApellido() + ".");
            System.out.println("Visitas restantes ANTES de la visita: " + recluso.getVisitasRestantes());

            System.out.println("\n¿Desea registrar la visita? (S/N)");
            String confirmar = sc.nextLine().trim().toUpperCase();
            if (confirmar.equalsIgnoreCase("S")) {
                if (recluso.registrarVisita()) {
                    System.out.println("Visita registrada con exito para el ID: " + id);
                }


                try {
                    JsonManager.guardarLista("Pabellones.json", Carcel.pdb.getP());
                    System.out.println("Cambios de visitas guardados en el sistema (Pabellones.json).");
                } catch (IOException e) {
                    System.out.println("Error al guardar la base de datos de pabellones: " + e.getMessage());
                }

            } else {
                System.out.println("Registro de visita cancelado.");
            }

        } else {
            System.out.println("ERROR: Prisionero con ID " + id + "no encontrado en ningun pabellon");
        }

        System.out.println("\nPresiona ENTER para continuar...");
        sc.nextLine();
    }

    public static void quitarPreso(Scanner sc) {
        System.out.println("Ingrese el id del prisionero");
        int id = sc.nextInt();
        sc.nextLine();
        if (Carcel.pdb.buscarReclusoDB(id) != null) {

            Pabellon p = Carcel.pdb.getPabellonDelRecluso(id);
            Recluso r = Carcel.pdb.buscarReclusoDB(id);

            System.out.println(r.toString() + "del " + p.toString());
            System.out.println("\nSeguro que quiere quitar al prisionero? S/N");
            String confirmar = sc.nextLine().trim().toUpperCase();

            if (confirmar.equalsIgnoreCase("S")) {


                try {
                    p.quitarRecluso(r);
                    JsonManager.guardarLista("Pabellones.json", Carcel.pdb.getP());
                    System.out.println("Prisionero quitado");
                    System.out.println("\nPresiona ENTER para continuar...");
                    sc.nextLine();
                } catch (IOException e) {
                    System.out.println("Error al quitar el prisionero: " + e.getMessage());
                    throw new RuntimeException(e);
                }
                return;
            } else {
                System.out.println("Operacion Cancelada");
                System.out.println("\nPresiona ENTER para continuar...");
                sc.nextLine();
                return;
            }
        } else {
            System.out.println("El prisionero " + id + " no existe");
            return;
        }
    }

    public static void agregarPreso(Scanner sc) {
        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Ingrese DNI: ");
        String dni = sc.nextLine();

        System.out.print("Ingrese edad: ");
        int edad = Integer.parseInt(sc.nextLine());

        System.out.print("Ingrese sentencia (en años): ");
        int sentencia = Integer.parseInt(sc.nextLine());

        System.out.println("Seleccione género:");
        System.out.println("1. MASCULINO");
        System.out.println("2. FEMENINO");
        System.out.println("3. OTRO");

        Genero genero;
        int opcion = Integer.parseInt(sc.nextLine());

        switch (opcion) {
            case 1 -> genero = Genero.HOMBRE;
            case 2 -> genero = Genero.MUJER;
            case 3 -> genero = Genero.OTRO;
            default -> {
                System.out.println("Opción no válida, se asigna 'OTRO'.");
                genero = Genero.OTRO;
            }

        }
        Recluso r = new Recluso(nombre, apellido, dni, edad, sentencia, genero);
        System.out.println("A que Pabellon se agrega el prisionero?");
        String nom =sc.nextLine();
        Pabellon p=Carcel.pdb.buscarPabellon(nom);
        try {
            p.agregarRecluso(r);
            JsonManager.guardarLista("Pabellones.json", Carcel.pdb.getP());
            System.out.println("Prisionero agregado");
        } catch (IOException e) {
            System.out.println("Error al agregar el prisionero: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
