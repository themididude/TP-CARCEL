package GestionCarcelMenus;

import java.util.*;

    public class ColeccionManager<T, K, V> {

        public ColeccionManager() {
        }

        /// LIST
        // Crear una lista genérica
        public List<T> crearLista() {
            return new ArrayList<>();
        }

        // Agregar un elemento a la lista
        public void agregarALista(List<T> lista, T elemento) {
            lista.add(elemento);
        }

        public void removerElemento(List<T> lista, T elemento) {lista.remove(elemento);}

        // Mostrar todos los elementos de la lista
        public void mostrarLista(List<T> lista) {
            System.out.println("Elementos en la lista:");
            for (T elem : lista) {
                System.out.println("- " + elem);
            }
        }


        // Crear una lista enlazada (LinkedList)
        public LinkedList<T> crearLinkedList() {
            return new LinkedList<>();
        }

        // (Opcional) Agregar un elemento a la lista enlazada
        public void agregarALinkedList(LinkedList<T> listaEnlazada, T elemento) {
            listaEnlazada.add(elemento);
        }

        // (Opcional) Mostrar los elementos de la lista enlazada
        public void mostrarLinkedList(LinkedList<T> listaEnlazada) {
            System.out.println("Elementos en la LinkedList:");
            for (T elem : listaEnlazada) {
                System.out.println("- " + elem);
            }
        }


        /// SET
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


        ///  MAP
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
            System.out.println("Elementos en el mapa:\n");
            for (Map.Entry<K, V> entry : mapa.entrySet()) {
                System.out.println(entry.getKey() + " key-> \n" + entry.getValue()+"\n");
            }
        }
    }
