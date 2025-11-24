package GestionCarcelMenus;

import PersonasEmpleadoUsuario.Cargo;
import PersonasEmpleadoUsuario.Genero;
import PersonasEmpleadoUsuario.Guardia;
import PersonasEmpleadoUsuario.Recluso;
import funcionalidad.JsonManager;
import funcionalidad.ReclusoNoEncontradoException;
import funcionalidad.WrongGenderException;
import org.json.JSONException;

import java.io.IOException;
import java.util.Scanner;

public class Funcion {

    public static void ListarPabellonesHombre (Scanner sc){
        System.out.println("Pabellones Masculinos:");
        for (Pabellon p :Carcel.pdb.getP()){
            if(p.getGenero().equals(Genero.HOMBRE)){
                System.out.println(p.getClave());
            }
        }

    }
    public static void ListarPabellonesMujer (Scanner sc){
        System.out.println("Pabellones Femeninos:");
        for (Pabellon p :Carcel.pdb.getP()){
            if(p.getGenero().equals(Genero.MUJER)){
                System.out.println(p.getClave());
            }
        }
    }

    public static void ListarPabellones (Scanner sc){
        System.out.println("===-----------------===");
        Funcion.ListarPabellonesHombre(sc);
        System.out.println("===-----------------===");
        Funcion.ListarPabellonesMujer(sc);
        System.out.println("===-----------------===");
    }
    public static void MoverPreso(Scanner sc) {
        System.out.println("Ingrese el ID del recluso a mover: ");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            Pabellon p = Carcel.pdb.getPabellonDelRecluso(id);
            Recluso r = Carcel.pdb.buscarReclusoDB(id);
            System.out.println(r.toString() + "Del " + p.toString());

            System.out.println("A que pabellon se traslada?");
            Funcion.ListarPabellones(sc);
            String nom = sc.nextLine();
            Pabellon p2 = Carcel.pdb.buscarPabellon(nom);
            p.moverRecluso(r, p2);

            /// JSON save
            JsonManager.guardarLista("Pabellones.json", Carcel.pdb.getP());
        } catch(ReclusoNoEncontradoException e){
            System.out.println("Error: recluso no encontrado! " + e.getMessage());
        } catch(JSONException e){
            System.out.println("Error: Json Exception catched!" + e.getMessage());
        }catch (IOException e) {
            System.out.println("Error: IOException!" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static String pedirCambios(Scanner sc, String campo, String valorActual){
        System.out.print(campo + " ACTUAL: (" + valorActual + ") -> NUEVO VALOR (Pulsa enter para mantener el valor actual!): ");
        String input = sc.nextLine().trim();
        return input.isEmpty() ? valorActual : input;
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
        try{
        Pabellon p = Carcel.pdb.getPabellonDelRecluso(id);
        Recluso r = Carcel.pdb.buscarReclusoDB(id);
        System.out.println(r.toString() + "Del Pabellon " + p.toString());
        } catch(ReclusoNoEncontradoException e )
        {
            System.out.println("Error, no encontramos ese recluso! " + e.getMessage());
        }


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
        System.out.println("Ingrese el ID del recluso:");
        int id = -1;
        if (sc.hasNextInt()) id = sc.nextInt();
        sc.nextLine();

        if (id == -1) {
            System.out.println("ID INVALIDO");
            return;
        }

        try {
            Recluso recluso = Carcel.pdb.buscarReclusoDB(id);
            if (recluso != null) {
                System.out.println("Recluso " + recluso.getNombre() + " " + recluso.getApellido() + " encontrado.");
                System.out.println("Desea registrar la una visita? Visitas restantes: " + recluso.getVisitasRestantes());
                String confirmar = sc.nextLine().trim().toUpperCase();
                if (confirmar.equalsIgnoreCase("S")) {
                    recluso.registrarVisita();
                } else {
                    System.out.println("Visita no registrada! Saliendo...");
                }
            }
        } catch (ReclusoNoEncontradoException e) {
            System.out.println("Error, no encontramos ese recluso! " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado! " + e.getMessage());
        }
    }

    public static void quitarPreso(Scanner sc) {
        System.out.println("Ingrese el ID del recluso");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            Pabellon p = Carcel.pdb.getPabellonDelRecluso(id);
            Recluso r = Carcel.pdb.buscarReclusoDB(id);

            if (r == null || p == null) {
                throw new ReclusoNoEncontradoException("El recluso con ID " + id + " no existe o no tiene pabellón asignado.");
            }

            System.out.println(r.toString() + " Del " + p.toString());
            System.out.println("\nSeguro que quiere quitar al recluso? S/N");
            String confirmar = sc.nextLine().trim().toUpperCase();

            if (confirmar.equalsIgnoreCase("S")) {

                try {
                    p.quitarRecluso(r);
                    JsonManager.guardarLista("Pabellones.json", Carcel.pdb.getP());
                    System.out.println("recluso quitado");

                } catch (IOException e) {
                    System.out.println("Error al guardar la información: " + e.getMessage());
                    throw new RuntimeException("Fallo al guardar los datos después de quitar el recluso.", e);
                }
            } else {
                System.out.println("Operacion Cancelada");
            }

        } catch (ReclusoNoEncontradoException e) {
            System.out.println("ERROR: recluso no encontrado!  " + e.getMessage());
            System.out.println("Por favor, verifique el ID ingresado.");
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
        Guardia g = new Guardia(nombre, apellido, dni, edad, salario, diasLibres, activo, placaPolicial, genero, rango);

        System.out.println("Guardia creado exitosamente!");
        System.out.println("Placa policial: " + g.getPlacaPolicial());

        System.out.println("Ingrese un Pabellon para" + g.getNombre() + ".");
        Funcion.ListarPabellones(sc);
        String nom = sc.nextLine();
        Pabellon p = Carcel.pdb.buscarPabellon(nom);
        try {
            p.agregarGuardia(g);
            JsonManager.guardarLista("Pabellones.json", Carcel.pdb.getP());
            System.out.println("El guardia " + g.getNombre() + "fue guardado en el Pabellon" + p.getClave() + ".");
        } catch (IOException e) {
            System.out.println("Error IOException al agregar al guardia: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
