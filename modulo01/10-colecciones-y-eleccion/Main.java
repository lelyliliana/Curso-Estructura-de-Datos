import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        List<Integer> l=List.of(3,1,3);
        Set<Integer> s=new TreeSet<>(l);
        Map<String,String> m=new TreeMap<>();
        m.put("curso","primero");
        m.put("curso","actual");
        System.out.println(l);
        System.out.println(s);
        System.out.println(m);
    }
}
