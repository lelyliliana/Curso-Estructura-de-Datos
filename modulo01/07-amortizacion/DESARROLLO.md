# Contar las ampliaciones y separar el costo acumulado del costo de una operación

[Lección](README.md) · [Índice del módulo](../README.md) · [Recorridos ejecutables](../../laboratorios/recorridos/README.md)

## 1. Fija qué mide cada contador

El vector empieza con capacidad 4 y duplica cuando una inserción ya no cabe. copias cuenta referencias movidas por esas ampliaciones. No cuenta las escrituras de elementos nuevos, los desplazamientos de insertar en medio ni el costo de verificar o imprimir el vector.

Para estudiar agregados al final, usa un modelo simple: una escritura por agregado más las referencias copiadas si hubo ampliación. Ese modelo permite razonar sobre trabajo, no predecir milisegundos de una máquina concreta.

## 2. Sigue las fronteras exactas

| Tamaño después de agregar | Capacidad | Copias acumuladas | Escritura + copias de ese agregado |
|---|---|---|---|
| 4 | 4 | 0 | 1 |
| 5 | 8 | 4 | 5 |
| 8 | 8 | 4 | 1 |
| 9 | 16 | 12 | 9 |
| 16 | 16 | 12 | 1 |
| 17 | 32 | 28 | 17 |

En la inserción número 17 se copian 16 referencias, pero las copias acumuladas pasan de 12 a 28. Confundir esos valores haría incorrecto el costo atribuido a esa operación. El salto sucede cuando cantidad es igual a capacidad antes de agregar; con 16 elementos aún cabe exactamente la región activa.

## 3. Justifica la secuencia sin suponer azar

Para 17 agregados desde vacío se escriben 17 elementos y se copian 4+8+16=28 referencias: 45 unidades en el modelo. Una operación individual puede ser lineal; el total de la secuencia permanece lineal porque las copias forman una progresión geométrica.

La cota de copias menor que 2n en estos agregados no requiere entradas aleatorias. No es una expectativa sobre distribución de valores y no fija una duración máxima por petición. Insertar al principio sigue desplazando elementos aunque el crecimiento se amortice.

## 4. Examina lo que ocurre al eliminar

Después de llegar a 17, elimina 16 elementos del final. El tamaño queda en 1; la capacidad permanece en 32 y copias sigue en 28. La implementación libera las referencias de posiciones retiradas, pero no contrae el arreglo. Memoria reservada y cantidad de objetos almacenados son medidas diferentes.

Una política de contracción sería otra decisión. Si alternaras ampliar y contraer con umbrales demasiado cercanos, podrías copiar repetidamente. No atribuyas esa política al vector actual: primero tendría que diseñarse y comprobarse.

## 5. Ejecuta sin convertir contadores en benchmark

```text
python scripts/recorridos.py amortizacion
```

El programa utiliza [Vector](../../src/curso/Vector.java), muestra las seis fronteras y verifica la cota en cada uno de los 17 agregados. Después comprueba capacidad retenida al eliminar. Explica la diferencia entre el resultado n=17, costo de la última operación y costo acumulado.

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2007%20%C2%B7%20Crecimiento%20geom%C3%A9trico%20y%20costo%20amortizado%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo01%2F07-amortizacion%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
