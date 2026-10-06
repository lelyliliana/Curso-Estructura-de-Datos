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
        Main.Lista l=new Main.Lista();
        Deque<Integer> ref=new ArrayDeque<>();
        Random r=new Random(18);
        for(int i=0; i<300; i++) {
            if(ref.isEmpty() || r.nextBoolean()) {
                int d=r.nextInt(5);
                l.agregar(d);
                ref.addLast(d);
            }
            else verificar(l.quitarPrimero()==ref.removeFirst());
            verificar(l.valores().equals(new ArrayList<>(ref)));
            verificar(l.cantidad==ref.size());
            if(l.cola!=null) {
                Main.Nodo p=l.cola.siguiente;
                for(int j=0; j<l.cantidad; j++) p=p.siguiente;
                verificar(p==l.cola.siguiente);
            }
        }
        while(!ref.isEmpty()) verificar(l.quitarPrimero()==ref.removeFirst());
        error(NoSuchElementException.class,()->l.quitarPrimero());
        verificar(l.cola==null && l.cantidad==0);
        System.out.println("Pruebas correctas");
    }
}
