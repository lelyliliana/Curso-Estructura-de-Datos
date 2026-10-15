# Siete recorridos de invariantes y decisiones

[Inicio](../../README.md) · [Pruebas](../../tests/README.md) · [Resultados de la revisión](../../docs/verificacion.md)

Estos recorridos complementan siete recursos del curso. Utilizan las clases de src/curso y colecciones Java; no sustituyen los 71 ejemplos ni duplican los algoritmos de las estructuras.

## Paso 1. Preparar las herramientas

Necesitas JDK 21 y Python 3.12 o superior. Sigue la [guía del ambiente](../../docs/ambiente-y-herramientas.md) y ejecuta desde la raíz de Curso-Estructura-de-Datos. Los comandos utilizan python; usa python3 o py -3 según el nombre disponible en tu instalación.

## Paso 2. Predecir una observación

Comienza por copias. Antes de ejecutar, sigue la ficha A que cambia a B, el agregado D y el reemplazo de la primera posición por C. Predice qué conserva la copia. Lee el [desarrollo de referencias](../../modulo01/05-genericos-y-vistas/DESARROLLO.md) sin consultar todavía la salida completa.

## Paso 3. Ejecutar solo ese tema

```text
python scripts/recorridos.py copias
```

El ejecutor compila las clases compartidas y [Recorridos.java](Recorridos.java) en una carpeta temporal y ejecuta el tema. Al terminar elimina las clases compiladas. No requiere Maven, paquetes Python ni servicios externos. Una comprobación que no se cumpla interrumpe el programa con un error; no cambia la expectativa para ocultarlo.

## Paso 4. Relacionar salida y condición

El resultado de copias termina con copia=[B], vector=[C, D]. Abre el método copias y localiza qué condición comprueba identidad, cuál separación de tamaños y cuál rechazo. La salida ayuda a seguir datos; las condiciones comprueban también propiedades que no se deducen solo de imprimir una lista.

## Paso 5. Avanzar a otro tema

Elige un tema cada vez y repite predicción, ejecución y explicación:

| Tema | Comando desde la raíz | Desarrollo y pregunta |
|---|---|---|
| Copias | `python scripts/recorridos.py copias` | [¿Qué objetos siguen compartidos?](../../modulo01/05-genericos-y-vistas/DESARROLLO.md) |
| Amortización | `python scripts/recorridos.py amortizacion` | [¿Cuánto copia la operación 17 y cuánto se ha copiado en total?](../../modulo01/07-amortizacion/DESARROLLO.md) |
| Colecciones | `python scripts/recorridos.py colecciones` | [¿Qué información pierde un conjunto?](../../modulo01/10-colecciones-y-eleccion/DESARROLLO.md) |
| Claves | `python scripts/recorridos.py claves` | [¿Pueden coexistir claves distintas con igual hash?](../../modulo04/03-igualdad-y-claves/DESARROLLO.md) |
| Trie | `python scripts/recorridos.py trie` | [¿Qué puede podarse sin borrar otra palabra?](../../modulo04/06-trie/DESARROLLO.md) |
| Topológico | `python scripts/recorridos.py topologico` | [¿Bloqueado significa miembro de un ciclo?](../../modulo05/20-orden-topologico/DESARROLLO.md) |
| Catálogo | `python scripts/recorridos.py catalogo` | [¿Cuándo puede sustituirse el catálogo activo?](../../modulo06/07-catalogo-e-indice-invertido/DESARROLLO.md) |

El catálogo usa exclusivamente archivos temporales propios y los elimina después de la comprobación. La demostración no guarda tu trabajo ni modifica tus archivos personales.

## Paso 6. Revisar el conjunto

Después de comprender los temas puedes ejecutarlos juntos:

```text
python scripts/recorridos.py todos
```

El conjunto tiene **7 recorridos y 69 comprobaciones**: 7 de copias, 24 de amortización, 4 de colecciones, 5 de claves, 10 de trie, 10 de topológico y 9 de catálogo. Consulta la [salida completa](esperado.txt) después de predecirla. No son 69 pruebas independientes ni mediciones de rendimiento.

La [verificación completa](../../tests/README.md) compila y ejecuta estos recorridos, compara esa salida y comprueba además los ejemplos y pruebas anteriores. En cada práctica conserva entrada, predicción, estado observado, propiedad comprobada y límite de tu conclusión.
