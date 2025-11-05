import java.util.ArrayList;
import java.util.List;

public class UserDB {

    private static final List<Usuario> usuarios = new ArrayList<>();

    static{
        usuarios.add(new Usuario("Admin-Man", "adminpass", Rol.ADMIN));
        usuarios.add(new Usuario("Rascal", "enlafarandula123", Rol.USER));
        usuarios.add(new Usuario("NachitoGames", "telaAgito23", Rol.SEGURIDAD));
    }

    /// login en si
    public static Usuario AUTENTICAR(String username, String password){
        for(Usuario u : usuarios){
            if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u; ///AUTENTICACION EXITOSA
            }
        }
        return null; /// AUTENTICACION FALLIDA
    }



}
