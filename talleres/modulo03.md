# Taller 3. Recursión y árboles de búsqueda

[Inicio](../README.md) · [Módulo](../modulo03/README.md)

## Propósito

Demuestra que puedes mantener el contrato y elegir una estructura según las operaciones. Entrega fuentes, trazas y casos reproducibles. Calcula a mano antes de ejecutar y conserva la entrada que produjo cada resultado.

## Actividades

1. **Recursión.** Declara caso base y demuestra reducción del problema.
2. **BST.** Inserta orden creciente y explica su altura.
3. **Recorridos.** Obtén preorden, inorden y postorden del mismo árbol.
4. **Eliminar.** Prueba BST con cero, uno y dos hijos.
5. **AVL.** Traza LL, RR, LR y RL y elimina hasta vacío.
6. **B.** Con t=2 provoca división, préstamo, fusión y contracción.
7. **B+.** Actualiza valor, elimina mínimos y conserva separadores.
8. **Rangos.** Compara un rango cerrado B+ con TreeMap.subMap.

## Diseño de evidencia

Para cada actividad define entrada, resultado esperado, operación y propiedad comprobada. Incluye vacío, un dato, duplicado, ausencia y una entrada inválida cuando sean pertinentes. Compara contenido con un oráculo que resuelva el mismo problema; no exijas una forma particular o una única ruta ante empates.

<details><summary>Orientación para contrastar</summary>

Un BST creciente puede tener altura n. AVL exige factores -1,0,1 en todos los nodos. B controla ocupación y profundidad; B+ guarda registros en hojas y actualiza separadores y enlaces.

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
