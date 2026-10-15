# List, Set y Map: elegir por operación

[Inicio](../../README.md) · [Anterior](../../modulo01/09-busqueda-binaria/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/01-nodo-simple/README.md)

[Desarrollo y traza del caso](DESARROLLO.md) · [Recorridos ejecutables](../../laboratorios/recorridos/README.md)

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

Historial: [3,1,3], tres eventos y dos apariciones de 3. Códigos únicos ordenados: [1,3]. Frecuencias: {1=1,3=2}. El conjunto por sí solo no conserva los conteos originales.

Para correo por usuario, el segundo put sobre u7 devuelve primero, deja actual como valor vigente y mantiene tamaño 1. Si el requisito admite varios correos o necesita historial, cambia el contrato del valor asociado.

sol y mar tienen longitud 3: el comparador devuelve cero y TreeSet conserva una sola representación. Para ordenar por longitud sin fusionar palabras distintas puedes desempatar por el texto completo. No esperes que HashSet imprima un orden ni que Map guarde automáticamente valores anteriores.

</details>

## Práctica paso a paso

1. Con [3,1,3], escribe qué obtendría un historial, un conjunto ordenado y un mapa de frecuencias. Señala qué información se pierde al convertir a conjunto.
2. Actualiza dos veces el correo de un usuario y registra tamaño, valor vigente y valor anterior devuelto por put.
3. Inserta sol y mar en un TreeSet comparado solo por longitud. Explica el resultado sin llamarlo colisión hash.
4. Ejecuta `python scripts/recorridos.py colecciones`. Propón un comparador que mantenga palabras distintas con igual longitud y un caso que falle si no desempatara por contenido.

Entrega necesidad, representación, información conservada y garantía de orden. Consulta el [desarrollo](DESARROLLO.md).

[Inicio](../../README.md) · [Anterior](../../modulo01/09-busqueda-binaria/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo02/01-nodo-simple/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2010%20%C2%B7%20List%2C%20Set%20y%20Map%3A%20elegir%20por%20operaci%C3%B3n%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo01%2F10-colecciones-y-eleccion%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
