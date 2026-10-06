import java.util.*;
public class Main {
    static List<List<Integer>> componentes(List<List<Integer>> g) {
        boolean[] visto = new boolean[g.size()];
        List<List<Integer>> grupos = new ArrayList<>();
        for (int inicio=0; inicio<g.size(); inicio++) if (!visto[inicio]) {
            List<Integer> grupo = new ArrayList<>();
            Deque<Integer> cola = new ArrayDeque<>();
            visto[inicio]=true;
            cola.add(inicio);
            while (!cola.isEmpty()) {
                int v=cola.removeFirst();
                grupo.add(v);
                for (int w:g.get(v)) if (!visto[w]) {
                    visto[w]=true;
                    cola.addLast(w);
                }
            }
            grupos.add(grupo);
        }
        return grupos;
    }
    public static void main(String[] args) {
        System.out.println(componentes(List.of(List.of(1),List.of(0),List.of())));
        System.out.println(componentes(List.of()));
    }
}
