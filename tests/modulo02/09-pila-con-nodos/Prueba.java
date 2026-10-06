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
        Main.Pila p=new Main.Pila();
        for(int i=0; i<100; i++)p.apilar(i);
        for(int i=99; i>=0; i--)verificar(p.desapilar()==i);
        verificar(p.cima==null && p.cantidad==0);
        error(NoSuchElementException.class,()->p.desapilar());
        System.out.println("Pruebas correctas");
    }
}
