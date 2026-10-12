# Cola circular con arreglo

[Inicio](../../README.md) · [Anterior](../../modulo02/10-dos-pilas-compartidas/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/12-cola-con-nodos/README.md)


## Objetivo

Reutilizar posiciones manteniendo orden FIFO.

## Conceptos y razonamiento

Una cola retira el primero agregado:FIFO. Un arreglo lineal que avanza sin reutilizar puede parecer lleno aunque haya espacios al principio. El buffer circular usa módulo para volver al comienzo.

frente identifica próximo retiro, cantidad cuántos elementos están activos. La siguiente inserción ocupa(frente+cantidad)%capacidad. Vacía y llena se distinguen por cantidad, no por igualdad de índices. Esta implementación permite utilizar todas las posiciones sin reservar una como centinela.

El recorrido físico del arreglo puede mostrar valores antiguos que no pertenecen a la cola. La estructura lógica se interpreta desde frente durante cantidad posiciones. Validar lleno/vacío antes de cambiar índices conserva estado en errores. La circularidad aquí pertenece al índice sobre un arreglo, no a referencias de nodos.

## Caso resuelto y prueba de escritorio

Capacidad 3:encolar 4, 8, 2; retirar 4 deja frente 1, cantidad 2. Encolar 6 ocupa índice 0. Retiros posteriores 8, 2, 6 demuestran reutilización sin perder FIFO.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static class Cola {
        final int[] datos;
        int frente, cantidad;
        Cola(int capacidad) {
            if (capacidad <= 0) throw new IllegalArgumentException("Capacidad positiva");
            datos = new int[capacidad];
        }
        void encolar(int dato) {
            if (cantidad == datos.length) throw new IllegalStateException("Llena");
            datos[(frente + cantidad) % datos.length] = dato;
            cantidad++;
        }
        int desencolar() {
            if (cantidad == 0) throw new java.util.NoSuchElementException("Vacía");
            int dato = datos[frente];
            frente = (frente + 1) % datos.length;
            cantidad--;
            return dato;
        }
    }
    public static void main(String[] args) {
        Cola c = new Cola(3);
        c.encolar(4);
        c.encolar(8);
        c.encolar(2);
        System.out.println(c.desencolar());
        c.encolar(6);
        while (c.cantidad > 0) System.out.println(c.desencolar());
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
4
8
2
6
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Cada operación Θ(1), memoria Θ(capacidad). No desplaza elementos al retirar.

## Errores frecuentes

- Interpretar igualdad de índices como único indicador de estado.
- Recorrer todo el arreglo físico como si fuera la cola.

## Ejercicio

Prueba capacidad 1 y alterna inserción/retiro cinco veces. Después comprueba lleno y vacío.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Con capacidad 1, el índice siempre es 0, pero cantidad distingue 0/1. Alternar debe conservar cada dato. Un segundo encolar sin retirar se rechaza; retirar dos veces también.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo02/10-dos-pilas-compartidas/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/12-cola-con-nodos/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2011%20%C2%B7%20Cola%20circular%20con%20arreglo%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo02%2F11-cola-circular-con-arreglo%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
