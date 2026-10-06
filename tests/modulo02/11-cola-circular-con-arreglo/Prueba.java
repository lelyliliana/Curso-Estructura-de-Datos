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
        for(int cap:new int[]{
            1,3,7
        }) {
            Main.Cola c=new Main.Cola(cap);
            Deque<Integer> ref=new ArrayDeque<>();
            Random r=new Random(20);
            for(int i=0; i<1000; i++) {
                if(ref.isEmpty() || (ref.size()<cap && r.nextBoolean())) {
                    c.encolar(i);
                    ref.addLast(i);
                }
                else verificar(c.desencolar()==ref.removeFirst());
                verificar(c.cantidad==ref.size());
            }
            while(!ref.isEmpty()) verificar(c.desencolar()==ref.removeFirst());
            error(NoSuchElementException.class,()->c.desencolar());
            for(int i=0; i<cap; i++)c.encolar(i);
            error(IllegalStateException.class,()->c.encolar(99));
            verificar(c.cantidad==cap);
        }
        System.out.println("Pruebas correctas");
    }
}
