# Elegir por operación

[Inicio](../README.md)

| Pregunta | Estructura apropiada | Qué comprobar |
|---|---|---|
| Obtener posición i | Arreglo o vector | Índice y región activa |
| Insertar junto a nodo conocido | Lista | Pertenencia y extremos |
| Deshacer lo último | Pila | Orden LIFO |
| Atender por llegada | Cola | Orden FIFO |
| Retirar menor prioridad | Montículo | Padre–hijos y desempate |
| Igualdad por clave | Hash o árbol | Distribución o garantía logarítmica |
| Listar intervalo ordenado | Árbol balanceado/B+ | Límites y salida |
| Autocompletar prefijo | Trie o índice ordenado | Terminales y tamaño de salida |
| Conectividad incremental | DSU | Partición y componentes |
| Ruta por número de conexiones | BFS | Alcanzabilidad y niveles |
| Ruta por suma no negativa | Dijkstra | Pesos y predecesores |
| Red de menor costo total | Kruskal | Ciclos, componentes y optimalidad |
| Todos los pares | Floyd | INF y ciclos negativos |
| Dependencias antes/después | Topológico | DAG y posición de arcos |

Especifica datos, operación dominante, orden requerido, garantías y memoria. Cuando se mantengan varios índices, cada modificación debe conservarlos coherentes. La salida también cuesta: devolver k resultados requiere construir o recorrer esos k elementos.
