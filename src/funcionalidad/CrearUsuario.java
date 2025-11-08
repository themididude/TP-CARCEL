package funcionalidad;


import java.util.Scanner;

public class CrearUsuario implements Funcionalidad {


    @Override
    public void ejecutar(Role rol, Scanner sc) {
        if (rol.tieneAccesoA(Role.PERMISOS.AGREGAR_USUARIO)) {

            System.out.print("Nombre de usuario: ");
            String nuevoNombre = sc.nextLine();

            System.out.print("Password: ");
            String nuevaPass = sc.nextLine();

            System.out.print("Rol (ADMIN / SEGURIDAD / USER): ");
            String rolStr = sc.nextLine().toUpperCase();
            System.out.println("Usuario creado con exito.");

        } else {
            System.out.println("No tiene permiso para agregar usuarios.");
        }
    }
}
