package funcionalidad;

import java.util.Scanner;

public class ConsultarInventario implements Funcionalidad {
    @Override
    public void ejecutar(Role rol, Scanner sc) {
        if (rol.tieneAccesoA(Role.PERMISOS.CONSULTAR_INVENTARIO)) {
            // Logica para consultar inventario lololololo
            System.out.println("Consultando Inventario de Recursos...");
        } else {
            System.out.println("No tiene permiso para consultar inventario.");
        }
    }
}