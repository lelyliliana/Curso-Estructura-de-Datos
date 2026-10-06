import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        int[] a=new int[6];
        a[0]=2;
        a[1]=4;
        a[2]=8;
        int n=3;
        System.arraycopy(a,2,a,3,n-2);
        a[2]=6;
        n++;
        System.out.println(Arrays.toString(Arrays.copyOf(a,n)));
        System.out.println("tamaño="+n+", capacidad="+a.length);
    }
}
