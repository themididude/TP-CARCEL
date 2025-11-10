import funcionalidad.CrearUsuario;

import java.util.Scanner;

public class Menu {

    public static Usuario UsuarioActual;

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

        System.out.println(">Ingrese su nombre de usuario: ");
        String username = sc.nextLine();
        System.out.println(">Ingrese su contraseña: ");
        String password = sc.nextLine();

        try {

            Usuario usuario = UserDB.AUTENTICAR(username, password);
            if (usuario == null) {
                System.out.println("Credenciales Invalidas");
                return;
            }

            System.out.println("Bienvenido, " + usuario.getUsername() + "! Rol: " + usuario.getRole().getName());
            UsuarioActual = usuario;
            System.out.println("──────────────────────────");

        } catch (Exception e) {

            System.err.println("Error de autenticacion! No se pudo verificar el usuario debido a un problema interno.");
            System.err.println("Detalles:" + e.getMessage());
        }
    } catch (java.util.NoSuchElementException e) {
        System.err.println("No se pudo leer el nombre de usuario.");
    }
}


public static void showMenu(Scanner sc) {
    Scanner sc = new Scanner(System.in);
    String rolNombre = UsuarioActual.getRole().getName();

   switch (rolNombre) {

       case "USER":
           MenuUsuario(sc);
           break;
       case "SEGURIDAD":
           MenuSeguridad(sc);
           break;
       case "ADMIN":
           MenuAdmin(sc);
           break;
   }

}
///==------------------------------------------------------------------------------------------==////

    public static void MenuUsuario(Scanner sc)
    {
        /*
        CONSULTAR_PRESOS,
        CONSULTAR_INVENTARIO,
        ,
        GENERAR_REPORTE,
        */
        while(true){
            System.out.println("─────────== BIENVENIDO, " + UsuarioActual.getUsername() + " ==────────");
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
                    System.out.println("Cerrando sesion...");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    public static void MenuSeguridad(Scanner sc)
    {

        while (true)
        {

            System.out.println("─────────== BIENVENIDO, " + UsuarioActual.getUsername() + " ==────────");
            System.out.println("----->Menu de SEGURIDAD.");
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

        while(true){
            System.out.println("─────────== BIENVENIDO, " + UsuarioActual.getUsername() + " ==────────");
            System.out.println("Menu de ADMIN.");
            System.out.println("---------| MANEJO DE USUARIOS | ---------");
            System.out.println(">");
            System.out.println("0. Mostrar Usuarios");
            System.out.println("1. Agregar Usuario");
            System.out.println("1. Eliminar Usuario");
            System.out.println("2. Reestablecer Constraseñas");
            System.out.println("3. Asignar Roles");
            System.out.println("4. Modificar Horarios");
            System.out.println("5. Generar Informe Financiero");
            System.out.println("---------| MANEJO DE SEGURIDAD |---------");
            System.out.println("6. Registrar Ronda");
            System.out.println("7. Registrar Incidente");
            System.out.println("8. Verificar Ubicacion");
            System.out.println("9. Registrar Visita");
            System.out.println("---------| TAREAS DE USUARIO |---------");
            System.out.println("10. Consultar Presos");
            System.out.println("11. Consultar Inventario");
            System.out.println("12. Generar Reporte");
            System.out.println("13. Salir");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){       /// /////// ya sabeis que hacer
                case 0:
                    UserDB.mostrarUsuarios();                      ////<----- ejemplo
                    break;
                case 1:
                    CrearUsuario creation = new CrearUsuario();
                    creation.ejecutar(UsuarioActual.getRole(), sc);
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
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    return;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        }
    }
}



