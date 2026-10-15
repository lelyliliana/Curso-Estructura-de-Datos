# Conservar la información que necesita la consulta antes de elegir una colección

[Lección](README.md) · [Índice del módulo](../README.md) · [Recorridos ejecutables](../../laboratorios/recorridos/README.md)

## 1. Usa los mismos datos con preguntas diferentes

El historial de códigos [3,1,3] contiene tres eventos. Un conjunto contiene dos códigos distintos. Un mapa de frecuencias asocia 1 con una aparición y 3 con dos. Convertir el historial a conjunto pierde multiplicidad; desde ese conjunto solo ya no puedes reconstruir cuántas veces apareció cada código.

| Necesidad | Representación de la práctica | Qué conserva |
|---|---|---|
| Eventos y posición | ArrayList | Repetidos y orden de la secuencia |
| Códigos distintos ordenados | TreeSet | Pertenencia y orden por comparación |
| Conteo por código | TreeMap de código a cantidad | Una asociación por código |
| Un correo vigente por usuario | Map de usuario a correo | Un valor actual por clave |

La elección depende de qué pregunta debe seguir siendo contestable. No depende solo de que los valores sean enteros o textos. Antes de comparar costos, define identidad, repetidos, orden y ausencias.

## 2. Observa qué devuelve una actualización

Poner u7→primero y después u7→actual deja una asociación. El segundo put devuelve primero como valor anterior. No agrega automáticamente un historial de correos. Si necesitas conservar cambios, el valor podría ser una secuencia o necesitarías otra estructura con ese contrato.

El recorrido usa TreeMap para que su impresión sea determinista. No supone un orden equivalente en HashMap. Si solo necesitas pertenencia y la distribución lo permite, una estructura hash puede ser una alternativa; justificarla exige declarar ese supuesto, no prometer un orden inexistente.

## 3. Revisa qué significa igualdad para un conjunto ordenado

Un TreeSet con comparador por longitud considera equivalentes sol y mar porque ambas longitudes son 3. Aunque String.equals sea falso, la segunda inserción no añade un elemento. No es una colisión hash: se trata de la equivalencia definida por la comparación ordenada.

Si el requisito es conservar palabras distintas, usa su orden natural o un comparador que también desempate por contenido. Ordenar por una propiedad parcial puede descartar información si ese mismo comparador define pertenencia en el conjunto.

## 4. Contrasta el resultado y su costo

```text
python scripts/recorridos.py colecciones
```

Comprueba tres eventos, dos códigos, frecuencias {1=1,3=2} y correo actualizado. Después explica por qué agregar mar devuelve false en el conjunto por longitud. Son decisiones de contrato observables, no una competencia de tiempos entre colecciones.

ArrayList permite acceso indexado constante y agregado amortizado constante. Los mapas/conjuntos de árbol ofrecen operaciones principales logarítmicas en cantidad de elementos. Las variantes hash requieren sus supuestos para costo esperado favorable. Un recorrido que imprime todo añade trabajo proporcional a lo mostrado, con independencia del costo de una consulta individual.

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2010%20%C2%B7%20List%2C%20Set%20y%20Map%3A%20elegir%20por%20operaci%C3%B3n%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo01%2F10-colecciones-y-eleccion%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
