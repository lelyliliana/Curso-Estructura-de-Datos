public class Main {
    static class Grafo {
        final java.util.List<java.util.List<Integer>> ady = new java.util.ArrayList<>();
        Grafo(int n) {
            if (n < 0) throw new IllegalArgumentException("Tamaño no negativo");
            for (int i = 0;  i < n;  i++) ady.add(new java.util.ArrayList<>());
        }
        void validar(int v) {
            if (v < 0 || v >= ady.size()) throw new IllegalArgumentException("Vértice fuera de rango");
        }
        boolean conectar(int a, int b) {
            validar(a);
            validar(b);
            if (a == b) throw new IllegalArgumentException("Sin lazos");
            if (ady.get(a).contains(b)) return false;
            ady.get(a).add(b);
            ady.get(b).add(a);
            return true;
        }
    }
    public static void main(String[] args) {
        Grafo g = new Grafo(4);
        g.conectar(0,1);
        g.conectar(0,2);
        g.conectar(1,2);
        System.out.println(g.ady);
        System.out.println(g.conectar(0,1));
    }
}
