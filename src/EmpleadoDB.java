import funcionalidad.Role;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoDB {

    private static final List<Empleado> empleados = new ArrayList<>();

    static {
        empleados.add(new Empleado("Juancito", "Perez", "3232332", 30, 15000, 2, Cargo.BASURERO));
        empleados.add(new Guardia("Juancito", "Perez", "3232332", 30, 15000, 2, Cargo.SARGENTO, true, "32323232"));
    }

}