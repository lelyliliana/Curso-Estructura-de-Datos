import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        for(int n:new int[]{
            4,8,16
        }){
            int[] a=new int[n];
            int visitas=0;
            for(int x:a){
                visitas++;
                if(x==99)break;
            }System.out.println(n+" -> "+visitas);
        }
    }
}
