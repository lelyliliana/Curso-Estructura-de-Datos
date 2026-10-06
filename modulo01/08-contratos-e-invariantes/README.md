# Invariantes y oráculos de comprobación

[Inicio](../../README.md) · [Anterior](../../modulo01/07-amortizacion/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/09-busqueda-binaria/README.md)


## Objetivo

Comprobar el resultado y la representación de una secuencia mediante invariantes y un oráculo independiente.

## Desarrollo conceptual

Una invariante debe conservarse entre operaciones públicas. En Vector, cantidad está entre cero y capacidad; las posiciones activas contienen datos no null y las restantes están limpias. Que el programa muestre una lista correcta no demuestra que las posiciones inactivas no estén reteniendo referencias antiguas.

Un oráculo calcula el resultado con otra estrategia. ArrayList sirve para comparar el contenido de una secuencia: se aplica la misma operación a ambos y se contrastan los valores después de cada paso. La comparación no exige que ambos tengan la misma capacidad interna, porque eso pertenece a su representación.

verificar recorre el respaldo del vector y revisa sus invariantes. copia devuelve el contenido lógico. La combinación detecta dos tipos de defecto: un resultado incorrecto y una representación incoherente. Las pruebas no sustituyen declarar precondiciones como los índices válidos y la prohibición de null.

Incluye vacío, un valor, extremos, repetidos, ausencia y secuencias de crecimiento y eliminación. Las pruebas aleatorias fijan una semilla para reproducir un fallo, pero no demuestran todas las entradas posibles. Al encontrar un error, conserva la primera operación que produjo la diferencia y reduce el caso antes de modificar varias funciones.

## Caso paso a paso

Agrega 30,10,20 al vector y al ArrayList. Elimina la posición 1 en ambos. El contenido queda [30,20] y la comparación devuelve true. verificar comprueba también que la posición liberada no conserve una referencia y que la región activa corresponda al tamaño.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Vector](../../src/curso/Vector.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.Vector;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Vector<Integer> v=new Vector<>();
        List<Integer> referencia=new ArrayList<>();
        for(int k:new int[]{30,10,20}){v.agregar(k);referencia.add(k);}
        v.eliminar(1);referencia.remove(1);
        v.verificar();
        System.out.println(v.copia());
        System.out.println(v.copia().equals(referencia));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py modulo01/08-contratos-e-invariantes
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py modulo01/08-contratos-e-invariantes
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[30, 20]
true
```


## Costos y límites

Consultar posición cuesta O(1), agregar es O(1) amortizado y eliminar en medio O(n). Construir la copia y compararla cuestan O(n). verificar recorre la capacidad, que puede superar el tamaño actual después de eliminar. El costo del proceso de pruebas no debe confundirse con el de una consulta individual.

## Errores frecuentes

Comprobar solo la impresión; exigir al oráculo la misma representación; olvidar limpiar una celda liberada; modificar la salida esperada para ocultar una diferencia sin justificarla.

## Ejercicio resuelto

Modifica una copia de eliminar para no limpiar la última celda después de desplazar. ¿Qué comprobación detecta el defecto aunque los datos activos impresos sigan correctos?

<details><summary>Ver una solución razonada</summary>

La comparación del contenido puede seguir devolviendo true, pero verificar detecta un dato no null fuera de la región activa. Eliminar debe limpiar datos[--cantidad] para retirar la referencia que ya no pertenece a la secuencia.

</details>

## Práctica y comprobación

1. Ejecuta el caso original y guarda su resultado.
2. Realiza el cambio propuesto en el ejercicio y contrasta con tu predicción.
3. Agrega un caso límite pertinente (vacío, ausencia, empate, extremo numérico o entrada inválida). Explica cuál aplica al contrato de esta lección.
4. Describe una propiedad comprobable y por qué una salida aparentemente correcta podría no ser suficiente.

Entrega la entrada utilizada, el resultado esperado y observado, y una explicación del costo de la operación principal. Si cambias un algoritmo, ejecuta las [pruebas del curso](../../tests/README.md).

## Preguntas de comprensión

- ¿Qué precondición necesita la operación y qué resultado promete?
- ¿Qué dato se modifica y qué propiedad debe conservarse?
- ¿Qué parte de la implementación explica el costo indicado?


[Inicio](../../README.md) · [Anterior](../../modulo01/07-amortizacion/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/09-busqueda-binaria/README.md)
