import curso.Catalogo;
import curso.Grafo;
import curso.Trie;
import curso.Vector;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/** Casos docentes: utilizan las estructuras del curso, no duplican sus algoritmos. */
public final class Recorridos {
    private static int comprobaciones;
    private static int temas;
    private static final List<String> TEMAS = List.of(
        "copias", "amortizacion", "colecciones", "claves", "trie", "topologico", "catalogo");
    private interface Accion { void ejecutar() throws Exception; }

    private static void comprobar(boolean condicion, String mensaje) {
        if (!condicion) throw new AssertionError(mensaje);
        comprobaciones++;
    }

    private static void rechaza(Class<? extends Throwable> tipo, Accion accion) throws Exception {
        try { accion.ejecutar(); }
        catch (Exception e) {
            comprobar(tipo.isInstance(e), "Tipo de rechazo: " + e);
            return;
        }
        throw new AssertionError("Falta rechazo " + tipo.getSimpleName());
    }

    private static final class Ficha {
        String nombre;
        Ficha(String nombre) { this.nombre = nombre; }
        @Override public String toString() { return nombre; }
    }

    private static void copias() throws Exception {
        Vector<Ficha> vector = new Vector<>();
        Ficha original = new Ficha("A");
        vector.agregar(original);
        List<Ficha> copia = vector.copia();
        comprobar(copia.get(0) == original, "Copia de referencias");
        rechaza(UnsupportedOperationException.class, () -> copia.add(new Ficha("X")));
        original.nombre = "B";
        comprobar(copia.get(0).nombre.equals("B") && vector.obtener(0).nombre.equals("B"), "Objeto compartido");
        vector.agregar(new Ficha("D"));
        comprobar(copia.size() == 1 && vector.tamano() == 2, "Contenedores separados");
        vector.cambiar(0, new Ficha("C"));
        comprobar(copia.get(0) == original && vector.obtener(0) != original, "Reemplazo no cambia copia");
        rechaza(NullPointerException.class, () -> vector.agregar(null));
        vector.verificar();
        comprobar(vector.tamano() == 2, "Rechazo no crece vector");
        System.out.println("copia=" + copia + ", vector=" + vector.copia());
        System.out.println("Contenedor no modificable; elemento compartido mutable.");
    }

    private static void amortizacion() {
        Vector<Integer> vector = new Vector<>();
        int[] tamanos = {4, 5, 8, 9, 16, 17};
        int[] capacidades = {4, 8, 8, 16, 16, 32};
        long[] copias = {0, 4, 4, 12, 12, 28};
        int fila = 0;
        System.out.println("n | capacidad | copias acumuladas | escritura + copias del agregado");
        for (int n = 1; n <= 17; n++) {
            long antes = vector.copias();
            vector.agregar(n - 1);
            comprobar(vector.copias() < 2L * n, "Cota de copias desde vacío");
            if (n == tamanos[fila]) {
                comprobar(vector.capacidad() == capacidades[fila] && vector.copias() == copias[fila], "Frontera de crecimiento");
                System.out.println(n + " | " + vector.capacidad() + " | " + vector.copias() + " | " + (1 + vector.copias() - antes));
                fila++;
            }
        }
        for (int i = 0; i < 16; i++) vector.eliminar(vector.tamano() - 1);
        vector.verificar();
        comprobar(vector.tamano() == 1 && vector.capacidad() == 32 && vector.copias() == 28, "No hay contracción automática");
        System.out.println("Tras eliminar 16: tamaño=1, capacidad=32, copias=28");
    }

    private static void colecciones() {
        List<Integer> historial = new ArrayList<>(List.of(3, 1, 3));
        Set<Integer> unicos = new TreeSet<>(historial);
        Map<Integer, Integer> frecuencias = new TreeMap<>();
        for (int codigo : historial) frecuencias.merge(codigo, 1, Integer::sum);
        comprobar(historial.size() == 3 && unicos.size() == 2, "Repetidos frente a pertenencia");
        comprobar(frecuencias.equals(Map.of(1, 1, 3, 2)), "Frecuencias conservan multiplicidad");
        Map<String, String> correo = new TreeMap<>();
        correo.put("u7", "primero");
        String anterior = correo.put("u7", "actual");
        comprobar(correo.size() == 1 && anterior.equals("primero") && correo.get("u7").equals("actual"), "Actualización de una asociación");
        System.out.println("historial=" + historial + ", únicos=" + unicos + ", frecuencias=" + frecuencias);
        System.out.println("correo=" + correo + ", valor anterior=" + anterior);
        Set<String> porLongitud = new TreeSet<>(Comparator.comparingInt(String::length));
        porLongitud.add("sol");
        boolean segundo = porLongitud.add("mar");
        comprobar(!"sol".equals("mar") && !segundo && porLongitud.size() == 1, "Comparador define equivalencia en conjunto ordenado");
        System.out.println("Por longitud: " + porLongitud + "; agregar mar=" + segundo);
    }

