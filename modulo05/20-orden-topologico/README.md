# Orden topológico y dependencias

[Inicio](../../README.md) · [Anterior](../19-seleccion-y-comprobacion/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo06/01-indice-espacial-kd/README.md)

## Objetivo

Ordenar tareas respetando sus dependencias y detectar cuándo un ciclo impide obtener un orden completo.

## Conceptos y razonamiento

Una dependencia u→v significa que u debe realizarse antes que v. Un orden topológico lista todos los vértices de modo que cada arco avance de una posición anterior a una posterior. Solo existe si el grafo dirigido no tiene ciclos (DAG). No es un camino: dos tareas consecutivas en la lista no necesitan estar conectadas.

El algoritmo de Kahn calcula el grado de entrada de cada vértice, es decir, la cantidad de dependencias pendientes. Los vértices con grado cero pueden procesarse. Al retirar uno, disminuye el grado de sus vecinos y agrega los que llegan a cero. Esta operación simula satisfacer sus dependencias.

Si hay varias tareas listas, existen varios órdenes válidos. El curso usa PriorityQueue para elegir el número de vértice menor y obtener una salida determinista. Una cola FIFO también puede resolver el problema, con un desempate distinto y sin el costo logarítmico de prioridad.

Cuando se agota la frontera, si faltan vértices por procesar hay al menos un ciclo. No se presenta la lista parcial como una solución completa. El método rechaza grafos no dirigidos, porque la relación de precedencia debe especificar sentido. Los pesos no se utilizan: esta operación ordena dependencias, no calcula duración ni ruta mínima.

## Caso paso a paso

Los arcos 0→2, 1→2 y 2→3 dejan inicialmente listas las tareas 0 y 1. Retirar 0 reduce el grado de 2 a uno; retirar 1 lo reduce a cero. Entonces pueden salir 2 y después 3. El orden es [0,1,2,3].

Agregar 3→0 crea un ciclo 0→2→3→0. Aunque 1 puede procesarse, el resto queda bloqueado. El método detecta que no incluyó todos los vértices y lanza la excepción documentada.

## Código completo

[Ejemplo](Main.java) · [Algoritmo en Grafo](../../src/curso/Grafo.java)

```java
import curso.Grafo;
public class Main {
    public static void main(String[] args){
        Grafo g=new Grafo(4,true);
        g.agregar(0,2,0);
        g.agregar(1,2,0);
        g.agregar(2,3,0);
        System.out.println(g.ordenTopologico());
        g.agregar(3,0,0);
        try{
            g.ordenTopologico();
        }catch(IllegalStateException e){
            System.out.println(e.getMessage());
        }
    }
}
```

Desde la raíz usa `python3 scripts/ejecutar.py modulo05/20-orden-topologico` en Ubuntu/macOS o `py -3 scripts/ejecutar.py modulo05/20-orden-topologico` en Windows.

### Salida esperada

```text
[0, 1, 2, 3]
Ciclo dirigido: no existe orden topológico
```

## Costos y límites

Calcular grados y retirar dependencias cuesta O(V+E). La prioridad agrega O(V log V), pues cada vértice entra y sale una vez. El espacio auxiliar es O(V). Con una cola ordinaria sería O(V+E). El ejemplo no calcula planificación óptima ni tareas en paralelo.

## Errores frecuentes

Confundir orden con camino; decrementar una dependencia más de una vez; presentar una salida parcial como completa; aplicar el método a un grafo sin sentido declarado.

## Ejercicio resuelto

Agrega un vértice 4 aislado al grafo original sin el ciclo. ¿Debe aparecer en el orden? Comprueba la propiedad para cada arco.

<details><summary>Solución</summary>

Debe aparecer exactamente una vez porque el orden incluye todos los vértices. Con este desempate queda [0,1,2,3,4]. Para cada u→v verifica posición(u)<posición(v). No exijas conexiones entre posiciones consecutivas.

</details>

## Práctica

Representa cinco tareas con dos prerrequisitos que converjan en una tercera. Calcula un orden, agrega un ciclo y comprueba el rechazo. Entrega la lista de arcos y la tabla de posiciones para justificar el resultado.

[Anterior](../19-seleccion-y-comprobacion/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo06/01-indice-espacial-kd/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2020%20%C2%B7%20Orden%20topol%C3%B3gico%20y%20dependencias%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo05%2F20-orden-topologico%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
