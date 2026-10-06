public class Main {
    public static void main(String[] args) {
        int[] grados = {
            2,2,2,0
        };
        int suma = 0;
        for (int grado : grados) suma += grado;
        System.out.println("Suma de grados: " + suma);
        System.out.println("Aristas: " + suma/2);
        System.out.println("Vértice aislado: " + (grados[3] == 0));
    }
}
