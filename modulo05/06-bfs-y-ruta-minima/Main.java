public class Main {
    static java.util.List<Integer> ruta(java.util.List<java.util.List<Integer>> g, int origen, int destino) {
        if (origen < 0 || destino < 0 || origen >= g.size() || destino >= g.size()) throw new IllegalArgumentException("Vértice inválido");
        int[] previo = new int[g.size()];
        java.util.Arrays.fill(previo,-1);
        boolean[] visto = new boolean[g.size()];
        java.util.Deque<Integer> cola = new java.util.ArrayDeque<>();
        visto[origen] = true;
        cola.addLast(origen);
        while (!cola.isEmpty()) {
            int v = cola.removeFirst();
            if (v == destino) break;
            for (int w : g.get(v)) if (!visto[w]) {
                visto[w]=true;
                previo[w]=v;
                cola.addLast(w);
            }
        }
        java.util.List<Integer> salida = new java.util.ArrayList<>();
        if (!visto[destino]) return salida;
        for (int v=destino; v!=-1; v=previo[v]) salida.add(v);
        java.util.Collections.reverse(salida);
        return salida;
    }
    public static void main(String[] args) {
        java.util.List<java.util.List<Integer>> g = java.util.List.of(java.util.List.of(1,2),java.util.List.of(0,3),java.util.List.of(0,3),java.util.List.of(1,2),java.util.List.of());
        System.out.println(ruta(g,0,3));
        System.out.println(ruta(g,0,4));
        System.out.println(ruta(g,0,0));
    }
}
