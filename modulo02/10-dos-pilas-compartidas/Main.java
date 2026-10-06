public class Main {
    static class DosPilas {
        final int[] datos;
        int izquierda = -1, derecha;
        DosPilas(int capacidad) {
            if (capacidad <= 0) throw new IllegalArgumentException("Capacidad positiva");
            datos = new int[capacidad];
            derecha = capacidad;
        }
        void apilar(int pila, int dato) {
            validar(pila);
            if (izquierda + 1 == derecha) throw new IllegalStateException("Sin espacio compartido");
            if (pila == 1) datos[++izquierda] = dato;
            else datos[--derecha] = dato;
        }
        int desapilar(int pila) {
            validar(pila);
            if (pila == 1) {
                if (izquierda < 0) throw new java.util.NoSuchElementException("Pila 1 vacía");
                return datos[izquierda--];
            }
            if (derecha == datos.length) throw new java.util.NoSuchElementException("Pila 2 vacía");
            return datos[derecha++];
        }
        void validar(int pila) {
            if (pila != 1 && pila != 2) throw new IllegalArgumentException("Pila 1 o 2");
        }
    }
    public static void main(String[] args) {
        DosPilas p = new DosPilas(3);
        p.apilar(1,4);
        p.apilar(2,8);
        p.apilar(1,2);
        System.out.println(p.desapilar(1));
        System.out.println(p.desapilar(2));
        System.out.println(p.desapilar(1));
    }
}
