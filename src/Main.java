/// NO ESCRIBAN EN EL MAIN CHAVLES.
/// SAQUEN ESTO Y PONGANLO EN UNA CLASE ↓↓↓↓↓↓↓↓↓↓

import funcionalidad.JsonManager;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Carcel.start();



        //Empleado juancito = new Empleado("Juancito", "Perez", "3232332", 30, 15000, 2, Cargo.BASURERO);
        //Empleado pedrito = new Guardia("Juancito", "Perez", "3232332", 30, 15000, 2, Cargo.SARGENTO, true, "32323232");

        try {
            Recluso leido = JsonManager.leerObjeto("Recluso1.json", Recluso::new);
            System.out.println(leido.toString());
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