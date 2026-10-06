public class Main {
    static class Nodo {
        int clave;
        Nodo izq, der;
        Nodo(int clave) {
            this.clave = clave;
        }
    }
    static Nodo insertar(Nodo n, int clave) {
        if (n == null) return new Nodo(clave);
        if (clave < n.clave) n.izq = insertar(n.izq,clave);
        else if (clave > n.clave) n.der = insertar(n.der,clave);
        return n;
    }
    static void inorden(Nodo n, java.util.List<Integer> salida) {
        if (n == null) return;
        inorden(n.izq,salida);
        salida.add(n.clave);
        inorden(n.der,salida);
    }
    public static void main(String[] args) {
        Nodo raiz = null;
        for (int d : new int[]{
            4,2,6,1,3,2
        }) raiz = insertar(raiz,d);
        java.util.List<Integer> salida = new java.util.ArrayList<>();
        inorden(raiz,salida);
        System.out.println(salida);
    }
}
