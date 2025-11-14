package GestionCarcelMenus;
import PersonasEmpleadoUsuario.Empleado;
import PersonasEmpleadoUsuario.EmpleadoDB;
import PersonasEmpleadoUsuario.Recluso;
import funcionalidad.Autenticacion;
import funcionalidad.JsonManager;
import funcionalidad.Rol;
import funcionalidad.Tareas.GenerarInforme;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

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

    private static void clearScreen() {
        System.out.print("\033[H\033[2J"); ///<----- es un quilombo explicar esto pero mueve el cursor
        System.out.flush();                 ///<----- NOTA PARA EL QUE LEA: EL FLUSH NO FUNCIONA EN INTELLIJ, PERO SI EN TERMINALES REALES
    }
    /// =--------------------------......................------------------------= ///


    public static void Welcome(Scanner sc)
    {
        System.out.println("────────────────────────────────────────────────────────");
        System.out.println("︶︶︶︶︶︶ BIENVENIDO A LA GESITON CARCELARIA ︶︶︶︶︶︶");
        System.out.println("────────────────────────────────────────────────────────");

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
    } catch (IllegalArgumentException e) {
        System.out.println("Error: Opcion de rol no reconocida o no valida.");
    } catch (Exception e) {
        System.out.println("Ha ocurrido un error inesperado. Saliendo del Menu...");
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
            System.out.println("5. Salir");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1:
                    Funcion.MostrarPresos(sc);
                    pause();
                    clearScreen();
                    break;
                case 2:
                    Funcion.MostrarPreso(sc);
                    pause();
                    clearScreen();
                    break;
                case 3:
                    newInforme = Paperwork.generarInforme(sc, Informe.Tipo.GENERAL);
                    gestor.agregarInforme(newInforme);
                    pause();
                    clearScreen();
                    break;
                case 4:
                    gestor.mostrarInformesPorTipo(Informe.Tipo.GENERAL);
                    pause();
                    clearScreen();
                    break;
                case 5:
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
            System.out.println("2. Verificar Ubicación");
            System.out.println("3. Registrar Visita");
            System.out.println("4. Trasladar Preso (Cambio de Pabellon)");
            System.out.println("5. Mostrar informes de tipo POLICIAL)");
            System.out.println("6. Salir");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1:
                    newInforme = Paperwork.generarInforme(sc, Informe.Tipo.POLICIAL);
                    gestor.agregarInforme(newInforme);
                    pause();
                    clearScreen();
                    break;
                case 2:
                    break;
                case 3:
                    Funcion.registrarVisita(sc);
                    pause();
                    clearScreen();
                    break;
                case 4:
                    Funcion.MoverPreso(sc);
                    pause();
                    clearScreen();
                    break;
                case 5:
                    newInforme = Paperwork.generarInforme(sc, Informe.Tipo.POLICIAL);
                    gestor.agregarInforme(newInforme);
                    pause();
                    clearScreen();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    public static void MenuAdmin(Scanner sc)
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

            System.out.println("---------| MANEJO DE SEGURIDAD |---------");
            System.out.println("8. Registrar Incidente Policial");
            System.out.println("9. Verificar Ubicación");
            System.out.println("10. Registrar Visita");
            System.out.println("11. Trasladar Preso (Cambio de Pabellon)");
            System.out.println("12. Mostrar informes de tipo POLICIAL)");

            System.out.println("---------| TAREAS DE USUARIO |---------");
            System.out.println("13. Mostrar Presos");
            System.out.println("14. Buscar Preso (y mostrar)");
            System.out.println("15. Generar Reporte General");
            System.out.println("16. Mostrar Informes Generales");
            System.out.println("17. Salir");

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
                    /// SOLAMENTE FALTAN ESTAS 2
                    break;
                case 5:
                    /// YEP YEP YEP
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
                    newInforme = Paperwork.generarInforme(sc, Informe.Tipo.POLICIAL);
                    gestor.agregarInforme(newInforme);
                    pause();
                    clearScreen();
                    break;
                case 9:
                    break;
                case 10:
                    Funcion.registrarVisita(sc);
                    pause();
                    clearScreen();
                    break;
                case 11:
                    Funcion.MoverPreso(sc);
                    pause();
                    clearScreen();
                    break;
                case 12:
                    gestor.mostrarInformesPorTipo(Informe.Tipo.POLICIAL);
                    pause();
                    clearScreen();
                    break;
                case 13:
                    Funcion.MostrarPresos(sc);
                    pause();
                    clearScreen();
                    break;
                case 14:
                    Funcion.MostrarPreso(sc);
                    pause();
                    clearScreen();
                    break;
                case 15:
                    newInforme = Paperwork.generarInforme(sc, Informe.Tipo.GENERAL);
                    gestor.agregarInforme(newInforme);
                    pause();
                    clearScreen();
                    break;
                case 16:
                    gestor.mostrarInformesPorTipo(Informe.Tipo.GENERAL);
                    pause();
                    clearScreen();
                    break;
                case 17:
                    return;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        }
    }


}



