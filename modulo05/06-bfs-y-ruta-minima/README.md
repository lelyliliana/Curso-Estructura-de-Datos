# BFS y ruta mínima en número de aristas

[Inicio](../../README.md) · [Anterior](../../modulo05/05-dfs/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/07-componentes-y-comprobacion/README.md)


## Objetivo

Usar FIFO y predecesores para reconstruir una ruta.

## Conceptos y razonamiento

BFS visita por capas de distancia desde origen usando una cola. Se marca cuando se encola, no después de retirar, para evitar introducir repetidamente el mismo vértice. Al descubrir un vecino, se guarda el predecesor por el que se llegó.

En un grafo sin pesos, BFS produce una ruta con mínimo número de aristas. No promete menor suma de pesos si las aristas tienen costos distintos. Entre varias rutas mínimas, el orden de vecinos decide cuál aparece. La reconstrucción sigue previos desde destino y después invierte la lista.

Si origen=destino, se retorna una lista con ese vértice, cero aristas. Si no hay conexión, se retorna lista vacía. Los índices fuera del conjunto son errores, no ausencia de ruta. La marca y el arreglo de previos deben ser nuevos para cada consulta.

## Caso resuelto y prueba de escritorio

Grafo 0–1, 0–2, 1–3, 2–3. La cola procesa 0, luego 1 y 2. Se descubre 3 desde 1 y la ruta es [0, 1, 3]. Un vértice 4 aislado no tiene ruta desde 0.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static java.util.List<Integer> ruta(java.util.List<java.util.List<Integer>> g, int origen, int destino) {
        if (origen < 0 || destino < 0 || origen >= g.size() || destino >= g.size()) throw new IllegalArgumentException("Vértice inválido");
        int[] previo = new int[g.size()];
        java.util.Arrays.fill(previo,-1);
        boolean[] visto = new boolean[g.size()];
        java.util.Deque<Integer> cola = new java.util.ArrayDeque<>();
        visto[origen] = true;
        cola.addLast(origen);
        while (!cola.isEmpty()) {
            int v = cola.removeFirst();
            if (v == destino) break;
            for (int w : g.get(v)) if (!visto[w]) {
                visto[w]=true;
                previo[w]=v;
                cola.addLast(w);
            }
        }
        java.util.List<Integer> salida = new java.util.ArrayList<>();
        if (!visto[destino]) return salida;
        for (int v=destino; v!=-1; v=previo[v]) salida.add(v);
        java.util.Collections.reverse(salida);
        return salida;
    }
    public static void main(String[] args) {
        java.util.List<java.util.List<Integer>> g = java.util.List.of(java.util.List.of(1,2),java.util.List.of(0,3),java.util.List.of(0,3),java.util.List.of(1,2),java.util.List.of());
        System.out.println(ruta(g,0,3));
        System.out.println(ruta(g,0,4));
        System.out.println(ruta(g,0,0));
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
[0, 1, 3]
[]
[0]
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

O(V+E) con listas, memoria O(V). Construir ruta agrega O(longitud de ruta), incluido en esa cota.

## Errores frecuentes

- Usar pila en vez de cola.
- Afirmar que BFS minimiza cualquier costo ponderado.

## Ejercicio

Agrega distancia en número de aristas y devuelve-1 cuando no hay ruta.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Si la lista está vacía, distancia=-1; si no, lista.size()-1. En el algoritmo puede guardarse distancia [w]=distancia [v]+1 al descubrir. No usar tamaño de lista como número de aristas.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo05/05-dfs/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/07-componentes-y-comprobacion/README.md)
