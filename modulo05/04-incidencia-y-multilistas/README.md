# Incidencia y multilistas de aristas

[Inicio](../../README.md) · [Anterior](../../modulo05/03-listas-de-adyacencia/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/05-dfs/README.md)


## Objetivo

Distinguir relaciones vértice-arista de vértice-vértice.

## Conceptos y razonamiento

Una matriz de incidencia usa filas de vértices y columnas de aristas. En simple no dirigido, cada columna tiene dos 1, uno por extremo. No es la matriz de adyacencia, que relaciona dos vértices. En dirigido puede usarse-1 para salida y+1 para entrada, si se declara esa convención.

Una matriz de adyacencia entre aristas representa otro grafo, llamado grafo línea, si relaciona aristas que comparten un extremo. No debe presentarse como una matriz de incidencia vértice-arista. Separar estos objetos evita confusiones de dimensión y significado.

La multilista guarda un objeto por arista no dirigida con sus dos extremos y enlaces de pertenencia a ambas listas de incidencia. Para recorrer desde un vértice se debe seguir el enlace correspondiente a ese extremo, no siempre el mismo campo. El código muestra primero referencias compartidas a objetos Arista desde listas de incidencia y después una multilista clásica con NodoArista, siguienteA y siguienteB. La selección de enlace depende del vértice que se está recorriendo. Insertar al inicio de cada lista cambia el orden de vecinos, pero conserva el grafo.

## Caso resuelto y prueba de escritorio

Vértices 0, 1, 2; aristas e 0=(0, 1), e 1=(1, 2). Incidencia filas [1, 0], [1, 1], [0, 1]. Cada objeto arista es compartido por las listas de sus extremos; no se crean dos aristas lógicas.

## Código completo

Archivo: [Main.java](Main.java).

```java
import java.util.*;
public class Main {
    record Arista(int a, int b) {
    }
    static class NodoArista {
        final int a, b;
        NodoArista siguienteA, siguienteB;
        NodoArista(int a, int b) {
            this.a=a;
            this.b=b;
        }
        NodoArista siguiente(int v) {
            if (v==a) return siguienteA;
            if (v==b) return siguienteB;
            throw new IllegalArgumentException("Vértice no incidente");
        }
        int otro(int v) {
            if (v==a) return b;
            if (v==b) return a;
            throw new IllegalArgumentException("Vértice no incidente");
        }
    }
    static class Multilista {
        final NodoArista[] cabezas;
        Multilista(int n) {
            if(n<0) throw new IllegalArgumentException("Tamaño no negativo");
            cabezas=new NodoArista[n];
        }
        void validar(int v) {
            if(v<0 || v>=cabezas.length) throw new IllegalArgumentException("Vértice inválido");
        }
        boolean conectar(int a, int b) {
            validar(a);
            validar(b);
            if(a==b) throw new IllegalArgumentException("Sin lazos");
            for(NodoArista p=cabezas[a]; p!=null; p=p.siguiente(a)) if(p.otro(a)==b) return false;
            NodoArista nuevo=new NodoArista(a,b);
            nuevo.siguienteA=cabezas[a];
            nuevo.siguienteB=cabezas[b];
            cabezas[a]=nuevo;
            cabezas[b]=nuevo;
            return true;
        }
        List<Integer> vecinos(int v) {
            validar(v);
            List<Integer> r=new ArrayList<>();
            for(NodoArista p=cabezas[v]; p!=null; p=p.siguiente(v)) r.add(p.otro(v));
            return r;
        }
    }
    public static void main(String[] args) {
        List<Arista> aristas = List.of(new Arista(0,1), new Arista(1,2));
        int[][] incidencia = new int[3][aristas.size()];
        List<List<Arista>> listas = new ArrayList<>();
        for (int i=0; i<3; i++) listas.add(new ArrayList<>());
        for (int j=0; j<aristas.size(); j++) {
            Arista e=aristas.get(j);
            incidencia[e.a()][j]=1;
            incidencia[e.b()][j]=1;
            listas.get(e.a()).add(e);
            listas.get(e.b()).add(e);
        }
        for (int[] fila : incidencia) System.out.println(Arrays.toString(fila));
        System.out.println(listas.get(0).get(0) == listas.get(1).get(0));
        Multilista m=new Multilista(3);
        m.conectar(0,1);
        m.conectar(1,2);
        for(int v=0; v<3; v++) System.out.println(m.vecinos(v));
    }
}
```

## Ejecución paso a paso

1. Prepara el JDK según la [guía de ambiente](../../docs/ambiente-y-herramientas.md).
2. Abre una terminal en la carpeta de esta lección. Cada Main.java es independiente: no compiles todos juntos.
3. Ejecuta este comando en PowerShell (Windows), Terminal (Ubuntu) o Terminal (macOS):

```bash
java Main.java
```

4. Compara con [esperado.txt](esperado.txt). Antes de modificar, explica qué instrucción produce cada resultado.

### Salida esperada

```text
[1, 0]
[1, 1]
[0, 1]
true
[1]
[2, 0]
[1]
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Matriz de incidencia Θ(VE) espacio; listas de incidencia Θ(V+E). El objeto compartido evita duplicar la arista lógica, aunque tiene dos pertenencias. En la multilista clásica, enumerar vecinos cuesta Θ(grado(v)); insertar busca antes un duplicado en O(grado(a)) y luego enlaza en Θ(1).

## Errores frecuentes

- Llamar incidencia a una matriz arista-arista.
- Seguir siempre siguienteA sin comprobar extremo actual.

## Ejercicio

Define NodoArista(a, b, siguienteA, siguienteB) y escribe la regla para avanzar desde v.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Si v==a, usa siguienteA; si v==b, usa siguienteB; otro v no es incidente y debe rechazarse. El campo cabeza [v] apunta a una arista incidente. Al insertarla, se conectan ambos campos a las cabezas previas y se sustituyen ambas cabezas.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.


[Inicio](../../README.md) · [Anterior](../../modulo05/03-listas-de-adyacencia/README.md) · [Índice](../README.md) · [Siguiente recurso](../../modulo05/05-dfs/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2004%20%C2%B7%20Incidencia%20y%20multilistas%20de%20aristas%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo05%2F04-incidencia-y-multilistas%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
