# Cola dinámica con frente y fin

[Inicio](../../README.md) · [Anterior](../../modulo02/11-cola-circular-con-arreglo/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/13-deque-y-aplicaciones/README.md)


## Objetivo

Insertar al final y retirar al principio con invariantes coherentes.

## Conceptos y razonamiento

La cola enlazada usa frente para próximo retiro y fin para siguiente inserción. Un nuevo nodo se conecta desde fin y se convierte en nuevo fin. En vacía también es frente. Desencolar mueve frente al siguiente y, si queda null, también limpia fin.

Omitir la limpieza de fin al retirar el último rompe la siguiente inserción:se puede enlazar desde un nodo que ya no representa la cola activa. La comprobación debe incluir vaciar y volver a llenar, no solo una ronda de retiros.

La cola dinámica y la circular fija implementan FIFO con distintas restricciones. El costo por operación no incluye recorrer porque se mantiene fin. Si solo se conserva frente, insertar al final tendría costo lineal. La representación adecuada depende de capacidad requerida y perfil de uso.

## Caso resuelto y prueba de escritorio

Encolar 4, 8 produce frente 4→8, fin 8. Desencolar 4, 8 deja ambos null. Encolar 2 después debe crear una cola válida y desencolar 2.

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
    static class Cola {
        Nodo frente, fin;
        int cantidad;
        void encolar(int dato) {
            Nodo n = new Nodo(dato);
            if (fin == null) frente = n;
            else fin.siguiente = n;
            fin = n;
            cantidad++;
        }
        int desencolar() {
            if (frente == null) throw new java.util.NoSuchElementException("Vacía");
            int dato = frente.dato;
            frente = frente.siguiente;
            cantidad--;
            if (frente == null) fin = null;
            return dato;
        }
    }
    public static void main(String[] args) {
        Cola c = new Cola();
        c.encolar(4);
        c.encolar(8);
        System.out.println(c.desencolar());
        System.out.println(c.desencolar());
        System.out.println(c.frente == null && c.fin == null);
        c.encolar(2);
        System.out.println(c.desencolar());
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
true
2
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Operaciones Θ(1), memoria Θ(n) de nodos. Mantener fin evita recorrido para encolar.

## Errores frecuentes

- Olvidar fin=null al retirar último.
- Insertar por frente y convertir accidentalmente FIFO en LIFO.

## Ejercicio

Añade frente sin retiro y una consulta de tamaño. Comprueba que mirar no altera cantidad.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Valida no vacía y retorna frente.dato; tamano retorna cantidad. Tras encolar 4, mirar dos veces deja tamaño 1 y desencolar retorna 4.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo02/11-cola-circular-con-arreglo/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/13-deque-y-aplicaciones/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2012%20%C2%B7%20Cola%20din%C3%A1mica%20con%20frente%20y%20fin%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo02%2F12-cola-con-nodos%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
