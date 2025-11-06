package funcionalidad;

import java.util.List;

public class GestorFuncionalidades {
    private List<Funcionalidad> funcionalidades;

    public GestorFuncionalidades(List<Funcionalidad> funcionalidades) {
        this.funcionalidades = funcionalidades;
    }

    public void ejecutarFuncionalidades(Role rol) {
        for (Funcionalidad funcionalidad : funcionalidades) {
            funcionalidad.ejecutar(rol);
        }
    }
}
