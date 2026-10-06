# Taller 1. Fundamentos, contratos y arreglos

[Inicio](../README.md) · [Módulo](../modulo01/README.md)

## Propósito

Demuestra que puedes mantener el contrato y elegir una estructura según las operaciones. Entrega fuentes, trazas y casos reproducibles. Calcula a mano antes de ejecutar y conserva la entrada que produjo cada resultado.

## Actividades

1. **Contratos.** Define insertar, consultar y eliminar con índices y política de null.
2. **Región activa.** Traza inserciones en inicio, medio y final de un arreglo.
3. **Referencias.** Distingue copiar una referencia de clonar un objeto mutable.
4. **Genéricos.** Usa Vector<String> y Vector<Integer> sin tipos sin parámetro.
5. **Ampliación.** Predice capacidades y copias para 4,5,9 y 17 agregados.
6. **Amortización.** Compara duplicar con crecer una posición cada vez.
7. **Búsqueda.** Prueba búsqueda binaria en vacío, límites y ausencia; conserva orden.
8. **Elección.** Elige List, Set o Map para tres consultas distintas.

## Diseño de evidencia

Para cada actividad define entrada, resultado esperado, operación y propiedad comprobada. Incluye vacío, un dato, duplicado, ausencia y una entrada inválida cuando sean pertinentes. Compara contenido con un oráculo que resuelva el mismo problema; no exijas una forma particular o una única ruta ante empates.

<details><summary>Orientación para contrastar</summary>

Capacidad y tamaño son diferentes. Con 4,5,9,17 agregados las capacidades son 4,8,16,32 y las copias 0,4,12,28. El agregado amortizado no convierte insertar en medio en constante.

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
