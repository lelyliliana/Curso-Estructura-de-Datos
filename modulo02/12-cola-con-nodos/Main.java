public class Main {
    static class Nodo {
        int dato;
        Nodo siguiente;
        Nodo(int dato) {
            this.dato = dato;
        }
    }
    static class Cola {
        Nodo frente, fin;
        int cantidad;
        void encolar(int dato) {
            Nodo n = new Nodo(dato);
            if (fin == null) frente = n;
            else fin.siguiente = n;
            fin = n;
            cantidad++;
        }
        int desencolar() {
            if (frente == null) throw new java.util.NoSuchElementException("Vacía");
            int dato = frente.dato;
            frente = frente.siguiente;
            cantidad--;
            if (frente == null) fin = null;
            return dato;
        }
    }
    public static void main(String[] args) {
        Cola c = new Cola();
        c.encolar(4);
        c.encolar(8);
        System.out.println(c.desencolar());
        System.out.println(c.desencolar());
        System.out.println(c.frente == null && c.fin == null);
        c.encolar(2);
        System.out.println(c.desencolar());
    }
}
