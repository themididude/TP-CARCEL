package funcionalidad;

public class GenerarReporte implements Funcionalidad {
    @Override
    public void ejecutar(Role rol) {
        if (rol.tieneAccesoA(Role.PERMISOS.GENERAR_REPORTE)) {
            // logicaaaaaaaaaaaaaaaaa
            System.out.println("Generando Reporte Básico de Actividad...");
        } else {
            System.out.println("No tiene permiso para generar reportes.");
        }
    }
}
