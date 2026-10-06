import curso.Vector;
import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Secuencia<String> s=new Vector<>();
        s.agregar("rojo");
        s.agregar("azul");
        s.insertar(1,"verde");
        System.out.println(s.eliminar(0));
        System.out.println(s.tamano());
        System.out.println(s.obtener(0));
    }
}
