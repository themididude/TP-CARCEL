package funcionalidad;

import java.util.ArrayList;


public class Role {

    private String name;

    public static enum PERMISOS {
        ///=--- user level ---==///
        CONSULTAR_PRESOS,
        CONSULTAR_INVENTARIO,
        MENSAJERIA,
        GENERAR_REPORTE,

        ///==--- Seguridad ---==///
        REGISTRAR_RONDA,             /// inicio / fin de patrullas
        REGISTRAR_INCIDENTE,         /// documentar eventos de seguridad
        VERIFICAR_UBICACION,         /// buscar celda de un recluso ASAP
        CONTROL_COMPUERTAS,          /// abrir, cerrar
        REGISTRAR_VISITA,

        ///==--- Admin level ---==///
        AGREGAR_USUARIO,
        ELIMINAR_USUARIO,
        REESTABLECER_CONTRASENAS,
        ASIGNAR_ROLES,
        MODIFICAR_HORARIOS,
        GENERAR_INFORME_FINANCIERO,

        TODOS_LOS_PERMISOS

    }

    private ArrayList<PERMISOS> permisos = new ArrayList<>();

    public boolean tieneAccesoA(PERMISOS permiso)
    {
        if(permisos.contains(PERMISOS.TODOS_LOS_PERMISOS)) return true;

        return permisos.contains(permiso);
    }


    ///---CONSTRUCTOR JEJEj
    private Role(ArrayList<PERMISOS> permisos, String name) {
        this.name = name; // asignar el name
        this.permisos = permisos;
    }

    ///permisos en si
    public static Role User()
    {
        ArrayList<PERMISOS> permisos = new ArrayList<>();
        permisos.add(PERMISOS.CONSULTAR_PRESOS);
        permisos.add(PERMISOS.CONSULTAR_INVENTARIO);
        permisos.add(PERMISOS.MENSAJERIA);
        permisos.add(PERMISOS.GENERAR_REPORTE);
        return new Role(permisos, "USER");
    }

    public static Role Seguridad()
    {
        ArrayList<PERMISOS> permisos = new ArrayList<>();
        permisos.add(PERMISOS.REGISTRAR_RONDA);
        permisos.add(PERMISOS.REGISTRAR_INCIDENTE);
        permisos.add(PERMISOS.VERIFICAR_UBICACION);
        permisos.add(PERMISOS.CONTROL_COMPUERTAS);
        permisos.add(PERMISOS.REGISTRAR_VISITA);
        return new Role(permisos, "SEGURIDAD");
    }

    public static Role Admin()
    {
        ArrayList<PERMISOS> permisos = new ArrayList<>();
        permisos.add(PERMISOS.TODOS_LOS_PERMISOS);

        return new Role(permisos, "ADMIN");
    }

    //------------------------------------------------///
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

