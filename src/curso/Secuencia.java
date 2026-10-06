package curso;
/** Secuencia indexada sin elementos null. Índices válidos: 0 <= i < tamaño. */
public interface Secuencia<T> {
    int tamano();
    T obtener(int i);
    void agregar(T valor);
    void insertar(int i,T valor);
    T eliminar(int i);
}
