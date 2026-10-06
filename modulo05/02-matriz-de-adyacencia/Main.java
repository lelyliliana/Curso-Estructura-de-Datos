public class Main {
    static class Grafo {
        final boolean[][] ady;
        Grafo(int n) {
            if (n < 0) throw new IllegalArgumentException("Tamaño no negativo");
            ady = new boolean[n][n];
        }
        void validar(int v) {
            if (v < 0 || v >= ady.length) throw new IllegalArgumentException("Vértice fuera de rango");
        }
        boolean conectar(int a, int b) {
            validar(a);
            validar(b);
            if (a == b) throw new IllegalArgumentException("Sin lazos");
            if (ady[a][b]) return false;
            ady[a][b] = ady[b][a] = true;
            return true;
        }
        int grado(int v) {
            validar(v);
            int total = 0;
            for (boolean e : ady[v]) if (e) total++;
            return total;
        }
    }
    public static void main(String[] args) {
        Grafo g = new Grafo(4);
        g.conectar(0,1);
        g.conectar(0,2);
        g.conectar(1,2);
        for (boolean[] fila : g.ady) System.out.println(java.util.Arrays.toString(fila));
        System.out.println(g.grado(0) + " " + g.grado(3));
    }
}
