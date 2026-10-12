# Selección y comprobación de algoritmos de grafos

[Inicio](../../README.md) · [Anterior](../../modulo05/18-ciclos-negativos/README.md) · [Índice](../README.md) · [Siguiente recurso](../20-orden-topologico/README.md)


## Objetivo

Comparar resultados con un oráculo apropiado y decidir según dirección, pesos y cantidad de consultas.

## Desarrollo conceptual

La selección comienza por el problema. Para mínimos desde un origen con pesos no negativos, Dijkstra suele ser apropiado. Para todos los pares en un grafo pequeño, Floyd–Warshall ofrece una matriz completa y permite detectar ciclos negativos. Para conectar componentes de un grafo no dirigido con costo total mínimo, Kruskal resuelve un bosque, no rutas.

La densidad también importa. Una lista de adyacencia ahorra memoria en redes dispersas. Una matriz ocupa O(V²) incluso si hay pocas aristas. Repetir Dijkstra desde varios orígenes puede ser razonable cuando se consultan pocas fuentes; Floyd paga O(V³) por anticipado para ofrecer luego distancias en O(1), sin contar la reconstrucción.

En grafos no negativos, Dijkstra y Floyd resuelven las mismas distancias cuando se usa el mismo origen y dirección. Comparar ambos detecta errores, pero no constituye una prueba independiente completa si comparten el mismo defecto de representación. Las pruebas del curso agregan un oráculo de Bellman–Ford para distancias pequeñas y enumeración de subconjuntos para el costo mínimo de Kruskal.

Las pruebas no exigen una única ruta cuando existen empates. Comprueban costo, extremos y pasos válidos. Para los bosques comprueban componentes, ausencia de ciclos y costo óptimo. La evidencia debe explicar qué propiedad se verificó y con qué entradas; decir que el programa corre no basta para sostener la corrección.

## Caso paso a paso

Para una red no dirigida de cuatro vértices, compara las distancias de Dijkstra desde 0 con la fila 0 de Floyd. Después calcula el bosque mínimo. Las distancias deben coincidir; el costo del bosque responde a una pregunta distinta.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Grafo](../../src/curso/Grafo.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Grafo g=new Grafo(4,false);
        g.agregar(0,1,3);
        g.agregar(1,2,2);
        g.agregar(0,2,10);
        g.agregar(2,3,4);
        Grafo.Rutas r=g.dijkstra(0);
        Grafo.Floyd f=g.floyd();
        for(int v=0;  v<4;  v++)System.out.println(v+": "+r.distancia(v)+" = "+f.distancia(0,v));
        System.out.println("bosque="+g.kruskal().costo());
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py modulo05/19-seleccion-y-comprobacion
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py modulo05/19-seleccion-y-comprobacion
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
0: 0 = 0
1: 3 = 3
2: 5 = 5
3: 9 = 9
bosque=9
```


## Costos y límites

Dijkstra: O(V+E log(E+1)); Floyd: O(V³); Kruskal: O(V+E log(E+1)). El número de consultas cambia qué costo conviene pagar.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Comparar listas exactas ante empates; usar MST como oráculo de rutas; asumir que dos algoritmos que coinciden prueban cualquier entrada.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Diseña cuatro casos: peso cero, aislado, empate y arista negativa. Decide qué algoritmo admite cada uno.

<details>
<summary>Ver una solución razonada</summary>

Dijkstra admite los tres primeros si todos los pesos son no negativos y rechaza el cuarto. Floyd admite negativos y detecta ciclos relevantes. Kruskal admite negativos en grafos no dirigidos y devuelve bosque si hay aislados.

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


[Inicio](../../README.md) · [Anterior](../../modulo05/18-ciclos-negativos/README.md) · [Índice](../README.md) · [Siguiente recurso](../20-orden-topologico/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2019%20%C2%B7%20Selecci%C3%B3n%20y%20comprobaci%C3%B3n%20de%20algoritmos%20de%20grafos%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo05%2F19-seleccion-y-comprobacion%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
