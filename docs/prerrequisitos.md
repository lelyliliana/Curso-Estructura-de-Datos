# Preparación y diagnóstico

[Inicio](../README.md) · [Comenzar](../modulo01/01-tipos-abstractos/README.md)

Este curso comienza desde las estructuras fundamentales. Necesitas programación básica en Java, pero no listas, árboles ni grafos previos. Repasa [Java](https://github.com/lelyliliana/Curso-Java) si los fuentes todavía resultan difíciles de leer y [Algoritmos](https://github.com/lelyliliana/Curso-Algoritmos) para razonamiento y trazas.

## Diagnóstico

1. ¿Qué diferencia hay entre una condición y un ciclo?
2. ¿Qué imprime un recorrido por los índices 0,1,2 de [4,6,8]?
3. ¿Qué devuelve una función y cómo se utiliza ese resultado?
4. ¿Qué significa crear un objeto y guardar una referencia?
5. ¿Por qué un arreglo de longitud cuatro no admite índice cuatro?
6. ¿Cómo compruebas que una entrada numérica inválida fue rechazada?
7. ¿Por qué conviene calcular a mano un ejemplo antes de ejecutarlo?
8. ¿Qué pasos distinguen compilar y ejecutar?

<details><summary>Respuestas de orientación</summary>

La condición elige según un booleano; el ciclo repite mientras su regla lo permita. Los índices 0,1,2 recuperan 4,6,8. Una función entrega un resultado que debe asignarse o usarse. Una referencia permite acceder a un objeto; copiarla no clona ese objeto. Los índices válidos de longitud cuatro van de cero a tres. Una entrada inválida necesita una señal explícita, como una excepción controlada. Predecir permite detectar discrepancias y explicar el estado. Compilar traduce el fuente a bytecode y ejecutar utiliza ese bytecode; el ejecutor del curso prepara ambos pasos.

</details>

## Primer ejercicio

Escribe una función que encuentre un valor en un arreglo y devuelva su posición o -1. Prueba presente, ausente y arreglo vacío. No necesitas búsqueda binaria ni árboles para esta preparación. Explica cuánto recorrido podría hacer en el peor caso.

Después revisa la [configuración](ambiente-y-herramientas.md) y comienza por [tipos abstractos](../modulo01/01-tipos-abstractos/README.md).
