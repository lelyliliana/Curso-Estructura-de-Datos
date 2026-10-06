# Búsqueda binaria y precondición de orden

[Inicio](../../README.md) · [Anterior](../../modulo01/08-contratos-e-invariantes/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/10-colecciones-y-eleccion/README.md)


## Objetivo

Reducir un intervalo conservando la posibilidad de encontrar la clave.

## Conceptos y razonamiento

La búsqueda binaria requiere datos ordenados con el mismo criterio usado al buscar. Compara el centro del intervalo y descarta una mitad. El contrato del ejemplo retorna cualquier coincidencia entre duplicados; no promete la primera. Detectar orden en cada consulta agregaría trabajo lineal, por lo que se declara como precondición.

Se representa el intervalo cerrado [izquierda, derecha]. Si datos [medio]<clave, todos los índices hasta medio se descartan; si es mayor, se descarta desde medio. Cada paso mueve un extremo más allá del centro, lo que asegura progreso. Cuando izquierda>derecha, el intervalo está vacío.

El centro se calcula izquierda+(derecha-izquierda)/2 para evitar sumar dos índices grandes. Binary search no debe confundirse con consultar cualquier estructura en O(log n): el acceso al centro debe ser eficiente. En una lista enlazada recorrer hasta el centro introduce otro costo.

## Caso resuelto y prueba de escritorio

Datos [1, 3, 5, 7, 9], clave 7: intervalo [0, 4], centro 2, valor 5; pasa a [3, 4], centro 3, valor 7 y retorna 3. Para 4 termina sin coincidencia. El arreglo vacío comienza con derecha=-1.

La prueba debe incluir primero, último, entre valores, fuera del rango y duplicados.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static int buscar(int[] datos, int clave) {
        int izq = 0, der = datos.length - 1;
        while (izq <= der) {
            int medio = izq + (der - izq) / 2;
            if (datos[medio] == clave) return medio;
            if (datos[medio] < clave) izq = medio + 1;
            else der = medio - 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] datos = {
            1, 3, 5, 7, 9
        };
        for (int clave : new int[]{
            7, 4, 1
        }) System.out.println(clave + " -> " + buscar(datos, clave));
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
7 -> 3
4 -> -1
1 -> 0
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Peor Θ(log n) comparaciones para n≥2 y memoria Θ(1). Ordenar antes tiene su propio costo; para una consulta no siempre conviene.

## Errores frecuentes

- Buscar datos sin ordenar.
- Mantener medio dentro del próximo intervalo y perder progreso.

## Ejercicio

Adapta para retornar la primera coincidencia. No detengas la búsqueda inmediatamente al encontrar.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Guarda resultado=medio al coincidir y continúa a la izquierda con der=medio-1. Con [1, 3, 3, 3, 5], clave 3 produce 1. La búsqueda sigue siendo logarítmica bajo las mismas precondiciones.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo01/08-contratos-e-invariantes/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/10-colecciones-y-eleccion/README.md)
