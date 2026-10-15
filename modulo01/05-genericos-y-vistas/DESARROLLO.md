# Seguir las referencias de una copia sin confundir el contenedor con sus objetos

[Lección](README.md) · [Índice del módulo](../README.md) · [Recorridos ejecutables](../../laboratorios/recorridos/README.md)

## 1. Haz dos preguntas distintas

Vector.copia devuelve una lista con los elementos de la región activa en ese momento. Después de obtenerla, agregar al vector no agrega posiciones a esa lista. Además, la lista rechaza operaciones como add. Esas garantías se refieren al contenedor; todavía debes averiguar qué objetos contienen sus posiciones.

El [método copia](../../src/curso/Vector.java) construye otra colección recorriendo los elementos y entrega List.copyOf. No crea una nueva instancia de cada T. Si T es una ficha mutable, el vector y la copia conservan una referencia al mismo objeto. La igualdad de referencias permite comprobar esa relación directamente.

## 2. Recorre cuatro cambios por separado

La ficha original tiene nombre A. Copia la colección y realiza estas operaciones en orden:

| Paso | Vector | Copia obtenida antes | Relación importante |
|---|---|---|---|
| Guardar ficha A y copiar | [A] | [A] | Ambas posiciones apuntan a la misma ficha |
| Cambiar nombre de esa ficha a B | [B] | [B] | Cambió el objeto compartido |
| Agregar otra ficha D al vector | [B, D] | [B] | No se comparte la estructura de posiciones |
| Sustituir posición 0 por una ficha C | [C, D] | [B] | La copia conserva la referencia anterior |

El último paso no vuelve a cambiar B: reemplaza una referencia del vector por otra. Por eso hay que distinguir mutar un objeto de sustituir el elemento almacenado. Una salida que solo muestra tamaños no alcanza para observar esa diferencia.

## 3. Lee el rechazo como parte del contrato

copia.add provoca UnsupportedOperationException. Modificar un campo de su ficha no es una llamada mutadora a la lista y sí puede observarse. Tampoco un record garantiza por sí solo inmutabilidad profunda si contiene una lista mutable.

El vector del curso rechaza null y verifica que todas las posiciones activas tengan un elemento y las demás estén vacías. Después de un agregado rechazado, tamaño y contenido deben conservarse. El [recorrido Java](../../laboratorios/recorridos/Recorridos.java) comprueba esa condición además de las referencias compartidas.

## 4. Ejecuta y explica una observación

Desde la raíz del curso:

```text
python scripts/recorridos.py copias
```

La salida final es copia=[B], vector=[C, D]. Antes de leerla, dibuja qué ficha conserva cada posición. El programa comprueba también que la copia no acepta add y que reemplazar el elemento del vector no sustituye el de la copia.

## 5. Declara el costo que realmente pagaste

Crear la copia recorre n referencias y necesita espacio proporcional a n; no clona el contenido de cada ficha. Si necesitaras una fotografía independiente de objetos mutables, deberías definir qué significa copiarlos y cómo tratar referencias compartidas o ciclos. Elegir elementos compuestos solo de valores inmutables puede evitar esa necesidad en un caso sencillo.

Consulta el contrato oficial de [List.copyOf](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/List.html#copyOf(java.util.Collection)) para contrastar el alcance de la lista no modificable.

---

### ¿Necesitas apoyo con este tema?

Si te bloqueaste con la práctica, necesitas revisar tu entorno o adaptar lo aprendido a tu proyecto, podemos trabajarlo en una sesión personalizada.

[**Solicitar asesoría para este tema por WhatsApp**](https://wa.me/573103618296?text=Hola%2C%20Leli.%20Estoy%20estudiando%2005%20%C2%B7%20Gen%C3%A9ricos%2C%20identidad%2C%20igualdad%20y%20vistas%20del%20curso%20Estructuras%20de%20Datos%20desde%20los%20fundamentos%20%28https%3A%2F%2Fgithub.com%2Flelyliliana%2FCurso-Estructura-de-Datos%2Fblob%2Fmain%2Fmodulo01%2F05-genericos-y-vistas%2FREADME.md%29%20y%20necesito%20una%20asesor%C3%ADa%20personalizada.%20Mi%20duda%20es%E2%80%A6)
