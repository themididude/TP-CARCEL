public interface Seguridad {
     boolean Activo = false;
     String placaPolicial = "";
    String generarReporte(); ///generar un reporte policial, dependiendo del rango cambia
    void switchActivo();
   ;////
}
