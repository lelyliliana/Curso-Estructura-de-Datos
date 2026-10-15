# Trie y búsqueda por prefijo

[Inicio](../../README.md) · [Anterior](../../modulo04/05-colas-de-prioridad/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/01-conceptos-de-grafos/README.md)

[Desarrollo y traza del caso](DESARROLLO.md) · [Recorridos ejecutables](../../laboratorios/recorridos/README.md)

## Objetivo

Separar fin de palabra de existencia de un camino y retirar claves sin borrar prefijos compartidos.

## Conceptos y razonamiento

Un trie organiza claves por sus componentes. Cada enlace representa una letra y un camino desde la raíz representa un prefijo. La marca terminal indica si ese camino completo es una palabra almacenada. La existencia de enlaces no implica que todos sus prefijos se hayan insertado como claves.

El ejemplo admite hasta ochenta letras ASCII a-z y también la palabra vacía, que corresponde a la marca terminal de la raíz. Es una restricción didáctica; un sistema para español necesita definir normalización, acentos y alfabeto de forma explícita. No se elimina un acento silenciosamente sin decidir si las claves deben considerarse equivalentes.

Agregar recorre o crea enlaces y marca el final. Consultar exige que el camino exista y sea terminal. Eliminar desmarca la palabra y retira nodos sin hijos que no sean terminales. Si se elimina casa pero sigue casita, la rama compartida debe conservarse.

Una consulta por prefijo primero baja al nodo del prefijo y después enumera sus descendientes terminales. El orden lexicográfico se obtiene con TreeMap de hijos. El costo incluye la longitud de las cadenas que se construyen como salida. Un trie puede ser conveniente para autocompletar; un mapa hash es adecuado para igualdad, pero no ofrece por sí solo ese recorrido de prefijos.

## Caso paso a paso

Agrega casa, caso y casita. Buscar cas devuelve falso porque solo es prefijo. Pedir cas devuelve las tres palabras. Eliminar casa conserva caso y casita.

Calcula el resultado antes de ejecutar. Sigue las variables que cambian y explica por qué cada operación conserva el contrato. Compara la representación antes y después, además de observar lo que se imprime.

## Código completo

El [ejemplo](Main.java) utiliza las [clases compartidas](../../src/curso). Examina el método que ejecuta cada operación. Las estructuras genéricas del curso no permiten null; los índices representan posiciones de la región activa.

```java
import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Trie t=new Trie();
        for(String s:List.of("casa","caso","casita"))t.agregar(s);
        System.out.println(t.contiene("cas"));
        System.out.println(t.prefijo("cas"));
        t.eliminar("casa");
        System.out.println(t.prefijo("cas"));
    }
}
```

## Ejecución

Desde la raíz, usa `python3 scripts/ejecutar.py modulo04/06-trie` en Ubuntu/macOS o `py -3 scripts/ejecutar.py modulo04/06-trie` en Windows. Revisa la [guía del ambiente](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
false
[casa, casita, caso]
[casita, caso]
```

## Eficiencia y límites

Con alfabeto fijo de 26, consultar/agregar/eliminar O(L), incluyendo el recorrido de caracteres. Enumerar agrega nodos visitados y tamaño total de salida. Memoria depende de los prefijos distintos.

Distingue la operación estudiada del recorrido que imprime datos y de las comprobaciones. Copiar o mostrar n elementos agrega trabajo lineal aunque consultar uno sea constante.

## Errores frecuentes

Marcar como palabra cada nodo creado; borrar una rama compartida; prometer costo independiente del texto devuelto.

## Ejercicio resuelto

Agrega cas y elimina casita. ¿Qué consultas por igualdad y prefijo deben cambiar?

<details><summary>Contrastar la solución</summary>

Después del ejemplo original, agrega cas y elimina casita: cas pasa a ser terminal y el prefijo conserva cas y caso. Si ejecutas la variante ampliada que también conserva casa, la lista final será cas, casa y caso. El resultado depende del conjunto de partida, no de un orden distinto del trie.

En el recorrido automático se insertan las cuatro palabras y primero se elimina cas: quedan casa, casita y caso. Después se elimina casita: quedan casa y caso. Solo desaparece el tramo exclusivo de casita; el camino cas sigue sosteniendo otras palabras.

Agregar la cadena vacía marca la raíz. Retirarla desmarca esa raíz y conserva sus hijos. El límite 80 se acepta; 81, mayúsculas y acentos se rechazan según el contrato ASCII. La enumeración debe pagar el texto que devuelve, además de bajar al prefijo.

</details>

## Práctica paso a paso

1. Inserta cas, casa, casita y caso. Marca cuáles nodos representan una palabra completa y cuáles solo un camino.
2. Elimina cas y después casita. Predice la lista de prefijo cas tras cada cambio y señala los enlaces que podrían podarse.
3. Agrega la palabra vacía, consulta contiene vacío y prefijo vacío; después elimínala. Distingue cadena vacía de lista vacía.
4. Ejecuta `python scripts/recorridos.py trie`. Prueba longitud 80, longitud 81, mayúsculas y un acento. Comprueba que los rechazos no destruyan las palabras anteriores.

Entrega terminales, enlaces conservados y costo de enumerar. El [desarrollo](DESARROLLO.md) incluye el esquema de la rama compartida.

[Inicio](../../README.md) · [Anterior](../../modulo04/05-colas-de-prioridad/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/01-conceptos-de-grafos/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2006%20%C2%B7%20Trie%20y%20b%C3%BAsqueda%20por%20prefijo%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo04%2F06-trie%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
