# Proyecto final: catálogo con índices coherentes

[Inicio](../../README.md) · [Anterior](../../modulo06/06-proyecto-integrador/README.md) · [Índice](../README.md) · [Siguiente recurso](../../proyecto/README.md)

## Objetivo

Integrar secuencias, mapas ordenados, conjuntos y un índice invertido con carga validada.

## Conceptos y razonamiento

El catálogo almacena libros con ID positivo único, título y autor. Un TreeMap resuelve igualdad por ID y devuelve registros ordenados. Un índice invertido relaciona cada palabra normalizada del título con un TreeSet de IDs. Consultar un prefijo de palabra recorre las claves correspondientes y reúne sus identificadores sin repetir libros.

Cada modificación debe mantener los dos índices coherentes. Agregar valida antes de registrar, rechaza IDs duplicados e incorpora las palabras. Eliminar retira el registro y cada asociación; una palabra sin libros se elimina del índice. verificar reconstruye un índice esperado a partir de los registros y lo compara con el índice actual.

La normalización de esta aplicación convierte a minúsculas y retira marcas de acento. Es una elección explícita para consultas, no una regla universal del español. Los títulos originales se conservan para mostrar y guardar. Una consulta es un prefijo de una sola palabra; no ofrece comprensión de frases ni búsqueda semántica.

El formato UTF-8 tiene cabecera CATALOGO-1 y filas id;titulo;autor. Los campos no permiten punto y coma ni saltos de línea. La carga construye un candidato completo y solo lo devuelve si todas las filas son válidas. El límite es diez mil registros. Guardar usa escritura directa y puede reemplazar el archivo; no promete atomicidad ni transacciones. La guía del proyecto define pruebas y extensiones.

## Caso paso a paso

Agrega tres libros. El prefijo arb reúne los títulos que contienen Árboles; ja encuentra Java. Eliminar un ID debe quitarlo del resultado aunque su título compartiera palabras con otro libro.

Calcula el resultado antes de ejecutar. Sigue las variables que cambian y explica por qué cada operación conserva el contrato. Compara la representación antes y después, además de observar lo que se imprime.

## Código completo

El [ejemplo](Main.java) utiliza las [clases compartidas](../../src/curso). Examina el método que ejecuta cada operación. Las estructuras genéricas del curso no permiten null; los índices representan posiciones de la región activa.

