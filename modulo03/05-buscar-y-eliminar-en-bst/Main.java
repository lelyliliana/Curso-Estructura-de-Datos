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
    static boolean contiene(Nodo n, int clave) {
        while (n != null) {
            if (clave == n.clave) return true;
            n = clave < n.clave ? n.izq : n.der;
        }
        return false;
    }
    static Nodo eliminar(Nodo n, int clave) {
        if (n == null) return null;
        if (clave < n.clave) n.izq = eliminar(n.izq,clave);
        else if (clave > n.clave) n.der = eliminar(n.der,clave);
        else {
            if (n.izq == null) return n.der;
            if (n.der == null) return n.izq;
            Nodo sucesor = n.der;
            while (sucesor.izq != null) sucesor = sucesor.izq;
            n.clave = sucesor.clave;
            n.der = eliminar(n.der,sucesor.clave);
        }
        return n;
    }
    public static void main(String[] args) {
        Nodo r = null;
        for (int d : new int[]{
            4,2,6,1,3,5,7
        }) r = insertar(r,d);
        r = eliminar(r,4);
        java.util.List<Integer> salida = new java.util.ArrayList<>();
        inorden(r,salida);
        System.out.println(salida);
        System.out.println(contiene(r,4));
        System.out.println(contiene(r,5));
    }
}
