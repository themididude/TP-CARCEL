package funcionalidad;

public class WrongGenderException extends Exception {
    public WrongGenderException() {
        super("ERROR: LOS GENEROS NO COINCIDEN");
    }
    public WrongGenderException(String mensaje) {
        super(mensaje);
    }
}
