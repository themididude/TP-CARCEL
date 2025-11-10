package GestionCarcelMenus;

public enum Sector {
    A ("Nivel de seguridad: bajo"),
    B ("Nivel de seguridad: intermedio"),
    C ("nivel de seguridad: alto"),
    D ("nivel de seguridad: maximo");

    private String descripcion;
    Sector(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
