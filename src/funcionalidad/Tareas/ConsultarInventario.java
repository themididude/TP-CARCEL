package funcionalidad.Tareas;

import funcionalidad.Rol;

import java.util.Scanner;

public class ConsultarInventario implements Funcionalidad {
    @Override
    public void ejecutar(Rol rol, Scanner sc) {
        if (rol == Rol.USER || rol == Rol.ADMIN) {

            // LOGICA


            System.out.println("Consultando Inventario de Recursos...");
        } else {
            System.out.println("No tiene permiso para consultar inventario.");
        }
    }
}