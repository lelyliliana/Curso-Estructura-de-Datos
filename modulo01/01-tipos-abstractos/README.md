# Tipos abstractos y representaciones

[Inicio](../../README.md) · [Anterior](../../docs/prerrequisitos.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/02-referencias-y-memoria/README.md)

## Objetivo

Definir operaciones y resultados antes de escoger cómo se almacenan los datos.

## Conceptos y razonamiento

Una estructura de datos organiza valores y relaciones para realizar operaciones. Un tipo abstracto de datos (TAD) define el comportamiento observable: una secuencia tiene posiciones; un conjunto no repite elementos; un mapa asocia claves con valores; una pila retira el último valor agregado. La implementación decide si usa arreglos, nodos o alguna combinación.

El contrato de Secuencia<T> define tamaño, consulta, agregar, insertar y eliminar. Vector<T> cumple ese contrato con un arreglo dinámico. Una variable declarada como Secuencia<String> puede utilizar Vector<String> sin que quien la usa dependa de su capacidad interna. Esa separación permite cambiar la representación conservando el comportamiento.

Define también casos límite: obtener exige 0≤i<n; insertar permite 0≤i≤n; eliminar devuelve el elemento retirado. Este vector prohíbe null y utiliza excepciones para índices inválidos. Vacío es un estado válido con tamaño cero, no un error del objeto.

El contrato incluye la política de duplicados. Una secuencia puede contener dos textos iguales en posiciones diferentes. Un conjunto conservaría una sola pertenencia. Una interfaz no promete automáticamente rapidez: dos implementaciones compatibles pueden tener costos diferentes. Documentar esas garantías evita escoger una estructura únicamente por el nombre de sus métodos.

## Caso paso a paso

La variable se declara Secuencia<String>, se construye con Vector y recibe rojo y azul. Insertar verde en posición 1 deja rojo,verde,azul. Eliminar posición 0 devuelve rojo y reduce el tamaño a 2.

Calcula el resultado antes de ejecutar. Sigue las variables que cambian y explica por qué cada operación conserva el contrato. Compara la representación antes y después, además de observar lo que se imprime.

## Código completo

El [ejemplo](Main.java) utiliza las [clases compartidas](../../src/curso). Examina el método que ejecuta cada operación. Las estructuras genéricas del curso no permiten null; los índices representan posiciones de la región activa.

```java
import curso.Vector;
import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Secuencia<String> s=new Vector<>();
        s.agregar("rojo");
        s.agregar("azul");
        s.insertar(1,"verde");
        System.out.println(s.eliminar(0));
        System.out.println(s.tamano());
        System.out.println(s.obtener(0));
    }
}
```

## Ejecución

Desde la raíz, usa `python3 scripts/ejecutar.py modulo01/01-tipos-abstractos` en Ubuntu/macOS o `py -3 scripts/ejecutar.py modulo01/01-tipos-abstractos` en Windows. Revisa la [guía del ambiente](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
rojo
2
verde
```

## Eficiencia y límites

El comportamiento no fija una complejidad universal. En Vector, consultar O(1), insertar/eliminar en medio O(n), agregar O(1) amortizado.

Distingue la operación estudiada del recorrido que imprime datos y de las comprobaciones. Copiar o mostrar n elementos agrega trabajo lineal aunque consultar uno sea constante.

## Errores frecuentes

Confundir interfaz con almacenamiento; definir solo el caso normal; asumir que toda colección impide duplicados.

## Ejercicio resuelto

Agrega azul nuevamente. ¿Debe crecer la secuencia? Escribe la poscondición de eliminar(i).

<details><summary>Contrastar la solución</summary>

Crece porque permite duplicados. eliminar retira exactamente la posición i, conserva el orden relativo restante, devuelve el valor anterior y reduce el tamaño en uno.

</details>

## Práctica y evidencia

1. Reproduce la entrada original y justifica cada resultado.
2. Resuelve el ejercicio antes de desplegar la solución.
3. Agrega una ausencia, un empate o una entrada inválida pertinente al contrato.
4. Comprueba una propiedad que pueda fallar aunque la salida parezca correcta.

Entrega entrada, resultado esperado y observado, y análisis del costo. Si cambias la implementación, ejecuta las [pruebas](../../tests/README.md).

[Inicio](../../README.md) · [Anterior](../../docs/prerrequisitos.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/02-referencias-y-memoria/README.md)
