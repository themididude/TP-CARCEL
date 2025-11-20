package GestionCarcelMenus;

import PersonasEmpleadoUsuario.Cargo;
import PersonasEmpleadoUsuario.Genero;
import PersonasEmpleadoUsuario.Guardia;
import PersonasEmpleadoUsuario.Recluso;
import funcionalidad.JsonManager;
import funcionalidad.WrongGenderException;

import java.io.IOException;
import java.util.Scanner;

public class Funcion {

   /* private static void pause() {
        System.out.println("\nPresione ENTER para continuar...");
        try {
            System.in.read();
        } catch (IOException e) {
        }
    }*/

    public static void ListarPabellonesHombre (Scanner sc){
        int i=0;
        System.out.println("\nPabellones Masculinos:");
        for (Pabellon p :Carcel.pdb.getP()){
            if(p.getGenero().equals(Genero.HOMBRE)){
                System.out.println(p.getClave());
            }
        }
    }
    public static void ListarPabellonesMujer (Scanner sc){
        int i=0;
        System.out.println("\nPabellones Femeninos:");
        for (Pabellon p :Carcel.pdb.getP()){
            if(p.getGenero().equals(Genero.MUJER)){
                System.out.println(p.getClave());
            }
        }
    }

    public static void ListarPabellones (Scanner sc){
        Funcion.ListarPabellonesHombre(sc);
        Funcion.ListarPabellonesMujer(sc);
    }
    public static void MoverPreso(Scanner sc) {
        System.out.println("Ingrese el id del prisionero");
        int id = sc.nextInt();
        sc.nextLine();

        Pabellon p = Carcel.pdb.getPabellonDelRecluso(id);
        Recluso r = Carcel.pdb.buscarReclusoDB(id);
        System.out.println(r.toString() + "Del " + p.toString());

        System.out.println("A que pabellon se traslada?");
        Funcion.ListarPabellones(sc);
        String nom = sc.nextLine();
        Pabellon p2 = Carcel.pdb.buscarPabellon(nom);
        p.moverRecluso(r, p2);

        try {
            JsonManager.guardarLista("Pabellones.json", Carcel.pdb.getP());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Pabellon BuscarPab(Scanner sc) {
        System.out.println("Que Pabellon desea mostrar?");
        Funcion.ListarPabellones(sc);
        String nom = sc.nextLine();
        return Carcel.pdb.buscarPabellon(nom);
    }

    public static void MostrarPresos(Scanner sc) {
        Pabellon p = Funcion.BuscarPab(sc);
        p.mostrarReclusos();


    }

    public static void MostrarGuardias(Scanner sc) {
        Pabellon p = Funcion.BuscarPab(sc);
        p.mostrarGuardias();

    }

    public static void MostrarPreso(Scanner sc) {
        System.out.println("Ingrese el id del prisionero que busca");
        int id = sc.nextInt();
        sc.nextLine();
        Pabellon p = Carcel.pdb.getPabellonDelRecluso(id);
        Recluso r = Carcel.pdb.buscarReclusoDB(id);
        System.out.println(r.toString() + "Del Pabellon " + p.toString());


    }
    public static void mostrarGuardia(Scanner sc) {
        System.out.println("Ingrese la placa policial del guardia");
        String placa = sc.nextLine();
        Pabellon p = Carcel.pdb.getPabellonDelGuardia(placa);
        Guardia g = Carcel.pdb.buscarGuardiaDB(placa);

        System.out.println(g.toString() + "Del Pabellon " + p.toString());

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


    }

    public static void quitarPreso(Scanner sc) {
        System.out.println("Ingrese el id del prisionero");
        int id = sc.nextInt();
        sc.nextLine();
        if (Carcel.pdb.buscarReclusoDB(id) != null) {

            Pabellon p = Carcel.pdb.getPabellonDelRecluso(id);
            Recluso r = Carcel.pdb.buscarReclusoDB(id);

            System.out.println(r.toString() + "Del " + p.toString());
            System.out.println("\nSeguro que quiere quitar al prisionero? S/N");
            String confirmar = sc.nextLine().trim().toUpperCase();

            if (confirmar.equalsIgnoreCase("S")) {


                try {
                    p.quitarRecluso(r);
                    JsonManager.guardarLista("Pabellones.json", Carcel.pdb.getP());
                    System.out.println("Prisionero quitado");

                } catch (IOException e) {
                    System.out.println("Error al quitar el prisionero: " + e.getMessage());
                    throw new RuntimeException(e);
                }
                return;
            } else {
                System.out.println("Operacion Cancelada");

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
        Recluso r = new Recluso(nombre, apellido, dni, edad, sentencia, genero,true);
        System.out.println("A que Pabellon se agrega el prisionero?");

        boolean loop = true;
        while(loop){
            if (opcion ==1){
                Funcion.ListarPabellonesHombre(sc);
            }
            else if (opcion ==2){
                Funcion.ListarPabellonesMujer(sc);
            }
            else{
                Funcion.ListarPabellones(sc);
            }
            String nom =sc.nextLine();
            Pabellon p=Carcel.pdb.buscarPabellon(nom);
            try {
                p.agregarRecluso(r);
                JsonManager.guardarLista("Pabellones.json", Carcel.pdb.getP());
                System.out.println("Prisionero agregado");
                loop = false;
            } catch (IOException e) {
                System.out.println("Error al agregar el prisionero: " + e.getMessage());
                loop=false;
                throw new RuntimeException(e);
            } catch (WrongGenderException e) {
                System.out.println(e.getMessage());
                System.out.println("Use un Pabellon del listado:\n");

            }
        }


    }
    public static void agregarGuardia (Scanner sc) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("DNI: ");
        String dni = sc.nextLine();

        System.out.print("Edad: ");
        int edad = Integer.parseInt(sc.nextLine());

        System.out.print("Salario: ");
        double salario = Double.parseDouble(sc.nextLine());

        System.out.print("Días libres: ");
        int diasLibres = Integer.parseInt(sc.nextLine());

        System.out.print("¿Está activo? (true/false): ");
        boolean activo = Boolean.parseBoolean(sc.nextLine());

        System.out.print("Placa policial: ");
        String placaPolicial = sc.nextLine();

        // === Selección de género ===
        System.out.println("Seleccione género:");
        System.out.println("1. MASCULINO");
        System.out.println("2. FEMENINO");
        System.out.println("3. OTRO");

        Genero genero;
        int opcionGenero = Integer.parseInt(sc.nextLine());

        switch (opcionGenero) {
            case 1 -> genero = Genero.HOMBRE;
            case 2 -> genero = Genero.MUJER;
            case 3 -> genero = Genero.OTRO;
            default -> {
                System.out.println("Opción no válida. Se asigna OTRO.");
                genero = Genero.OTRO;
            }
        }

        // === Selección de rango del guardia ===
        System.out.println("Seleccione rango del guardia:");
        for (Cargo c : Cargo.values()) {
            System.out.println("- " + c);
        }

        Cargo rango;

        while (true) {
            try {
                System.out.print("Ingrese rango exacto: ");
                rango = Cargo.valueOf(sc.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Rango inválido. Intente nuevamente.");
            }
        }

        // Crear el objeto Guardia
        Guardia g = new Guardia(
                nombre,
                apellido,
                dni,
                edad,
                salario,
                diasLibres,
                activo,
                placaPolicial,
                genero,
                rango
        );

        System.out.println("\nGuardia creado exitosamente:");
        System.out.println("Placa policial: " + g.getPlacaPolicial());

        System.out.println("A que Pabellon se agrega el guardia?");
        Funcion.ListarPabellones(sc);
        String nom =sc.nextLine();
        Pabellon p=Carcel.pdb.buscarPabellon(nom);
        try {
            p.agregarGuardia(g);
            JsonManager.guardarLista("Pabellones.json", Carcel.pdb.getP());
            System.out.println("Guardia agregado");
        } catch (IOException e) {
            System.out.println("Error al agregar el guardia: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
