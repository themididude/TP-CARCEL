import funcionalidad.Role;

import java.util.Scanner;

public class Menu {

    private Menu()
    {
        throw new IllegalStateException("Clase de utilidad, no debe ser instanciada");
    }



public static void Welcome()
{
    System.out.println("────────────────────────────────────────────────────────");
    System.out.println("︶︶︶︶︶︶ BIENVENIDO A LA GESITON CARCELARIA ︶︶︶︶︶︶");
    System.out.println("────────────────────────────────────────────────────────");
}

public static void MostrarMenu(Role rol)
{

}

public static void showLoginMenu(){
    try (Scanner sc = new Scanner(System.in)) {

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

            System.out.println("Bienvenido, " + usuario.getUsername() + "! Rol: " + usuario.getRole());
            System.out.println("────────────────────────────────────────────────────────");

        } catch (Exception e) {

            System.err.println("Error de autenticacion! No se pudo verificar el usuario debido a un problema interno.");
            System.err.println("Detalles:" + e.getMessage());
        }
    } catch (java.util.NoSuchElementException e) {
        System.err.println("No se pudo leer el nombre de usuario.");
    }

}



}
