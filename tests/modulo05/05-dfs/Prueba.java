import java.util.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
public class Prueba {
    static void verificar(boolean condicion) {
        if (!condicion) throw new AssertionError("Condición incumplida");
    }
    interface Accion {
        void ejecutar() throws Exception;
    }
    static void error(Class<? extends Throwable> tipo, Accion accion) throws Exception {
        try {
            accion.ejecutar();
        } catch (Throwable e) {
            if (tipo.isInstance(e)) return;
            throw new AssertionError("Error distinto",e);
        }
        throw new AssertionError("Faltó el error esperado");
    }
    public static void main(String[] args) throws Exception {
        List<List<Integer>> g=List.of(List.of(1,2),List.of(0,3),List.of(0,3),List.of(1,2),List.of());
        List<Integer> r=Main.dfs(g,0);
        verificar(r.equals(List.of(0,1,3,2)));
        verificar(new HashSet<>(r).size()==r.size());
        verificar(Main.dfs(g,4).equals(List.of(4)));
        error(IllegalArgumentException.class,()->Main.dfs(g,5));
        System.out.println("Pruebas correctas");
    }
}
