# Igualdad, hashCode y claves estables

[Inicio](../../README.md) · [Anterior](../../modulo04/02-carga-y-eleccion-de-indices/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo04/04-monticulos/README.md)

[Desarrollo y traza del caso](DESARROLLO.md) · [Recorridos ejecutables](../../laboratorios/recorridos/README.md)

## Objetivo

Definir claves cuya igualdad y hash permanezcan coherentes durante su pertenencia a un mapa.

## Conceptos y razonamiento

Si dos claves son iguales según equals, deben producir el mismo hashCode. Lo contrario no es obligatorio: claves diferentes pueden compartir hash y la colección debe resolver la colisión comparando igualdad. Un hash no identifica universalmente un objeto ni es una contraseña criptográfica.

Una clave mutable es peligrosa si cambia un campo que participa en equals o hashCode mientras está almacenada. El mapa la colocó según su hash anterior; después una consulta puede explorar otra cubeta y no encontrarla. La igualdad cambiante también puede romper conjuntos. La práctica habitual es utilizar claves inmutables o retirar y reinsertar de forma controlada.

Un record con componentes inmutables proporciona una clave sencilla. En el ejemplo Clave contiene un int y dos instancias con el mismo id son iguales. El valor del mapa puede cambiar sin cambiar la clave. Una etiqueta visible del registro no necesita pertenecer a la identidad si la regla del negocio usa únicamente el ID.

No uses la posición del objeto en memoria como identificador persistente. Tampoco supongas que hashCode es único o que una función hash de la biblioteca sirve para seguridad. El curso compara claves completas y usa políticas explícitas para duplicados y actualización. Cuando cambies el modelo de clave, revisa pruebas de igualdad, actualización y ausencia antes de evaluar rendimiento.

## Caso paso a paso

Dos Clave(7) son iguales y tienen hash igual. Poner ambas en el mapa actualiza una sola asociación. El tamaño final es uno y consultar con otra Clave(7) recupera el valor actualizado.

Calcula el resultado antes de ejecutar. Sigue las variables que cambian y explica por qué cada operación conserva el contrato. Compara la representación antes y después, además de observar lo que se imprime.

## Código completo

El [ejemplo](Main.java) utiliza las [clases compartidas](../../src/curso). Examina el método que ejecuta cada operación. Las estructuras genéricas del curso no permiten null; los índices representan posiciones de la región activa.

```java
import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        record Clave(int id){
        };
        Clave a=new Clave(7),b=new Clave(7);
        Map<Clave,String> m=new HashMap<>();
        m.put(a,"original");
        m.put(b,"actual");
        System.out.println(a.equals(b));
        System.out.println(a.hashCode()==b.hashCode());
        System.out.println(m.size());
        System.out.println(m.get(new Clave(7)));
    }
}
```

## Ejecución

Desde la raíz, usa `python3 scripts/ejecutar.py modulo04/03-igualdad-y-claves` en Ubuntu/macOS o `py -3 scripts/ejecutar.py modulo04/03-igualdad-y-claves` en Windows. Revisa la [guía del ambiente](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
true
true
1
actual
```

## Eficiencia y límites

Para claves int acotadas, igualdad/hash O(1). Consulta en HashMap depende de distribución y representación; no se promete ausencia de colisiones.

Distingue la operación estudiada del recorrido que imprime datos y de las comprobaciones. Copiar o mostrar n elementos agrega trabajo lineal aunque consultar uno sea constante.

## Errores frecuentes

Exigir hash único; cambiar campos de una clave almacenada; sobreescribir equals sin mantener hashCode coherente.

## Ejercicio resuelto

Diseña una clave para estudiante con ID estable y nombre editable. ¿Debe el nombre participar en el hash?

<details><summary>Contrastar la solución</summary>

Si la identidad la define el ID, la clave usa únicamente ese valor estable; el nombre editable pertenece al valor asociado. Cambiar Ana a Ana María no cambia dónde se busca Id(7).

Dos Id(7) son iguales aunque no sean el mismo objeto y actualizan una asociación. Id(8) puede tener el mismo hash sin ser igual: el mapa conserva las dos claves lógicas y debe recuperar actual para 7 y otro para 8. El tamaño final es 2.

El recorrido fuerza una colisión con un hash constante para observar esa diferencia. No prueba rendimiento constante. Tampoco promete un resultado específico después de mutar una clave afectando equals, porque ese uso queda fuera del comportamiento especificado por Map.

</details>

## Práctica paso a paso

1. Define una clave por ID inmutable y crea dos instancias iguales y una distinta. Compara ==, equals y hashCode.
2. Fuerza una colisión válida mediante un hash constante y registra el resultado de poner las tres claves. Comprueba ambos valores con nuevas instancias iguales.
3. Guarda el nombre editable en el valor y cámbialo sin modificar la clave. Consulta otra vez por ID.
4. Ejecuta `python scripts/recorridos.py claves` y explica por qué un hash constante conserva corrección, pero no una buena distribución. No exijas una salida concreta después de violar el contrato con una clave mutable.

Entrega la tabla de asociaciones y el criterio de identidad. Consulta el [desarrollo](DESARROLLO.md).

[Inicio](../../README.md) · [Anterior](../../modulo04/02-carga-y-eleccion-de-indices/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo04/04-monticulos/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2003%20%C2%B7%20Igualdad%2C%20hashCode%20y%20claves%20estables%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo04%2F03-igualdad-y-claves%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
