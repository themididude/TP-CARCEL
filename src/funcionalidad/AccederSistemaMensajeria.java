package funcionalidad;

import java.util.Scanner;

public class AccederSistemaMensajeria implements Funcionalidad {
    @Override
    public void ejecutar(Role rol,  Scanner sc) {
        if (rol.tieneAccesoA(Role.PERMISOS.MENSAJERIA)) {
            // LOGICA HERE
            System.out.println("Accediendo al Sistema de Mensajería...");
        } else {
            System.out.println("No tiene permiso para acceder al sistema de mensajería.");
        }
    }
}