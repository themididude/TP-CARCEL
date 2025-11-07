import java.util.*;

    public class ColeccionManager<T, K, V> {

        // Crear una lista genérica
        public List<T> crearLista() {
            return new ArrayList<>();
        }

        // Agregar un elemento a la lista
        public void agregarALista(List<T> lista, T elemento) {
            lista.add(elemento);
        }

        // Mostrar todos los elementos de la lista
        public void mostrarLista(List<T> lista) {
            System.out.println("Elementos en la lista:");
            for (T elem : lista) {
                System.out.println("- " + elem);
            }
        }

        // Crear un conjunto (Set)
        public Set<T> crearConjunto() {
            return new HashSet<>();
        }

        // Agregar un elemento al conjunto
        public void agregarAConjunto(Set<T> conjunto, T elemento) {
            conjunto.add(elemento);
        }

        // Mostrar conjunto
        public void mostrarConjunto(Set<T> conjunto) {
            System.out.println("Elementos en el conjunto:");
            for (T elem : conjunto) {
                System.out.println("- " + elem);
            }
        }

        // Crear un mapa genérico
        public Map<K, V> crearMapa() {
            return new HashMap<>();
        }

        // Agregar un elemento al mapa
        public void agregarAMapa(Map<K, V> mapa, K clave, V valor) {
            mapa.put(clave, valor);
        }

        // Mostrar mapa
        public void mostrarMapa(Map<K, V> mapa) {
            System.out.println("Elementos en el mapa:");
            for (Map.Entry<K, V> entry : mapa.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
    }
