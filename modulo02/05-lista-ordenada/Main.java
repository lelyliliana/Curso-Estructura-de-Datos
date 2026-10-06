import java.util.*;
public class Main {
    static class Nodo {
        int dato;
        Nodo siguiente;
        Nodo(int dato) {
            this.dato = dato;
        }
    }
    static Nodo insertar(Nodo cabeza, int clave) {
        Nodo previo = null, actual = cabeza;
        while (actual != null && actual.dato <= clave) {
            previo = actual;
            actual = actual.siguiente;
        }
        Nodo nuevo = new Nodo(clave);
        nuevo.siguiente = actual;
        if (previo == null) return nuevo;
        previo.siguiente = nuevo;
        return cabeza;
    }
    public static void main(String[] args) {
        Nodo cabeza = null;
        for (int d : new int[]{
            4, 2, 4, 1
        }) cabeza = insertar(cabeza, d);
        List<Integer> r = new ArrayList<>();
        for (Nodo p = cabeza;  p != null;  p = p.siguiente) r.add(p.dato);
        System.out.println(r);
    }
}
