# Resultados y alcance de la revisión

[Inicio](../README.md) · [Repetir comprobaciones](../tests/README.md) · [Recorridos docentes](../laboratorios/recorridos/README.md)

Revisión del 14 de octubre de 2026 (America/Bogota). Se reforzaron siete recursos con desarrollo de casos, práctica específica y soluciones explicadas: copias/referencias, amortización, elección de colecciones, claves hash, trie, orden topológico y catálogo. Se conservan las implementaciones compartidas y los 71 ejemplos originales.

## Evidencia ejecutada

| Comprobación | Resultado |
|---|---|
| 71 ejemplos originales | Compilación aislada y salida coincidente con esperado.txt |
| Código y salida dentro de las lecciones | Coinciden con Main.java y esperado.txt |
| Pruebas generales | 20 grupos, incluidos los 4 de Fundamentos |
| Pruebas asociadas a recursos básicos | 16 grupos correctos |
| Nuevos recorridos docentes | 7 recorridos y 69 comprobaciones correctas |
| Archivos de catálogo del recorrido | Guardado/carga UTF-8, índices y conservación del activo ante candidato inválido |
| Estructura documental | 104 documentos revisados, 1.044 enlaces locales correctos, bloques de código y codificación comprobados |

El verificador también comprueba GeoJSON con un parser independiente y el menú de catálogo. Las semillas de las pruebas permiten reproducir secuencias. Los contadores describen conjuntos distintos: los siete recorridos no son siete lecciones nuevas y las 69 comprobaciones no son 69 grupos generales.

## Qué significa cada resultado

Los recorridos observan las estructuras públicas y las comparan con resultados previstos. No exponen ni cambian campos privados para fabricar una salida. La colisión de claves se prueba con hashes iguales y claves distintas estables; no se promete un resultado después de violar el contrato mutando una clave.

La amortización usa un modelo de escrituras y copias, no un cronómetro. El trie mantiene su dominio ASCII. El orden topológico detecta imposibilidad de orden completo, sin afirmar que enumere los miembros de cada ciclo. El catálogo conserva el activo al fallar un candidato, pero guardar sigue usando escritura directa.

## Entorno y límites

Ejecución local en Linux con Amazon Corretto 21.0.12.1 y Python 3.12.14, sin dependencias externas. La matriz de GitHub Actions está configurada para Java 21/Python 3.12 en Ubuntu, Windows y macOS; consulta los resultados del commit para declarar esas ejecuciones realizadas.

No se midieron tiempos de producción ni consumo exacto de memoria. Los casos aleatorios y límites comprobados aportan evidencia, no una demostración de ausencia de errores para toda entrada. El catálogo no ofrece escritura atómica, transacciones, bloqueo entre procesos ni recuperación ante interrupción de escritura.

## Repetir desde la raíz

Para un tema cada vez:

```text
python scripts/recorridos.py copias
```

Para todo el curso:

```text
python scripts/verificar.py
```

Utiliza python3 o py -3 si corresponde a tu instalación. Una discrepancia debe investigarse contra contrato, invariante y caso; no se resuelve cambiando esperado.txt para ocultarla.
