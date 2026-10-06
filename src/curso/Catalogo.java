package curso;
import java.util.*;
import java.text.Normalizer;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.io.*;
/** Catálogo con índice por ID y un índice invertido de palabras del título. */
public final class Catalogo {
    public record Libro(int id,String titulo,String autor){
        public Libro {
            if(id<=0)throw new IllegalArgumentException("ID positivo");
            validar(titulo,120);
            validar(autor,80);
        }
        private static void validar(String s,int max){
            Objects.requireNonNull(s);
            if(s.isBlank()||s.length()>max||s.indexOf(';')>=0||s.indexOf('\n')>=0||s.indexOf('\r')>=0)throw new IllegalArgumentException("Texto inválido");
        }
    }
    private final TreeMap<Integer,Libro> libros=new TreeMap<>();
    private final TreeMap<String,TreeSet<Integer>> palabras=new TreeMap<>();
    public static String normalizar(String s){
        Objects.requireNonNull(s);
        if(s.length()>120)throw new IllegalArgumentException("Consulta demasiado larga");
        return Normalizer.normalize(s,Normalizer.Form.NFD).replaceAll("\\p{M}+","").toLowerCase(Locale.ROOT);
    }
    private static Set<String> tokens(String s){
        Set<String> r=new TreeSet<>();
        for(String p:normalizar(s).split("[^\\p{L}\\p{N}]+"))if(!p.isEmpty())r.add(p);
        return r;
    }
    public void agregar(Libro l){
        Objects.requireNonNull(l);
        if(libros.size()>=10000)throw new IllegalStateException("Catálogo lleno");
        if(libros.putIfAbsent(l.id(),l)!=null)throw new IllegalArgumentException("ID duplicado");
        for(String p:tokens(l.titulo()))palabras.computeIfAbsent(p,k->new TreeSet<>()).add(l.id());
    }
    public boolean eliminar(int id){
        Libro l=libros.remove(id);
        if(l==null)return false;
        for(String p:tokens(l.titulo())){
            TreeSet<Integer> ids=palabras.get(p);
            ids.remove(id);
            if(ids.isEmpty())palabras.remove(p);
        }return true;
    }
    public Libro buscar(int id){
        return libros.get(id);
    }
    public List<Libro> todos(){
        return List.copyOf(libros.values());
    }
    public List<Libro> prefijo(String entrada){
        String p=normalizar(entrada);
        if(p.isBlank()||!p.matches("[\\p{L}\\p{N}]+"))throw new IllegalArgumentException("Escribe un prefijo de una sola palabra");
        Set<Integer> ids=new TreeSet<>();
        for(var e:palabras.tailMap(p,true).entrySet()){
            if(!e.getKey().startsWith(p))break;
            ids.addAll(e.getValue());
        }return ids.stream().map(libros::get).toList();
    }
    public void guardar(Path archivo)throws IOException{
        List<String> lineas=new ArrayList<>();
        lineas.add("CATALOGO-1");
        for(Libro l:libros.values())lineas.add(l.id()+";"+l.titulo()+";"+l.autor());
        Files.write(archivo,lineas,StandardCharsets.UTF_8);
    }
    public static Catalogo cargar(Path archivo)throws IOException{
        Catalogo c=new Catalogo();
        try(BufferedReader r=Files.newBufferedReader(archivo,StandardCharsets.UTF_8)){
            if(!"CATALOGO-1".equals(r.readLine()))throw new IllegalArgumentException("Cabecera inválida");
            String linea;
            while((linea=r.readLine())!=null){
                String[] p=linea.split(";",-1);
                if(p.length!=3)throw new IllegalArgumentException("Registro inválido");
                c.agregar(new Libro(Integer.parseInt(p[0]),p[1],p[2]));
            }
        }
        return c;
    }
    public void verificar(){
        TreeMap<String,TreeSet<Integer>> ref=new TreeMap<>();
        for(Libro l:libros.values())for(String p:tokens(l.titulo()))ref.computeIfAbsent(p,k->new TreeSet<>()).add(l.id());
        if(!ref.equals(palabras))throw new IllegalStateException("Índice invertido incoherente");
    }
}
