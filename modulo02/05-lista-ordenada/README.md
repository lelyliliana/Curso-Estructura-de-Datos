# Lista simple ordenada y duplicados

[Inicio](../../README.md) · [Anterior](../../modulo02/04-eliminar-nodos/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/06-listas-circulares/README.md)


## Objetivo

Insertar una clave conservando el orden de la cadena.

## Conceptos y razonamiento

En una lista ordenada ascendente cada dato es menor o igual al siguiente. La inserción localiza la primera posición con dato mayor que la clave para colocarla antes. Este contrato admite duplicados y los coloca después de los existentes.

La regla de igualdad afecta estabilidad cuando los nodos contienen registros: avanzar mientras dato<=clave ubica el nuevo después de claves iguales. El nodo nuevo enlaza al siguiente antes de que el previo lo enlace a él. Cabeza requiere un caso especial si no hubo previo.

Un orden por clave permite detener búsqueda cuando se supera la clave, pero no ofrece acceso directo al centro. La búsqueda binaria típica de arreglo no produce automáticamente costo logarítmico en una lista enlazada. Para polinomios puede ordenarse por exponente y combinar términos iguales mediante una regla adicional, no almacenar duplicados sin significado.

## Caso resuelto y prueba de escritorio

Insertar 4, 2, 4, 1 produce [1, 2, 4, 4]. Para el segundo 4 se avanza sobre los iguales. Insertar al final debe enlazar null como siguiente.

## Código completo

Archivo: [Main.java](Main.java).

```java
import java.util.*;
public class Main {
    static class Nodo {
        int dato;
        Nodo siguiente;
        Nodo(int dato) {
            this.dato = dato;
        }
    }
    static Nodo insertar(Nodo cabeza, int clave) {
        Nodo previo = null, actual = cabeza;
        while (actual != null && actual.dato <= clave) {
            previo = actual;
            actual = actual.siguiente;
        }
        Nodo nuevo = new Nodo(clave);
        nuevo.siguiente = actual;
        if (previo == null) return nuevo;
        previo.siguiente = nuevo;
        return cabeza;
    }
    public static void main(String[] args) {
        Nodo cabeza = null;
        for (int d : new int[]{
            4, 2, 4, 1
        }) cabeza = insertar(cabeza, d);
        List<Integer> r = new ArrayList<>();
        for (Nodo p = cabeza;  p != null;  p = p.siguiente) r.add(p.dato);
        System.out.println(r);
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
[1, 2, 4, 4]
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Localizar inserción peor Θ(n), enlazar Θ(1). Memoria nueva Θ(1) por término insertado.

## Errores frecuentes

- Mover referencias antes de enlazar el nuevo nodo.
- Prometer búsqueda logarítmica sin acceso al centro.

## Ejercicio

Representa términos(coeficiente, exponente) ordenados por exponente. Combina exponentes iguales y retira términos con coeficiente 0.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Localiza por exponente; si coincide, suma coeficientes y elimina si da 0; si no, crea un nodo en la posición. Ejemplo 3 x²+2 x²−5 x² cancela el término. Se debe decidir tipo numérico y detección de desbordamiento.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo02/04-eliminar-nodos/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/06-listas-circulares/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2005%20%C2%B7%20Lista%20simple%20ordenada%20y%20duplicados%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo02%2F05-lista-ordenada%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
