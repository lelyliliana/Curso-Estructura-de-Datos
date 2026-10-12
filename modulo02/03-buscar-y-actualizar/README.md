# Lista simple: búsqueda y actualización

[Inicio](../../README.md) · [Anterior](../../modulo02/02-crear-e-insertar/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/04-eliminar-nodos/README.md)


## Objetivo

Localizar un nodo por dato y distinguir cambio de valor y enlace.

## Conceptos y razonamiento

La búsqueda recorre enlaces hasta hallar una coincidencia o null. Este contrato elige la primera coincidencia. Puede retornar un nodo interno para estudiar estructura, pero una API pública debe decidir si expone referencias que permitan romper invariantes.

Cambiar dato no cambia la topología. Si la lista tiene orden por ese dato, una actualización puede invalidar el orden y debe reubicar el elemento o rechazarse. Esta lista no ordenada permite cambiarlo directamente.

La ausencia debe comprobarse antes de usar el nodo retornado. null no significa que el dato sea 0. Con varios duplicados, actualizar la primera coincidencia no actualiza todas; esa elección debe verse en el contrato y en las pruebas.

## Caso resuelto y prueba de escritorio

Cadena 4→8→4. Buscar 4 retorna la cabeza; se cambia a 2 y queda 2→8→4. Buscar 99 produce null. La última aparición de 4 no se altera.

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
    static Nodo buscar(Nodo cabeza, int clave) {
        for (Nodo p = cabeza;  p != null;  p = p.siguiente) if (p.dato == clave) return p;
        return null;
    }
    public static void main(String[] args) {
        Nodo a = new Nodo(4);
        a.siguiente = new Nodo(8);
        a.siguiente.siguiente = new Nodo(4);
        Nodo encontrado = buscar(a, 4);
        if (encontrado != null) encontrado.dato = 2;
        for (Nodo p = a;  p != null;  p = p.siguiente) System.out.println(p.dato);
        System.out.println(buscar(a, 99) == null);
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
2
8
4
true
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Buscar peor Θ(n). Cambiar un campo de un nodo ya conocido Θ(1), sin contar el costo de localizarlo.

## Errores frecuentes

- Acceder a encontrado.dato cuando es null.
- Actualizar una clave ordenada sin reparar el orden.

## Ejercicio

Retorna un valor opcional o una copia de datos en vez de exponer el nodo. Justifica la diferencia.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Puede usarse OptionalInt para el dato o un registro inmutable de resultados. Así el llamador no recibe siguiente ni puede conectar nodos externos. Si necesita actualizar, se ofrece un método de la lista que preserve reglas.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo02/02-crear-e-insertar/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/04-eliminar-nodos/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2003%20%C2%B7%20Lista%20simple%3A%20b%C3%BAsqueda%20y%20actualizaci%C3%B3n%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo02%2F03-buscar-y-actualizar%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
