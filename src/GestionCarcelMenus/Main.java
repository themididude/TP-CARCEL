package GestionCarcelMenus;/// NO ESCRIBAN EN EL MAIN CHAVLES.
/// SAQUEN ESTO Y PONGANLO EN UNA CLASE ↓↓↓↓↓↓↓↓↓↓

public class Main {
    public static void main(String[] args) {

        Carcel.start();



        //PersonasEmpleadoUsuario.Empleado juancito = new PersonasEmpleadoUsuario.Empleado("Juancito", "Perez", "3232332", 30, 15000, 2, PersonasEmpleadoUsuario.Cargo.BASURERO);
        //PersonasEmpleadoUsuario.Empleado pedrito = new PersonasEmpleadoUsuario.Guardia("Juancito", "Perez", "3232332", 30, 15000, 2, PersonasEmpleadoUsuario.Cargo.SARGENTO, true, "32323232");

       /* try {
            String contenido= Files.readString(Paths.get("Presos.json"));
            JSONObject json = new JSONObject(contenido);
            Map<Integer,PersonasEmpleadoUsuario.Recluso> mapa = new HashMap<>();
            mapa=JsonManager.leerMapaInt("Presos.json",PersonasEmpleadoUsuario.Recluso::new);
            GestionCarcelMenus.ColeccionManager<Object,Integer,PersonasEmpleadoUsuario.Recluso> m= new GestionCarcelMenus.ColeccionManager<>();
            m.mostrarMapa(mapa);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }


}


/*⠀⠀⠀⠀⠀⠀⢀⣀⣀⣠⣤⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⢀⣴⡟⢫⡿⢙⣳⣄⣈⡙⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⣾⣁⡷⣿⠛⠋⠉⠀⠈⠉⠙⠛⠦⣄⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⣸⣿⠉⠀⠿⠆⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⣆⠀⠀⠀⠀⠀
⠀⢀⣾⠃⠘⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⡀⠀⠀⠀
⠀⣼⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢧⠀⠀⠀
⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣇⠀⠀
⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⡀⠀
⠀⢹⡆⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣤⢤⣤⣀⠀⣠⣤⠦⢤⣨⡷⠀
⠀⠸⣷⠀⠀⠀⠀⠀⠀⢠⣾⡋⠀⠀⠀⠀⠉⢿⣉⠀⠀⠀⠈⠳⡄
⠀⢀⣿⡆⢸⣧⠀⠀⠀⣾⢃⣄⡀⠀⠀⠀⠀⢸⡟⢠⡀⠀⠀⠀⣿          Ｇｒａｃｉａｓ ｈｅｒｍａｎｏ
⠀⣸⡟⣷⢸⠘⣷⠀⠀⣷⠈⠿⠇⠀⠀⠀⠀⢸⣇⣘⣟⣁⡀⢀⡿
⠀⢿⣇⣹⣿⣦⡘⠇⠀⠘⠷⣄⡀⠀⠀⣀⣴⠟⠉⠉⠉⠉⠉⢻⡅
⠀⠀⠈⡿⢿⠿⡆⠀⠀⠀⠀⠈⠉⠉⣉⣩⣄⣀⣀⣀⣀⣀⣠⡾⠃
⠀⠀⠀⠻⣮⣤⡿⠀⠀⠀⢀⣤⠞⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⣷⠀
⠀⠀⠀⠀⢸⠄⠀⠀⠀⣠⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡄
⠀⠀⠀⠀⣾⠀⠀⠀⢠⡏⠀⣀⡦⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣧
⠀⠀⠀⠀⡏⠀⠀⠀⢸⡄⠀⠛⠉⠲⣤⣀⣀⠀⠀⠀⠀⠀⣀⣴⡿
⠀⢀⣀⣰⡇⠀⠀⠀⠈⢷⡀⠀⠀⠀⠀⠀⠉⠉⣩⡿⠋⠉⠁⠀⠀
⠀⣾⠙⢾⣁⠀⠀⠀⠀⠈⠛⠦⣄⣀⣀⣀⣤⡞⠋⠀⠀⠀⠀⠀⠀
⢸⡇⠀⠀⠈⠙⠲⢦⣄⡀⠀⠀⠀⠀⠀⠀⢸⠋⠳⠦⣄⠀⠀⠀⠀
⢿⡀⠀⠀⠀⠀⠀⠀⠀⠉⠙⠓⢲⢦⡀⠀⢸⠀⠀⣰⢿⠀⠀⠀⠀
⠀⠙⠳⣄⡀⠀⠀⠀⠀⠀⠀⢀⡟⠀⠙⣦⠸⡆⣰⠏⢸⡄⠀⠀⠀
⠀⠀⠀⠀⠙⠳⢤⣄⣀⠀⠀⣾⠁⠀⠀⠈⠳⣿⣿⣄⣈⡇⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⢺⠇⠀⠀⠀⠀⠀⠈⠘⣧⠙⠃⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⢀⢀⢀⣀⠀⠀⠀⠀⠀⠀⡀⣈⡁⠀⠀⠀*/