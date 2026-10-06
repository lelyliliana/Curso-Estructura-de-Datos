import java.util.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
public class Prueba {
    static void verificar(boolean condicion) {
        if (!condicion) throw new AssertionError("Condición incumplida");
    }
    interface Accion {
        void ejecutar() throws Exception;
    }
    static void error(Class<? extends Throwable> tipo, Accion accion) throws Exception {
        try {
            accion.ejecutar();
        } catch (Throwable e) {
            if (tipo.isInstance(e)) return;
            throw new AssertionError("Error distinto",e);
        }
        throw new AssertionError("Faltó el error esperado");
    }
    public static void main(String[] args) throws Exception {
        for(int mascara=0; mascara<64; mascara++) {
            List<List<Integer>> g=new ArrayList<>();
            for(int i=0; i<4; i++)g.add(new ArrayList<>());
            int[][] dist=new int[4][4];
            for(int i=0; i<4; i++){
                Arrays.fill(dist[i],99);
                dist[i][i]=0;
            }
            int bit=0;
            for(int i=0; i<4; i++)for(int j=i+1; j<4; j++)if((mascara&(1<<bit++))!=0){
                g.get(i).add(j);
                g.get(j).add(i);
                dist[i][j]=dist[j][i]=1;
            }
            for(int k=0; k<4; k++)for(int i=0; i<4; i++)for(int j=0; j<4; j++)dist[i][j]=Math.min(dist[i][j],dist[i][k]+dist[k][j]);
            for(int a=0; a<4; a++)for(int b=0; b<4; b++) {
                List<Integer> camino=Main.ruta(g,a,b);
                if(dist[a][b]==99)verificar(camino.isEmpty());
                else {
                    verificar(camino.size()-1==dist[a][b]);
                    verificar(camino.get(0)==a && camino.get(camino.size()-1)==b);
                    for(int i=1; i<camino.size(); i++)verificar(g.get(camino.get(i-1)).contains(camino.get(i)));
                }
            }
        }
        error(IllegalArgumentException.class,()->Main.ruta(List.of(),0,0));
        System.out.println("Pruebas correctas");
    }
}
