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
        Main.Cola c=new Main.Cola();
        for(int ronda=0; ronda<20; ronda++) {
            for(int i=0; i<10; i++)c.encolar(i);
            for(int i=0; i<10; i++)verificar(c.desencolar()==i);
            verificar(c.frente==null && c.fin==null && c.cantidad==0);
        }
        error(NoSuchElementException.class,()->c.desencolar());
        System.out.println("Pruebas correctas");
    }
}
