# Preorden, inorden y postorden

[Inicio](../../README.md) · [Anterior](../../modulo03/03-arbol-binario-de-busqueda/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo03/05-buscar-y-eliminar-en-bst/README.md)


## Objetivo

Ubicar la visita de raíz respecto de ambos subárboles.

## Conceptos y razonamiento

Preorden visita raíz, izquierdo, derecho. Inorden visita izquierdo, raíz, derecho. Postorden visita izquierdo, derecho, raíz. El caso base null no agrega nada. Cada nodo se agrega una sola vez, pero en distinto momento.

Estos recorridos no exigen BST. Inorden solo produce orden de claves cuando el árbol tiene esa propiedad. Postorden puede procesar dependencias antes de su padre; preorden puede presentar una jerarquía desde la raíz. Las aplicaciones deben explicar qué significa procesar.

Un tipo de recorrido inválido se rechaza antes de empezar. El helper presupone árbol acíclico y tipo validado. La salida es una lista nueva por consulta, no una global que conserve residuos del recorrido anterior. La profundidad de la pila depende de forma del árbol.

## Caso resuelto y prueba de escritorio

Árbol raíz 4, hijo izquierdo 2 con hijos 1 y 3, hijo derecho 6. Pre [4, 2, 1, 3, 6]; in [1, 2, 3, 4, 6]; post [1, 3, 2, 6, 4]. El mismo nodo 4 aparece al principio, medio lógico o final según regla.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static class Nodo {
        int dato;
        Nodo izq, der;
        Nodo(int dato) {
            this.dato = dato;
        }
    }
    static void recorrer(Nodo n, String tipo, java.util.List<Integer> salida) {
        if (n == null) return;
        if (tipo.equals("pre")) salida.add(n.dato);
        recorrer(n.izq,tipo,salida);
        if (tipo.equals("in")) salida.add(n.dato);
        recorrer(n.der,tipo,salida);
        if (tipo.equals("post")) salida.add(n.dato);
    }
    static java.util.List<Integer> recorrido(Nodo raiz, String tipo) {
        if (!java.util.Set.of("pre","in","post").contains(tipo)) throw new IllegalArgumentException("Tipo inválido");
        java.util.List<Integer> salida = new java.util.ArrayList<>();
        recorrer(raiz,tipo,salida);
        return salida;
    }
    public static void main(String[] args) {
        Nodo r = new Nodo(4);
        r.izq = new Nodo(2);
        r.der = new Nodo(6);
        r.izq.izq = new Nodo(1);
        r.izq.der = new Nodo(3);
        for (String tipo : new String[]{
            "pre","in","post"
        }) System.out.println(tipo + ": " + recorrido(r,tipo));
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
pre: [4, 2, 1, 3, 6]
in: [1, 2, 3, 4, 6]
post: [1, 3, 2, 6, 4]
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Cada recorrido Θ(n), pila O(h), salida Θ(n). Por niveles usa una cola cuyo máximo depende del ancho.

## Errores frecuentes

- Suponer que inorden siempre ordena cualquier árbol.
- Encolar null en ArrayDeque.

## Ejercicio

Haz un recorrido por niveles con una cola y compara con preorden.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Encola raíz si existe; retira, agrega dato y encola hijos no null de izquierda a derecha. Salida [4, 2, 6, 1, 3]. ArrayDeque no admite null.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo03/03-arbol-binario-de-busqueda/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo03/05-buscar-y-eliminar-en-bst/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2004%20%C2%B7%20Preorden%2C%20inorden%20y%20postorden%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo03%2F04-recorridos-de-arboles%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
