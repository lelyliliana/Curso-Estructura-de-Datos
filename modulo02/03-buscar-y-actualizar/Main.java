public class Main {
    static class Nodo {
        int dato;
        Nodo siguiente;
        Nodo(int dato) {
            this.dato = dato;
        }
    }
    static Nodo buscar(Nodo cabeza, int clave) {
        for (Nodo p = cabeza;  p != null;  p = p.siguiente) if (p.dato == clave) return p;
        return null;
    }
    public static void main(String[] args) {
        Nodo a = new Nodo(4);
        a.siguiente = new Nodo(8);
        a.siguiente.siguiente = new Nodo(4);
        Nodo encontrado = buscar(a, 4);
        if (encontrado != null) encontrado.dato = 2;
        for (Nodo p = a;  p != null;  p = p.siguiente) System.out.println(p.dato);
        System.out.println(buscar(a, 99) == null);
    }
}
