# Componentes y verificación de recorridos

[Inicio](../../README.md) · [Anterior](../../modulo05/06-bfs-y-ruta-minima/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/08-grafos-ponderados/README.md)


## Objetivo

Cubrir todos los vértices incluidos los aislados.

## Conceptos y razonamiento

Para obtener componentes no dirigidas, se recorre cada vértice y se inicia una exploración solo si todavía no ha sido visitado. Un recorrido desde un origen no cubre automáticamente todo el grafo. Cada vértice aislado forma una componente de un elemento.

Las marcas se comparten dentro de esta operación entre exploraciones. Al comenzar una nueva consulta completa se crean de nuevo. El número de componentes de un grafo vacío es 0. Si el grafo tiene un vértice aislado, es 1 y puede considerarse conectado bajo la convención usual para un solo vértice; la definición para vacío debe declararse.

En dirigido este procedimiento siguiendo salidas no calcula componentes fuertemente conexas ni necesariamente las débilmente conexas. El contrato de esta lección es no dirigido. Las pruebas verifican partición:todos los vértices exactamente una vez y ninguna arista conecta grupos distintos.

## Caso resuelto y prueba de escritorio

Grafo 0–1 y 2 aislado genera [[0, 1], [2]]. El escaneo empieza 0, marca 0 y 1, omite 1 al encontrarse ya visto y empieza en 2. Total 2.

## Código completo

Archivo: [Main.java](Main.java).

```java
import java.util.*;
public class Main {
    static List<List<Integer>> componentes(List<List<Integer>> g) {
        boolean[] visto = new boolean[g.size()];
        List<List<Integer>> grupos = new ArrayList<>();
        for (int inicio=0; inicio<g.size(); inicio++) if (!visto[inicio]) {
            List<Integer> grupo = new ArrayList<>();
            Deque<Integer> cola = new ArrayDeque<>();
            visto[inicio]=true;
            cola.add(inicio);
            while (!cola.isEmpty()) {
                int v=cola.removeFirst();
                grupo.add(v);
                for (int w:g.get(v)) if (!visto[w]) {
                    visto[w]=true;
                    cola.addLast(w);
                }
            }
            grupos.add(grupo);
        }
        return grupos;
    }
    public static void main(String[] args) {
        System.out.println(componentes(List.of(List.of(1),List.of(0),List.of())));
        System.out.println(componentes(List.of()));
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
[[0, 1], [2]]
[]
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Θ(V+E) para grafo completo con listas; memoria Θ(V) marcas, cola y salida total.

## Errores frecuentes

- Reiniciar marcas para cada vértice y repetir componentes.
- Aplicar la misma regla a conectividad fuerte dirigida.

## Ejercicio

Cuenta componentes después de añadir una arista que una dos grupos y después de borrarla.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Con 0–1 y 2, se tienen 2; al añadir 1–2 queda 1; al borrar 1–2 vuelve 2. Recalcula marcas para cada operación. La respuesta no se conserva como caché sin una política de actualización.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo05/06-bfs-y-ruta-minima/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/08-grafos-ponderados/README.md)
