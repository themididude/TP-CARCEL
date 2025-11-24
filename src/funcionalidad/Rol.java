package funcionalidad;

    public enum Rol {
    USER(null),
    SEGURIDAD("PEACEANDLOVE"),
    ADMIN ("ADMINPASS99");

    private final String password;

    Rol(String password)
    {
        this.password = password;
    }

        public String getPassword() {
            return password;
        }

        public static Rol fromString(String nombre)
        {
            try{
                return Rol.valueOf(nombre.toUpperCase());
            } catch (IllegalArgumentException e) {

                System.err.println("Rol " + nombre + " no existe.");
                return null;
            }
        }
    }