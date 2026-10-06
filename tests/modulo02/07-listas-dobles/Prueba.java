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
        List<Integer> ref=new ArrayList<>();
        Random r=new Random(19);
        for(int i=0; i<500; i++) {
            int d=r.nextInt(10);
            if(r.nextBoolean()) {
                l.agregar(d);
                ref.add(d);
            } else verificar(l.eliminar(d)==ref.remove(Integer.valueOf(d)));
            verificar(l.valores(false).equals(ref));
            List<Integer> rev=new ArrayList<>(ref);
            Collections.reverse(rev);
            verificar(l.valores(true).equals(rev));
            verificar(l.cantidad==ref.size());
            Main.Nodo anterior=null;
            int n=0;
            for(Main.Nodo p=l.cabeza; p!=null; p=p.siguiente) {
                verificar(p.anterior==anterior);
                anterior=p;
                n++;
                verificar(n<=ref.size());
            }
            verificar(anterior==l.cola);
            if(l.cabeza!=null) verificar(l.cabeza.anterior==null);
            if(l.cola!=null) verificar(l.cola.siguiente==null);
        }
        while(!ref.isEmpty()) {
            int d=ref.remove(0);
            l.eliminar(d);
        } verificar(l.cabeza==null && l.cola==null);
        System.out.println("Pruebas correctas");
    }
}
