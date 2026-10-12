# Lista circular y recorrido completo

[Inicio](../../README.md) · [Anterior](../../modulo02/05-lista-ordenada/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/07-listas-dobles/README.md)


## Objetivo

Usar el retorno al inicio como condición de finalización.

## Conceptos y razonamiento

Una lista circular no termina en null:el último enlaza al primero. Con una referencia cola, la cabeza es cola.siguiente. En vacía, cola=null. Con un nodo, siguiente apunta al propio nodo. Ese ciclo es deliberado y exige otro recorrido.

Para listar se guarda inicio y se recorre al menos una vez hasta volver al mismo objeto. Comparar datos para detectar el retorno sería incorrecto con duplicados; se compara identidad de referencias. Quitar el primero modifica cola.siguiente, o coloca cola=null si era el único.

Una estructura circular puede modelar turnos, pero el orden de atención es una decisión del algoritmo que la usa. La circularidad no vuelve un recorrido infinito si hay una condición correcta. Cantidad permite otra forma de controlar visitas, si se mantiene consistente.

## Caso resuelto y prueba de escritorio

Agregar 4, 8 produce cola 8 y 8.siguiente 4. Listar visita 4, 8 y termina al volver al nodo 4. Quitar primero retorna 4, después 8 y deja vacía.

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
    static class Lista {
        Nodo cola;
        int cantidad;
        void agregar(int dato) {
            Nodo n = new Nodo(dato);
            if (cola == null) n.siguiente = n;
            else {
                n.siguiente = cola.siguiente;
                cola.siguiente = n;
            }
            cola = n;
            cantidad++;
        }
        int quitarPrimero() {
            if (cola == null) throw new java.util.NoSuchElementException("Vacía");
            Nodo primero = cola.siguiente;
            if (primero == cola) cola = null;
            else cola.siguiente = primero.siguiente;
            primero.siguiente = null;
            cantidad--;
            return primero.dato;
        }
        java.util.List<Integer> valores() {
            java.util.List<Integer> r = new java.util.ArrayList<>();
            if (cola != null) {
                Nodo inicio = cola.siguiente, p = inicio;
                do {
                    r.add(p.dato);
                    p = p.siguiente;
                } while (p != inicio);
            }
            return r;
        }
    }
    public static void main(String[] args) {
        Lista l = new Lista();
        l.agregar(4);
        l.agregar(8);
        System.out.println(l.valores());
        System.out.println(l.quitarPrimero());
        System.out.println(l.quitarPrimero());
        System.out.println(l.valores());
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
[4, 8]
4
8
[]
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Insertar y quitar primero Θ(1), recorrer Θ(n). El listado crea Θ(n) salida.

## Errores frecuentes

- Esperar null en una lista circular.
- Comparar dato para detectar retorno al inicio.

## Ejercicio

Simula una ronda de tres participantes sin eliminarlos:recorre exactamente una vuelta desde un inicio conocido.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Usa do/while hasta p==inicio. Si arrancas en otra posición, esa es la referencia que debes guardar. Identidades distintas con el mismo dato no deben detener antes el recorrido.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo02/05-lista-ordenada/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/07-listas-dobles/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2006%20%C2%B7%20Lista%20circular%20y%20recorrido%20completo%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo02%2F06-listas-circulares%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
