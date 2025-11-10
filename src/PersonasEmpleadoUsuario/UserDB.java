package PersonasEmpleadoUsuario;

import funcionalidad.Role;

import java.util.ArrayList;
import java.util.List;

public class UserDB {

    private static final List<Usuario> usuarios = new ArrayList<>();

    static{
        usuarios.add(new Usuario("Admin-Man", "adminpass", Role.Admin()));
        usuarios.add(new Usuario("Rascal", "enlafarandula123", Role.User()));
    usuarios.add(new Usuario("NachitoGames", "telaAgito23", Role.Seguridad()));
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

    public static void agregarUsuario(Usuario u){
        usuarios.add(u);
    }

    public static void mostrarUsuarios() {
        System.out.println("────────── LISTA DE USUARIOS ──────────");
        for (Usuario u : usuarios) {
            System.out.println("• " + u.getUsername() + "  |  Rol: " + u.getRole());
        }
        System.out.println("────────────────────────────────────────");
    }
}
