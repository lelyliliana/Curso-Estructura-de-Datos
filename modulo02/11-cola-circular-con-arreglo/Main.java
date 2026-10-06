public class Main {
    static class Cola {
        final int[] datos;
        int frente, cantidad;
        Cola(int capacidad) {
            if (capacidad <= 0) throw new IllegalArgumentException("Capacidad positiva");
            datos = new int[capacidad];
        }
        void encolar(int dato) {
            if (cantidad == datos.length) throw new IllegalStateException("Llena");
            datos[(frente + cantidad) % datos.length] = dato;
            cantidad++;
        }
        int desencolar() {
            if (cantidad == 0) throw new java.util.NoSuchElementException("Vacía");
            int dato = datos[frente];
            frente = (frente + 1) % datos.length;
            cantidad--;
            return dato;
        }
    }
    public static void main(String[] args) {
        Cola c = new Cola(3);
        c.encolar(4);
        c.encolar(8);
        c.encolar(2);
        System.out.println(c.desencolar());
        c.encolar(6);
        while (c.cantidad > 0) System.out.println(c.desencolar());
    }
}
