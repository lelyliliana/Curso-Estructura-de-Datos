import curso.Vector;
import curso.*;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
public class Fundamentos {
    static void exigir(boolean c,String texto){
        if(!c)throw new AssertionError(texto);
    }
    interface Accion {
        void ejecutar()throws Exception;
    }
    static void rechaza(Class<? extends Throwable> tipo,Accion a)throws Exception{
        try{
            a.ejecutar();
        }catch(Throwable e){
            if(tipo.isInstance(e))return;
            throw e;
        }throw new AssertionError("Entrada aceptada indebidamente");
    }
    static void vector()throws Exception{
        Vector<Integer> v=new Vector<>();
        List<Integer> ref=new ArrayList<>();
        Random r=new Random(501);
        for(int i=0; i<6000; i++){
            if(ref.isEmpty()||r.nextBoolean()){
                int p=r.nextInt(ref.size()+1),x=r.nextInt();
                v.insertar(p,x);
                ref.add(p,x);
            }
            else {
                int p=r.nextInt(ref.size());
                exigir(v.eliminar(p).equals(ref.remove(p)),"Valor eliminado");
            }
            v.verificar();
            exigir(v.copia().equals(ref)&&v.tamano()==ref.size(),"Vector contra ArrayList");
        }
        rechaza(IndexOutOfBoundsException.class,()->v.obtener(-1));
        rechaza(IndexOutOfBoundsException.class,()->v.insertar(v.tamano()+1,1));
        rechaza(NullPointerException.class,()->v.agregar(null));
        List<Integer> copia=v.copia();
        rechaza(UnsupportedOperationException.class,()->copia.add(1));
        v.agregar(7);
        exigir(v.tamano()==copia.size()+1,"Copia independiente del contenedor");
        Vector<Integer> crecimiento=new Vector<>();
        for(int i=0; i<10000; i++)crecimiento.agregar(i);
        exigir(crecimiento.copias()<2L*crecimiento.tamano(),"Crecimiento geométrico");
        System.out.println("OK Vector: 6000 operaciones, límites, vistas y crecimiento");
    }
    static String palabra(Random r){
        int n=r.nextInt(8);
        StringBuilder s=new StringBuilder();
        for(int i=0; i<n; i++)s.append((char)('a'+r.nextInt(4)));
        return s.toString();
    }
    static void trie()throws Exception{
        Trie t=new Trie();
        TreeSet<String> ref=new TreeSet<>();
        Random r=new Random(502);
        for(int i=0; i<3000; i++){
            String s=palabra(r);
            if(r.nextBoolean()){
                t.agregar(s);
                ref.add(s);
            }else {
                t.eliminar(s);
                ref.remove(s);
            }exigir(t.contiene(s)==ref.contains(s),"Trie igualdad");
            String p=s.substring(0,s.length()/2);
            exigir(t.prefijo(p).equals(ref.stream().filter(x->x.startsWith(p)).toList()),"Trie prefijos");
        }
        for(String s:new ArrayList<>(ref))t.eliminar(s);
        exigir(t.prefijo("").isEmpty(),"Trie vacío");
        rechaza(IllegalArgumentException.class,()->t.agregar("árbol"));
        rechaza(IllegalArgumentException.class,()->t.agregar("a".repeat(81)));
        System.out.println("OK Trie: 3000 operaciones, prefijo vacío y poda");
    }
    static void catalogo()throws Exception{
        Catalogo c=new Catalogo();
        TreeMap<Integer,Catalogo.Libro> ref=new TreeMap<>();
        Random r=new Random(503);
        for(int i=0; i<2000; i++){
            int id=1+r.nextInt(150);
            if(r.nextBoolean()){
                Catalogo.Libro libro=new Catalogo.Libro(id,"Árboles Java número "+id,"Autora "+id);
                if(ref.containsKey(id))rechaza(IllegalArgumentException.class,()->c.agregar(libro));
                else {
                    c.agregar(libro);
                    ref.put(id,libro);
                }
            }else exigir(c.eliminar(id)==(ref.remove(id)!=null),"Eliminar catálogo");
            c.verificar();
            exigir(c.todos().equals(new ArrayList<>(ref.values())),"Contenido catálogo");
            for(String p:List.of("arb","java","num","zzz")){
                List<Catalogo.Libro> esperados=ref.values().stream().filter(l->Arrays.stream(Catalogo.normalizar(l.titulo()).split("[^\\p{L}\\p{N}]+")).anyMatch(s->s.startsWith(p))).toList();
                exigir(c.prefijo(p).equals(esperados),"Índice contra búsqueda exhaustiva");
            }
        }
        Path carpeta=Files.createTempDirectory("catálogo con espacios ");
        Path archivo=carpeta.resolve("datos.txt");
        try{
            c.guardar(archivo);
            Catalogo otro=Catalogo.cargar(archivo);
            otro.verificar();
            exigir(c.todos().equals(otro.todos())&&c.prefijo("arb").equals(otro.prefijo("arb")),"Ida y vuelta UTF8");
            for(String s:List.of("MALA\n","CATALOGO-1\n1;Uno;Ana\n1;Dos;Eva\n","CATALOGO-1\n0;Uno;Ana\n","CATALOGO-1\n1;Uno;Ana\nmal\n")){
                Files.writeString(archivo,s,StandardCharsets.UTF_8);
                rechaza(IllegalArgumentException.class,()->Catalogo.cargar(archivo));
                exigir(c.todos().equals(new ArrayList<>(ref.values())),"Rechazo sin modificar el catálogo previo");
            }
            Catalogo repeticion=new Catalogo();
            repeticion.agregar(new Catalogo.Libro(1,"Árboles arbustos","Ana"));
            exigir(repeticion.prefijo("arb").size()==1,"Un libro no se duplica por palabras");
        }finally{
            Files.deleteIfExists(archivo);
            Files.delete(carpeta);
        }
        rechaza(IllegalArgumentException.class,()->new Catalogo.Libro(1,"A;B","Ana"));
        rechaza(IllegalArgumentException.class,()->c.prefijo("dos palabras"));
        System.out.println("OK Catálogo: 2000 operaciones, índices, duplicados y persistencia");
    }
    static void topologico()throws Exception{
        int[][] posibles={
            {
                0,1
            },{
                0,2
            },{
                0,3
            },{
                1,2
            },{
                1,3
            },{
                2,3
            }
        };
        for(int mascara=0; mascara<64; mascara++){
            Grafo g=new Grafo(4,true);
            for(int i=0; i<6; i++)if((mascara&(1<<i))!=0)g.agregar(posibles[i][0],posibles[i][1],0);
            List<Integer> orden=g.ordenTopologico();
            int[] pos=new int[4];
            exigir(new HashSet<>(orden).size()==4,"Cada vértice una vez");
            for(int i=0; i<4; i++)pos[orden.get(i)]=i;
            for(Grafo.Arista e:g.aristas())exigir(pos[e.origen()]<pos[e.destino()],"Dependencias respetadas");
        }
        Grafo ciclo=new Grafo(2,true);
        ciclo.agregar(0,1,0);
        ciclo.agregar(1,0,0);
        rechaza(IllegalStateException.class,ciclo::ordenTopologico);
        rechaza(IllegalArgumentException.class,()->new Grafo(2,false).ordenTopologico());
        exigir(new Grafo(0,true).ordenTopologico().isEmpty(),"DAG vacío");
        System.out.println("OK Topológico: 64 DAG, ciclos y dirección");
    }
    public static void main(String[] args)throws Exception{
        vector();
        trie();
        catalogo();
        topologico();
        System.out.println("Fundamentos comprobados");
    }
}
