# Costos, mejor caso y peor caso

[Inicio](../../README.md) · [Anterior](../../modulo01/05-genericos-y-vistas/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/07-amortizacion/README.md)

## Objetivo

Contar trabajo según n y separar garantías del resultado de un cronómetro.

## Conceptos y razonamiento

La complejidad describe cómo crece el trabajo con el tamaño de la entrada. O es una cota superior asintótica; Θ expresa una cota ajustada. Una búsqueda secuencial puede terminar en el primer dato, pero en el peor caso revisa toda la secuencia. No se resume su peor caso con la entrada más favorable.

En un arreglo, obtener una posición válida es constante. Buscar por contenido es diferente: sin un índice, puede requerir n comparaciones. Si la secuencia está ordenada, búsqueda binaria descarta aproximadamente la mitad por comparación. Preparar ese orden tiene un costo que no desaparece del problema completo.

El tiempo y el espacio se analizan por separado. Construir una lista para mostrar resultados usa memoria adicional. La recursión ocupa pila según la profundidad y un arreglo dinámico puede conservar capacidad después de eliminar datos. Especifica qué representa n y qué estructuras se consideran ya preparadas.

Medir milisegundos introduce compilación, calentamiento, sistema y tamaño insuficiente. Contar comparaciones ayuda a visualizar el crecimiento, pero también requiere escoger casos representativos. Un único cronómetro no prueba una clase asintótica. En las prácticas se razona primero y se utiliza medición solo como evidencia complementaria.

## Caso paso a paso

Busca un valor ausente en arreglos de tamaños 4,8,16. El contador visita cada celda, por lo que duplica el trabajo cuando n se duplica. Si el valor está primero, visita una celda; ambos resultados pertenecen al mismo algoritmo con casos distintos.

Calcula el resultado antes de ejecutar. Sigue las variables que cambian y explica por qué cada operación conserva el contrato. Compara la representación antes y después, además de observar lo que se imprime.

## Código completo

El [ejemplo](Main.java) utiliza las [clases compartidas](../../src/curso). Examina el método que ejecuta cada operación. Las estructuras genéricas del curso no permiten null; los índices representan posiciones de la región activa.

```java
import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        for(int n:new int[]{
            4,8,16
        }){
            int[] a=new int[n];
            int visitas=0;
            for(int x:a){
                visitas++;
                if(x==99)break;
            }System.out.println(n+" -> "+visitas);
        }
    }
}
```

## Ejecución

Desde la raíz, usa `python3 scripts/ejecutar.py modulo01/06-complejidad` en Ubuntu/macOS o `py -3 scripts/ejecutar.py modulo01/06-complejidad` en Windows. Revisa la [guía del ambiente](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
4 -> 4
8 -> 8
16 -> 16
```

## Eficiencia y límites

Búsqueda secuencial: mejor Θ(1), peor Θ(n), espacio O(1) sin contar entrada. La preparación e impresión se analizan aparte.

Distingue la operación estudiada del recorrido que imprime datos y de las comprobaciones. Copiar o mostrar n elementos agrega trabajo lineal aunque consultar uno sea constante.

## Errores frecuentes

Confundir acceso por índice y búsqueda; expresar O como segundos; deducir el peor caso de una entrada favorable.

## Ejercicio resuelto

Añade un caso con el valor en la última posición y otro con el valor primero. Compara número de visitas.

<details><summary>Contrastar la solución</summary>

Último: n visitas. Primero: una. Ausente: n. Debes declarar el tipo de caso al presentar cada medida.

</details>

## Práctica y evidencia

1. Reproduce la entrada original y justifica cada resultado.
2. Resuelve el ejercicio antes de desplegar la solución.
3. Agrega una ausencia, un empate o una entrada inválida pertinente al contrato.
4. Comprueba una propiedad que pueda fallar aunque la salida parezca correcta.

Entrega entrada, resultado esperado y observado, y análisis del costo. Si cambias la implementación, ejecuta las [pruebas](../../tests/README.md).

[Inicio](../../README.md) · [Anterior](../../modulo01/05-genericos-y-vistas/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo01/07-amortizacion/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2006%20%C2%B7%20Costos%2C%20mejor%20caso%20y%20peor%20caso%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo01%2F06-complejidad%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
