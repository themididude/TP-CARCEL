package funcionalidad;

import java.util.List;
import java.util.Scanner;

public class GestorFuncionalidades {
    private List<Funcionalidad> funcionalidades;

    public GestorFuncionalidades(List<Funcionalidad> funcionalidades) {
        this.funcionalidades = funcionalidades;
    }

    public void ejecutarFuncionalidades(Rol rol, Scanner sc) {
        for (Funcionalidad funcionalidad : funcionalidades) {
            funcionalidad.ejecutar(rol,sc);
        }
    }
}
