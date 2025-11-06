import funcionalidad.Role;

public class Usuario {
    private String username;
    private String password;
    private Role rol;

    public Usuario(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.rol = role;
    }

    ///===--- GETTERS Y SETTERS ---===//
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public Role getRole() {return rol;}
}


