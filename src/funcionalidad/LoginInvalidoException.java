package funcionalidad;

public class LoginInvalidoException extends IllegalArgumentException{

    public LoginInvalidoException(String message) {
        super(message);
    }
}
