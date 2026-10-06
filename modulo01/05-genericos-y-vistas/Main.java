import curso.Vector;
import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        String a=new String("aula"),b=new String("aula");
        System.out.println(a==b);
        System.out.println(a.equals(b));
        Vector<String> v=new Vector<>();
        v.agregar(a);
        List<String> copia=v.copia();
        v.agregar("otro");
        System.out.println(copia);
        System.out.println(v.copia());
    }
}
