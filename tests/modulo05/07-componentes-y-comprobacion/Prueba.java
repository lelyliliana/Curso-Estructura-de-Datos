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
        verificar(Main.componentes(List.of()).isEmpty());
        verificar(Main.componentes(List.of(List.of(1),List.of(0),List.of())).equals(List.of(List.of(0,1),List.of(2))));
        verificar(Main.componentes(List.of(List.of(),List.of(),List.of())).size()==3);
        System.out.println("Pruebas correctas");
    }
}
