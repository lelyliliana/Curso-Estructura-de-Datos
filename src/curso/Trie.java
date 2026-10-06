package curso;
import java.util.*;
/** Conjunto de palabras ASCII a-z; recorrido lexicográfico. */
public final class Trie {
    private static final class Nodo {
        boolean terminal;
        final TreeMap<Character,Nodo> hijos=new TreeMap<>();
    }
    private final Nodo raiz=new Nodo();
    private static void validar(String s){
        Objects.requireNonNull(s);
        if(s.length()>80||!s.matches("[a-z]*"))throw new IllegalArgumentException("Hasta 80 letras a-z");
    }
    public void agregar(String s){
        validar(s);
        Nodo n=raiz;
        for(char c:s.toCharArray())n=n.hijos.computeIfAbsent(c,k->new Nodo());
        n.terminal=true;
    }
    private Nodo bajar(String s){
        Nodo n=raiz;
        for(char c:s.toCharArray()){
            n=n.hijos.get(c);
            if(n==null)return null;
        }return n;
    }
    public boolean contiene(String s){
        validar(s);
        Nodo n=bajar(s);
        return n!=null&&n.terminal;
    }
    public void eliminar(String s){
        validar(s);
        eliminar(raiz,s,0);
    }
    private static boolean eliminar(Nodo n,String s,int i){
        if(i==s.length())n.terminal=false;
        else {
            char c=s.charAt(i);
            Nodo h=n.hijos.get(c);
            if(h!=null&&eliminar(h,s,i+1))n.hijos.remove(c);
        }
        return !n.terminal&&n.hijos.isEmpty();
    }
    public List<String> prefijo(String s){
        validar(s);
        Nodo n=bajar(s);
        List<String> r=new ArrayList<>();
        if(n!=null)listar(n,new StringBuilder(s),r);
        return List.copyOf(r);
    }
    private static void listar(Nodo n,StringBuilder s,List<String> r){
        if(n.terminal)r.add(s.toString());
        for(var e:n.hijos.entrySet()){
            s.append(e.getKey());
            listar(e.getValue(),s,r);
            s.deleteCharAt(s.length()-1);
        }
    }
}
