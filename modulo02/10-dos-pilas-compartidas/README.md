# Dos pilas en un arreglo compartido

[Inicio](../../README.md) · [Anterior](../../modulo02/09-pila-con-nodos/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/11-cola-circular-con-arreglo/README.md)


## Objetivo

Compartir capacidad sin mover una partición fija.

## Conceptos y razonamiento

Dos pilas pueden crecer desde extremos opuestos de un arreglo. La primera inicia con cima-1 y avanza; la segunda inicia en capacidad y retrocede. El espacio libre está entre ambas. Se considera lleno cuando izquierda+1==derecha.

Esta disposición evita reservar una mitad fija para cada pila y no desplaza todos los datos para ceder espacio. Cada pila conserva LIFO independiente. La suma de sus tamaños no puede superar capacidad, pero una sola puede utilizar todo el arreglo si la otra está vacía.

La identificación de pila se valida antes de alterar índices. Retirar de una pila vacía es error aunque la otra contenga datos. Compartir almacenamiento no mezcla sus elementos ni convierte ambas en una cola. Este ejemplo resuelve el objetivo de compartir espacio con una regla más simple que mover límites y registros continuamente.

## Caso resuelto y prueba de escritorio

Capacidad 3:apilar(1, 4), apilar(2, 8), apilar(1, 2) ocupa todo. La pila 1 retira 2 y 4; la 2 retira 8. El espacio libre depende de ambos índices.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static class DosPilas {
        final int[] datos;
        int izquierda = -1, derecha;
        DosPilas(int capacidad) {
            if (capacidad <= 0) throw new IllegalArgumentException("Capacidad positiva");
            datos = new int[capacidad];
            derecha = capacidad;
        }
        void apilar(int pila, int dato) {
            validar(pila);
            if (izquierda + 1 == derecha) throw new IllegalStateException("Sin espacio compartido");
            if (pila == 1) datos[++izquierda] = dato;
            else datos[--derecha] = dato;
        }
        int desapilar(int pila) {
            validar(pila);
            if (pila == 1) {
                if (izquierda < 0) throw new java.util.NoSuchElementException("Pila 1 vacía");
                return datos[izquierda--];
            }
            if (derecha == datos.length) throw new java.util.NoSuchElementException("Pila 2 vacía");
            return datos[derecha++];
        }
        void validar(int pila) {
            if (pila != 1 && pila != 2) throw new IllegalArgumentException("Pila 1 o 2");
        }
    }
    public static void main(String[] args) {
        DosPilas p = new DosPilas(3);
        p.apilar(1,4);
        p.apilar(2,8);
        p.apilar(1,2);
        System.out.println(p.desapilar(1));
        System.out.println(p.desapilar(2));
        System.out.println(p.desapilar(1));
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
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Operaciones Θ(1), memoria Θ(capacidad); no requiere desplazar particiones.

## Errores frecuentes

- Tratar una pila vacía como llena porque la otra está llena.
- Permitir que índices se crucen.

## Ejercicio

Llena todo con pila 2 y después libera espacio para pila 1. Prueba un identificador 3.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Pila 2 puede ocupar desde capacidad-1 hasta 0. Retirar libera una posición que puede usar pila 1; los índices no deben cruzarse. Identificador 3 lanza error antes de cambiar estado.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo02/09-pila-con-nodos/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/11-cola-circular-con-arreglo/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2010%20%C2%B7%20Dos%20pilas%20en%20un%20arreglo%20compartido%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo02%2F10-dos-pilas-compartidas%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
