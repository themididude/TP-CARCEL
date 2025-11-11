package funcionalidad.Tareas;

import funcionalidad.Rol;

import java.util.Scanner;

public class GenerarReporte implements Funcionalidad {
    @Override
    public void ejecutar(Rol rol, Scanner sc) {
        if (rol == Rol.USER || rol == Rol.ADMIN) {
            // logicaaaaaaaaaaaaaaaaa
            System.out.println("Generando Reporte Básico de Actividad...");
        } else {
            System.out.println("No tiene permiso para generar reportes.");
        }
    }
}
