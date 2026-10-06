public class Main {
    static class Nodo {
        int dato;
        Nodo anterior, siguiente;
        Nodo(int dato) {
            this.dato = dato;
        }
    }
    static class Lista {
        Nodo cabeza, cola;
        int cantidad;
        void agregar(int dato) {
            Nodo n = new Nodo(dato);
            n.anterior = cola;
            if (cola == null) cabeza = n;
            else cola.siguiente = n;
            cola = n;
            cantidad++;
        }
        boolean eliminar(int clave) {
            Nodo p = cabeza;
            while (p != null && p.dato != clave) p = p.siguiente;
            if (p == null) return false;
            if (p.anterior == null) cabeza = p.siguiente;
            else p.anterior.siguiente = p.siguiente;
            if (p.siguiente == null) cola = p.anterior;
            else p.siguiente.anterior = p.anterior;
            p.anterior = p.siguiente = null;
            cantidad--;
            return true;
        }
        java.util.List<Integer> valores(boolean inverso) {
            java.util.List<Integer> r = new java.util.ArrayList<>();
            for (Nodo p = inverso ? cola : cabeza;  p != null;  p = inverso ? p.anterior : p.siguiente) r.add(p.dato);
            return r;
        }
    }
    public static void main(String[] args) {
        Lista l = new Lista();
        for (int d : new int[]{
            4, 8, 2
        }) l.agregar(d);
        System.out.println(l.valores(false));
        System.out.println(l.valores(true));
        l.eliminar(8);
        System.out.println(l.valores(false));
        System.out.println(l.valores(true));
    }
}
