import curso.Vector;
import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Vector<String> v=new Vector<>();
        for(int i=0; i<5; i++)v.agregar("v"+i);
        v.verificar();
        System.out.println(v.copia());
        System.out.println("capacidad="+v.capacidad()+", copias="+v.copias());
        v.eliminar(2);
        System.out.println(v.copia());
    }
}
