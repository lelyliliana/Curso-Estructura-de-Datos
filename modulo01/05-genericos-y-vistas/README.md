# Genéricos, identidad, igualdad y vistas

[Inicio](../../README.md) · [Anterior](../../modulo01/04-arreglo-dinamico/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/06-complejidad/README.md)

## Objetivo

Reutilizar una estructura con tipos diferentes y distinguir copia de colección de copia de objetos.

## Conceptos y razonamiento

T representa el tipo de los elementos de una estructura genérica. Vector<String> y Vector<Integer> comparten el algoritmo y restringen qué valores se pueden insertar. Java no admite primitivas como parámetro de tipo; Integer es el envoltorio de int y puede implicar boxing.

En referencias, == compara identidad del objeto. equals expresa igualdad lógica cuando la clase la define. Un record tiene igualdad basada en sus componentes. No uses == para comparar contenido de textos recibidos de archivos o entradas: pueden ser objetos diferentes con los mismos caracteres.

copia devuelve una lista no modificable con las referencias que existían en ese momento. Es una copia de la colección y no una copia profunda de cada elemento. Si T es mutable, modificar un objeto compartido puede verse desde ambas colecciones. La palabra inmutable debe precisar si se refiere al contenedor o a todo el grafo de objetos.

No se permite null para hacer clara la región activa y usar List.copyOf. Otras colecciones pueden tener otra política; hay que leer su contrato. La interfaz no fuerza a exponer nodos o arreglos, y las operaciones deben impedir modificaciones externas que salten la validación. Al trabajar con tipos genéricos, documenta también si la igualdad usa equals o un comparador.

## Caso paso a paso

Dos String construidos por separado contienen aula. == es falso y equals verdadero. Se guarda uno en el vector y se obtiene una copia; agregar al vector después no modifica el tamaño de esa copia.

Calcula el resultado antes de ejecutar. Sigue las variables que cambian y explica por qué cada operación conserva el contrato. Compara la representación antes y después, además de observar lo que se imprime.

## Código completo

El [ejemplo](Main.java) utiliza las [clases compartidas](../../src/curso). Examina el método que ejecuta cada operación. Las estructuras genéricas del curso no permiten null; los índices representan posiciones de la región activa.

```java
import curso.Vector;
import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        String a=new String("aula"),b=new String("aula");
        System.out.println(a==b);
        System.out.println(a.equals(b));
        Vector<String> v=new Vector<>();
        v.agregar(a);
        List<String> copia=v.copia();
        v.agregar("otro");
        System.out.println(copia);
        System.out.println(v.copia());
    }
}
```

## Ejecución

Desde la raíz, usa `python3 scripts/ejecutar.py modulo01/05-genericos-y-vistas` en Ubuntu/macOS o `py -3 scripts/ejecutar.py modulo01/05-genericos-y-vistas` en Windows. Revisa la [guía del ambiente](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
false
true
[aula]
[aula, otro]
```

## Eficiencia y límites

Comparar referencias O(1); equals de texto depende de su longitud. Copiar el contenedor O(n) tiempo y memoria; no clona los objetos.

Distingue la operación estudiada del recorrido que imprime datos y de las comprobaciones. Copiar o mostrar n elementos agrega trabajo lineal aunque consultar uno sea constante.

## Errores frecuentes

Usar tipos sin parámetros; comparar textos con ==; anunciar copia profunda cuando solo se copiaron referencias.

## Ejercicio resuelto

Usa un objeto mutable como elemento y modifica un campo después de copiar. Explica qué se comparte y cómo elegir un elemento inmutable.

<details><summary>Contrastar la solución</summary>

La referencia al objeto se comparte, por lo que su cambio se observa desde ambos contenedores. Un record cuyos componentes sean valores inmutables permite una vista más segura; un record con una lista mutable no garantiza inmutabilidad profunda.

</details>

## Práctica y evidencia

1. Reproduce la entrada original y justifica cada resultado.
2. Resuelve el ejercicio antes de desplegar la solución.
3. Agrega una ausencia, un empate o una entrada inválida pertinente al contrato.
4. Comprueba una propiedad que pueda fallar aunque la salida parezca correcta.

Entrega entrada, resultado esperado y observado, y análisis del costo. Si cambias la implementación, ejecuta las [pruebas](../../tests/README.md).

[Inicio](../../README.md) · [Anterior](../../modulo01/04-arreglo-dinamico/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/06-complejidad/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2005%20%C2%B7%20Gen%C3%A9ricos%2C%20identidad%2C%20igualdad%20y%20vistas%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo01%2F05-genericos-y-vistas%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
