# DFS: recorrido en profundidad

[Inicio](../../README.md) · [Anterior](../../modulo05/04-incidencia-y-multilistas/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/06-bfs-y-ruta-minima/README.md)


## Objetivo

Marcar visitados antes de explorar vecinos para terminar con ciclos.

## Conceptos y razonamiento

DFS explora una rama antes de regresar a otras. La recursión funciona como pila de trabajo pendiente. Se marca el vértice antes de explorar vecinos, para que un ciclo no vuelva a iniciar indefinidamente su visita.

El recorrido desde un origen alcanza solo su componente en un grafo no dirigido. El orden de salida depende del orden de vecinos. No produce necesariamente una ruta con menor número de aristas. El contrato presupone que cada vecino es un índice válido y el grafo no cambia durante el recorrido.

Con listas de adyacencia, cada vértice alcanzado se visita una vez y cada pertenencia de arista se examina una vez. El arreglo visto se dimensiona para todos los vértices, incluso aislados. Un DFS recursivo muy profundo puede agotar pila; una versión iterativa usa una pila explícita y debe cuidar orden y momento de marcado.

## Caso resuelto y prueba de escritorio

Grafo listas [1, 2], [0, 3], [0, 3], [1, 2], []; DFS desde 0 visita 0, 1, 3, 2. La arista que vuelve 0 no provoca recursión porque visto 0 y aestrue. El vértice 4 no es alcanzable.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static void visitar(java.util.List<java.util.List<Integer>> g, int v, boolean[] visto, java.util.List<Integer> salida) {
        visto[v] = true;
        salida.add(v);
        for (int w : g.get(v)) if (!visto[w]) visitar(g,w,visto,salida);
    }
    static java.util.List<Integer> dfs(java.util.List<java.util.List<Integer>> g, int origen) {
        if (origen < 0 || origen >= g.size()) throw new IllegalArgumentException("Origen inválido");
        boolean[] visto = new boolean[g.size()];
        java.util.List<Integer> salida = new java.util.ArrayList<>();
        visitar(g,origen,visto,salida);
        return salida;
    }
    public static void main(String[] args) {
        java.util.List<java.util.List<Integer>> g = java.util.List.of(java.util.List.of(1,2),java.util.List.of(0,3),java.util.List.of(0,3),java.util.List.of(1,2),java.util.List.of());
        System.out.println(dfs(g,0));
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
[0, 1, 3, 2]
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Tiempo O(V+E) para inicialización y componente recorrida con listas, pila O(V), salida O(V). Con matriz recorrer vecinos cuesta O(V²) en recorrido completo.

## Errores frecuentes

- Marcar después de recursión y no terminar en un ciclo.
- Prometer ruta mínima mediante DFS.

## Ejercicio

Implementa DFS iterativo y compara conjuntos visitados, no solo secuencia.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Usa Deque<Integer>como pila. Puede insertarse vecinos en orden inverso para aproximar orden recursivo, pero marcar al insertar o al retirar afecta duplicados pendientes y secuencia. Define el procedimiento y comprueba visitados sin repetir.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo05/04-incidencia-y-multilistas/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/06-bfs-y-ruta-minima/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2005%20%C2%B7%20DFS%3A%20recorrido%20en%20profundidad%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo05%2F05-dfs%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
