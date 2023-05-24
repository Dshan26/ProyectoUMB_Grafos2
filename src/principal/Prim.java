package principal;

import java.util.*;
import java.util.PriorityQueue;

public class Prim {
    public static List<String> prim(Grafo grafo, String origen) {
        Map<String, Double> distancias = new HashMap<>();
        Map<String, String> antecesores = new HashMap<>();
        Set<String> nodosVisitados = new HashSet<>();
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();

        // Inicializar distancias con infinito y antecesores con null para todos los nodos
        for (String nodo : grafo.getNodos()) {
            if (nodo.equals(origen)) {
                distancias.put(nodo, 0.0);
            } else {
                distancias.put(nodo, Double.POSITIVE_INFINITY);
            }
            antecesores.put(nodo, null);
        }

        // Agregar el nodo origen a la cola de prioridad
        colaPrioridad.offer(new Nodo(origen, 0.0));

        while (!colaPrioridad.isEmpty()) {
            Nodo nodoActual = colaPrioridad.poll();
            String nodo = nodoActual.getNombre();

            // Verificar si ya se ha visitado el nodo actual
            if (nodosVisitados.contains(nodo)) {
                continue;
            }

            nodosVisitados.add(nodo);

            // Obtener los vecinos del nodo actual
            List<String> vecinos = grafo.getVecinos(nodo);

            // Calcular la distancia desde el nodo actual a cada vecino y actualizar si es menor
            for (String vecino : vecinos) {
                double distancia = grafo.getDistancia(nodo, vecino);
                if (distancia < distancias.get(vecino)) {
                    distancias.put(vecino, distancia);
                    antecesores.put(vecino, nodo);
                    colaPrioridad.offer(new Nodo(vecino, distancia));
                }
            }
        }

        // Construir el árbol de expansión mínima desde el origen
        List<String> arbolExpansionMinima = new ArrayList<>();

        for (String nodo : antecesores.keySet()) {
            if (!nodo.equals(origen)) {
                arbolExpansionMinima.add(nodo);
            }
        }

        return arbolExpansionMinima;
    }

    private static class Nodo implements Comparable<Nodo> {
        private final String nombre;
        private final double distancia;

        public Nodo(String nombre, double distancia) {
            this.nombre = nombre;
            this.distancia = distancia;
        }

        public String getNombre() {
            return nombre;
        }

        public double getDistancia() {
            return distancia;
        }

        @Override
        public int compareTo(Nodo otro) {
            return Double.compare(distancia, otro.distancia);
        }
    }

}
