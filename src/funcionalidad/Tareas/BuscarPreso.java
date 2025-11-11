package funcionalidad.Tareas;

import GestionCarcelMenus.Pabellon;
import PersonasEmpleadoUsuario.Recluso;
import funcionalidad.Rol;

public class BuscarPreso {

    private final Pabellon pabellon;
    private final int PrisonerID;

    public BuscarPreso(Pabellon pabellon, int PrisonerID) {
        this.pabellon = pabellon;
        this.PrisonerID = PrisonerID;
    }

    public static Recluso Buscar(Pabellon pabellon, int prisonerID) {
        if(pabellon.getPresos().get(prisonerID) != null)
        {
            return pabellon.getPresos().get(prisonerID);
        } else {
            System.out.println("Prisionero no encontrado.");
            return null;
        }

    }

}
