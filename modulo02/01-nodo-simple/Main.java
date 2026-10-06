public class Main {
    static class Nodo {
        int dato;
        Nodo siguiente;
        Nodo(int dato) {
            this.dato = dato;
        }
    }
    public static void main(String[] args) {
        Nodo a = new Nodo(4), b = new Nodo(8), c = new Nodo(2);
        a.siguiente = b;
        b.siguiente = c;
        for (Nodo p = a;  p != null;  p = p.siguiente) System.out.println(p.dato);
        System.out.println("Cabeza: " + a.dato);
    }
}
