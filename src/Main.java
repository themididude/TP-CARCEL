import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("────────────────────────────────────────────────────────");
        System.out.println("︶︶︶︶︶︶ BIENVENIDO A LA GESITON CARCELARIA ︶︶︶︶︶︶");
        System.out.println("────────────────────────────────────────────────────────");


        Scanner sc = new Scanner(System.in);


        System.out.println(">Ingrese su nombre de usuario: ");
        String username = sc.nextLine();
        System.out.println(">Ingrese su contraseña: ");
        String password = sc.nextLine();

        Usuario usuario = UserDB.AUTENTICAR(username, password);
        if(usuario == null) {
            System.out.println("Credenciales Invalidas");
            return;
        }

        System.out.println("Bienvenido, " + usuario.getUsername() + "! Rol: " + usuario.getRole());
        System.out.println("────────────────────────────────────────────────────────");

        switch(usuario.getRole()) {
            case Rol.ADMIN:
                MenuAdmin(sc);
                break;

            case USER:
                break;
            default:
                System.out.println("bro wat....");
                break;
        }

        sc.close();
    }


    /// ────────────────────────────────────────── ///
    private static void MenuAdmin(Scanner sc)                           //<------- agregar mas funciones
    {
        while(true){
            System.out.println("─────────== MENU ADMIN ==────────");
            System.out.println("1. Ver usuarios");
            System.out.println("2. Crear nuevo usuario");
            System.out.println("3. Salir");
            System.out.println("> ");

            int opcion = sc.nextInt();
            sc.nextLine(); ///limpiar buffer :v

            switch(opcion){
                case 1:
                    UserDB.mostrarUsuarios();
                    break;
                case 2:
                    crearUsuario(sc);
                    break;
                case 3:
                    System.out.println("Cerrando sesion...");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    /// ────────────────────────────────────────── ///
    private static void MenuUsuario(Scanner sc)
    {
        while(true){

            System.out.println("─────────== MENU USER ==────────");
            System.out.println("1. Ver usuarios");
            System.out.println("2. Mostrar Visitas disponibles");      ////not yet
            System.out.println("3. Salir");
            System.out.println("> ");

            int opcion = sc.nextInt();
            sc.nextLine(); ///limpiar buffer :v

            switch(opcion){
                case 1:
                    UserDB.mostrarUsuarios();
                    break;
                case 2:
                    //no hecho todavia
                    break;
                case 3:
                    System.out.println("Cerrando sesion...");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }

        }
    }
    /// ────────────────────────────────────────── ///

    private static void crearUsuario(Scanner sc) { /// ADMIN-ONLY
        System.out.print("Nombre de usuario: ");
        String nuevoNombre = sc.nextLine();

        System.out.print("Password: ");
        String nuevaPass = sc.nextLine();

        System.out.print("Rol (ADMIN / SEGURIDAD / USER): ");
        String rolStr = sc.nextLine().toUpperCase();

        Rol nuevoRol = Rol.valueOf(rolStr);
        Usuario nuevo = new Usuario(nuevoNombre, nuevaPass, nuevoRol);
        UserDB.agregarUsuario(nuevo);

        System.out.println("Usuario creado con exito.");
    }
}

/*⠀⠀⠀⠀⠀⠀⢀⣀⣀⣠⣤⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⢀⣴⡟⢫⡿⢙⣳⣄⣈⡙⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⣾⣁⡷⣿⠛⠋⠉⠀⠈⠉⠙⠛⠦⣄⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⣸⣿⠉⠀⠿⠆⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⣆⠀⠀⠀⠀⠀
⠀⢀⣾⠃⠘⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⡀⠀⠀⠀
⠀⣼⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢧⠀⠀⠀
⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣇⠀⠀
⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⡀⠀
⠀⢹⡆⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣤⢤⣤⣀⠀⣠⣤⠦⢤⣨⡷⠀
⠀⠸⣷⠀⠀⠀⠀⠀⠀⢠⣾⡋⠀⠀⠀⠀⠉⢿⣉⠀⠀⠀⠈⠳⡄
⠀⢀⣿⡆⢸⣧⠀⠀⠀⣾⢃⣄⡀⠀⠀⠀⠀⢸⡟⢠⡀⠀⠀⠀⣿          Ｇｒａｃｉａｓ ｈｅｒｍａｎｏ
⠀⣸⡟⣷⢸⠘⣷⠀⠀⣷⠈⠿⠇⠀⠀⠀⠀⢸⣇⣘⣟⣁⡀⢀⡿
⠀⢿⣇⣹⣿⣦⡘⠇⠀⠘⠷⣄⡀⠀⠀⣀⣴⠟⠉⠉⠉⠉⠉⢻⡅
⠀⠀⠈⡿⢿⠿⡆⠀⠀⠀⠀⠈⠉⠉⣉⣩⣄⣀⣀⣀⣀⣀⣠⡾⠃
⠀⠀⠀⠻⣮⣤⡿⠀⠀⠀⢀⣤⠞⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⣷⠀
⠀⠀⠀⠀⢸⠄⠀⠀⠀⣠⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡄
⠀⠀⠀⠀⣾⠀⠀⠀⢠⡏⠀⣀⡦⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣧
⠀⠀⠀⠀⡏⠀⠀⠀⢸⡄⠀⠛⠉⠲⣤⣀⣀⠀⠀⠀⠀⠀⣀⣴⡿
⠀⢀⣀⣰⡇⠀⠀⠀⠈⢷⡀⠀⠀⠀⠀⠀⠉⠉⣩⡿⠋⠉⠁⠀⠀
⠀⣾⠙⢾⣁⠀⠀⠀⠀⠈⠛⠦⣄⣀⣀⣀⣤⡞⠋⠀⠀⠀⠀⠀⠀
⢸⡇⠀⠀⠈⠙⠲⢦⣄⡀⠀⠀⠀⠀⠀⠀⢸⠋⠳⠦⣄⠀⠀⠀⠀
⢿⡀⠀⠀⠀⠀⠀⠀⠀⠉⠙⠓⢲⢦⡀⠀⢸⠀⠀⣰⢿⠀⠀⠀⠀
⠀⠙⠳⣄⡀⠀⠀⠀⠀⠀⠀⢀⡟⠀⠙⣦⠸⡆⣰⠏⢸⡄⠀⠀⠀
⠀⠀⠀⠀⠙⠳⢤⣄⣀⠀⠀⣾⠁⠀⠀⠈⠳⣿⣿⣄⣈⡇⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⢺⠇⠀⠀⠀⠀⠀⠈⠘⣧⠙⠃⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⢀⢀⢀⣀⠀⠀⠀⠀⠀⠀⡀⣈⡁⠀⠀⠀*/