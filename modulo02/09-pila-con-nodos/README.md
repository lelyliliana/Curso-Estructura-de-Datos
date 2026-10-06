# Pila dinámica con nodos

[Inicio](../../README.md) · [Anterior](../../modulo02/08-pila-con-arreglo/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/10-dos-pilas-compartidas/README.md)


## Objetivo

Aplicar LIFO insertando y retirando por cabeza.

## Conceptos y razonamiento

Una pila enlazada sitúa cima en la cabeza de una cadena. Apilar crea un nodo que apunta a la cima anterior. Desapilar guarda su dato y mueve cima al siguiente. Ninguna operación recorre la cadena.

No se necesita una cola porque ambos cambios ocurren en el mismo extremo. En vacía, cima=null y cantidad=0. El último retiro deja ese estado. La ausencia de capacidad fija definida en esta clase no equivale a crecimiento infinito:pueden agotarse recursos.

El TAD es el mismo que la pila con arreglo, pero cambian representación, memoria y restricciones. El cliente debería poder razonar sobre LIFO sin depender de los enlaces internos. Java conserva objetos aún alcanzables; dejar de referenciar el nodo no promete un momento exacto de liberación.

## Caso resuelto y prueba de escritorio

Apilar 4, 8 crea 8→4. Desapilar retorna 8 y mueve cima a 4. El siguiente retiro retorna 4 y deja cima=null.

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
    static class Pila {
        Nodo cima;
        int cantidad;
        void apilar(int dato) {
            Nodo n = new Nodo(dato);
            n.siguiente = cima;
            cima = n;
            cantidad++;
        }
        int desapilar() {
            if (cima == null) throw new java.util.NoSuchElementException("Vacía");
            int dato = cima.dato;
            cima = cima.siguiente;
            cantidad--;
            return dato;
        }
    }
    public static void main(String[] args) {
        Pila p = new Pila();
        p.apilar(4);
        p.apilar(8);
        System.out.println(p.desapilar());
        System.out.println(p.desapilar());
        System.out.println(p.cima == null && p.cantidad == 0);
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
8
4
true
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Apilar/desapilar Θ(1) en el modelo de asignación elemental; memoria Θ(n). Asignaciones y recolección afectan tiempo real.

## Errores frecuentes

- Buscar el final para apilar innecesariamente.
- Confundir sin capacidad declarada con memoria ilimitada.

## Ejercicio

Implementa cima sin retirar y compara memoria con la pila fija cuando se usan pocos elementos.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Devuelve cima.dato después de validar no vacía. La enlazada reserva nodos para elementos presentes con sobrecosto por objeto/enlace; la fija reserva capacidad completa. No se afirma cuál consume menos bytes sin medir representación.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo02/08-pila-con-arreglo/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/10-dos-pilas-compartidas/README.md)