```java
import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Catalogo c=new Catalogo();
        c.agregar(new Catalogo.Libro(1,"Árboles y grafos","Ana"));
        c.agregar(new Catalogo.Libro(2,"Java práctico","Luis"));
        c.agregar(new Catalogo.Libro(3,"Árboles balanceados","Eva"));
        if(args.length==0){
            c.verificar();
            System.out.println(c.prefijo("arb").stream().map(Catalogo.Libro::id).toList());
            c.eliminar(1);
            c.verificar();
            System.out.println(c.prefijo("arb").stream().map(Catalogo.Libro::id).toList());
            return;
        }
        if(args.length!=1||!args[0].equals("--menu"))throw new IllegalArgumentException("Uso: --menu");
        Scanner s=new Scanner(System.in,java.nio.charset.StandardCharsets.UTF_8);
        while(true){
            System.out.println("1 Agregar | 2 ID | 3 Prefijo | 4 Eliminar | 5 Guardar | 6 Cargar | 0 Salir");
            if(!s.hasNextLine())return;
            String opcion=s.nextLine().trim();
            try{
                switch(opcion){
                    case "0" -> {
                        System.out.println("Fin");
                        return;
                    }
                    case "1" -> {
                        System.out.println("ID:");
                        if(!s.hasNextLine())return;
                        int id=Integer.parseInt(s.nextLine());
                        System.out.println("Título:");
                        if(!s.hasNextLine())return;
                        String titulo=s.nextLine();
                        System.out.println("Autor:");
                        if(!s.hasNextLine())return;
                        String autor=s.nextLine();
                        c.agregar(new Catalogo.Libro(id,titulo,autor));
                        System.out.println("Libro agregado");
                    }
                    case "2" -> {
                        System.out.println("ID:");
                        if(!s.hasNextLine())return;
                        Catalogo.Libro l=c.buscar(Integer.parseInt(s.nextLine()));
                        System.out.println(l==null?"No encontrado":l);
                    }
                    case "3" -> {
                        System.out.println("Prefijo:");
                        if(!s.hasNextLine())return;
                        System.out.println(c.prefijo(s.nextLine()));
                    }
                    case "4" -> {
                        System.out.println("ID:");
                        if(!s.hasNextLine())return;
                        System.out.println(c.eliminar(Integer.parseInt(s.nextLine()))?"Libro eliminado":"No encontrado");
                    }
                    case "5" -> {
                        System.out.println("Archivo (puede reemplazarlo):");
                        if(!s.hasNextLine())return;
                        c.guardar(java.nio.file.Path.of(s.nextLine()));
                        System.out.println("Catálogo guardado");
                    }
                    case "6" -> {
                        System.out.println("Archivo:");
                        if(!s.hasNextLine())return;
                        Catalogo candidato=Catalogo.cargar(java.nio.file.Path.of(s.nextLine()));
                        c=candidato;
                        System.out.println("Catálogo cargado");
                    }
                    default -> System.out.println("Opción inválida");
                }
            }catch(IllegalArgumentException|IllegalStateException|java.io.IOException e){
                System.out.println("Error: "+e.getMessage());
            }
        }
    }
}
```

## Ejecución

Desde la raíz, usa `python3 scripts/ejecutar.py modulo06/07-catalogo-e-indice-invertido` en Ubuntu/macOS o `py -3 scripts/ejecutar.py modulo06/07-catalogo-e-indice-invertido` en Windows. Revisa la [guía del ambiente](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[1, 3]
[3]
```

## Eficiencia y límites

Con palabras por título y longitudes acotadas, agregar/eliminar depende de O(log n) por índice y asociación. Consulta por prefijo agrega las palabras encontradas y las asociaciones reunidas; no es O(1). verificar recorre todo el catálogo.

Distingue la operación estudiada del recorrido que imprime datos y de las comprobaciones. Copiar o mostrar n elementos agrega trabajo lineal aunque consultar uno sea constante.

## Errores frecuentes

Actualizar solo el índice principal; duplicar resultados por varias palabras coincidentes; perder acentos originales; devolver una carga parcial.

## Ejercicio resuelto

Agrega un libro con dos palabras que empiecen por arb y comprueba una sola aparición. Guarda, carga y elimina un registro; verifica ambos índices.

<details><summary>Contrastar la solución</summary>

El conjunto de IDs deduplica coincidencias. Tras cargar, todos los registros y prefijos deben coincidir. Después de eliminar, verificar reconstruye el índice y detecta asociaciones sobrantes. Un archivo con ID repetido se rechaza.

</details>

## Práctica y evidencia

1. Reproduce la entrada original y justifica cada resultado.
2. Resuelve el ejercicio antes de desplegar la solución.
3. Agrega una ausencia, un empate o una entrada inválida pertinente al contrato.
4. Comprueba una propiedad que pueda fallar aunque la salida parezca correcta.

Entrega entrada, resultado esperado y observado, y análisis del costo. Si cambias la implementación, ejecuta las [pruebas](../../tests/README.md).

[Inicio](../../README.md) · [Anterior](../../modulo06/06-proyecto-integrador/README.md) · [Índice](../README.md) · [Siguiente recurso](../../proyecto/README.md)

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2007%20%C2%B7%20Proyecto%20final%3A%20cat%C3%A1logo%20con%20%C3%ADndices%20coherentes%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo06%2F07-catalogo-e-indice-invertido%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
