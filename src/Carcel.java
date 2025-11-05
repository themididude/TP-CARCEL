import java.util.Objects;

public class Carcel {
 private int numCarcel;
 private String provincia;
 private String partido;
 private String localidad;
 private String direccion;

    public Carcel(int numCarcel, String provincia, String partido, String localidad, String direccion) {
        this.numCarcel = numCarcel;
        this.provincia = provincia;
        this.partido = partido;
        this.localidad = localidad;
        this.direccion = direccion;
    }

    public int getNumCarcel() {
        return numCarcel;
    }

    public void setNumCarcel(int numCarcel) {
        this.numCarcel = numCarcel;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Carcel carcel = (Carcel) o;
        return numCarcel == carcel.numCarcel && Objects.equals(provincia, carcel.provincia) && Objects.equals(partido, carcel.partido) && Objects.equals(localidad, carcel.localidad) && Objects.equals(direccion, carcel.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numCarcel, provincia, partido, localidad, direccion);
    }

    @Override
    public String
    toString() {
        return "Carcel{" +
                "numCarcel=" + numCarcel +
                ", provincia='" + provincia + '\'' +
                ", partido='" + partido + '\'' +
                ", localidad='" + localidad + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
