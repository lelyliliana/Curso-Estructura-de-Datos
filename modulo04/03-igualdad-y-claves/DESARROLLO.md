# Separar identidad, igualdad y colisión sin depender del orden de un HashMap

[Lección](README.md) · [Índice del módulo](../README.md) · [Recorridos ejecutables](../../laboratorios/recorridos/README.md)

## 1. Decide qué hace única a una clave

Dos instancias de una clave pueden tener el mismo identificador y representar la misma asociación. == pregunta si es el mismo objeto; equals pregunta si representan el mismo valor según su contrato. HashMap necesita que las claves iguales tengan el mismo hash. Ese requisito no obliga a que todas las claves distintas tengan hashes distintos.

El recorrido declara un record Id con un entero estable y un hash constante deliberado. Id(7) e Id(8) colisionan, pero no son iguales. El mapa debe conservar ambos. La función constante es útil para observar colisiones; no se recomienda por rendimiento.

## 2. Sigue tres inserciones y dos consultas

| Operación | Claves lógicas almacenadas | Resultado |
|---|---|---|
| put Id(7), original | 7 | Una asociación |
| put otra Id(7), actual | 7 | Actualiza la asociación igual |
| put Id(8), otro | 7 y 8 | Conserva dos claves distintas con el mismo hash |
| get nueva Id(7) | Sin cambios | Devuelve actual |
| get nueva Id(8) | Sin cambios | Devuelve otro |

No uses la impresión del HashMap como prueba de orden. El recorrido consulta cada clave por separado y comprueba tamaño y valores. Así valida el contrato incluso si cambia el orden interno de iteración.

## 3. Cambia el dato editable sin cambiar identidad

Si el ID define al estudiante, el nombre puede estar dentro del valor asociado. Modificar Ana a Ana María en ese valor no altera la clave Id(7). Una consulta con otra instancia igual de Id(7) sigue recuperando la misma ficha.

En cambio, cambiar una clave almacenada de manera que afecte equals invalida una precondición del mapa. El [contrato de Map](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Map.html) no especifica el comportamiento de ese uso. No conviertas un fallo observado en una JVM en una salida universal que cualquier mapa deba producir.

Para cambiar una identidad de negocio, primero diseña cómo comprobar ausencia/duplicado y reemplazar la asociación. La práctica aquí evita ese problema con una clave estable; no implementa edición de identificadores.

## 4. Ejecuta una colisión válida

```text
python scripts/recorridos.py claves
```

Predice tamaño 2, consulta de 7 igual a actual y consulta de 8 igual a otro. Después localiza el cambio de nombre en el valor. Explica por qué este ensayo no necesita modificar una clave ni prometer un resultado fuera del contrato.

## 5. Relaciona corrección y rendimiento

Resolver colisiones por igualdad preserva corrección; una distribución mala puede deteriorar costo. Que dos claves se recuperen correctamente con el mismo hash no demuestra consulta constante para cualquier población. Cuando evalúes rendimiento, declara carga, distribución y representación, además de mantener claves estables.

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2003%20%C2%B7%20Igualdad%2C%20hashCode%20y%20claves%20estables%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo04%2F03-igualdad-y-claves%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
