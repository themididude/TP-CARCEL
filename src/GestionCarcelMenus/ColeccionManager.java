package GestionCarcelMenus;

import funcionalidad.Tareas.Activable;

import java.util.*;

    public class ColeccionManager<T, K, V> {

        public ColeccionManager() {
        }

        ///=>----------- LO BASICO -------------<=////
        //--------> crear listas
        public List<T> crearLista() {return new ArrayList<>();
        }
        public LinkedList<T> crearLinkedList() {
            return new LinkedList<>();
        }


        public void agregarALista(List<T> lista, T elemento) {
            lista.add(elemento);
        }

        public <T extends Activable> void removerElemento(List<T> lista, T elemento)
        {
            elemento.setActivo(false);
        }

        public <T extends Activable> void recuperarElemento(List<T> lista, T elemento)
        {
            elemento.setActivo(true);
        }

        /// Mostrar todos los elementos (activos).
        public<T extends Activable> void mostrarLista(List<T> lista) {
            System.out.println("Elementos en la lista:");
            for (T elem : lista) {
                if(elem.isActivo()) System.out.println("- " + elem);
            }
        }

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

        // Crear un LinkedHashSet
        public LinkedHashSet<T> crearLinkedHashSet() {
            return new LinkedHashSet<>();
        }

        // Agregar un elemento al LinkedHashSet
        public void agregarALinkedHashSet(LinkedHashSet<T> conjunto, T elemento) {
            conjunto.add(elemento);
        }

        // Mostrar los elementos del LinkedHashSet
        public void mostrarLinkedHashSet(LinkedHashSet<T> conjunto) {
            System.out.println("Elementos en el LinkedHashSet:");
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
                System.out.println(entry.getKey() + " key-> \n" + entry.getValue());
            }
        }
    }
