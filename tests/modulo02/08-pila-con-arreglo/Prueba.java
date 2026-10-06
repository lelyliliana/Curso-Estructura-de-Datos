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
        Main.Pila p=new Main.Pila(2);
        p.apilar(0);
        p.apilar(8);
        error(IllegalStateException.class,()->p.apilar(9));
        verificar(p.cantidad==2 && p.cima()==8);
        verificar(p.desapilar()==8);
        verificar(p.desapilar()==0);
        error(NoSuchElementException.class,()->p.desapilar());
        verificar(p.cantidad==0);
        p.apilar(4);
        verificar(p.desapilar()==4);
        error(IllegalArgumentException.class,()->new Main.Pila(0));
        System.out.println("Pruebas correctas");
    }
}
