package GestionCarcelMenus;
import PersonasEmpleadoUsuario.Empleado;
import PersonasEmpleadoUsuario.EmpleadoDB;
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

            System.out.println("1. Mostrar Presos");
            System.out.println("2. Buscar Preso (y mostrar)");
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
            System.out.println("3. Trasladar Preso (Cambio de Pabellon)");
            System.out.println("4. Mostrar informes de tipo POLICIAL)");
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
            System.out.println("2. Manejo de Seguridad y Presos");
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
                        System.out.println("1. Mostrar Empleados");
                        System.out.println("2. Agregar Empleados");
                        System.out.println("3. Despedir Empleados");
                        System.out.println("4. Agregar Guardia");
                        System.out.println("5. Buscar y mostrar Guardia");
                        System.out.println("6. Mostrar Guardias");
                        System.out.println("0. Volver al menú principal");
                        System.out.print("Seleccione una opción: ");
                        int opcion = sc.nextInt();
                        sc.nextLine();
                        ending();

                        switch (opcion) {
                            case 1 -> { EmpleadoDB.mostrarEmpleados(); ending();}
                            case 2 -> {
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
                            case 3 -> {
                                System.out.print("Ingrese el ID del Empleado a despedir: ");
                                int id = sc.nextInt();
                                sc.nextLine();
                                EmpleadoDB.eliminarEmpleado(id);
                                ending();
                            }
                            case 4 -> Funcion.agregarGuardia(sc);
                            case 5 -> Funcion.mostrarGuardia(sc);
                            case 6 -> Funcion.MostrarGuardias(sc);
                            case 0 -> {
                                ending();
                                break; // salir al menú principal
                            }
                            default -> System.out.println("Opción inválida.");
                        }
                        if (opcion == 0) break;
                    }
                    break;

                case 2: // Manejo de Seguridad y Presos
                    while (true) {
                        System.out.println("---------| MANEJO DE SEGURIDAD Y PRESOS |---------");
                        System.out.println("1. Agregar Preso");
                        System.out.println("2. Eliminar Preso");
                        System.out.println("3. Registrar Incidente Policial");
                        System.out.println("4. Registrar Visita");
                        System.out.println("5. Trasladar Preso (Cambio de Pabellón)");
                        System.out.println("6. Mostrar informes de tipo POLICIAL");
                        System.out.println("7. Mostrar Presos");
                        System.out.println("8. Buscar Preso (y mostrar)");
                        System.out.println("0. Volver al menú principal");
                        System.out.print("Seleccione una opción: ");
                        int opcion = sc.nextInt();
                        sc.nextLine();
                        clearScreen();

                        switch (opcion) {
                            case 1 -> { Funcion.agregarPreso(sc); ending(); }
                            case 2 -> { Funcion.quitarPreso(sc); ending(); }
                            case 3 -> {
                                newInforme = Paperwork.generarInforme(sc, Informe.Tipo.POLICIAL);
                                gestor.agregarInforme(newInforme);
                                ending();
                            }
                            case 4 -> { Funcion.registrarVisita(sc); ending(); }
                            case 5 -> { Funcion.MoverPreso(sc); ending(); }
                            case 6 -> { gestor.mostrarInformesPorTipo(Informe.Tipo.POLICIAL); ending(); }
                            case 7 -> { Funcion.MostrarPresos(sc); ending(); }
                            case 8 -> { Funcion.MostrarPreso(sc); ending(); }
                            case 0 -> {
                                ending();
                                break; // volver al menú principal
                            }
                            default -> System.out.println("Opción inválida.");
                        }
                        if (opcion == 0) break;
                    }
                    break;

                case 3: // Informes
                    while (true) {
                        System.out.println("---------| INFORMES |---------");
                        System.out.println("1. Generar Informe Financiero");
                        System.out.println("2. Generar Informe General");
                        System.out.println("3. Generar Informe Policial");
                        System.out.println("4. Mostrar todos los Informes");
                        System.out.println("5. Mostrar Informes Generales");
                        System.out.println("0. Volver al menú principal");
                        System.out.print("Seleccione una opción: ");
                        int opcion = sc.nextInt();
                        sc.nextLine();
                        ending();

                        switch (opcion) {
                            case 1 -> {
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
                            case 2 -> {
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
                            case 3 -> {
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
                            case 4 -> { gestor.mostrarTodosLosInformes(); ending();}
                            case 5 -> { gestor.mostrarInformesPorTipo(Informe.Tipo.GENERAL); ending();}
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

    /// MENU ADMIN VIEJO (backup)
   /* public static void MenuAdminVIEJO(Scanner sc)
    {
        Paperwork gestor = Paperwork.getInstancia();
        Informe newInforme = null;

        System.out.println("─────────== BIENVENIDO, ADMIN ==────────");
        while(true){
            System.out.println("---------| MANEJO DE USUARIOS | ---------");
            System.out.println("1. Mostrar Empleados");
            System.out.println("2. Agregar Empleados");
            System.out.println("3. Despedir Empleados");
            System.out.println("4. Agregar Reclusos");
            System.out.println("5. Eliminar Reclusos");
            System.out.println("6. Generar Informe Financiero");
            System.out.println("7. Mostrar todos los Informes");

            System.out.println("8. Agregar Guardia");
            System.out.println("9. Buscar y mostrar Guardia");
            System.out.println("10. Mostrar Guardias");

            System.out.println("---------| MANEJO DE SEGURIDAD |---------");
            System.out.println("11. Registrar Incidente Policial");
            System.out.println("12. Registrar Visita");
            System.out.println("13. Trasladar Preso (Cambio de Pabellon)");
            System.out.println("14. Mostrar informes de tipo POLICIAL)");

            System.out.println("---------| TAREAS DE USUARIO |---------");
            System.out.println("15. Mostrar Presos");
            System.out.println("16. Buscar Preso (y mostrar)");
            System.out.println("17. Generar Reporte General");
            System.out.println("18. Mostrar Informes Generales");
            System.out.println("19. Salir");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    EmpleadoDB.mostrarEmpleados();
                    pause();
                    clearScreen();
                    break;
                case 2:
                    System.out.println("==------------- AGREGAR EMPLEADO -------------==");
                    Empleado nuevo = EmpleadoDB.crearEmpleadoDesdeConsola(sc);
                    EmpleadoDB.agregarEmpleado(nuevo);
                    System.out.println("Empleado agregado:");
                    System.out.println(nuevo);
                    pause();
                    clearScreen();
                    break;
                case 3:
                    System.out.println("Ingrese el ID del Empleado a despedir");
                    int id = sc.nextInt();
                    sc.nextLine();
                    EmpleadoDB.eliminarEmpleado(id);
                    pause();
                    clearScreen();
                    break;
                case 4:
                    Funcion.agregarPreso(sc);
                    pause();
                    clearScreen();
                    break;
                case 5:
                    Funcion.quitarPreso(sc);
                    pause();
                    clearScreen();
                    break;
                case 6:
                    newInforme = Paperwork.generarInforme(sc, Informe.Tipo.FINANCIERO);
                    gestor.agregarInforme(newInforme);
                    break;
                case 7:
                    gestor.mostrarTodosLosInformes();
                    pause();
                    clearScreen();
                    break;
                case 8:
                    Funcion.agregarGuardia(sc);
                    break;
                case 9:
                    Funcion.mostrarGuardia(sc);
                    break;
                case 10:
                    Funcion.MostrarGuardias(sc);

                case 11:
                    newInforme = Paperwork.generarInforme(sc, Informe.Tipo.POLICIAL);
                    gestor.agregarInforme(newInforme);
                    pause();
                    clearScreen();
                    break;
                case 12:
                    Funcion.registrarVisita(sc);
                    pause();
                    clearScreen();
                    break;
                case 13:
                    Funcion.MoverPreso(sc);
                    pause();
                    clearScreen();
                    break;
                case 14:
                    gestor.mostrarInformesPorTipo(Informe.Tipo.POLICIAL);
                    pause();
                    clearScreen();
                    break;
                case 15:
                    Funcion.MostrarPresos(sc);
                    pause();
                    clearScreen();
                    break;
                case 16:
                    Funcion.MostrarPreso(sc);
                    pause();
                    clearScreen();
                    break;
                case 17:
                    newInforme = Paperwork.generarInforme(sc, Informe.Tipo.GENERAL);
                    gestor.agregarInforme(newInforme);
                    pause();
                    clearScreen();
                    break;
                case 18:
                    gestor.mostrarInformesPorTipo(Informe.Tipo.GENERAL);
                    pause();
                    clearScreen();
                    break;
                case 19:
                    return;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        }
    }
*/
}



