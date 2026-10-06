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
        Random r=new Random(7);
        for(int ensayo=0; ensayo<100; ensayo++) {
            int[] a=new int[r.nextInt(40)];
            for(int i=0; i<a.length; i++) a[i]=r.nextInt(20)-10;
            Arrays.sort(a);
            for(int clave=-12; clave<=12; clave++) {
                int indice=Main.buscar(a,clave);
                boolean presente=false;
                for(int v:a) if(v==clave) presente=true;
                verificar((indice>=0)==presente);
                if(indice>=0) verificar(a[indice]==clave);
            }
        }
        System.out.println("Pruebas correctas");
    }
}
