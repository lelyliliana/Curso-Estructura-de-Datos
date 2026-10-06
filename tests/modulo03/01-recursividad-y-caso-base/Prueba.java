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
        long f=1;
        for(int n=0; n<=20; n++) {
            if(n>0)f=Math.multiplyExact(f,n);
            verificar(Main.factorial(n)==f);
        }
        error(IllegalArgumentException.class,()->Main.factorial(-1));
        error(IllegalArgumentException.class,()->Main.factorial(21));
        System.out.println("Pruebas correctas");
    }
}
