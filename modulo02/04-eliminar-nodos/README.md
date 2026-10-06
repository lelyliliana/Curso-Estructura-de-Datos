# Lista simple: eliminación y casos extremos

[Inicio](../../README.md) · [Anterior](../../modulo02/03-buscar-y-actualizar/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/05-lista-ordenada/README.md)


## Objetivo

Desconectar la primera coincidencia conservando invariantes.

## Conceptos y razonamiento

Eliminar requiere localizar el nodo y su anterior. Si se elimina cabeza, se actualiza cabeza; si es cola, se actualiza cola al previo. En un único nodo, previo=null y siguiente=null, por lo que ambos extremos quedan null.

El algoritmo elimina solo la primera coincidencia y retorna bool para indicar éxito. Ausente produce false y no cambia cantidad. Desconectar siguiente del nodo eliminado hace explícita su separación; no libera memoria de manera inmediata ni invalida otras referencias que alguien conserve.

Los casos vacía, único, primero, intermedio, último y ausente deben comprobarse. Un recorrido que solo funciona al borrar un nodo intermedio no constituye una eliminación completa. La cantidad se reduce exactamente una vez al encontrar.

## Caso resuelto y prueba de escritorio

Lista [4, 8, 2]. Eliminar 4 mueve cabeza a 8. Eliminar 2 mueve cola a 8. Eliminar 8 deja vacía. Eliminar 99 no cambia nada. Duplicados requieren una regla diferente si se desea eliminar todos.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static class Nodo {
        int dato;
        Nodo siguiente;
        Nodo(int dato) {
            this.dato = dato;
        }
    }
    static class Lista {
        Nodo cabeza, cola;
        int cantidad;
        void agregar(int dato) {
            Nodo n = new Nodo(dato);
            if (cola == null) cabeza = n;
            else cola.siguiente = n;
            cola = n;
            cantidad++;
        }
        boolean eliminar(int clave) {
            Nodo previo = null, actual = cabeza;
            while (actual != null && actual.dato != clave) {
                previo = actual;
                actual = actual.siguiente;
            }
            if (actual == null) return false;
            if (previo == null) cabeza = actual.siguiente;
            else previo.siguiente = actual.siguiente;
            if (actual == cola) cola = previo;
            actual.siguiente = null;
            cantidad--;
            return true;
        }
        java.util.List<Integer> valores() {
            java.util.List<Integer> r = new java.util.ArrayList<>();
            for (Nodo p = cabeza;  p != null;  p = p.siguiente) r.add(p.dato);
            return r;
        }
    }
    public static void main(String[] args) {
        Lista l = new Lista();
        for (int d : new int[]{
            4, 8, 2
        }) l.agregar(d);
        System.out.println(l.eliminar(4));
        System.out.println(l.valores());
        l.eliminar(2);
        l.eliminar(8);
        System.out.println(l.cabeza == null && l.cola == null && l.cantidad == 0);
        System.out.println(l.eliminar(99));
    }
}
```

## Ejecución paso a paso

1. Prepara el JDK según la [guía de ambiente](../../docs/ambiente-y-herramientas.md).
2. Abre una terminal en la carpeta de esta lección. Cada Main.java es independiente: no compiles todos juntos.
3. Ejecuta este comando en PowerShell (Windows), Terminal (Ubuntu) o Terminal (macOS):

```bash
java Main.java
```

4. Compara con [esperado.txt](esperado.txt). Antes de modificar, explica qué instrucción produce cada resultado.

### Salida esperada

```text
true
[8, 2]
true
false
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Localizar y eliminar por clave peor Θ(n). Desconectar con previo conocido Θ(1). Retornar todos los datos cuesta Θ(n) memoria de salida.

## Errores frecuentes

- No actualizar cola al borrar último.
- Decrementar cantidad al no encontrar.

## Ejercicio

Implementa eliminarTodas sin saltarte coincidencias consecutivas.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Puede repetirse eliminar(clave) hasta false, pero costaría hasta cuadrático. En un solo recorrido, avanza actual siempre; previo solo cambia si conservas el nodo. Ajusta cola al final y cuenta eliminaciones. Prueba [4, 4, 8, 4].

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo02/03-buscar-y-actualizar/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/05-lista-ordenada/README.md)
