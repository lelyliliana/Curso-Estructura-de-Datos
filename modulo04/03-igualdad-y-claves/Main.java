import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        record Clave(int id){
        };
        Clave a=new Clave(7),b=new Clave(7);
        Map<Clave,String> m=new HashMap<>();
        m.put(a,"original");
        m.put(b,"actual");
        System.out.println(a.equals(b));
        System.out.println(a.hashCode()==b.hashCode());
        System.out.println(m.size());
        System.out.println(m.get(new Clave(7)));
    }
}
