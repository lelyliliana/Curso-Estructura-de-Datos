# Implementar un arreglo dinámico

[Inicio](../../README.md) · [Anterior](../../modulo01/03-arreglos-y-region-activa/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/05-genericos-y-vistas/README.md)

## Objetivo

Ampliar capacidad sin perder la región activa ni confundir crecimiento con tamaño.

## Conceptos y razonamiento

Vector<T> inicia con cuatro posiciones. Si una inserción no cabe, duplica el arreglo de respaldo y copia las referencias activas. El usuario observa una secuencia que puede crecer, pero cada arreglo concreto sigue teniendo longitud fija. Se reemplaza la referencia al respaldo, no se modifica su longitud.

La función reservar se llama antes de desplazar o escribir la nueva entrada. Esto mantiene la operación dentro de los límites. Las entradas no null ocupan exactamente la región [0,cantidad); la región restante está limpia. verificar comprueba esa propiedad y los límites del contador.

obtener utiliza un cast desde Object a T. La supresión de advertencia se limita a ese método y se justifica porque todas las escrituras públicas reciben T. Usar el tipo genérico sin parámetro o introducir casts externos puede romper esa garantía; no debe emplearse como solución a un error de tipos.

Eliminar no reduce automáticamente la capacidad. Eso evita copias y oscilaciones al agregar y quitar cerca de un límite. Podría agregarse una política de contracción con margen, pero requeriría revisar amortización y costos. El diseño base distingue tamaño, capacidad y memoria retenida deliberadamente.

## Caso paso a paso

Agrega cinco textos. La quinta inserción supera capacidad 4 y copia cuatro referencias a un respaldo de capacidad 8. Eliminar una posición reduce tamaño pero no capacidad.

Calcula el resultado antes de ejecutar. Sigue las variables que cambian y explica por qué cada operación conserva el contrato. Compara la representación antes y después, además de observar lo que se imprime.

## Código completo

El [ejemplo](Main.java) utiliza las [clases compartidas](../../src/curso). Examina el método que ejecuta cada operación. Las estructuras genéricas del curso no permiten null; los índices representan posiciones de la región activa.

```java
import curso.Vector;
import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Vector<String> v=new Vector<>();
        for(int i=0; i<5; i++)v.agregar("v"+i);
        v.verificar();
        System.out.println(v.copia());
        System.out.println("capacidad="+v.capacidad()+", copias="+v.copias());
        v.eliminar(2);
        System.out.println(v.copia());
    }
}
```

## Ejecución

Desde la raíz, usa `python3 scripts/ejecutar.py modulo01/04-arreglo-dinamico` en Ubuntu/macOS o `py -3 scripts/ejecutar.py modulo01/04-arreglo-dinamico` en Windows. Revisa la [guía del ambiente](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[v0, v1, v2, v3, v4]
capacidad=8, copias=4
[v0, v1, v3, v4]
```

## Eficiencia y límites

Consulta O(1). Agregar O(1) amortizado y O(n) cuando amplía. Insertar o eliminar en medio O(n). Espacio O(capacidad), que es O(n+1) para esta política sin contracción y crecimiento desde vacío, pero puede exceder al tamaño actual después de eliminar.

Distingue la operación estudiada del recorrido que imprime datos y de las comprobaciones. Copiar o mostrar n elementos agrega trabajo lineal aunque consultar uno sea constante.

## Errores frecuentes

Cambiar tamaño al reservar; exponer el arreglo interno; afirmar que eliminar siempre libera toda la capacidad.

## Ejercicio resuelto

Agrega nueve valores desde vacío. Indica las capacidades y cuántas referencias se copiaron por ampliación.

<details><summary>Contrastar la solución</summary>

Capacidades 4→8→16. Se copiaron 4+8=12 referencias. No se cuenta como copia de ampliación escribir cada nuevo elemento.

</details>

## Práctica y evidencia

1. Reproduce la entrada original y justifica cada resultado.
2. Resuelve el ejercicio antes de desplegar la solución.
3. Agrega una ausencia, un empate o una entrada inválida pertinente al contrato.
4. Comprueba una propiedad que pueda fallar aunque la salida parezca correcta.

Entrega entrada, resultado esperado y observado, y análisis del costo. Si cambias la implementación, ejecuta las [pruebas](../../tests/README.md).

[Inicio](../../README.md) · [Anterior](../../modulo01/03-arreglos-y-region-activa/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/05-genericos-y-vistas/README.md)
