public class Usuario {
    private String username;
    private String password;
    private Rol role;

    public Usuario(String username, String password, Rol role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    ///===--- GETTERS Y SETTERS ---===//
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public Rol getRole() {return role;}
}


