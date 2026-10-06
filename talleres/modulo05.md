# Taller 5. Grafos y algoritmos de conexión

[Inicio](../README.md) · [Módulo](../modulo05/README.md)

## Propósito

Demuestra que puedes mantener el contrato y elegir una estructura según las operaciones. Entrega fuentes, trazas y casos reproducibles. Calcula a mano antes de ejecutar y conserva la entrada que produjo cada resultado.

## Actividades

1. **Representación.** Compara matriz, listas e incidencia en un grafo pequeño.
2. **Recorridos.** Obtén BFS y DFS y distingue sus preguntas.
3. **Componentes.** Incluye un aislado y verifica alcanzabilidad.
4. **Dijkstra.** Traza mejoras y entrada desactualizada con peso cero.
5. **Kruskal.** Usa DSU y comprueba V-C aristas y costo óptimo.
6. **Floyd.** Calcula etapas k y rutas con negativos sin ciclo.
7. **Ciclo negativo.** Clasifica parejas afectadas, inaccesibles y triviales.
8. **Topológico.** Representa dependencias, valida posiciones y rechaza un ciclo.

## Diseño de evidencia

Para cada actividad define entrada, resultado esperado, operación y propiedad comprobada. Incluye vacío, un dato, duplicado, ausencia y una entrada inválida cuando sean pertinentes. Compara contenido con un oráculo que resuelva el mismo problema; no exijas una forma particular o una única ruta ante empates.

<details><summary>Orientación para contrastar</summary>

BFS minimiza número de aristas, Dijkstra suma no negativa, Kruskal costo de red y Floyd todos los pares. Un ciclo negativo afecta solo parejas que pueden entrar y salir; topológico exige DAG.

Vuelve a la lección de la operación que no puedas justificar. Un resultado impreso no reemplaza comprobar la representación. Explica cada diferencia entre mejor caso, peor caso, esperado y amortizado cuando aparezca en el análisis.

</details>

## Criterios (100 puntos)

| Evidencia | Puntos |
|---|---|
| Contratos y trazas razonadas | 25 |
| Implementación e invariantes | 30 |
| Casos límite y oráculos | 25 |
| Selección y costos de tiempo/memoria | 20 |

No cambies varias estructuras al mismo tiempo sin conservar un caso pequeño que explique la modificación. Ejecuta las [comprobaciones](../tests/README.md) y entrega también una explicación de lo que esas pruebas cubren.
