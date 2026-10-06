import curso.Vector;
import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Vector<Integer> v=new Vector<>();
        for(int i=0; i<17; i++)v.agregar(i);
        System.out.println("tamaño="+v.tamano()+", capacidad="+v.capacidad()+", copias="+v.copias());
    }
}
