# Taller 4. Hashing, prioridad y prefijos

[Inicio](../README.md) · [Módulo](../modulo04/README.md)

## Propósito

Demuestra que puedes mantener el contrato y elegir una estructura según las operaciones. Entrega fuentes, trazas y casos reproducibles. Calcula a mano antes de ejecutar y conserva la entrada que produjo cada resultado.

## Actividades

1. **Sondeo.** Fuerza colisiones y una tumba antes de una clave existente.
2. **Carga.** Distingue carga activa y usada; provoca reconstrucción.
3. **Encadenamiento.** Usa carga mayor que uno y explica recorrido de cubeta.
4. **Claves.** Prueba igualdad y hash de claves inmutables.
5. **Montículo.** Traza ascenso y descenso con duplicados y negativos.
6. **Prioridad.** Define desempate por llegada sin restar enteros.
7. **Heapsort.** Comprueba vacío, creciente, decreciente y repetidos.
8. **Trie.** Prueba palabra, prefijo, vacío y poda sin perder otra clave.

## Diseño de evidencia

Para cada actividad define entrada, resultado esperado, operación y propiedad comprobada. Incluye vacío, un dato, duplicado, ausencia y una entrada inválida cuando sean pertinentes. Compara contenido con un oráculo que resuelva el mismo problema; no exijas una forma particular o una única ruta ante empates.

<details><summary>Orientación para contrastar</summary>

Las tumbas no terminan búsqueda. Encadenamiento admite carga mayor que uno. Hash igual no implica clave igual. El montículo no es un BST; trie exige marca terminal y conserva prefijos compartidos.

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
