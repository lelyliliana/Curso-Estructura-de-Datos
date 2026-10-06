# BST: buscar y eliminar

[Inicio](../../README.md) · [Anterior](../../modulo03/04-recorridos-de-arboles/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo03/06-alturas-y-factor-avl/README.md)


## Objetivo

Resolver borrado de hoja, un hijo y dos hijos.

## Conceptos y razonamiento

Buscar compara con la raíz y sigue un único lado según la propiedad BST. Una clave ausente termina en null. El recorrido iterativo no necesita pila de llamadas para buscar.

Eliminar requiere tres casos:hoja retorna null; un hijo retorna ese hijo; dos hijos sustituyen la clave por el sucesor inorden, el mínimo del subárbol derecho, y eliminan ese sucesor de allí. No se deja una copia duplicada del sucesor.

La función retorna nueva raíz del subárbol y el llamador la conserva. En este conjunto de int, copiar la clave basta; en registros con otros campos se debe decidir cómo mover el registro completo. La eliminación no balancea el árbol ni garantiza una altura reducida. Los casos de raíz y último nodo son esenciales.

## Caso resuelto y prueba de escritorio

Claves 4, 2, 6, 1, 3, 5, 7. Eliminar 4 usa sucesor 5 y retira su nodo del subárbol derecho. Inorden resultante [1, 2, 3, 5, 6, 7]. Buscar 4 pasa a false y buscar 5 conserva true.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static class Nodo {
        int clave;
        Nodo izq, der;
        Nodo(int clave) {
            this.clave = clave;
        }
    }
    static Nodo insertar(Nodo n, int clave) {
        if (n == null) return new Nodo(clave);
        if (clave < n.clave) n.izq = insertar(n.izq,clave);
        else if (clave > n.clave) n.der = insertar(n.der,clave);
        return n;
    }
    static void inorden(Nodo n, java.util.List<Integer> salida) {
        if (n == null) return;
        inorden(n.izq,salida);
        salida.add(n.clave);
        inorden(n.der,salida);
    }
    static boolean contiene(Nodo n, int clave) {
        while (n != null) {
            if (clave == n.clave) return true;
            n = clave < n.clave ? n.izq : n.der;
        }
        return false;
    }
    static Nodo eliminar(Nodo n, int clave) {
        if (n == null) return null;
        if (clave < n.clave) n.izq = eliminar(n.izq,clave);
        else if (clave > n.clave) n.der = eliminar(n.der,clave);
        else {
            if (n.izq == null) return n.der;
            if (n.der == null) return n.izq;
            Nodo sucesor = n.der;
            while (sucesor.izq != null) sucesor = sucesor.izq;
            n.clave = sucesor.clave;
            n.der = eliminar(n.der,sucesor.clave);
        }
        return n;
    }
    public static void main(String[] args) {
        Nodo r = null;
        for (int d : new int[]{
            4,2,6,1,3,5,7
        }) r = insertar(r,d);
        r = eliminar(r,4);
        java.util.List<Integer> salida = new java.util.ArrayList<>();
        inorden(r,salida);
        System.out.println(salida);
        System.out.println(contiene(r,4));
        System.out.println(contiene(r,5));
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
[1, 2, 3, 5, 6, 7]
false
true
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Buscar y eliminar O(h+1), peor O(n). Buscar iterativo Θ(1) auxiliar, borrar recursivo O(h).

## Errores frecuentes

- Copiar sucesor sin retirarlo de su ubicación.
- No asignar el nuevo resultado a raíz.

## Ejercicio

Elimina todos los elementos en órdenes diferentes y compara el conjunto restante con TreeSet.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Después de cada eliminación, genera inorden y compara con la referencia. Incluye eliminar ausente y raíz de un único nodo. Al final raíz=null. También valida la propiedad global con límites de cada subárbol.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo03/04-recorridos-de-arboles/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo03/06-alturas-y-factor-avl/README.md)
