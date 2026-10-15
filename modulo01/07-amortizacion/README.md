# Crecimiento geométrico y costo amortizado

[Inicio](../../README.md) · [Anterior](../../modulo01/06-complejidad/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/08-contratos-e-invariantes/README.md)

[Desarrollo y traza del caso](DESARROLLO.md) · [Recorridos ejecutables](../../laboratorios/recorridos/README.md)

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

Con 9 agregados, capacidad 16 y copias acumuladas 12. Con 16, se conservan esos mismos valores. El agregado número 17 exige ampliar a 32: copia 16 referencias nuevas y lleva el contador acumulado a 28. Su costo en el modelo es 16 copias + 1 escritura, no 28 + 1.

Para toda la secuencia de 17, el trabajo modelado es 17 escrituras + 28 copias = 45. Ese total lineal sustenta el costo amortizado constante de agregar al final; no convierte cada operación individual en constante.

Tras eliminar 16 elementos quedan tamaño 1, capacidad 32 y copias 28. La siguiente frontera de crecimiento ocurre al agregar el elemento 33: capacidad 64 y copias acumuladas 4+8+16+32=60. Los desplazamientos de insertar en medio no están incluidos en copias y conservan su costo lineal.

</details>

## Práctica paso a paso

1. Completa la tabla para tamaños 4, 5, 8, 9, 16 y 17: capacidad, copias acumuladas y copias del último agregado. No ejecutes todavía.
2. Calcula 17 escrituras más 28 copias y explica por qué el total no significa que cada agregado costara 45.
3. Predice tamaño/capacidad después de eliminar 16 elementos del final. Comprueba la región activa.
4. Ejecuta `python scripts/recorridos.py amortizacion` y justifica cada salto. En una copia propia prueba la siguiente frontera, 33, y deriva el resultado antes de ejecutar.

Entrega la cuenta de una operación, la cuenta acumulada y el análisis de memoria retenida. El [desarrollo](DESARROLLO.md) define el modelo de costo.

[Inicio](../../README.md) · [Anterior](../../modulo01/06-complejidad/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/08-contratos-e-invariantes/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2007%20%C2%B7%20Crecimiento%20geom%C3%A9trico%20y%20costo%20amortizado%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo01%2F07-amortizacion%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
