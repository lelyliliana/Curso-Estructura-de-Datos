public class Main {
    static class Nodo {
        final String dato;
        final java.util.List<Nodo> hijos = new java.util.ArrayList<>();
        Nodo(String dato) {
            this.dato = dato;
        }
    }
    static int cantidad(Nodo n) {
        if (n == null) return 0;
        int total = 1;
        for (Nodo hijo : n.hijos) total += cantidad(hijo);
        return total;
    }
    static void preorden(Nodo n, java.util.List<String> salida) {
        if (n == null) return;
        salida.add(n.dato);
        for (Nodo hijo : n.hijos) preorden(hijo, salida);
    }
    public static void main(String[] args) {
        Nodo a = new Nodo("A"), b = new Nodo("B"), c = new Nodo("C");
        a.hijos.add(b);
        a.hijos.add(c);
        b.hijos.add(new Nodo("D"));
        java.util.List<String> salida = new java.util.ArrayList<>();
        preorden(a,salida);
        System.out.println(salida);
        System.out.println(cantidad(a));
    }
}
