import java.util.LinkedList;
import java.util.Map;

public class Pabellon {

    private Sector sector;
    private Map <Recluso, Integer> presos;
    private LinkedList<Guardia> guardias;
    private Genero genero;


    //Constructores
    public Pabellon(Sector sector, Genero genero) {
        ColeccionManager<Guardia,Recluso,Integer> manager = new ColeccionManager<>();
        this.guardias=manager.crearLinkedList();
        this.presos=manager.crearMapa();
        this.sector = sector;
        this.genero = genero;
    }


    //getters y setters

    public Map<Recluso, Integer> getPresos() {
        return presos;
    }
    public LinkedList<Guardia> getGuardias() {
        return guardias;
    }


    //otros metodos
    public void mostrarReclusos (Map<Recluso, Integer> reclusos){
        ColeccionManager <Object,Recluso,Integer> manager = new ColeccionManager<>();
       manager.mostrarMapa(reclusos);
    }
    public void mostrarGuardias (Map<Recluso, Integer> reclusos){
        ColeccionManager <Guardia,Object,Object> manager = new ColeccionManager<>();
        manager.mostrarLinkedList(this.guardias);
    }

    public void agregarRecluso(Recluso recluso) {
        if (recluso.getGenero()!=this.genero){
            System.out.println("Este recluso no es "+ this.genero);
        }
        else{
            this.presos.put(recluso, recluso.getPrisonerID());
            System.out.println("recluso encarcelado\n");

        }

    }
    public void agregarGuardia(Guardia guardia) {

        this.guardias.add(guardia);
    }

    public void quitarRecluso(Recluso recluso) {
        this.presos.remove(recluso);
    }
    public void quitarGuardia(Guardia guardia) {
        this.guardias.remove(guardia);
    }

    public void moverRecluso(Recluso recluso, Pabellon otroPabellon) {
        quitarRecluso(recluso);
        otroPabellon.agregarRecluso(recluso);
    }

}

