# Grafos: vértices, aristas y caminos

[Inicio](../../README.md) · [Anterior](../../modulo04/06-trie/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/02-matriz-de-adyacencia/README.md)


## Objetivo

Distinguir dirección, peso, ciclos y conectividad.

## Conceptos y razonamiento

Un grafo G=(V, E) relaciona vértices mediante aristas. En dirigido las aristas tienen orientación; en no dirigido relacionan ambos extremos. Un grafo simple no tiene lazos ni aristas paralelas. Un peso puede representar distancia o costo, pero su significado y unidades se definen.

Un camino conecta vértices mediante aristas según dirección. Un ciclo vuelve al inicio. El grado de un vértice no dirigido cuenta aristas incidentes; en dirigido se distingue entrada y salida. Para simple no dirigido, la suma de grados es 2 E. Un árbol no dirigido conectado con n vértices tiene n-1 aristas y no contiene ciclos.

No todos los grafos son árboles:pueden tener ciclos y componentes separadas. En dirigido, alcanzabilidad desde un origen no equivale a conectividad fuerte. Las prácticas siguientes trabajan grafos simples no dirigidos sin pesos salvo donde se indique, para mantener contratos claros.

## Caso resuelto y prueba de escritorio

V={A, B, C, D}, E={AB, AC, BC}. A, B, C forman un ciclo; D es aislado. Grados 2, 2, 2, 0, sumatoria 6=2×3. Hay dos componentes. Un recorrido desde A no visita D.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    public static void main(String[] args) {
        int[] grados = {
            2,2,2,0
        };
        int suma = 0;
        for (int grado : grados) suma += grado;
        System.out.println("Suma de grados: " + suma);
        System.out.println("Aristas: " + suma/2);
        System.out.println("Vértice aislado: " + (grados[3] == 0));
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
Suma de grados: 6
Aristas: 3
Vértice aislado: true
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

El ejemplo suma Θ(V) grados ya disponibles. Construirlos requiere analizar aristas.

## Errores frecuentes

- Llamar árbol a cualquier grafo conectado.
- Aplicar 2 E a una sola suma de grados de entrada en un dirigido.

## Ejercicio

Orienta AB, AC, BC de A→B, A→C, B→C. Calcula grados de entrada/salida y analiza alcanzabilidad.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Entradas A0, B1, C2, D0; salidas A2, B1, C0, D0. Desde A se llega B, C, pero desde C no se vuelve A. Cada suma de grados de entrada o salida es E=3.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo04/06-trie/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/02-matriz-de-adyacencia/README.md)
