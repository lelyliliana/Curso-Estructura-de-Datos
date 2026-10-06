public class Main {
    static class Pila {
        final int[] datos;
        int cantidad;
        Pila(int capacidad) {
            if (capacidad <= 0) throw new IllegalArgumentException("Capacidad positiva");
            datos = new int[capacidad];
        }
        void apilar(int dato) {
            if (cantidad == datos.length) throw new IllegalStateException("Llena");
            datos[cantidad++] = dato;
        }
        int desapilar() {
            if (cantidad == 0) throw new java.util.NoSuchElementException("Vacía");
            return datos[--cantidad];
        }
        int cima() {
            if (cantidad == 0) throw new java.util.NoSuchElementException("Vacía");
            return datos[cantidad-1];
        }
    }
    public static void main(String[] args) {
        Pila p = new Pila(2);
        p.apilar(4);
        p.apilar(8);
        System.out.println(p.cima());
        System.out.println(p.desapilar());
        System.out.println(p.desapilar());
        System.out.println(p.cantidad);
    }
}
