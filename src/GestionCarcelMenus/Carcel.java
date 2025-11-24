package GestionCarcelMenus;

import PersonasEmpleadoUsuario.Empleado;
import PersonasEmpleadoUsuario.EmpleadoDB;
import funcionalidad.JsonManager;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Carcel {

    public static final String RUTA_JSON = "carcel.json";
    public static PabellonDB pdb = new PabellonDB();





    public static void start() {
        //--| algunos atributos| --//
        boolean salir = false;
        Scanner sc = new Scanner(System.in);
        //--------------------------------------------------------------------------------------------------------------//

        /// ----> Leer pabellones y tal
        try {
            pdb.setP((ArrayList<Pabellon>) JsonManager.leerLista("Pabellones.json", Pabellon::new));
            System.out.println(pdb.toString());
        } catch(JSONException e){
            System.out.println("ERROR JSON");
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            System.out.println("no se pudo acceder a la base de datos");
            throw new RuntimeException(e);
        }

        ///  ----> Leamos los informes y tal
        try {
            List<Informe> cargados = JsonManager.leerLista(
                    "informes.json",
                    Informe::fromJSON
            );
            Paperwork.getInstancia().informes.addAll(cargados);
            System.out.println("Informes cargados desde JSON: " + cargados.size());
        } catch (Exception e) {
            System.out.println("No se pudo cargar informes.json (¿primera ejecucion?)");
        }
        ///  ----> Leemos los empleadinhos y tal
        try {
            List<Empleado> cargados = JsonManager.leerLista(
                    "empleados.json",
                    Empleado::fromJSON
            );
            EmpleadoDB.getEmpleados().addAll(cargados);
            System.out.println("Empleados cargados desde JSON: " + cargados.size());
        } catch (Exception e) {
            System.out.println("No se pudo cargar empleados.json (¿primera ejecución?)");
        }


        ///───────────────────────────────────────| PRIMER START  |──────────────────────────────────────────────///
        try{
            String contenido = new String(Files.readAllBytes(Paths.get(RUTA_JSON)));
            JSONObject carcel = new JSONObject(contenido);

            if(carcel.getInt("first_time") == 1)
            {
                System.out.println("────────────────────────────────────────");
                System.out.println("Bienvenido a su Carcel! Ingrese los datos antes de continuar:");

                System.out.println("Nombre de su Carcel: ");
                carcel.put("nombre_carcel", sc.nextLine());

                System.out.println("Localidad: ");
                carcel.put("localidad", sc.nextLine());

                System.out.println("Provincia: ");
                carcel.put("provincia", sc.nextLine());

                System.out.println("Codigo Postal: ");
                carcel.put("codigo_postal", sc.nextLine());

                System.out.println("Capacidad: ");
                carcel.put("capacidad", sc.nextDouble());
                sc.nextLine(); //resetear buffer

                System.out.println("Configurando. . . . . . ");

                carcel.put("first_time", 0);

                Files.write(Paths.get(RUTA_JSON), carcel.toString(4).getBytes());
                System.out.println("Carcel guardada con exito!");
                System.out.println("────────────────────────────────────────");
            } else {

                System.out.println("────────────────────────────────────────");
                System.out.println("Iniciando Carcel . . . . . .!");
                System.out.println("────────────────────────────────────────");
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

    private static String pedirCambios(Scanner sc, String campo, String valorActual){
        System.out.print(campo + " ACTUAL: (" + valorActual + ") -> NUEVO VALOR (Pulsa enter para mantener el valor actual!): ");
        String input = sc.nextLine().trim();
        return input.isEmpty() ? valorActual : input;
    }

    private static void editarCarcel(Scanner sc) {
        try {
            String contenido = new String(Files.readAllBytes(Paths.get(RUTA_JSON)));
            JSONObject carcel = new JSONObject(contenido);

            System.out.println("────────── EDITAR DATOS DE LA CÁRCEL ──────────");

            // usamos la funcioncinha auxiliar
            carcel.put("nombre_carcel", pedirCambios(sc, "Nombre de la cárcel", carcel.getString("nombre_carcel")));
            carcel.put("localidad", pedirCambios(sc, "Localidad", carcel.getString("localidad")));
            carcel.put("provincia", pedirCambios(sc, "Provincia", carcel.getString("provincia")));
            carcel.put("codigo_postal", pedirCambios(sc, "Código Postal", carcel.getString("codigo_postal")));

            System.out.print("Capacidad actual (" + carcel.getInt("capacidad") + ") -> nueva capacidad (ENTER para no cambiar): ");
            String cap = sc.nextLine().trim();
            if(!cap.isEmpty()){
                try {
                    carcel.put("capacidad", Integer.parseInt(cap));
                } catch(NumberFormatException e){
                    System.out.println("Capacidad invalidaa. Mantendremos el valor previo.");
                }
            }

            Files.write(Paths.get(RUTA_JSON), carcel.toString(4).getBytes());
            System.out.println("DATOS");

        } catch (IOException e) {
            System.out.println("Error al editar carcel.json: " + e.getMessage());
        }
    }

    public static void mostrarMenuEdicion(Scanner sc) {
        editarCarcel(sc);
    }
}
