public class Main {
    static long factorial(int n) {
        if (n < 0 || n > 20) throw new IllegalArgumentException("Entero de 0 a 20");
        if (n == 0) return 1;
        return Math.multiplyExact(n, factorial(n - 1));
    }
    public static void main(String[] args) {
        for (int n : new int[]{
            0, 3, 5
        }) System.out.println(n + "! = " + factorial(n));
    }
}
