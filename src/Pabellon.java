import java.util.LinkedList;
import java.util.Map;

public class Pabellon {

    private Sector sector;
    private Map <Recluso, Integer> presos;
    private LinkedList<Guardia> guardias;
    private Genero genero;

    public Pabellon(Sector sector, Map<Recluso, Integer> presos, LinkedList<Guardia> guardias, Genero genero) {
        this.sector = sector;
        this.presos = presos;
        this.guardias = guardias;
        this.genero = genero;
    }

    public Pabellon() {
    }

    public void agregarRecluso(Recluso recluso) {
        if (recluso.getGenero()!=this.genero){
            this.presos.put(recluso, recluso.getPrisonerID());
            System.out.println("recluso encarcelado");
        }
        else{
            System.out.println("Este recluso no es "+ this.genero);
        }

    }
    public void agregarGuardia(Guardia guardia) {
        this.guardias.add(guardia);
    }


}

