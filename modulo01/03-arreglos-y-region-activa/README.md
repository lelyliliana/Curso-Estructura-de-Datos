# Arreglos y región activa

[Inicio](../../README.md) · [Anterior](../../modulo01/02-referencias-y-memoria/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/04-arreglo-dinamico/README.md)

## Objetivo

Separar capacidad física, tamaño lógico y posiciones válidas.

## Conceptos y razonamiento

Un arreglo Java tiene longitud fija después de crearse. Puede almacenar primitivas o referencias. Si reserva seis posiciones para una secuencia de tres datos, la longitud es seis y el tamaño lógico es tres. Las posiciones restantes no son elementos activos, aunque tengan valores predeterminados.

Consultar un índice del arreglo cuesta O(1), pero buscar por valor puede requerir recorrer n posiciones. Insertar en medio desplaza a la derecha para conservar el orden; eliminar desplaza a la izquierda. Una estructura puede usar el arreglo como capacidad disponible y mantener un contador separado.

Al insertar, se mueven datos desde el final de la región activa hacia el punto de inserción. Recorrer en sentido incorrecto puede sobrescribir el dato que todavía debe copiarse. System.arraycopy admite regiones solapadas y simplifica esa operación. En una implementación con referencias, la posición que deja de ser activa se limpia para que no retenga objetos innecesarios.

El valor cero no es una señal universal de ausencia: puede formar parte de los datos. El tamaño lógico es el que determina qué posiciones representan la secuencia. Las comprobaciones deben incluir inicio, medio, final, vacío y capacidad agotada. Un error de límites suele aparecer al confundir el último índice válido n-1 con la posición de inserción al final n.

## Caso paso a paso

El arreglo tiene seis celdas y tres datos activos 2,4,8. Para insertar 6 en posición 2, mueve 8 a la posición 3 y escribe 6. El tamaño pasa a cuatro; la capacidad permanece seis.

Calcula el resultado antes de ejecutar. Sigue las variables que cambian y explica por qué cada operación conserva el contrato. Compara la representación antes y después, además de observar lo que se imprime.

## Código completo

El [ejemplo](Main.java) utiliza las [clases compartidas](../../src/curso). Examina el método que ejecuta cada operación. Las estructuras genéricas del curso no permiten null; los índices representan posiciones de la región activa.

```java
import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        int[] a=new int[6];
        a[0]=2;
        a[1]=4;
        a[2]=8;
        int n=3;
        System.arraycopy(a,2,a,3,n-2);
        a[2]=6;
        n++;
        System.out.println(Arrays.toString(Arrays.copyOf(a,n)));
        System.out.println("tamaño="+n+", capacidad="+a.length);
    }
}
```

## Ejecución

Desde la raíz, usa `python3 scripts/ejecutar.py modulo01/03-arreglos-y-region-activa` en Ubuntu/macOS o `py -3 scripts/ejecutar.py modulo01/03-arreglos-y-region-activa` en Windows. Revisa la [guía del ambiente](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[2, 4, 6, 8]
tamaño=4, capacidad=6
```

## Eficiencia y límites

Consultar por posición O(1). Buscar valor e insertar/eliminar manteniendo orden O(n). Memoria reservada O(capacidad).

Distingue la operación estudiada del recorrido que imprime datos y de las comprobaciones. Copiar o mostrar n elementos agrega trabajo lineal aunque consultar uno sea constante.

## Errores frecuentes

Recorrer hasta capacidad al buscar datos activos; usar cero como vacío; desplazar hacia el sentido que destruye información.

## Ejercicio resuelto

Elimina la posición 1 y muestra solo la región activa. ¿Qué secuencia y tamaño quedan?

<details><summary>Contrastar la solución</summary>

Queda [2,6,8], tamaño 3 y capacidad 6. Se desplazan las posiciones siguientes; al mostrar se utiliza tamaño, no longitud completa.

</details>

## Práctica y evidencia

1. Reproduce la entrada original y justifica cada resultado.
2. Resuelve el ejercicio antes de desplegar la solución.
3. Agrega una ausencia, un empate o una entrada inválida pertinente al contrato.
4. Comprueba una propiedad que pueda fallar aunque la salida parezca correcta.

Entrega entrada, resultado esperado y observado, y análisis del costo. Si cambias la implementación, ejecuta las [pruebas](../../tests/README.md).

[Inicio](../../README.md) · [Anterior](../../modulo01/02-referencias-y-memoria/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/04-arreglo-dinamico/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2003%20%C2%B7%20Arreglos%20y%20regi%C3%B3n%20activa%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo01%2F03-arreglos-y-region-activa%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
