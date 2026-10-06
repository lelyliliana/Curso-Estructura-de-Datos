public class Main {
    static class Nodo {
        int dato;
        Nodo(int dato) {
            this.dato = dato;
        }
    }
    static void reasignar(Nodo p) {
        p = new Nodo(99);
    }
    public static void main(String[] args) {
        Nodo a = new Nodo(4), b = a;
        b.dato = 8;
        reasignar(a);
        System.out.println(a.dato);
        b = new Nodo(2);
        System.out.println(a.dato + " " + b.dato);
        System.out.println(a == b);
    }
}
