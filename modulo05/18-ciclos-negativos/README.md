# Ciclos negativos y parejas afectadas

[Inicio](../../README.md) · [Anterior](../../modulo05/17-floyd-warshall/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/19-seleccion-y-comprobacion/README.md)


## Objetivo

Distinguir un camino inexistente de uno sin mínimo finito y detectar las parejas afectadas.

## Desarrollo conceptual

Un ciclo negativo tiene suma de pesos menor que cero. Si una ruta puede entrar al ciclo, recorrerlo repetidamente y luego llegar al destino, su costo puede disminuir sin límite. En ese caso no hay una distancia mínima finita para esa pareja. No se debe mostrar el valor provisional de la matriz como si fuera la respuesta matemática.

Después de Floyd–Warshall, un valor d[k][k]<0 identifica la posibilidad de un ciclo negativo accesible desde k y de vuelta a k. La pareja i,j está afectada cuando i alcanza k y k alcanza j. Un ciclo en otra componente no invalida todas las consultas del grafo.

La biblioteca construye una matriz booleana de parejas afectadas. distancia y camino lanzan una excepción para esas parejas. Para una pareja sin conexión, en cambio, distancia devuelve INF y camino devuelve una lista vacía. Para parejas alcanzables no afectadas se devuelve el costo finito normal.

Durante el cálculo, los valores negativos pueden crecer en magnitud por repeticiones de ciclos. La implementación satura las sumas a [-INF,INF] para evitar desbordes, pero no interpreta los valores saturados como distancias válidas. Los límites de entrada garantizan que los caminos simples finitos quedan lejos de esos extremos; la matriz de afectación distingue las consultas que deben rechazarse.

## Caso paso a paso

Los arcos 1→2=-3 y 2→1=1 forman un ciclo de costo -2. Si existe 0→1 y 2→3, la pareja 0,3 está afectada. La pareja 3,0 no tiene camino y 3,3 conserva su ruta trivial si no puede entrar al ciclo.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Grafo](../../src/curso/Grafo.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Grafo g=new Grafo(4,true);
        g.agregar(0,1,2);
        g.agregar(1,2,-3);
        g.agregar(2,1,1);
        g.agregar(2,3,2);
        Grafo.Floyd f=g.floyd();
        System.out.println(f.cicloAfecta(0,3));
        System.out.println(f.cicloAfecta(3,0));
        try{
            f.distancia(0,3);
        }catch(IllegalStateException e){
            System.out.println(e.getMessage());
        }System.out.println(f.distancia(3,3));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py modulo05/18-ciclos-negativos
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py modulo05/18-ciclos-negativos
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
true
false
Ruta afectada por ciclo negativo
0
```


## Costos y límites

Floyd y propagación de afectación: O(V³). Memoria O(V²).

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Confundir arista negativa con ciclo negativo; invalidar todo el grafo por un ciclo lejano; interpretar una distancia saturada como respuesta.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Agrega un vértice 4 aislado. Clasifica las consultas 0→3, 3→0 y 4→4.

<details>
<summary>Ver una solución razonada</summary>

0→3 no tiene mínimo finito por el ciclo. 3→0 es inaccesible. 4→4 tiene distancia cero y camino [4]. Son tres estados distintos.

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


[Inicio](../../README.md) · [Anterior](../../modulo05/17-floyd-warshall/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/19-seleccion-y-comprobacion/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2018%20%C2%B7%20Ciclos%20negativos%20y%20parejas%20afectadas%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo05%2F18-ciclos-negativos%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
