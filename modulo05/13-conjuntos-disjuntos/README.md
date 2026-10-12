# Conjuntos disjuntos: unión y compresión

[Inicio](../../README.md) · [Anterior](../../modulo05/12-validacion-de-pesos/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/14-kruskal/README.md)


## Objetivo

Mantener componentes de conectividad sin recorrer todo el grafo en cada consulta.

## Desarrollo conceptual

La estructura de conjuntos disjuntos, también llamada union-find o DSU, mantiene una partición de elementos. Cada conjunto tiene un representante. Dos elementos pertenecen al mismo conjunto si sus representantes coinciden. No almacena rutas ni enumera automáticamente todas las aristas del grafo.

Al principio cada vértice forma un conjunto de tamaño uno y es su propio padre. unir(a,b) busca sus representantes. Si ya coinciden, no cambia nada y devuelve falso. Si son distintos, enlaza el representante del conjunto menor al del mayor, suma sus tamaños y reduce en uno la cantidad de componentes.

La compresión de caminos hace que las consultas futuras salten directamente al representante. La implementación primero busca la raíz y después recorre el camino original, reasignando sus padres. La unión por tamaño y la compresión evitan árboles internos innecesariamente largos.

El costo amortizado por operación es O(α(V)), donde α es la función inversa de Ackermann, de crecimiento extremadamente lento. Esta expresión no significa que cada operación individual tenga costo exactamente constante. La inicialización requiere O(V) memoria y tiempo. DSU es útil en Kruskal porque permite detectar si una arista uniría componentes o crearía un ciclo en la selección.

## Caso paso a paso

Con cinco elementos, unir 0–1 y 1–2 forma {0,1,2}. Unir nuevamente 0–2 devuelve falso. Unir 3–4 deja dos componentes. Los números de representante son detalles de implementación; la equivalencia es lo observable.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Disjuntos](../../src/curso/Disjuntos.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Disjuntos d=new Disjuntos(5);
        System.out.println(d.unir(0,1));
        d.unir(1,2);
        System.out.println(d.unir(0,2));
        d.unir(3,4);
        System.out.println("componentes="+d.componentes());
        System.out.println(d.representante(0)==d.representante(2));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py modulo05/13-conjuntos-disjuntos
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py modulo05/13-conjuntos-disjuntos
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
true
false
componentes=2
true
```


## Costos y límites

Inicialización O(V). Secuencia de operaciones O(α(V)) amortizado por consulta/unión; espacio O(V).

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Confundir representante con vértice de origen; esperar una ruta; reducir componentes cuando la unión era redundante.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Une 2 con 4 y determina componentes. ¿Por qué no debes exigir un representante concreto en las pruebas?

<details>
<summary>Ver una solución razonada</summary>

Queda una componente. El representante depende de la política y orden de uniones; se comprueba igualdad de representantes entre elementos conectados, no una etiqueta fija.

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


[Inicio](../../README.md) · [Anterior](../../modulo05/12-validacion-de-pesos/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/14-kruskal/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2013%20%C2%B7%20Conjuntos%20disjuntos%3A%20uni%C3%B3n%20y%20compresi%C3%B3n%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo05%2F13-conjuntos-disjuntos%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
