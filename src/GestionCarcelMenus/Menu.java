package GestionCarcelMenus;
import PersonasEmpleadoUsuario.Empleado;
import PersonasEmpleadoUsuario.EmpleadoDB;
import PersonasEmpleadoUsuario.Recluso;
import PersonasEmpleadoUsuario.Guardia;
import funcionalidad.Autenticacion;
import funcionalidad.JsonManager;
import funcionalidad.Rol;
import funcionalidad.LoginInvalidoException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class Menu {


    public static final String RUTA_JSON = "carcel.json";

    private Menu()
    {
        throw new IllegalStateException("Clase de utilidad, no debe ser instanciada");
    }

    /// =-------------------------- UTILIDADES PREVIAS ------------------------= ///
    private static void pause() {
        System.out.println("\nPresione ENTER para continuar...");
        try {
            System.in.read();   // esperaria a q pongamos enter . . .. .
        } catch (IOException e) {
            // ignoramos
        }
    }

    private static void clearScreen() { /// ahora si
        try {
            if (System.getProperty("os.name").startsWith("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception ignored) {}
    }

    private static void ending(){ //para simplificar un poco
        pause();
        clearScreen();
    }
    /// =--------------------------......................------------------------= ///


    public static void Welcome(Scanner sc)
    {
        try {
            String contenido = new String(Files.readAllBytes(Paths.get(RUTA_JSON)));
            JSONObject carcel = new JSONObject(contenido);

            System.out.println("────────────────────────────────────────────────────────");
            System.out.println("︶︶︶︶︶︶ BIENVENIDO A " + carcel.getString("nombre_carcel") + " ︶︶︶︶︶︶");
            System.out.println("────────────────────────────────────────────────────────");
        } catch (IOException e)
        {
            System.out.println("IOException . . . . . . . . ");
        }

    }


    public static void showLoginMenu(Scanner sc){
        try {
            System.out.println("Escriba su rol: ");
            System.out.println("> USER");
            System.out.println("> SEGURIDAD");
            System.out.println("> ADMIN");
            String opcionUsuario = sc.nextLine();

            Rol rolElegido = Rol.fromString(opcionUsuario);
            if (rolElegido != null) {

                if (rolElegido.equals(Rol.USER)) {
                    Autenticacion.Login(Rol.USER, null);
                    showMenu(sc, Rol.USER);
                } else {

                    System.out.println(">Ingrese su contraseña: ");
                    String password = sc.nextLine();

                    if (Autenticacion.Login(rolElegido, password)) {
                        Autenticacion.LoginExitoso(rolElegido);
                        showMenu(sc, rolElegido);
                    } else {
                        System.out.println("ERROR: CONTRASEÑA INCORRECTA.");
                    }
                }
            }
        } catch (LoginInvalidoException e) {
            System.out.println("Error: Opcion de rol no reconocida o no valida.");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado. Saliendo del Menu..." + e.getMessage());
        }
    }


    public static void showMenu(Scanner sc, Rol rolElegido) {

        switch (rolElegido){
            case Rol.USER:
                MenuUsuario(sc);
                break;
            case Rol.SEGURIDAD:
                MenuSeguridad(sc);
                break;
            case Rol.ADMIN:
                MenuAdmin(sc);
                break;
        }

    }
    ///==------------------------------------------------------------------------------------------==////

    public static void MenuUsuario(Scanner sc)
    {
        Paperwork gestor = Paperwork.getInstancia();
        Informe newInforme = null;

        System.out.println("─────────== BIENVENIDO, User ==────────");
        while(true){
            System.out.println("1. Listar Reclusos");
            System.out.println("2. Buscar Recluso");
            System.out.println("3. Generar Reporte General");
            System.out.println("4. Mostrar Informes Generales");
            System.out.println("0. Salir");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1:
                    Funcion.MostrarPresos(sc);
                    ending();
                    break;
                case 2:
                    Funcion.MostrarPreso(sc);
                    pause();
                    clearScreen();
                    break;
                case 3:
                    newInforme = Paperwork.generarInforme(sc, Informe.Tipo.GENERAL);
                    gestor.agregarInforme(newInforme);
                    ending();
                    break;
                case 4:
                    gestor.mostrarInformesPorTipo(Informe.Tipo.GENERAL);
                    ending();
                    break;
                case 0:
                    System.out.println("Cerrando sesion...");
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    public static void MenuSeguridad(Scanner sc)
    {
        Paperwork gestor = Paperwork.getInstancia();
        Informe newInforme = null;

        System.out.println("─────────== BIENVENIDO, SEGURIDAD ==────────");

        while (true)
        {
            System.out.println("1. Registrar Incidente Policial");
            System.out.println("2. Registrar Visita");
            System.out.println("3. Transladar Recluso");
            System.out.println("4. Mostrar informes de tipo POLICIAL");
            System.out.println("0. Salir");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1:
                    newInforme = Paperwork.generarInforme(sc, Informe.Tipo.POLICIAL);
                    gestor.agregarInforme(newInforme);
                    ending();
                    break;
                case 2:
                    Funcion.registrarVisita(sc);
                    ending();
                    break;
                case 3:
                    Funcion.MoverPreso(sc);
                    ending();
                    break;
                case 4:
                    Paperwork gestor1 = Paperwork.getInstancia();
                    gestor.mostrarInformesPorTipo(Informe.Tipo.POLICIAL);
                    ending();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    public static void MenuAdmin(Scanner sc) {
        Paperwork gestor = Paperwork.getInstancia();
        Informe newInforme = null;

        while (true) {
            System.out.println("─────────== BIENVENIDO, ADMIN ==────────");
            System.out.println("1. Manejo de Empleados");
            System.out.println("2. Manejo de Seguridad y Reclusos");
            System.out.println("3. Informes");
            System.out.println("4. Editar datos de la Carcel");
            System.out.println("0. Salir");
            System.out.print("Seleccione una categoría: ");
            int categoria = sc.nextInt();
            sc.nextLine();
            clearScreen();

            switch (categoria) {
                case 1: // Manejo de Empleados
                    while (true) {
                        System.out.println("---------| MANEJO DE EMPLEADOS | ---------");
                        System.out.println("1. Agregar Empleados");
                        System.out.println("2. Despedir Empleados");
                        System.out.println("3. Modificar Empleados");
                        System.out.println("4. Buscar Empleados");
                        System.out.println("5. Listar Empleados");
                        System.out.println("6. Listar Empleados por cargo");
                        System.out.println("7. Agregar Guardia");
                        System.out.println("8. Eliminar Guardia");
                        System.out.println("9. Modificar Guardia");
                        System.out.println("10. Buscar Guardia");
                        System.out.println("11. Listar Guardias");
                        System.out.println("0. Volver al menú principal");
                        System.out.print("Seleccione una opción: ");
                        int opcion = sc.nextInt();
                        sc.nextLine();
                        ending();

                        switch (opcion) {
                            case 1 -> {
                                System.out.println("==------------- AGREGAR EMPLEADO -------------==");
                                Empleado nuevo = EmpleadoDB.crearEmpleadoDesdeConsola(sc);
                                EmpleadoDB.agregarEmpleado(nuevo);
                                System.out.println("Empleado agregado:\n" + nuevo.getNombre());

                                try {
                                    JsonManager.guardarLista("empleados.json", EmpleadoDB.getEmpleados());
                                } catch(JSONException e){
                                    System.out.println("Ha ocurrido un error JSON: " + e.getMessage());

                                } catch(IOException e) {
                                    System.out.println("ERROR al guardar empleados.json: " + e.getMessage());
                                }

                                ending();
                            }
                            case 2 -> {
                                System.out.print("Ingrese el ID del Empleado a despedir: ");
                                int id = sc.nextInt();
                                sc.nextLine();
                                EmpleadoDB.eliminarEmpleado(id);
                                ending();
                            }
                            case 3 -> {
                                System.out.println("Ingrese el ID del Empleado a modificar: ");
                                int id = sc.nextInt();
                                sc.nextLine();
                                EmpleadoDB.modificarEmpleado(sc, id);

                                try {
                                    JsonManager.guardarLista("empleados.json", EmpleadoDB.getEmpleados());
                                } catch(JSONException e){
                                    System.out.println("JSON EXCEPTION . . . ");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                } catch (Exception e){
                                    System.out.println("Ha ocurrido un error inesperado.");
                                }

                                ending();
                            }
                            case 4 -> {
                                System.out.print("Ingrese el ID del Empleado a buscar: ");
                                int id = sc.nextInt();
                                sc.nextLine();

                                try{
                                Empleado found = EmpleadoDB.buscarEmpleadoPorId(id);
                                    System.out.println("==================");
                                    System.out.println(found.toString());
                                    System.out.println("==================");
                                } catch (NullPointerException e){
                                    System.out.println("Empleado no encontrado.");
                                } catch (IllegalArgumentException e){
                                    System.out.println("ID invalido.");
                                } catch (Exception e){
                                    System.out.println("Ha ocurrido un error inesperado.");
                                }
                                ending();
                            }
                            case 5 -> { EmpleadoDB.mostrarEmpleados(); ending();}
                            case 6 -> { EmpleadoDB.mostrarEmpleadosPorCargo(sc); ending();}
                            case 7 -> Funcion.agregarGuardia(sc);
                            case 8 -> {
                                boolean encontrado = false;
                                System.out.print("Ingrese la placa policial de la guardia a modificar: ");
                                String placa = sc.nextLine().trim();

                                for (Pabellon p : Carcel.pdb.getP()) {
                                    Guardia g = p.buscarGuardia(placa);
                                    if (g != null) {
                                        p.quitarGuardia(g);
                                        encontrado = true;
                                        System.out.println("Guardia eliminado correctamente.");
                                    }
                                }
                                if (encontrado) {
                                    System.out.println("Guardia eliminado correctamente.");

                                    try {
                                        JsonManager.guardarLista("Pabellones.json", Carcel.pdb.getP());
                                    } catch (JSONException e) {
                                        System.out.println("Error JSON: " + e.getMessage());
                                    } catch (IOException e) {
                                        System.out.println("Error al guardar archivo: " + e.getMessage());
                                    }

                                } else {
                                    System.out.println("No se encontro guardia con esa placa.");
                                }
                                ending();
                            }
                            case 9 -> {
                                try {
                                    System.out.print("Ingrese la placa policial de la guardia a modificar: ");
                                    String placa = sc.nextLine().trim();
                                    boolean encontrado = false;

                                    for (Pabellon p : Carcel.pdb.getP()) {
                                        Guardia g = p.buscarGuardia(placa);

                                        if (g != null) {
                                            p.modificarGuardia(sc, g);
                                            System.out.println("Guardia modificada correctamente.");
                                            encontrado = true;

                                            /// guardamos en el JSON
                                            try {
                                                JsonManager.guardarLista("Pabellones.json", Carcel.pdb.getP());
                                            } catch (JSONException e) {
                                                System.out.println("Ha ocurrido un error JSON: " + e.getMessage());
                                            }
                                            break;
                                        }
                                    }
                                    if (!encontrado) {
                                        System.out.println("No se encontro guardia con placa: " + placa);
                                    }

                                } catch (Exception e) {
                                    System.out.println("No se pudo modificar el guardia: " + e.getMessage());
                                }

                                ending();
                            }
                            case 10 -> Funcion.mostrarGuardia(sc);
                            case 11 -> Funcion.MostrarGuardias(sc);
                            case 0 -> {
                                ending();
                            }
                            default -> System.out.println("Opción invalida.");
                        }
                        if (opcion == 0) break;
                    }
                    break;

                case 2: // Manejo de Seguridad y Presos
                    while (true) {
                        System.out.println("---------| MANEJO DE SEGURIDAD Y RECLUSOS |---------");
                        System.out.println("1. Agregar Recluso");
                        System.out.println("2. Modificar Recluso");
                        System.out.println("3. Eliminar Recluso");
                        System.out.println("4. Listar Reclusos");
                        System.out.println("5. Buscar Recluso");
                        System.out.println("6. Trasladar Recluso");
                        System.out.println("7. Registrar Visita de Recluso");
                        System.out.println("8. Registrar Incidente Policial");
                        System.out.println("9. Mostrar informes de tipo POLICIAL");
                        System.out.println("0. Volver al menú principal");
                        System.out.println("----------------------------------------------");
                        System.out.print("Seleccione una opción: ");
                        int opcion = sc.nextInt();
                        sc.nextLine();
                        clearScreen();

                        switch (opcion) {
                            case 1 -> { Funcion.agregarPreso(sc); ending(); }
                            case 2 -> {
                                try {
                                    System.out.print("Ingrese ID del recluso a modificar: ");
                                    int idPreso = Integer.parseInt(sc.nextLine().trim());
                                    boolean encontrado = false;

                                    /// buscar entre tooodos los pabellones
                                    for (Pabellon pab : Carcel.pdb.getP()) {
                                        Recluso r = pab.buscarRecluso(idPreso);
                                        if (r != null) {
                                            pab.modificarRecluso(sc, r);
                                            System.out.println("Preso modificado correctamente.");
                                            encontrado = true;

                                            /// guardamos en el JSON
                                            try {
                                                JsonManager.guardarLista("Pabellones.json", Carcel.pdb.getP());
                                            } catch (JSONException e) {
                                                System.out.println("Ha ocurrido un error JSON: " + e.getMessage());
                                            }
                                            break;
                                        }
                                    }
                                    if (!encontrado) {
                                        System.out.println("No se encontró un preso con ID: " + idPreso);
                                    }
                                } catch (Exception e) {
                                    System.out.println("No se pudo modificar el preso (verifique la existencia de PabellonDB o la entrada): " + e.getMessage());
                                }
                                ending();
                            }
                            case 3 -> { Funcion.quitarPreso(sc); ending(); }
                            case 4 -> { Funcion.MostrarPresos(sc); ending(); }
                            case 5 -> { Funcion.MostrarPreso(sc); ending(); }
                            case 6 -> { Funcion.MoverPreso(sc); ending(); }
                            case 7 -> { Funcion.registrarVisita(sc); ending(); }
                            case 8 -> {
                                newInforme = Paperwork.generarInforme(sc, Informe.Tipo.POLICIAL);
                                gestor.agregarInforme(newInforme);
                                ending();
                            }
                            case 9 -> { gestor.mostrarInformesPorTipo(Informe.Tipo.POLICIAL); ending(); }
                            case 0 -> {
                                ending();
                            }
                            default -> System.out.println("Opción inválida.");
                        }
                        if (opcion == 0) break;
                    }
                    break;

                case 3: // Informes
                    while (true) {
                        System.out.println("---------| INFORMES |---------");
                        System.out.println("1. Generar Informe General");
                        System.out.println("2. Generar Informe Policial");
                        System.out.println("3. Generar Informe Financiero");
                        System.out.println("4. Mostrar Informes Generales");
                        System.out.println("5. Mostrar todos los Informes");
                        System.out.println("6. Buscar un informe");
                        System.out.println("7. Modificar un informe");
                        System.out.println("6. Eliminar un informe");
                        System.out.println("0. Volver al menú principal");
                        System.out.print("Seleccione una opción: ");
                        int opcion = sc.nextInt();
                        sc.nextLine();
                        ending();

                        switch (opcion) {
                            case 1 -> {
                                newInforme = Paperwork.generarInforme(sc, Informe.Tipo.GENERAL);
                                gestor.agregarInforme(newInforme);
                                try {
                                    JsonManager.guardarLista("informes.json", gestor.informes);
                                } catch(JSONException e){
                                    System.out.println("JSON EXCEPTION . . . ");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                            case 2 -> {
                                newInforme = Paperwork.generarInforme(sc, Informe.Tipo.POLICIAL);
                                gestor.agregarInforme(newInforme);
                                try {
                                    JsonManager.guardarLista("informes.json", gestor.informes);
                                } catch (JSONException e) {
                                    System.out.println("JSON EXCEPTION . . . ");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            case 3 -> {
                                newInforme = Paperwork.generarInforme(sc, Informe.Tipo.FINANCIERO);
                                gestor.agregarInforme(newInforme);
                                try {
                                    JsonManager.guardarLista("informes.json", gestor.informes);
                                } catch(JSONException e){
                                    System.out.println("JSON EXCEPTION . . . ");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                            case 4 -> { gestor.mostrarInformesPorTipo(Informe.Tipo.GENERAL); ending();}
                            case 5 -> { gestor.mostrarTodosLosInformes(); ending();}
                            case 6 -> {
                                System.out.println("Ingrese la ID del informe buscado . . .");
                                int id = sc.nextInt();
                                Informe inf = gestor.buscarInforme(id);
                                gestor.mostrarInforme(inf);
                                ending();
                            }
                            case 7 -> {
                                System.out.println("Ingrese la ID del informe a modificar . . .");
                                int id = sc.nextInt();
                                sc.nextLine();
                                gestor.modificarInforme(sc, id);
                                try {
                                    JsonManager.guardarLista("informes.json", gestor.informes);
                                } catch(JSONException e){
                                    System.out.println("JSON EXCEPTION . . . ");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                } catch (Exception e){
                                    System.out.println("Ha ocurrido un error inesperado.");
                                }
                                ending();
                            }
                            case 8 -> {
                                System.out.println("Ingrese la ID del informe a eliminar . . .");
                                int id = sc.nextInt();
                                sc.nextLine();
                                gestor.eliminarInforme(id);
                                ending();
                            }
                            case 0 -> {
                                break; // volver al menú principal
                            }
                            default -> System.out.println("Opción inválida.");
                        }
                        if (opcion == 0) break;
                    }
                    break;
                case 4:
                    ///POR SI QUEREMOS EDITAR LA CARCEL!
                    Carcel.mostrarMenuEdicion(sc);
                    ending();
                    break;
                case 0: // Salir
                    return;

                default:
                    System.out.println("Categoría inválida.");
            }
        }
    }

}