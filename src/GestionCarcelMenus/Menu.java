package GestionCarcelMenus;
import PersonasEmpleadoUsuario.Recluso;
import funcionalidad.Autenticacion;
import funcionalidad.Rol;
import funcionalidad.Tareas.GenerarInforme;

import java.util.Scanner;

public class Menu {

    private Menu()
    {
        throw new IllegalStateException("Clase de utilidad, no debe ser instanciada");
    }

    public static void Welcome(Scanner sc)
    {
        System.out.println("────────────────────────────────────────────────────────");
        System.out.println("︶︶︶︶︶︶ BIENVENIDO A LA GESITON CARCELARIA ︶︶︶︶︶︶");
        System.out.println("────────────────────────────────────────────────────────");

    }


public static void showLoginMenu(Scanner sc){
    try {

        System.out.println("Ingrese su rol: ");
        System.out.println("1. USER");
        System.out.println("2. SEGURIDAD");
        System.out.println("3. ADMIN");
        System.out.print("> ");
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
        System.out.println("─────────== BIENVENIDO, User ==────────");
        while(true){

            System.out.println("1. Consultar Presos");
            System.out.println("2. Consultar Inventario");
            System.out.println("3. Generar Reporte");
            System.out.println("4. Salir");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1:
                    break;
                case 2:
                    break;
                case 3:

                    break;
                case 4:
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
        System.out.println("─────────== BIENVENIDO, SEGURIDAD ==────────");

        while (true)
        {

            System.out.println("1. Registrar Ronda");
            System.out.println("2. Registrar Incidente");
            System.out.println("3. Control Compuertas");
            System.out.println("4. Registrar Visita");
            System.out.println("5. Verificar Ubicacion");
            System.out.println("6. Salir");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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
            System.out.println("4. Agregar Personas");
            System.out.println("5. Eliminar Personas");
            System.out.println("6. Generar Informe Financiero");
            System.out.println("7. Mostrar todos los Informes");
            System.out.println("---------| MANEJO DE SEGURIDAD |---------");
            System.out.println("8. Registrar Ronda");
            System.out.println("9. Registrar Incidente Policial");
            System.out.println("10. Verificar Ubicación");
            System.out.println("11. Registrar Visita");
            System.out.println("12. Trasladar Preso (Cambio de Pabellon)");
            System.out.println("13. Mostrar informes de tipo POLICIAL)");
            System.out.println("---------| TAREAS DE USUARIO |---------");
            System.out.println("14. Mostrar Presos");
            System.out.println("15. Buscar Preso (y mostrar)");
            System.out.println("16. Generar Reporte General");
            System.out.println("17. Mostrar Informes Generales");
            System.out.println("18. Salir");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    newInforme = Paperwork.generarInforme(sc, Informe.Tipo.FINANCIERO);
                    gestor.agregarInforme(newInforme);
                    break;
                case 7:
                    gestor.mostrarTodosLosInformes();
                    break;
                case 8:
                    break;
                case 9:
                    newInforme = Paperwork.generarInforme(sc, Informe.Tipo.POLICIAL);
                    gestor.agregarInforme(newInforme);
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    System.out.println("Ingrese el id del prisionero");
                    int id=sc.nextInt();
                    sc.nextLine();
                    System.out.println("a que pabellon se traslada?");
                    String nom=sc.nextLine();
                    Pabellon p=PabellonDB.getPabellonDelRecluso(id);
                    Recluso r= PabellonDB.buscarReclusoDB(id);
                    Pabellon p2= PabellonDB.buscarPabellon(nom);
                    if(r!=null&&p2!=null&&p!=null){

                        p.moverRecluso(r,p2);
                    }
                    break;
                case 13:
                    gestor.mostrarInformesPorTipo(Informe.Tipo.POLICIAL);
                    break;
                case 14:
                    break;
                case 15:
                    break;
                case 16:
                    newInforme = Paperwork.generarInforme(sc, Informe.Tipo.GENERAL);
                    gestor.agregarInforme(newInforme);
                    break;
                case 17:
                    gestor.mostrarInformesPorTipo(Informe.Tipo.GENERAL);
                    break;
                case 18:
                    return;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        }
    }

}



