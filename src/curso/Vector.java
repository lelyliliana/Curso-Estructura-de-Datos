package curso;
import java.util.*;
/** Arreglo dinámico genérico con crecimiento geométrico. */
public final class Vector<T> implements Secuencia<T> {
    private Object[] datos=new Object[4];
    private int cantidad;
    private long referenciasCopiadas;
    public int tamano(){
        return cantidad;
    }
    public int capacidad(){
        return datos.length;
    }
    public long copias(){
        return referenciasCopiadas;
    }
    private void indice(int i){
        if(i<0||i>=cantidad)throw new IndexOutOfBoundsException("Índice fuera de la secuencia");
    }
    @SuppressWarnings("unchecked")
    public T obtener(int i){
        indice(i);
        return (T)datos[i];
    }
    private void reservar(){
        if(cantidad<datos.length)return;
        referenciasCopiadas+=cantidad;
        datos=Arrays.copyOf(datos,Math.multiplyExact(datos.length,2));
    }
    public void agregar(T valor){
        insertar(cantidad,valor);
    }
    public void insertar(int i,T valor){
        if(i<0||i>cantidad)throw new IndexOutOfBoundsException("Posición de inserción inválida");
        Objects.requireNonNull(valor);
        reservar();
        System.arraycopy(datos,i,datos,i+1,cantidad-i);
        datos[i]=valor;
        cantidad++;
    }
    public T eliminar(int i){
        T anterior=obtener(i);
        System.arraycopy(datos,i+1,datos,i,cantidad-i-1);
        datos[--cantidad]=null;
        return anterior;
    }
    public void cambiar(int i,T valor){
        indice(i);
        datos[i]=Objects.requireNonNull(valor);
    }
    public List<T> copia(){
        List<T> r=new ArrayList<>();
        for(int i=0; i<cantidad; i++)r.add(obtener(i));
        return List.copyOf(r);
    }
    public void verificar(){
        if(cantidad<0||cantidad>datos.length)throw new IllegalStateException("Tamaño lógico");
        for(int i=0; i<datos.length; i++)if((i<cantidad)!=(datos[i]!=null))throw new IllegalStateException("Región activa");
    }
}