    private record Id(int valor) {
        @Override public int hashCode() { return 1; } // Colisión deliberada, clave estable.
    }

    private static void claves() {
        Id a = new Id(7), igual = new Id(7), distinta = new Id(8);
        comprobar(a != igual && a.equals(igual), "Igualdad no es identidad");
        comprobar(a.hashCode() == distinta.hashCode() && !a.equals(distinta), "Colisión no implica igualdad");
        Map<Id, String> mapa = new HashMap<>();
        mapa.put(a, "original");
        mapa.put(igual, "actual");
        mapa.put(distinta, "otro");
        comprobar(mapa.size() == 2, "Igual actualiza; distinto no se pierde");
        comprobar(mapa.get(new Id(7)).equals("actual") && mapa.get(new Id(8)).equals("otro"), "Consulta con otra instancia igual");
        System.out.println("Mismo hash; id7=" + mapa.get(new Id(7)) + ", id8=" + mapa.get(new Id(8)) + ", tamaño=" + mapa.size());
        Map<Id, Ficha> alumnos = new HashMap<>();
        Ficha ficha = new Ficha("Ana");
        alumnos.put(a, ficha);
        ficha.nombre = "Ana María";
        comprobar(alumnos.get(igual) == ficha && alumnos.get(igual).nombre.equals("Ana María"), "Editar valor no cambia clave estable");
        System.out.println("Nombre editable: " + alumnos.get(new Id(7)));
    }

    private static String palabras(List<String> lista) {
        return lista.stream().map(s -> "'" + s + "'").toList().toString();
    }

    private static void trie() throws Exception {
        Trie trie = new Trie();
        for (String s : List.of("cas", "casa", "casita", "caso")) trie.agregar(s);
        comprobar(trie.contiene("cas"), "Prefijo también terminal cuando se insertó");
        trie.eliminar("cas");
        comprobar(!trie.contiene("cas") && trie.contiene("casa") && trie.contiene("casita"), "Desmarcar conserva descendientes");
        System.out.println("Eliminar cas: " + palabras(trie.prefijo("cas")));
        trie.eliminar("casita");
        comprobar(trie.prefijo("cas").equals(List.of("casa", "caso")), "Poda solo la rama exclusiva");
        System.out.println("Eliminar casita: " + palabras(trie.prefijo("cas")));
        trie.eliminar("ausente");
        trie.agregar("");
        comprobar(trie.contiene("") && trie.prefijo("").equals(List.of("", "casa", "caso")), "Raíz terminal y prefijo vacío");
        System.out.println("Agregar palabra vacía: " + palabras(trie.prefijo("")));
        trie.eliminar("");
        comprobar(!trie.contiene("") && trie.prefijo("").equals(List.of("casa", "caso")), "Raíz persiste sin su marca");
        rechaza(IllegalArgumentException.class, () -> trie.agregar("árbol"));
        rechaza(IllegalArgumentException.class, () -> trie.agregar("CASA"));
        trie.agregar("x".repeat(80));
        comprobar(trie.contiene("x".repeat(80)), "Límite 80 aceptado");
        rechaza(IllegalArgumentException.class, () -> trie.agregar("x".repeat(81)));
        trie.eliminar("x".repeat(80));
        comprobar(trie.prefijo("").equals(List.of("casa", "caso")), "Rechazos no destruyen claves");
        System.out.println("ASCII y límite 80 comprobados; quedan " + palabras(trie.prefijo("")));
    }

    private static void topologico() throws Exception {
        Grafo grafo = new Grafo(5, true);
        grafo.agregar(0, 2, 0);
        grafo.agregar(0, 2, 0); // Dos arcos: se cuentan y se retiran dos dependencias.
        grafo.agregar(1, 2, 0);
        grafo.agregar(2, 3, -7); // Los pesos no intervienen en precedencia.
        List<Integer> orden = grafo.ordenTopologico();
        comprobar(orden.equals(List.of(0, 1, 2, 3, 4)), "Desempate e inclusión del aislado");
        comprobar(new HashSet<>(orden).size() == grafo.vertices(), "Cada vértice una vez");
        int[] posicion = new int[grafo.vertices()];
        for (int i = 0; i < orden.size(); i++) posicion[orden.get(i)] = i;
        for (Grafo.Arista arista : grafo.aristas())
            comprobar(posicion[arista.origen()] < posicion[arista.destino()], "Precedencia de cada arco");
        System.out.println("Paralelos + aislado: " + orden);
        Grafo ciclo = new Grafo(4, true);
        ciclo.agregar(0, 1, 0);
        ciclo.agregar(1, 0, 0);
        ciclo.agregar(1, 2, 0);
        rechaza(IllegalStateException.class, ciclo::ordenTopologico);
        System.out.println("Ciclo 0-1; 2 bloqueado sin pertenecer al ciclo; 3 aislado. Orden completo rechazado.");
        comprobar(new Grafo(0, true).ordenTopologico().isEmpty(), "DAG vacío");
        rechaza(IllegalArgumentException.class, () -> new Grafo(1, false).ordenTopologico());
        Grafo lazo = new Grafo(1, true);
        lazo.agregar(0, 0, 0);
        rechaza(IllegalStateException.class, lazo::ordenTopologico);
        System.out.println("Vacío, dirección y lazo comprobados.");
    }

