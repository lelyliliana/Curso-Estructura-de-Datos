# Lista simple: crear e insertar

[Inicio](../../README.md) · [Anterior](../../modulo02/01-nodo-simple/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/03-buscar-y-actualizar/README.md)


## Objetivo

Mantener cabeza, cola y cantidad al insertar en extremos.

## Conceptos y razonamiento

Una lista con cabeza y cola permite insertar en ambos extremos sin recorrer. El invariante de vacía exige cabeza=cola=null y cantidad=0. En una lista no vacía, cola es el último nodo y cola.siguiente=null. Una sola inserción en vacía debe actualizar ambos extremos.

Al insertar al inicio, el nuevo nodo enlaza la antigua cabeza antes de sustituirla. Al insertar al final, se conecta desde la cola anterior y después se actualiza cola. El orden importa: perder la antigua referencia sin enlazarla puede desconectar la lista.

Cantidad es tamaño lógico y debe coincidir con nodos alcanzables. No es capacidad de un arreglo. La lista crece creando objetos y sigue limitada por recursos. Si después se incorpora borrado, el último nodo eliminado debe dejar ambos extremos en null.

## Caso resuelto y prueba de escritorio

Vacía→al Final 4 produce cabeza=cola=4, cantidad 1. al Inicio 2 enlaza 2→4. al Final 8 conecta 4→8 y cola=8. Resultado [2, 4, 8], cantidad 3.

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
        void alInicio(int dato) {
            Nodo n = new Nodo(dato);
            n.siguiente = cabeza;
            cabeza = n;
            if (cola == null) cola = n;
            cantidad++;
        }
        void alFinal(int dato) {
            Nodo n = new Nodo(dato);
            if (cola == null) cabeza = n;
            else cola.siguiente = n;
            cola = n;
            cantidad++;
        }
        String valores() {
            java.util.List<Integer> datos = new java.util.ArrayList<>();
            for (Nodo p = cabeza;  p != null;  p = p.siguiente) datos.add(p.dato);
            return datos.toString();
        }
    }
    public static void main(String[] args) {
        Lista l = new Lista();
        l.alFinal(4);
        l.alInicio(2);
        l.alFinal(8);
        System.out.println(l.valores());
        System.out.println(l.cantidad);
        System.out.println(l.cola.siguiente == null);
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
[2, 4, 8]
3
true
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Inserción en extremos Θ(1) con cola. Listar Θ(n) y salida auxiliar Θ(n).

## Errores frecuentes

- No actualizar cola en primera inserción.
- Perder la cabeza anterior antes de conectar el nuevo nodo.

## Ejercicio

Agrega insertarDespués de un nodo conocido. Explica cómo cambias cola si era el último.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

nuevo.siguiente=actual.siguiente; actual.siguiente=nuevo. Si actual==cola, coloca cola=nuevo. Aumenta cantidad. El contrato debe exigir que actual pertenece a la lista; validar pertenencia recorriendo agrega costo lineal.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo02/01-nodo-simple/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/03-buscar-y-actualizar/README.md)
