public class Main {
    static void visitar(java.util.List<java.util.List<Integer>> g, int v, boolean[] visto, java.util.List<Integer> salida) {
        visto[v] = true;
        salida.add(v);
        for (int w : g.get(v)) if (!visto[w]) visitar(g,w,visto,salida);
    }
    static java.util.List<Integer> dfs(java.util.List<java.util.List<Integer>> g, int origen) {
        if (origen < 0 || origen >= g.size()) throw new IllegalArgumentException("Origen inválido");
        boolean[] visto = new boolean[g.size()];
        java.util.List<Integer> salida = new java.util.ArrayList<>();
        visitar(g,origen,visto,salida);
        return salida;
    }
    public static void main(String[] args) {
        java.util.List<java.util.List<Integer>> g = java.util.List.of(java.util.List.of(1,2),java.util.List.of(0,3),java.util.List.of(0,3),java.util.List.of(1,2),java.util.List.of());
        System.out.println(dfs(g,0));
    }
}
