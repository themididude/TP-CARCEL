package GestionCarcelMenus;

import PersonasEmpleadoUsuario.Genero;
import PersonasEmpleadoUsuario.Recluso;
import funcionalidad.JsonManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        //Carcel.start();

        PabellonDB pDB = new PabellonDB();
        try {
            pDB.setP((java.util.ArrayList<Pabellon>) JsonManager.leerLista("Pabellones.json", Pabellon::new));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Pabellon p = new Pabellon(Sector.A, Genero.HOMBRE);
        try {
            p.setPresos(JsonManager.leerMapaInt("Presos3.json", Recluso::new));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(p.toString());
        pDB.agregarPabellon(p);
        try {
            JsonManager.guardarLista("Pabellones.json",pDB.getP());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


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