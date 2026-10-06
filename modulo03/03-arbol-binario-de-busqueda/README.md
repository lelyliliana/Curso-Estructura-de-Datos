# Árbol binario y árbol binario de búsqueda

[Inicio](../../README.md) · [Anterior](../../modulo03/02-arboles-generales/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo03/04-recorridos-de-arboles/README.md)


## Objetivo

Insertar conservando una regla de orden en todo el subárbol.

## Conceptos y razonamiento

Un árbol binario tiene como máximo dos hijos distinguibles:izquierdo y derecho. No todo árbol binario es de búsqueda. Un BST del contrato conserva claves menores en todo el subárbol izquierdo y mayores en todo el derecho. Comparar solo hijos inmediatos no basta para asegurar la propiedad global.

El ejemplo representa un conjunto de enteros:duplicados no crean un nodo nuevo. insertar retorna la raíz del subárbol resultante; el llamador debe conservarla, sobre todo cuando parte de null. La recursión decide un lado y vuelve a enlazarlo.

El árbol no se balancea. Insertar claves ascendentes crea una cadena de hijos derechos y empeora costos. Inorden produce claves ordenadas solo porque se mantiene la propiedad BST; en un árbol binario arbitrario puede producir cualquier secuencia.

## Caso resuelto y prueba de escritorio

Insertar 4, 2, 6, 1, 3 genera raíz 4, izquierda 2 con hijos 1 y 3, derecha 6. Inorden [1, 2, 3, 4, 6]. Insertar 2 otra vez conserva el conjunto.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static class Nodo {
        int clave;
        Nodo izq, der;
        Nodo(int clave) {
            this.clave = clave;
        }
    }
    static Nodo insertar(Nodo n, int clave) {
        if (n == null) return new Nodo(clave);
        if (clave < n.clave) n.izq = insertar(n.izq,clave);
        else if (clave > n.clave) n.der = insertar(n.der,clave);
        return n;
    }
    static void inorden(Nodo n, java.util.List<Integer> salida) {
        if (n == null) return;
        inorden(n.izq,salida);
        salida.add(n.clave);
        inorden(n.der,salida);
    }
    public static void main(String[] args) {
        Nodo raiz = null;
        for (int d : new int[]{
            4,2,6,1,3,2
        }) raiz = insertar(raiz,d);
        java.util.List<Integer> salida = new java.util.ArrayList<>();
        inorden(raiz,salida);
        System.out.println(salida);
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
[1, 2, 3, 4, 6]
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Insertar Θ(h+1), peor Θ(n), pila O(h). Balance apropiado puede reducir h a Θ(log n), pero esta clase no lo asegura.

## Errores frecuentes

- Afirmar todo árbol binario busca logarítmicamente.
- Ignorar el retorno cuando se inserta en raíz vacía.

## Ejercicio

Compara insertar 1, 2, 3, 4, 5 con insertar 3, 1, 5, 2, 4 y calcula altura.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

La primera forma una cadena con altura 4; la segunda tiene altura 2. Ambas conservan las mismas claves y el mismo inorden. La forma depende del orden de inserción.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo03/02-arboles-generales/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo03/04-recorridos-de-arboles/README.md)
