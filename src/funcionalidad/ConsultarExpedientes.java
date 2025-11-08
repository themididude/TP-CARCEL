package funcionalidad;

import java.util.Scanner;

public class ConsultarExpedientes implements Funcionalidad {
    @Override
    public void ejecutar(Role rol,  Scanner sc) {
        if (rol.tieneAccesoA(Role.PERMISOS.CONSULTAR_PRESOS)) {
            // Logica para consultar expedientes iria aca lolololol
            System.out.println("Consultando Expedientes de Presos...");
        } else {
            System.out.println("No tiene permiso para consultar expedientes.");
        }
    }
}