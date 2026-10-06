# Taller 2. Listas, pilas, colas y deques

[Inicio](../README.md) · [Módulo](../modulo02/README.md)

## Propósito

Demuestra que puedes mantener el contrato y elegir una estructura según las operaciones. Entrega fuentes, trazas y casos reproducibles. Calcula a mano antes de ejecutar y conserva la entrada que produjo cada resultado.

## Actividades

1. **Lista simple.** Conserva cabeza, cola y tamaño al pasar de vacío a uno y volver.
2. **Inserción.** Agrega al inicio y final y después de un nodo conocido.
3. **Eliminación.** Borra primero, último, único y ausente.
4. **Circular.** Verifica cierre a cabeza y fin de un recorrido completo.
5. **Doble.** Comprueba enlaces anterior/siguiente y ambos sentidos.
6. **Pila.** Compara arreglo y nodos; prueba capacidad y extracción vacía.
7. **Cola circular.** Alterna insertar y retirar hasta envolver índices.
8. **Deque.** Resuelve paréntesis y comprueba errores de orden y cierre.

## Diseño de evidencia

Para cada actividad define entrada, resultado esperado, operación y propiedad comprobada. Incluye vacío, un dato, duplicado, ausencia y una entrada inválida cuando sean pertinentes. Compara contenido con un oráculo que resuelva el mismo problema; no exijas una forma particular o una única ruta ante empates.

<details><summary>Orientación para contrastar</summary>

La lista vacía exige extremos null y tamaño cero. Una cola circular no desplaza cada elemento al retirar; los índices y cantidad distinguen lleno de vacío. El deque debe respetar el cierre anidado.

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
