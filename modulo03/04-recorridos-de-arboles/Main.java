public class Main {
    static class Nodo {
        int dato;
        Nodo izq, der;
        Nodo(int dato) {
            this.dato = dato;
        }
    }
    static void recorrer(Nodo n, String tipo, java.util.List<Integer> salida) {
        if (n == null) return;
        if (tipo.equals("pre")) salida.add(n.dato);
        recorrer(n.izq,tipo,salida);
        if (tipo.equals("in")) salida.add(n.dato);
        recorrer(n.der,tipo,salida);
        if (tipo.equals("post")) salida.add(n.dato);
    }
    static java.util.List<Integer> recorrido(Nodo raiz, String tipo) {
        if (!java.util.Set.of("pre","in","post").contains(tipo)) throw new IllegalArgumentException("Tipo inválido");
        java.util.List<Integer> salida = new java.util.ArrayList<>();
        recorrer(raiz,tipo,salida);
        return salida;
    }
    public static void main(String[] args) {
        Nodo r = new Nodo(4);
        r.izq = new Nodo(2);
        r.der = new Nodo(6);
        r.izq.izq = new Nodo(1);
        r.izq.der = new Nodo(3);
        for (String tipo : new String[]{
            "pre","in","post"
        }) System.out.println(tipo + ": " + recorrido(r,tipo));
    }
}
