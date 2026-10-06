import curso.Vector;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Vector<Integer> v=new Vector<>();
        List<Integer> referencia=new ArrayList<>();
        for(int k:new int[]{30,10,20}){v.agregar(k);referencia.add(k);}
        v.eliminar(1);referencia.remove(1);
        v.verificar();
        System.out.println(v.copia());
        System.out.println(v.copia().equals(referencia));
    }
}
