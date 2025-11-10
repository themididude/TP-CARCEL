import java.util.Scanner;

public class Carcel {
    ///===------ CARCEL pasaria a ser un "App.Java" que coordina la mayoria para que el main solo tenga que hacer carcel.start();
    /// ===-------------------- De igual manera, carcel estaria subdividida en Menu que tiene un monton de funciones, carcel solamente las ejecuta en secuencia

    public static void start() {
Scanner sc = new Scanner(System.in);
        ///=---------- AAAAAAAAAAA
        Menu.Welcome(sc);

        Menu.showLoginMenu(sc); ///-----> de aca desembocan los demas paneles

        Menu.showMenu(sc);

    }
}
