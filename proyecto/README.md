# Proyecto final: catálogo con índices

[Inicio](../README.md) · [Lección final](../modulo06/07-catalogo-e-indice-invertido/README.md) · [Proyecto de red](red/README.md)

## Objetivo y modelo

Gestiona registros con ID, título y autor, consulta por ID o prefijo de una palabra del título y conserva coherencia entre índices. TreeMap permite orden por ID; el índice invertido asocia palabras normalizadas a conjuntos ordenados de IDs. Un libro puede aparecer en varias palabras sin duplicarse en la consulta.

## Ejecutar

Desde la raíz, en Ubuntu/macOS:

```bash
python3 scripts/ejecutar.py modulo06/07-catalogo-e-indice-invertido
```

En Windows utiliza py -3 en lugar de python3. Para abrir el menú agrega --menu:

```bash
python3 scripts/ejecutar.py modulo06/07-catalogo-e-indice-invertido --menu
```

1 agrega, 2 busca por ID, 3 consulta prefijo, 4 elimina, 5 guarda, 6 carga y 0 termina. Los mensajes solicitan cada campo. Los archivos se resuelven desde la raíz y el directorio de salida debe existir. Guardar puede reemplazar un archivo.

## Formato

UTF-8, cabecera CATALOGO-1, después filas id;titulo;autor. No se permiten punto y coma ni saltos de línea dentro de campos. ID es positivo y único; título tiene hasta 120 caracteres y autor hasta 80, ambos no vacíos. El límite es diez mil registros. No es un parser CSV de propósito general.

```text
CATALOGO-1
1;Árboles y grafos;Ana
2;Java práctico;Luis
3;Árboles balanceados;Eva
```

La carga construye un candidato antes de sustituir el catálogo activo. Si falla, el menú conserva el anterior. El índice se construye por operaciones validadas y nunca se acepta una asociación parcial del archivo.

## Etapas de trabajo

Primero define doce registros y una tabla de consultas por ID. Después calcula manualmente palabras y prefijos, incluida una coincidencia en dos palabras del mismo título. Agrega y elimina comprobando los dos índices. Guarda y carga para comprobar ida y vuelta de acentos y orden. Finalmente prepara archivos inválidos y demuestra que se conservó el catálogo previo.

## Casos de aceptación

| Caso | Resultado |
|---|---|
| ID existente | Un único registro |
| ID ausente | No encontrado |
| Prefijo arb con títulos Árboles | Coincidencias sin repetir IDs |
| Libro con Árboles arbustos | Una aparición para arb |
| Eliminar | ID y asociaciones retirados |
| ID duplicado | Rechazo sin sustituir registro |
| Cabecera o fila inválida | Carga rechazada, catálogo anterior intacto |
| Guardar/cargar | Mismos datos originales y resultados de consulta |

## Costos

La operación por ID es O(log n). La actualización del índice agrega las palabras distintas del título y consultas/modificaciones a mapas y conjuntos. Con longitudes y palabras acotadas es logarítmica por asociación. Buscar prefijo baja a la primera palabra y recorre las coincidentes, reúne sus IDs y obtiene registros; incluye trabajo por asociaciones y tamaño de salida. No puede declararse O(1).

## Entrega y rúbrica (100 puntos)

Entrega fuentes, datos válidos e inválidos, tabla de consultas, resultados y justificación de estructuras. Si extiendes el modelo, define primero sus contratos y añade pruebas.

| Criterio | Puntos |
|---|---|
| Modelo, validación y contratos | 20 |
| Coherencia de índices al agregar/eliminar | 30 |
| Consultas y deduplicación | 20 |
| Persistencia y rechazo sin carga parcial | 15 |
| Casos límite, evidencia y costos | 15 |

## Extensiones

Agrega un índice por autor, paginación o un criterio de orden adicional. Explica la memoria y mantenimiento que incorporas. La escritura base es directa, no atómica ni transaccional; una extensión de persistencia segura debe definir recuperación y fallos. Para relacionar grafos y cartografía, desarrolla también el [proyecto de red](red/README.md).
