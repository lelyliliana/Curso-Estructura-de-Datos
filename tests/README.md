# Comprobaciones del curso

[Inicio](../README.md) · [Contratos y pruebas](../docs/contratos-y-pruebas.md)

Desde la raíz ejecuta python3 scripts/verificar.py en Ubuntu/macOS o py -3 scripts/verificar.py en Windows. Necesitas JDK 21 y Python 3.12 o superior, sin paquetes adicionales.

Se contrastan **71 ejemplos** con sus salidas y documentación. Se comprueban **20 grupos generales** y **16 grupos asociados a recursos básicos**, además de enlaces, JSON y el menú del catálogo. Cada ejemplo se compila aislado para evitar conflictos entre clases Main. Se ejecutan además los [siete recorridos docentes](../laboratorios/recorridos/README.md), con 69 comprobaciones y comparación de su salida completa.

| Área | Evidencia |
|---|---|
| Vector genérico | 6000 operaciones frente a ArrayList; límites, región activa y crecimiento |
| Listas, pilas y colas | Casos vacíos, extremos, capacidad, secuencias aleatorias y contratos |
| Árboles | Contenido frente a conjuntos/mapas, alturas, ocupación y hojas enlazadas |
| Hash y prioridad | Colisiones, tumbas, carga, reconstrucción y extracción |
| Trie | 3000 operaciones frente a un conjunto y filtrado de prefijos |
| Rutas | Grafos pequeños frente a Bellman–Ford y costo reconstruido |
| Kruskal | 64 grafos y costo óptimo mediante enumeración de subconjuntos |
| Orden topológico | 64 DAG, cada vértice una vez y precedencia de cada arco |
| Índice espacial | Vecinos y rangos frente a búsqueda lineal |
| Catálogo | 2000 operaciones, búsqueda exhaustiva de prefijos y persistencia UTF-8 |
| Aplicaciones | GeoJSON con parser independiente, estadística en flujo y estados del bot |
| Recorridos docentes | Referencias compartidas, crecimiento, contratos de colecciones, colisiones, poda, precedencia y carga sin sustitución parcial |

Las semillas fijadas permiten reproducir fallos. Las salidas documentadas deben coincidir exactamente con Main.java y esperado.txt.

Un resultado correcto aporta evidencia sobre las entradas y contratos comprobados. Si cambias una política, agrega casos para ella y justifica el cambio de salida. No reemplaces esperado.txt para esconder una discrepancia con el razonamiento.

[Resultados y límites de la revisión](../docs/verificacion.md).
