package funcionalidad;


import javax.management.relation.Role;
import java.util.Scanner;

public class ConsultarExpedientes implements Funcionalidad {
    @Override
    public void ejecutar(Rol rol,  Scanner sc) {
        if (rol == Rol.USER || rol == Rol.ADMIN ) {

            //LOGICA

            System.out.println("Consultando Expedientes de Presos...");
        } else {
            System.out.println("No tiene permiso para consultar expedientes.");
        }
    }
}