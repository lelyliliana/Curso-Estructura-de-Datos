# List, Set y Map: elegir por operación

[Inicio](../../README.md) · [Anterior](../../modulo01/09-busqueda-binaria/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/01-nodo-simple/README.md)

## Objetivo

Distinguir secuencia, pertenencia y asociación y especificar orden y duplicados.

## Conceptos y razonamiento

List conserva posiciones y admite repetidos. Set expresa pertenencia sin duplicar elementos. Map asocia una clave con un valor y actualiza la asociación al poner la misma clave. Son contratos diferentes, no variantes de una sola colección intercambiable.

ArrayList ofrece acceso indexado y TreeSet mantiene elementos ordenados. HashSet se utiliza para pertenencia con costo esperado favorable, pero no promete orden de iteración. TreeMap mantiene claves según comparación y permite rangos. El tipo de valores no determina por sí solo cuál estructura conviene; lo decide la consulta dominante y sus garantías.

Al interpretar igualdad, un conjunto hash utiliza equals junto con hashCode. Un conjunto ordenado considera equivalente lo que su comparador devuelve como cero. El comparador debería ser coherente con equals cuando se busca el comportamiento habitual de un conjunto. Diferencias no declaradas pueden producir resultados sorprendentes.

El ejemplo elige estructuras con orden determinista para mostrar resultados reproducibles. No debe extrapolarse a HashSet que su impresión aparecerá en el mismo orden. El curso implementa varias estructuras desde sus mecanismos para entender sus límites, y también usa la biblioteca como herramienta y oráculo de pruebas. Copiar una solución de biblioteca sin poder explicar el contrato no completa el aprendizaje.

## Caso paso a paso

La lista [3,1,3] tiene tres posiciones; el TreeSet contiene [1,3]. El mapa pone dos veces la clave curso y conserva el último valor. Los resultados permiten observar tres políticas distintas.

Calcula el resultado antes de ejecutar. Sigue las variables que cambian y explica por qué cada operación conserva el contrato. Compara la representación antes y después, además de observar lo que se imprime.

## Código completo

El [ejemplo](Main.java) utiliza las [clases compartidas](../../src/curso). Examina el método que ejecuta cada operación. Las estructuras genéricas del curso no permiten null; los índices representan posiciones de la región activa.

```java
import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        List<Integer> l=List.of(3,1,3);
        Set<Integer> s=new TreeSet<>(l);
        Map<String,String> m=new TreeMap<>();
        m.put("curso","primero");
        m.put("curso","actual");
        System.out.println(l);
        System.out.println(s);
        System.out.println(m);
    }
}
```

## Ejecución

Desde la raíz, usa `python3 scripts/ejecutar.py modulo01/10-colecciones-y-eleccion` en Ubuntu/macOS o `py -3 scripts/ejecutar.py modulo01/10-colecciones-y-eleccion` en Windows. Revisa la [guía del ambiente](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[3, 1, 3]
[1, 3]
{curso=actual}
```

## Eficiencia y límites

ArrayList: obtener O(1), agregar amortizado O(1). TreeSet/TreeMap: operaciones principales O(log n). HashSet/HashMap: O(1) esperado con supuestos adecuados.

Distingue la operación estudiada del recorrido que imprime datos y de las comprobaciones. Copiar o mostrar n elementos agrega trabajo lineal aunque consultar uno sea constante.

## Errores frecuentes

Esperar posición en un Set; asumir orden en un hash; usar una comparación incoherente; olvidar que poner la misma clave reemplaza.

## Ejercicio resuelto

Elige estructura para historial de eventos, códigos únicos y correo por usuario. Explica cuál conserva repetidos.

<details><summary>Contrastar la solución</summary>

Historial: secuencia; códigos: conjunto; correo por usuario: mapa. El historial conserva eventos repetidos y su orden. El contrato debe decidir si un usuario puede tener varios correos o un único valor asociado.

</details>

## Práctica y evidencia

1. Reproduce la entrada original y justifica cada resultado.
2. Resuelve el ejercicio antes de desplegar la solución.
3. Agrega una ausencia, un empate o una entrada inválida pertinente al contrato.
4. Comprueba una propiedad que pueda fallar aunque la salida parezca correcta.

Entrega entrada, resultado esperado y observado, y análisis del costo. Si cambias la implementación, ejecuta las [pruebas](../../tests/README.md).

[Inicio](../../README.md) · [Anterior](../../modulo01/09-busqueda-binaria/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/01-nodo-simple/README.md)
