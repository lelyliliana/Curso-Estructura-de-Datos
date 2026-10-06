# Taller 6. Aplicaciones y proyectos

[Inicio](../README.md) · [Módulo](../modulo06/README.md)

## Propósito

Demuestra que puedes mantener el contrato y elegir una estructura según las operaciones. Entrega fuentes, trazas y casos reproducibles. Calcula a mano antes de ejecutar y conserva la entrada que produjo cada resultado.

## Actividades

1. **Espacial.** Contrasta vecino cercano y rango con búsqueda exhaustiva.
2. **Topología.** Modela un cruce visual sin conexión real.
3. **GeoJSON.** Comprueba coordenadas y escape con un parser independiente.
4. **Flujo.** Calcula media y varianza y declara memoria por grupos.
5. **Bot.** Recorre estados, invalida un código y cancela una consulta.
6. **Red.** Compara ruta mínima y bosque de menor costo.
7. **Catálogo.** Modifica registros y comprueba el índice invertido.
8. **Persistencia.** Guarda/carga acentos y rechaza duplicados sin cambiar el estado.

## Diseño de evidencia

Para cada actividad define entrada, resultado esperado, operación y propiedad comprobada. Incluye vacío, un dato, duplicado, ausencia y una entrada inválida cuando sean pertinentes. Compara contenido con un oráculo que resuelva el mismo problema; no exijas una forma particular o una única ruta ante empates.

<details><summary>Orientación para contrastar</summary>

Coordenadas no crean conectividad. GeoJSON es intercambio, no grafo automático. La memoria del flujo crece con grupos. El catálogo debe retirar asociaciones al eliminar y no entregar una carga parcial.

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
