# Matriz de adyacencia

[Inicio](../../README.md) · [Anterior](../../modulo05/01-conceptos-de-grafos/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/03-listas-de-adyacencia/README.md)


## Objetivo

Representar un grafo simple mediante una tabla V×V.

## Conceptos y razonamiento

La matriz de adyacencia usa fila origen y columna destino. En un grafo no dirigido es simétrica. Con boolean, true indica arista, false ausencia. La diagonal permanece false porque el contrato excluye lazos.

La consulta de una arista es directa, pero enumerar vecinos inspecciona una fila completa. Con pocos enlaces y muchos vértices, se reserva bastante espacio para ausencias. En grafos densos puede ser una representación conveniente. Una matriz de pesos no debe usar 0 como ausencia si un peso 0 es válido; se necesita otra convención.

Se valida ambos extremos antes de escribir. Una arista existente retorna false sin duplicar. Un vértice aislado conserva fila vacía. n=0 se admite como grafo vacío, pero cualquier consulta de vértice se rechaza.

## Caso resuelto y prueba de escritorio

Con 4 vértices y aristas 0–1, 0–2, 1–2, las filas 0..2 tienen dostrue y la fila 3 ninguno. Grado 0=2 y grado 3=0.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static class Grafo {
        final boolean[][] ady;
        Grafo(int n) {
            if (n < 0) throw new IllegalArgumentException("Tamaño no negativo");
            ady = new boolean[n][n];
        }
        void validar(int v) {
            if (v < 0 || v >= ady.length) throw new IllegalArgumentException("Vértice fuera de rango");
        }
        boolean conectar(int a, int b) {
            validar(a);
            validar(b);
            if (a == b) throw new IllegalArgumentException("Sin lazos");
            if (ady[a][b]) return false;
            ady[a][b] = ady[b][a] = true;
            return true;
        }
        int grado(int v) {
            validar(v);
            int total = 0;
            for (boolean e : ady[v]) if (e) total++;
            return total;
        }
    }
    public static void main(String[] args) {
        Grafo g = new Grafo(4);
        g.conectar(0,1);
        g.conectar(0,2);
        g.conectar(1,2);
        for (boolean[] fila : g.ady) System.out.println(java.util.Arrays.toString(fila));
        System.out.println(g.grado(0) + " " + g.grado(3));
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
[false, true, true, false]
[true, false, true, false]
[true, true, false, false]
[false, false, false, false]
2 0
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Espacio Θ(V²), conectar/consultar Θ(1), enumerar vecinos Θ(V). Inicializar matriz Θ(V²).

## Errores frecuentes

- Guardar solo una dirección en grafo no dirigido.
- Confundir matriz de pesos con boolean sin declarar ausencia.

## Ejercicio

Añade desconectar(a, b) y comprueba simetría después de borrar.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Valida extremos, consulta existencia y coloca ambas posicionesfalse. Retorna si había arista. Grados de cada extremo disminuyen 1 solo si existía.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo05/01-conceptos-de-grafos/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/03-listas-de-adyacencia/README.md)
