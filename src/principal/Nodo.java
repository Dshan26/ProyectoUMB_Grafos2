package principal;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nodo {
    private final String nombre;
    private final Map<Nodo, Double> vecinos;

    public Nodo(String nombre) {
        this.nombre = nombre;
        vecinos = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Nodo> getVecinos() {
        return new ArrayList<>(vecinos.keySet());
    }

    public void agregarVecino(Nodo vecino, double distancia) {
        vecinos.put(vecino, distancia);
    }

    public double getDistanciaHacia(Nodo vecino) {
        if (vecinos.containsKey(vecino)) {
            return vecinos.get(vecino);
        } else {
            throw new IllegalArgumentException("El nodo vecino no existe.");
        }
    }
}
