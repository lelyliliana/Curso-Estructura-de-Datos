import java.util.*;
public class Main {
    record Arista(int a, int b) {
    }
    static class NodoArista {
        final int a, b;
        NodoArista siguienteA, siguienteB;
        NodoArista(int a, int b) {
            this.a=a;
            this.b=b;
        }
        NodoArista siguiente(int v) {
            if (v==a) return siguienteA;
            if (v==b) return siguienteB;
            throw new IllegalArgumentException("Vértice no incidente");
        }
        int otro(int v) {
            if (v==a) return b;
            if (v==b) return a;
            throw new IllegalArgumentException("Vértice no incidente");
        }
    }
    static class Multilista {
        final NodoArista[] cabezas;
        Multilista(int n) {
            if(n<0) throw new IllegalArgumentException("Tamaño no negativo");
            cabezas=new NodoArista[n];
        }
        void validar(int v) {
            if(v<0 || v>=cabezas.length) throw new IllegalArgumentException("Vértice inválido");
        }
        boolean conectar(int a, int b) {
            validar(a);
            validar(b);
            if(a==b) throw new IllegalArgumentException("Sin lazos");
            for(NodoArista p=cabezas[a]; p!=null; p=p.siguiente(a)) if(p.otro(a)==b) return false;
            NodoArista nuevo=new NodoArista(a,b);
            nuevo.siguienteA=cabezas[a];
            nuevo.siguienteB=cabezas[b];
            cabezas[a]=nuevo;
            cabezas[b]=nuevo;
            return true;
        }
        List<Integer> vecinos(int v) {
            validar(v);
            List<Integer> r=new ArrayList<>();
            for(NodoArista p=cabezas[v]; p!=null; p=p.siguiente(v)) r.add(p.otro(v));
            return r;
        }
    }
    public static void main(String[] args) {
        List<Arista> aristas = List.of(new Arista(0,1), new Arista(1,2));
        int[][] incidencia = new int[3][aristas.size()];
        List<List<Arista>> listas = new ArrayList<>();
        for (int i=0; i<3; i++) listas.add(new ArrayList<>());
        for (int j=0; j<aristas.size(); j++) {
            Arista e=aristas.get(j);
            incidencia[e.a()][j]=1;
            incidencia[e.b()][j]=1;
            listas.get(e.a()).add(e);
            listas.get(e.b()).add(e);
        }
        for (int[] fila : incidencia) System.out.println(Arrays.toString(fila));
        System.out.println(listas.get(0).get(0) == listas.get(1).get(0));
        Multilista m=new Multilista(3);
        m.conectar(0,1);
        m.conectar(1,2);
        for(int v=0; v<3; v++) System.out.println(m.vecinos(v));
    }
}
