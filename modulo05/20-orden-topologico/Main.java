import curso.Grafo;
public class Main {
    public static void main(String[] args){
        Grafo g=new Grafo(4,true);
        g.agregar(0,2,0);
        g.agregar(1,2,0);
        g.agregar(2,3,0);
        System.out.println(g.ordenTopologico());
        g.agregar(3,0,0);
        try{
            g.ordenTopologico();
        }catch(IllegalStateException e){
            System.out.println(e.getMessage());
        }
    }
}
