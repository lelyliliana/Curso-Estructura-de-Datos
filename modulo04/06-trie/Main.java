import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Trie t=new Trie();
        for(String s:List.of("casa","caso","casita"))t.agregar(s);
        System.out.println(t.contiene("cas"));
        System.out.println(t.prefijo("cas"));
        t.eliminar("casa");
        System.out.println(t.prefijo("cas"));
    }
}
