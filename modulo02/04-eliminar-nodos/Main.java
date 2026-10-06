public class Main {
    static class Nodo {
        int dato;
        Nodo siguiente;
        Nodo(int dato) {
            this.dato = dato;
        }
    }
    static class Lista {
        Nodo cabeza, cola;
        int cantidad;
        void agregar(int dato) {
            Nodo n = new Nodo(dato);
            if (cola == null) cabeza = n;
            else cola.siguiente = n;
            cola = n;
            cantidad++;
        }
        boolean eliminar(int clave) {
            Nodo previo = null, actual = cabeza;
            while (actual != null && actual.dato != clave) {
                previo = actual;
                actual = actual.siguiente;
            }
            if (actual == null) return false;
            if (previo == null) cabeza = actual.siguiente;
            else previo.siguiente = actual.siguiente;
            if (actual == cola) cola = previo;
            actual.siguiente = null;
            cantidad--;
            return true;
        }
        java.util.List<Integer> valores() {
            java.util.List<Integer> r = new java.util.ArrayList<>();
            for (Nodo p = cabeza;  p != null;  p = p.siguiente) r.add(p.dato);
            return r;
        }
    }
    public static void main(String[] args) {
        Lista l = new Lista();
        for (int d : new int[]{
            4, 8, 2
        }) l.agregar(d);
        System.out.println(l.eliminar(4));
        System.out.println(l.valores());
        l.eliminar(2);
        l.eliminar(8);
        System.out.println(l.cabeza == null && l.cola == null && l.cantidad == 0);
        System.out.println(l.eliminar(99));
    }
}
