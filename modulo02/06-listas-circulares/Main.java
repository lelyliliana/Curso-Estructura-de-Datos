public class Main {
    static class Nodo {
        int dato;
        Nodo siguiente;
        Nodo(int dato) {
            this.dato = dato;
        }
    }
    static class Lista {
        Nodo cola;
        int cantidad;
        void agregar(int dato) {
            Nodo n = new Nodo(dato);
            if (cola == null) n.siguiente = n;
            else {
                n.siguiente = cola.siguiente;
                cola.siguiente = n;
            }
            cola = n;
            cantidad++;
        }
        int quitarPrimero() {
            if (cola == null) throw new java.util.NoSuchElementException("Vacía");
            Nodo primero = cola.siguiente;
            if (primero == cola) cola = null;
            else cola.siguiente = primero.siguiente;
            primero.siguiente = null;
            cantidad--;
            return primero.dato;
        }
        java.util.List<Integer> valores() {
            java.util.List<Integer> r = new java.util.ArrayList<>();
            if (cola != null) {
                Nodo inicio = cola.siguiente, p = inicio;
                do {
                    r.add(p.dato);
                    p = p.siguiente;
                } while (p != inicio);
            }
            return r;
        }
    }
    public static void main(String[] args) {
        Lista l = new Lista();
        l.agregar(4);
        l.agregar(8);
        System.out.println(l.valores());
        System.out.println(l.quitarPrimero());
        System.out.println(l.quitarPrimero());
        System.out.println(l.valores());
    }
}
