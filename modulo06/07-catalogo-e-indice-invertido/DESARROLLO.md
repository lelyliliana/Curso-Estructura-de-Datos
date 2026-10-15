# Mantener dos índices y sustituir el catálogo solo después de una carga válida

[Lección](README.md) · [Índice del módulo](../README.md) · [Recorridos ejecutables](../../laboratorios/recorridos/README.md)

## 1. Relaciona registro e índice invertido

El índice principal conserva un libro por ID. El índice invertido asocia palabras normalizadas con conjuntos de IDs. Para el libro 1, «Árboles arbustos», aparecen arboles→{1} y arbustos→{1}. Si otro libro 3 contiene Árboles, arboles pasa a {1,3}.

Consultar arb recorre las palabras que comienzan por ese prefijo y une sus IDs. Como el libro 1 coincide por dos palabras, un conjunto evita devolverlo dos veces. El resultado es [1,3], ordenado por ID. No se busca una frase ni una representación semántica del título.

## 2. Sigue la eliminación de ambas asociaciones

| Paso | Índice por ID | arboles | arbustos | Resultado de arb |
|---|---|---|---|---|
| Libros 1 y 3 presentes | 1 y 3, además de otros | {1,3} | {1} | [1,3] |
| Eliminar libro 1 | Solo 3 entre los coincidentes | {3} | Se retira la palabra vacía de IDs | [3] |

No basta borrar el registro principal y dejar asociaciones colgantes. verificar reconstruye desde los libros un índice esperado y lo compara con el mantenido. Esa operación cuesta recorrer los registros y sus palabras; no debe describirse como consulta constante.

## 3. Conserva originales y normaliza solo la consulta/índice

ÁRB y arb buscan las mismas palabras según la política de minúsculas y eliminación de marcas del catálogo. El título guardado sigue siendo «Árboles arbustos». Normalizar no autoriza a perder el original al guardar UTF-8.

Esta política difiere del trie ASCII, que rechaza acentos en vez de normalizarlos. Son contratos explícitos para aplicaciones distintas. En el catálogo el prefijo debe ser una sola palabra no vacía; no se interpreta «dos palabras» como una búsqueda compuesta.

## 4. Carga un candidato antes de sustituir el activo

El archivo de práctica comienza con CATALOGO-1. Una fila válida de ID 9 seguida de otra fila con el mismo ID produce rechazo. El candidato puede haber incorporado su primera fila, pero no se devuelve como catálogo válido ni se asigna al activo.

En el menú, la asignación ocurre después de Catalogo.cargar. Si falla, el activo anterior conserva identidad y registros. Esto evita una carga parcial visible para quien usa el menú. No significa que guardar sea atómico: guardar escribe directamente y puede reemplazar un archivo. La validación de candidato en memoria y la durabilidad transaccional del archivo son garantías diferentes.

## 5. Ejecuta con archivos temporales propios

```text
python scripts/recorridos.py catalogo
```

El programa comprueba prefijo con acento, deduplicación, ID repetido, guardar/cargar, eliminación coherente y rechazo de archivo duplicado. Usa una carpeta temporal, conserva el catálogo activo ante el fallo y elimina sus archivos al terminar. No sobrescribe un archivo personal del estudiante.

Abre [Catalogo](../../src/curso/Catalogo.java), localiza putIfAbsent, tokens, eliminar, cargar y verificar. Explica qué comprobación detectaría actualizar un solo índice o asignar el candidato antes de terminar de validar.

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2007%20%C2%B7%20Proyecto%20final%3A%20cat%C3%A1logo%20con%20%C3%ADndices%20coherentes%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo06%2F07-catalogo-e-indice-invertido%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
