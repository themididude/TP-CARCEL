package GestionCarcelMenus;

import java.util.Scanner;

public class Carcel {

    public static void start() {
        boolean salir = false;
        Scanner sc = new Scanner(System.in);


        while (!salir) {
            ///=---------- MENU -----------------=//
            Menu.Welcome(sc);
            Menu.showLoginMenu(sc); ///de aca desembocan los demas paneles


            /// si llegamos aca, el usuario finalizo sesion
            System.out.println("\n--- Sesión Finalizada ---");
            System.out.println("¿Qué desea hacer?");
            System.out.println("1. Iniciar sesión de nuevo");
            System.out.println("2. Salir de la aplicación");
            System.out.print("> ");

            if (sc.hasNextLine()) {
                String opcion = sc.nextLine();

                if (opcion.equals("2")) {
                    System.out.println("Hasta Pronto!");
                    salir = true;
                } else if (!opcion.equals("1")) {
                    System.out.println("Opción inválida. Volviendo a iniciar sesión automáticamente.");
                }
            } else {
                System.out.println("¡Gracias por usar el sistema! Saliendo...");
                sc.close();
                salir = true;
            }
        }
    }

}

