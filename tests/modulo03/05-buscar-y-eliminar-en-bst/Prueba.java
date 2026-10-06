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
        Main.Nodo raiz=null;
        Set<Integer> ref=new TreeSet<>();
        Random r=new Random(21);
        for(int i=0; i<600; i++) {
            int d=r.nextInt(101)-50;
            if(r.nextBoolean()){
                raiz=Main.insertar(raiz,d);
                ref.add(d);
            }else{
                raiz=Main.eliminar(raiz,d);
                ref.remove(d);
            }
            List<Integer> actual=new ArrayList<>();
            Main.inorden(raiz,actual);
            verificar(actual.equals(new ArrayList<>(ref)));
            for(int c=-52; c<=52; c++)verificar(Main.contiene(raiz,c)==ref.contains(c));
        }
        for(int d:new ArrayList<>(ref))raiz=Main.eliminar(raiz,d);
        verificar(raiz==null);
        raiz=Main.insertar(null,Integer.MIN_VALUE);
        raiz=Main.insertar(raiz,Integer.MAX_VALUE);
        raiz=Main.eliminar(raiz,Integer.MIN_VALUE);
        verificar(raiz.clave==Integer.MAX_VALUE);
        System.out.println("Pruebas correctas");
    }
}
