# Crecimiento geométrico y costo amortizado

[Inicio](../../README.md) · [Anterior](../../modulo01/06-complejidad/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/08-contratos-e-invariantes/README.md)

## Objetivo

Distribuir las copias de ampliación entre una secuencia de inserciones sin prometer costo constante individual.

## Conceptos y razonamiento

Al duplicar capacidad, algunas inserciones son caras porque copian los datos anteriores. Sin embargo, la suma de esas copias forma una progresión geométrica: 4+8+16+… . Antes de llegar a n inserciones, esa suma es menor que un múltiplo constante de n. Dividir el costo total entre las operaciones da costo amortizado constante para agregar.

Amortizado no es lo mismo que esperado. El argumento de capacidad no requiere entradas aleatorias; se aplica a cualquier secuencia de agregados desde vacío. El costo esperado, como el de hashing, usa supuestos sobre distribución o aleatoriedad. Tampoco significa que cada operación tenga la misma duración.

Crecer una celda cada vez obliga a copiar cantidades que suman 1+2+…+n y produce costo total cuadrático. Un factor geométrico mayor reduce ampliaciones pero reserva más memoria. La política debe equilibrar recursos y debe explicar qué ocurre al eliminar.

El contador copias registra referencias movidas por ampliación, no desplazamientos por inserción en medio ni escrituras de elementos nuevos. Agregar al final evita esos desplazamientos. El vector no se contrae al eliminar, por lo que una secuencia mixta debe analizar memoria retenida además del tamaño actual. La amortización del agregado no convierte insertar en medio en O(1).

## Caso paso a paso

Con 17 agregados, las ampliaciones copian 4,8,16 referencias: total 28. La capacidad final es 32. Con crecimiento de una celda, las copias serían mucho más numerosas.

Calcula el resultado antes de ejecutar. Sigue las variables que cambian y explica por qué cada operación conserva el contrato. Compara la representación antes y después, además de observar lo que se imprime.

## Código completo

El [ejemplo](Main.java) utiliza las [clases compartidas](../../src/curso). Examina el método que ejecuta cada operación. Las estructuras genéricas del curso no permiten null; los índices representan posiciones de la región activa.

```java
import curso.Vector;
import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Vector<Integer> v=new Vector<>();
        for(int i=0; i<17; i++)v.agregar(i);
        System.out.println("tamaño="+v.tamano()+", capacidad="+v.capacidad()+", copias="+v.copias());
    }
}
```

## Ejecución

Desde la raíz, usa `python3 scripts/ejecutar.py modulo01/07-amortizacion` en Ubuntu/macOS o `py -3 scripts/ejecutar.py modulo01/07-amortizacion` en Windows. Revisa la [guía del ambiente](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
tamaño=17, capacidad=32, copias=28
```

## Eficiencia y límites

n agregados desde vacío cuestan O(n) total por crecimiento geométrico; una ampliación individual O(n). Insertar en medio permanece O(n).

Distingue la operación estudiada del recorrido que imprime datos y de las comprobaciones. Copiar o mostrar n elementos agrega trabajo lineal aunque consultar uno sea constante.

## Errores frecuentes

Llamar aleatoria a la amortización; ocultar picos de copia; contar solo tamaño actual al hablar de memoria después de borrar.

## Ejercicio resuelto

Predice capacidad y copias para 9,16 y 17 agregados. ¿Por qué el salto ocurre en 17 y no en 16?

<details><summary>Contrastar la solución</summary>

9: capacidad16 y copias12. 16: capacidad16 y copias12. 17: capacidad32 y copias28. La posición 16 es la primera que no cabe en un arreglo de longitud16.

</details>

## Práctica y evidencia

1. Reproduce la entrada original y justifica cada resultado.
2. Resuelve el ejercicio antes de desplegar la solución.
3. Agrega una ausencia, un empate o una entrada inválida pertinente al contrato.
4. Comprueba una propiedad que pueda fallar aunque la salida parezca correcta.

Entrega entrada, resultado esperado y observado, y análisis del costo. Si cambias la implementación, ejecuta las [pruebas](../../tests/README.md).

[Inicio](../../README.md) · [Anterior](../../modulo01/06-complejidad/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/08-contratos-e-invariantes/README.md)
