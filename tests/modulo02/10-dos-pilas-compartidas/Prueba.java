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
        Main.DosPilas p=new Main.DosPilas(3);
        p.apilar(2,4);
        p.apilar(2,8);
        p.apilar(2,2);
        error(IllegalStateException.class,()->p.apilar(1,9));
        verificar(p.desapilar(2)==2);
        p.apilar(1,9);
        verificar(p.desapilar(1)==9);
        verificar(p.desapilar(2)==8);
        verificar(p.desapilar(2)==4);
        error(NoSuchElementException.class,()->p.desapilar(1));
        error(NoSuchElementException.class,()->p.desapilar(2));
        error(IllegalArgumentException.class,()->p.apilar(3,1));
        System.out.println("Pruebas correctas");
    }
}
