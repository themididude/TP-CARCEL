package funcionalidad;

public class Autenticacion {

    public Autenticacion() {
        throw new IllegalStateException("Clase de utilidad, no debe ser instanciada");
    }

    public static boolean Autenticar(Rol rol, String password)
    {
        return rol.getPassword() != null && rol.getPassword().equals(password);

    }

    public static boolean Login(Rol rol, String password)
    {
        if (rol.equals(Rol.USER)) return true;

        if (rol.equals(Rol.ADMIN) || rol.equals(Rol.SEGURIDAD)) {
            return Autenticar(rol, password);
        }
        return false;
    }

    public static void LoginExitoso(Rol rolSeleccionado)
    {
        switch (rolSeleccionado) {
            case ADMIN:
                System.out.println("Acceso máximo concedido. Bienvenido, Admin.");
                break;
            case SEGURIDAD:
                System.out.println("Acceso de seguridad concedido. Bienvenido, Seguridad.");
                break;
            case USER:
                System.out.println("Acceso estándar de Usuario concedido. Bienvenido, User.");
                break;
            default:
                System.out.println("Bienvenido. Rol no reconocido.");
                break;
        }
    }
}
