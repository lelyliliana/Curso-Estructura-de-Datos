# Listas de adyacencia

[Inicio](../../README.md) · [Anterior](../../modulo05/02-matriz-de-adyacencia/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/04-incidencia-y-multilistas/README.md)


## Objetivo

Enumerar vecinos almacenando solo enlaces presentes.

## Conceptos y razonamiento

Una lista de adyacencia almacena vecinos por vértice. En grafo no dirigido cada arista figura en dos listas. Los vértices aislados también necesitan una entrada vacía; no desaparecen por no tener conexiones.

La representación usa aquí listas estándar de enteros; se puede implementar cada lista con nodos, sin cambiar el significado. La colección exterior corresponde al conjunto de vértices. El orden de vecinos depende de inserción y afectará el orden del recorrido, aunque no la alcanzabilidad.

El contrato rechaza lazos y devuelve false para duplicados. La comprobación contains recorre vecinos, por lo que agregar no se describe como siempre constante. Una alternativa de conjuntos cambia el costo y el orden; se debe explicar por qué elegirla. La estructura interna no se modifica directamente desde clientes del proyecto final.

## Caso resuelto y prueba de escritorio

0 conecta 1 y 2, 1 conecta 2. Listas [1, 2], [0, 2], [0, 1], []. Suma de longitudes 6=2 E. Volver a conectar 0–1 no añade vecinos repetidos.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static class Grafo {
        final java.util.List<java.util.List<Integer>> ady = new java.util.ArrayList<>();
        Grafo(int n) {
            if (n < 0) throw new IllegalArgumentException("Tamaño no negativo");
            for (int i = 0;  i < n;  i++) ady.add(new java.util.ArrayList<>());
        }
        void validar(int v) {
            if (v < 0 || v >= ady.size()) throw new IllegalArgumentException("Vértice fuera de rango");
        }
        boolean conectar(int a, int b) {
            validar(a);
            validar(b);
            if (a == b) throw new IllegalArgumentException("Sin lazos");
            if (ady.get(a).contains(b)) return false;
            ady.get(a).add(b);
            ady.get(b).add(a);
            return true;
        }
    }
    public static void main(String[] args) {
        Grafo g = new Grafo(4);
        g.conectar(0,1);
        g.conectar(0,2);
        g.conectar(1,2);
        System.out.println(g.ady);
        System.out.println(g.conectar(0,1));
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
[[1, 2], [0, 2], [0, 1], []]
false
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Espacio Θ(V+E), enumerar vecinos Θ(grado(v)), comprobar arista O(grado(v)).

## Errores frecuentes

- Omitir vértices aislados.
- Declarar inserción constante cuando se busca duplicado linealmente.

## Ejercicio

Construye la matriz equivalente a partir de las listas y comprueba simetría.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Reserva V×V y marca [a][b]=true por cada vecino. Con contrato válido, ambas direcciones aparecen. Revisar todos los enlaces usaΘ(V+E), pero reservar la matriz añadeΘ(V²).

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo05/02-matriz-de-adyacencia/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/04-incidencia-y-multilistas/README.md)
