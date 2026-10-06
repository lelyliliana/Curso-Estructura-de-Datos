# Deque y aplicaciones de pilas y colas

[Inicio](../../README.md) · [Anterior](../../modulo02/12-cola-con-nodos/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo03/01-recursividad-y-caso-base/README.md)


## Objetivo

Usar una API estándar y comprobar delimitadores anidados.

## Conceptos y razonamiento

Deque admite operaciones en ambos extremos. Como pila, push/pop trabajan sobre el inicio. Como cola, addLast/removeFirst producen FIFO. ArrayDeque usa un arreglo que puede crecer; no es la lista enlazada creada en las lecciones anteriores.

Una implementación estándar evita reescribir estructuras para cada aplicación. No admite null; peek/poll pueden retornarlo como señal de vacío, mientras get/remove pueden lanzar excepción. El comportamiento de cada método debe consultarse según el contrato. Sus operaciones usuales en extremos tienen costo amortizado constante, pero crecer puede copiar elementos.

Para validar delimitadores se apila cada apertura y se compara cada cierre con la última apertura pendiente. Al finalizar la pila debe estar vacía. El ejemplo reconoce() y [] e ignora otros caracteres; no analiza cadenas literales o comentarios de un lenguaje completo. Esa limitación es parte del contrato.

## Caso resuelto y prueba de escritorio

"([x])" apila(, luego [, retira [al cerrar] y retira(al cerrar). "([)]" falla porque) encuentra [como última apertura. "("falla por apertura pendiente.

## Código completo

Archivo: [Main.java](Main.java).

```java
import java.util.*;
public class Main {
    static boolean balanceado(String texto) {
        Deque<Character> pila = new ArrayDeque<>();
        for (char c : texto.toCharArray()) {
            if (c == '(' || c == '[') pila.push(c);
            else if (c == ')' || c == ']') {
                if (pila.isEmpty()) return false;
                char apertura = pila.pop();
                if ((c == ')' && apertura != '(') || (c == ']' && apertura != '[')) return false;
            }
        }
        return pila.isEmpty();
    }
    public static void main(String[] args) {
        for (String s : new String[]{
            "([x])", "([)]", "("
        }) System.out.println(s + " -> " + balanceado(s));
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
([x]) -> true
([)] -> false
( -> false
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Tiempo Θ(n), espacio O(n) en máximo anidamiento. Operaciones ArrayDeque amortizadas O(1).

## Errores frecuentes

- Usar una cola para emparejar la apertura más antigua.
- Dar por válida la entrada al terminar sin revisar aperturas pendientes.

## Ejercicio

Añade llaves{} y prueba vacío, cierre inicial y anidamiento de tres tipos.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Amplía apertura y correspondencia de cierre. Vacío es válido; "}"es inválido; "{[()]}"válido. Sigue ignorando otros caracteres según el contrato y no afirma validar todo un programa.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo02/12-cola-con-nodos/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo03/01-recursividad-y-caso-base/README.md)
