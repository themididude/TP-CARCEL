package funcionalidad;

public class WrongGenderException extends Exception {
    public WrongGenderException() {

        super("El genero del recluso no corresponde al del pabellon");
    }
    public WrongGenderException(String mensaje) {
        super(mensaje);
    }
}
