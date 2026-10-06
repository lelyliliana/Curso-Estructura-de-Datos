public class Main {
    static int buscar(int[] datos, int clave) {
        int izq = 0, der = datos.length - 1;
        while (izq <= der) {
            int medio = izq + (der - izq) / 2;
            if (datos[medio] == clave) return medio;
            if (datos[medio] < clave) izq = medio + 1;
            else der = medio - 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] datos = {
            1, 3, 5, 7, 9
        };
        for (int clave : new int[]{
            7, 4, 1
        }) System.out.println(clave + " -> " + buscar(datos, clave));
    }
}
