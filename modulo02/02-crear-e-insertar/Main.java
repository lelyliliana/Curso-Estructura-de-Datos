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
        void alInicio(int dato) {
            Nodo n = new Nodo(dato);
            n.siguiente = cabeza;
            cabeza = n;
            if (cola == null) cola = n;
            cantidad++;
        }
        void alFinal(int dato) {
            Nodo n = new Nodo(dato);
            if (cola == null) cabeza = n;
            else cola.siguiente = n;
            cola = n;
            cantidad++;
        }
        String valores() {
            java.util.List<Integer> datos = new java.util.ArrayList<>();
            for (Nodo p = cabeza;  p != null;  p = p.siguiente) datos.add(p.dato);
            return datos.toString();
        }
    }
    public static void main(String[] args) {
        Lista l = new Lista();
        l.alFinal(4);
        l.alInicio(2);
        l.alFinal(8);
        System.out.println(l.valores());
        System.out.println(l.cantidad);
        System.out.println(l.cola.siguiente == null);
    }
}