    private static List<Integer> ids(Catalogo catalogo, String prefijo) {
        return catalogo.prefijo(prefijo).stream().map(Catalogo.Libro::id).toList();
    }

    private static void catalogo() throws Exception {
        Catalogo actual = new Catalogo();
        actual.agregar(new Catalogo.Libro(1, "Árboles arbustos", "Ana"));
        actual.agregar(new Catalogo.Libro(2, "Java práctico", "Luis"));
        actual.agregar(new Catalogo.Libro(3, "Árboles balanceados", "Eva"));
        comprobar(ids(actual, "ÁRB").equals(List.of(1, 3)), "Normalización y deduplicación");
        System.out.println("Prefijo ÁRB: " + ids(actual, "ÁRB") + "; título original=" + actual.buscar(1).titulo());
        List<Catalogo.Libro> inicial = actual.todos();
        Catalogo activoInicial = actual;
        rechaza(IllegalArgumentException.class, () -> activoInicial.agregar(new Catalogo.Libro(1, "Otro", "Eva")));
        comprobar(actual.todos().equals(inicial), "Duplicado conserva registros");
        actual.verificar();
        Path carpeta = Files.createTempDirectory("curso-catalogo-recorrido-");
        Path valido = carpeta.resolve("valido.txt"), invalido = carpeta.resolve("invalido.txt");
        try {
            actual.guardar(valido);
            actual = Catalogo.cargar(valido);
            actual.verificar();
            comprobar(actual.todos().equals(inicial) && ids(actual, "arb").equals(List.of(1, 3)), "Carga reconstruye índices y conserva UTF-8");
            actual.eliminar(1);
            actual.verificar();
            comprobar(ids(actual, "arb").equals(List.of(3)) && actual.buscar(1) == null, "Eliminar de ambos índices");
            System.out.println("Después de cargar y eliminar 1: " + ids(actual, "arb"));
            Files.writeString(invalido, "CATALOGO-1\n9;Válido;Ana\n9;Duplicado;Eva\n", StandardCharsets.UTF_8);
            Catalogo anterior = actual;
            try {
                Catalogo candidato = Catalogo.cargar(invalido);
                actual = candidato;
                throw new AssertionError("Debe rechazar el ID duplicado del archivo");
            } catch (IllegalArgumentException esperado) {
                comprobar(actual == anterior, "Solo se sustituye tras carga completa");
            }
            actual.verificar();
            comprobar(actual.buscar(9) == null && ids(actual, "arb").equals(List.of(3)), "No hay carga parcial en el catálogo activo");
            Catalogo conservado = actual;
            rechaza(IllegalArgumentException.class, () -> conservado.prefijo("dos palabras"));
            rechaza(IllegalArgumentException.class, () -> conservado.prefijo(""));
            System.out.println("Archivo duplicado rechazado; catálogo activo conservado sin id9.");
        } finally {
            Files.deleteIfExists(valido);
            Files.deleteIfExists(invalido);
            Files.deleteIfExists(carpeta);
        }
    }

    public static void main(String[] args) throws Exception {
        String elegido = args.length == 0 ? "copias" : args[0];
        if (args.length > 1 || (!TEMAS.contains(elegido) && !elegido.equals("todos")))
            throw new IllegalArgumentException("Tema: " + TEMAS + " o todos");
        for (String tema : TEMAS) {
            if (!elegido.equals("todos") && !tema.equals(elegido)) continue;
            int antes = comprobaciones;
            System.out.println("\nTema: " + tema);
            switch (tema) {
                case "copias" -> copias();
                case "amortizacion" -> amortizacion();
                case "colecciones" -> colecciones();
                case "claves" -> claves();
                case "trie" -> trie();
                case "topologico" -> topologico();
                case "catalogo" -> catalogo();
                default -> throw new AssertionError(tema);
            }
            temas++;
            System.out.println("Comprobaciones del tema: " + (comprobaciones - antes));
        }
        System.out.println("\nRecorridos: " + temas + "; comprobaciones: " + comprobaciones);
    }
}
