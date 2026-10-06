public class Main {
    static class Nodo {
        int dato;
        Nodo siguiente;
        Nodo(int dato) {
            this.dato = dato;
        }
    }
    static class Pila {
        Nodo cima;
        int cantidad;
        void apilar(int dato) {
            Nodo n = new Nodo(dato);
            n.siguiente = cima;
            cima = n;
            cantidad++;
        }
        int desapilar() {
            if (cima == null) throw new java.util.NoSuchElementException("Vacía");
            int dato = cima.dato;
            cima = cima.siguiente;
            cantidad--;
            return dato;
        }
    }
    public static void main(String[] args) {
        Pila p = new Pila();
        p.apilar(4);
        p.apilar(8);
        System.out.println(p.desapilar());
        System.out.println(p.desapilar());
        System.out.println(p.cima == null && p.cantidad == 0);
    }
}
