# Recursividad, caso base y progreso

[Inicio](../../README.md) · [Anterior](../../modulo02/13-deque-y-aplicaciones/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo03/02-arboles-generales/README.md)


## Objetivo

Seguir llamadas y justificar terminación.

## Conceptos y razonamiento

Una función recursiva se llama a sí misma de forma directa o indirecta. Para un cálculo que debe terminar necesita caso base y reducción del problema. La definición no significa que un objeto contenga físicamente una copia infinita de sí mismo.

Factorial satisface 0!=1 y n!=n×(n-1)! para n>0. Cada llamada espera un retorno antes de multiplicar. La pila almacena contexto de llamadas. El ejemplo admite 0..20 porque 21!no cabe en long; Math.multiplyExact detecta desbordamiento si una modificación rompe esa restricción.

La recursividad puede expresar elegantemente estructuras, pero no garantiza mejor rendimiento. Una alternativa iterativa para factorial usa memoria auxiliar constante. En Java no se presupone optimización de llamadas recursivas de cola. Profundidades grandes pueden producir StackOverflowError; no se resuelve cambiando el caso base sin estudiar el contrato.

## Caso resuelto y prueba de escritorio

factorial 3 espera 3×factorial 2; esta espera 2×factorial 1; esta espera 1×factorial 0. Retornos 1, 1, 2, 6. Para 0 la función retorna sin más llamadas.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static long factorial(int n) {
        if (n < 0 || n > 20) throw new IllegalArgumentException("Entero de 0 a 20");
        if (n == 0) return 1;
        return Math.multiplyExact(n, factorial(n - 1));
    }
    public static void main(String[] args) {
        for (int n : new int[]{
            0, 3, 5
        }) System.out.println(n + "! = " + factorial(n));
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
0! = 1
3! = 6
5! = 120
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Θ(n) multiplicaciones de long, Θ(n) pila recursiva; iterativo Θ(1) auxiliar. El contrato acota n, pero el análisis describe la familia del procedimiento.

## Errores frecuentes

- Aceptar negativos que nunca alcanzan 0.
- Afirmar que recursión siempre ahorra tiempo o memoria.

## Ejercicio

Implementa factorial iterativo con el mismo contrato y compara 0..20.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Inicializa resultado 1 y multiplica desde 2 hasta n con multiplyExact. Devuelve 1 para 0 y 1. Conserva validación. La versión iterativa evita una llamada por nivel.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo02/13-deque-y-aplicaciones/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo03/02-arboles-generales/README.md)
