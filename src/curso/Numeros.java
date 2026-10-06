package curso;

/** Validación numérica compartida por geometría y estadística. */
public final class Numeros {
    private Numeros(){}
    public static void finito(double x){
        if(!Double.isFinite(x))throw new IllegalArgumentException("Número no finito");
    }
}
