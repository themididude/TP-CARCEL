package GestionCarcelMenus;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Carcel {

    public static final String RUTA_JSON = "data/carcel.json";

    public static void start() {
        //--| algunos atributos| --//
        boolean salir = false;
        Scanner sc = new Scanner(System.in);


        ///───────────────────────────────────────| PRIMER START  |──────────────────────────────────────────────///
        try{
            String contenido = new String(Files.readAllBytes(Paths.get(RUTA_JSON)));
            JSONObject carcel = new JSONObject(contenido);

            if(carcel.getInt("first_time") == 1)
            {
                System.out.println("Bienvenido a su Carcel! Ingrese los datos antes de continuar:");

                System.out.println("Nombre de su Carcel: ");
                carcel.put("nombre_carcel", sc.next());

                System.out.println("Localidad: ");
                carcel.put("localidad", sc.next());

                System.out.println("Provincia: ");
                carcel.put("provincia", sc.next());

                System.out.println("Codigo Postal: ");
                carcel.put("codigo_postal", sc.next());

                System.out.println("Capacidad: ");
                carcel.put("capacidad", sc.nextDouble());

                System.out.println("Configurando. . . . . . ");

                carcel.put("first_time", 0);

                Files.write(Paths.get(RUTA_JSON), carcel.toString(4).getBytes());
                System.out.println("Carcel guardada con exito!");
            } else {

                System.out.println("Iniciando Carcel" + carcel.getString("nombre_carcel" + "!"));
            }




        } catch (IOException e)
        {
            System.out.println("Error al leer o escribir el archivo JSON: " + e.getMessage());
        }



        ///───────────────────────────────────────|  LOOP  PRINCIPAL  |─────────────────────────────────────────///
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

            ///───────────────────────────────────────|  END  |─────────────────────────────────────────///
        }
    }

}

