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
        List<Integer> referencia=new ArrayList<>();
        Random r=new Random(17);
        for(int i=0; i<500; i++) {
            int d=r.nextInt(10);
            if(r.nextBoolean()) {
                l.agregar(d);
                referencia.add(d);
            }
            else verificar(l.eliminar(d)==referencia.remove(Integer.valueOf(d)));
            verificar(l.valores().equals(referencia));
            verificar(l.cantidad==referencia.size());
            verificar((l.cabeza==null)==referencia.isEmpty());
            verificar((l.cola==null)==referencia.isEmpty());
            if(l.cola!=null) verificar(l.cola.siguiente==null && l.cola.dato==referencia.get(referencia.size()-1));
        }
        while(!referencia.isEmpty()) {
            int d=referencia.remove(0);
            verificar(l.eliminar(d));
        }
        verificar(l.cabeza==null && l.cola==null && l.cantidad==0);
        System.out.println("Pruebas correctas");
    }
}
