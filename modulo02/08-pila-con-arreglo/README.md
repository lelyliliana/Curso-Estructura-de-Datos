# Pila con arreglo y capacidad fija

[Inicio](../../README.md) · [Anterior](../../modulo02/07-listas-dobles/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/09-pila-con-nodos/README.md)


## Objetivo

Implementar LIFO con límites explícitos.

## Conceptos y razonamiento

Una pila es un TAD que retira primero el último elemento agregado:LIFO. La representación puede ser arreglo o nodos. El contrato ofrece apilar, desapilar y cima; cima observa sin retirar. El tamaño lógico separa valores vigentes del espacio libre.

Esta versión tiene capacidad fija positiva. cantidad va de 0 a capacidad y la cima está en cantidad-1. La siguiente inserción escribe en cantidad y luego aumenta. Desapilar decrementa primero y devuelve la posición que dejó de estar activa.

Intentar apilar llena o retirar vacía lanza excepción antes de cambiar estado. No se usa 0 como centinela porque puede ser dato válido. Con objetos se debería limpiar la posición retirada para no mantener referencias innecesarias; con int los valores antiguos no se consideran activos aunque permanezcan en el arreglo.

## Caso resuelto y prueba de escritorio

Capacidad 2:apilar 4, 8; cima 8; desapilar 8 y después 4. El tamaño queda 0. Una tercera inserción antes de retirar se rechazaría conservando los dos datos.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static class Pila {
        final int[] datos;
        int cantidad;
        Pila(int capacidad) {
            if (capacidad <= 0) throw new IllegalArgumentException("Capacidad positiva");
            datos = new int[capacidad];
        }
        void apilar(int dato) {
            if (cantidad == datos.length) throw new IllegalStateException("Llena");
            datos[cantidad++] = dato;
        }
        int desapilar() {
            if (cantidad == 0) throw new java.util.NoSuchElementException("Vacía");
            return datos[--cantidad];
        }
        int cima() {
            if (cantidad == 0) throw new java.util.NoSuchElementException("Vacía");
            return datos[cantidad-1];
        }
    }
    public static void main(String[] args) {
        Pila p = new Pila(2);
        p.apilar(4);
        p.apilar(8);
        System.out.println(p.cima());
        System.out.println(p.desapilar());
        System.out.println(p.desapilar());
        System.out.println(p.cantidad);
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
8
4
0
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Cada operación Θ(1), memoria reservada Θ(capacidad). Inicializar el arreglo tiene costo proporcional a capacidad.

## Errores frecuentes

- Confundir cima con retirar.
- Decrementar antes de validar vacío.

## Ejercicio

Agrega vacia y llena, y prueba que un rechazo no cambia tamaño ni cima.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

vacia=cantidad==0; llena=cantidad==datos.length. Llenar 2, intentar tercero y comprobar cantidad 2, cima 8. Vaciar e intentar retirar sin disminuir cantidad por debajo de 0.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo02/07-listas-dobles/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/09-pila-con-nodos/README.md)
