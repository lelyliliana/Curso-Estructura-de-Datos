# Referencias, alias y memoria en Java

[Inicio](../../README.md) · [Anterior](../../modulo01/01-tipos-abstractos/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/03-arreglos-y-region-activa/README.md)


## Objetivo

Distinguir referencia, objeto, reasignación y modificación.

## Conceptos y razonamiento

Una referencia permite acceder a un objeto. No es un entero con el que podamos hacer aritmética de direcciones en Java. El lenguaje administra memoria mediante la JVM y recolección de basura; no se libera un nodo escribiendo free como en C. null señala ausencia de referencia y usarlo para acceder a un campo produce una excepción.

Asignar b=a copia el valor de la referencia, por lo que ambos nombres pueden acceder al mismo objeto. Cambiar un campo mediante b se observa mediante a. Reasignar b a otro objeto no modifica la asociación de a. Los argumentos Java se pasan por valor, también cuando ese valor es una referencia: el parámetro puede acceder al objeto compartido, pero reasignarlo no reasigna el nombre del llamador.

La memoria dinámica no significa memoria infinita ni mejor rendimiento por definición. Cada nodo tiene datos y enlaces; necesita espacio adicional y puede tener peor localidad que un arreglo. Cuando no queda ninguna referencia alcanzable a un objeto, puede ser recuperado por el recolector en un momento no controlado por este ejemplo.

## Caso resuelto y prueba de escritorio

a apunta a un nodo con 4; b=a. b.dato=8 cambia el objeto compartido. Después b=new Nodo(2) crea otro objeto; a conserva 8 y b contiene 2.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static class Nodo {
        int dato;
        Nodo(int dato) {
            this.dato = dato;
        }
    }
    static void reasignar(Nodo p) {
        p = new Nodo(99);
    }
    public static void main(String[] args) {
        Nodo a = new Nodo(4), b = a;
        b.dato = 8;
        reasignar(a);
        System.out.println(a.dato);
        b = new Nodo(2);
        System.out.println(a.dato + " " + b.dato);
        System.out.println(a == b);
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
8 2
false
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Copiar una referencia usa Θ(1) en este modelo. Copiar una cadena de n nodos requiere crear y enlazar n objetos.

## Errores frecuentes

- Llamar referencia Java a una dirección manipulable como en C.
- Suponer que reasignar un parámetro reasigna la variable externa.

## Ejercicio

Crea una función que cambie el campo de un nodo y otra que retorne un nodo nuevo. Explica qué cambia en el llamador.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Cambiar p.dato afecta el objeto compartido. Retornar new Nodo y asignarlo a=a_funcion(a) cambia la referencia del llamador por una asignación explícita. Reasignar solo p dentro de la función no lo hace.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo01/01-tipos-abstractos/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/03-arreglos-y-region-activa/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2002%20%C2%B7%20Referencias%2C%20alias%20y%20memoria%20en%20Java%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo01%2F02-referencias-y-memoria%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
