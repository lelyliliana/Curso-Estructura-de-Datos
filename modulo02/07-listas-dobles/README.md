# Lista doble y consistencia de enlaces

[Inicio](../../README.md) · [Anterior](../../modulo02/06-listas-circulares/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/08-pila-con-arreglo/README.md)


## Objetivo

Mantener enlaces en ambas direcciones al insertar y borrar.

## Conceptos y razonamiento

Un nodo doble tiene anterior y siguiente. Para nodos vecinos, a.siguiente=b implica b.anterior=a. Cabeza.anterior=null y cola.siguiente=null. La lista vacía conserva ambos extremos null.

Borrar un nodo conocido permite conectar anterior y siguiente sin buscar su previo. Localizarlo por clave sigue costando un recorrido. Al eliminar extremos se actualiza cabeza o cola, y al eliminar único ambos. Se limpian los enlaces del nodo retirado para expresar su separación.

Un recorrido hacia adelante puede parecer correcto aunque anterior esté mal. Por eso se comprueba el inverso y la simetría local de enlaces. Cada nodo añade una referencia respecto de la lista simple; esa memoria y las actualizaciones adicionales deben considerarse al elegir.

## Caso resuelto y prueba de escritorio

Agregar 4, 8, 2 genera directo [4, 8, 2] e inverso [2, 8, 4]. Eliminar 8 conecta 4.siguiente 2 y 2.anterior 4. Ambos sentidos deben reflejar el mismo conjunto.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static class Nodo {
        int dato;
        Nodo anterior, siguiente;
        Nodo(int dato) {
            this.dato = dato;
        }
    }
    static class Lista {
        Nodo cabeza, cola;
        int cantidad;
        void agregar(int dato) {
            Nodo n = new Nodo(dato);
            n.anterior = cola;
            if (cola == null) cabeza = n;
            else cola.siguiente = n;
            cola = n;
            cantidad++;
        }
        boolean eliminar(int clave) {
            Nodo p = cabeza;
            while (p != null && p.dato != clave) p = p.siguiente;
            if (p == null) return false;
            if (p.anterior == null) cabeza = p.siguiente;
            else p.anterior.siguiente = p.siguiente;
            if (p.siguiente == null) cola = p.anterior;
            else p.siguiente.anterior = p.anterior;
            p.anterior = p.siguiente = null;
            cantidad--;
            return true;
        }
        java.util.List<Integer> valores(boolean inverso) {
            java.util.List<Integer> r = new java.util.ArrayList<>();
            for (Nodo p = inverso ? cola : cabeza;  p != null;  p = inverso ? p.anterior : p.siguiente) r.add(p.dato);
            return r;
        }
    }
    public static void main(String[] args) {
        Lista l = new Lista();
        for (int d : new int[]{
            4, 8, 2
        }) l.agregar(d);
        System.out.println(l.valores(false));
        System.out.println(l.valores(true));
        l.eliminar(8);
        System.out.println(l.valores(false));
        System.out.println(l.valores(true));
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
[4, 8, 2]
[2, 8, 4]
[4, 2]
[2, 4]
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Buscar Θ(n), borrar nodo localizado Θ(1), recorrer Θ(n). Memoria de estructura Θ(n) con dos enlaces por nodo.

## Errores frecuentes

- Actualizar siguiente y olvidar anterior.
- Afirmar borrado por clave Θ(1) sin contar localización.

## Ejercicio

Añade agregarAlInicio y comprueba vacío, único y varios.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

nuevo.siguiente=cabeza; si cabeza existe, cabeza.anterior=nuevo; si no, cola=nuevo. Después cabeza=nuevo y cantidad++. El anterior del nuevo queda null. Revisa ambos recorridos.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo02/06-listas-circulares/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/08-pila-con-arreglo/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2007%20%C2%B7%20Lista%20doble%20y%20consistencia%20de%20enlaces%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo02%2F07-listas-dobles%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
