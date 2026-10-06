import curso.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Catalogo c=new Catalogo();
        c.agregar(new Catalogo.Libro(1,"Árboles y grafos","Ana"));
        c.agregar(new Catalogo.Libro(2,"Java práctico","Luis"));
        c.agregar(new Catalogo.Libro(3,"Árboles balanceados","Eva"));
        if(args.length==0){
            c.verificar();
            System.out.println(c.prefijo("arb").stream().map(Catalogo.Libro::id).toList());
            c.eliminar(1);
            c.verificar();
            System.out.println(c.prefijo("arb").stream().map(Catalogo.Libro::id).toList());
            return;
        }
        if(args.length!=1||!args[0].equals("--menu"))throw new IllegalArgumentException("Uso: --menu");
        Scanner s=new Scanner(System.in,java.nio.charset.StandardCharsets.UTF_8);
        while(true){
            System.out.println("1 Agregar | 2 ID | 3 Prefijo | 4 Eliminar | 5 Guardar | 6 Cargar | 0 Salir");
            if(!s.hasNextLine())return;
            String opcion=s.nextLine().trim();
            try{
                switch(opcion){
                    case "0" -> {
                        System.out.println("Fin");
                        return;
                    }
                    case "1" -> {
                        System.out.println("ID:");
                        if(!s.hasNextLine())return;
                        int id=Integer.parseInt(s.nextLine());
                        System.out.println("Título:");
                        if(!s.hasNextLine())return;
                        String titulo=s.nextLine();
                        System.out.println("Autor:");
                        if(!s.hasNextLine())return;
                        String autor=s.nextLine();
                        c.agregar(new Catalogo.Libro(id,titulo,autor));
                        System.out.println("Libro agregado");
                    }
                    case "2" -> {
                        System.out.println("ID:");
                        if(!s.hasNextLine())return;
                        Catalogo.Libro l=c.buscar(Integer.parseInt(s.nextLine()));
                        System.out.println(l==null?"No encontrado":l);
                    }
                    case "3" -> {
                        System.out.println("Prefijo:");
                        if(!s.hasNextLine())return;
                        System.out.println(c.prefijo(s.nextLine()));
                    }
                    case "4" -> {
                        System.out.println("ID:");
                        if(!s.hasNextLine())return;
                        System.out.println(c.eliminar(Integer.parseInt(s.nextLine()))?"Libro eliminado":"No encontrado");
                    }
                    case "5" -> {
                        System.out.println("Archivo (puede reemplazarlo):");
                        if(!s.hasNextLine())return;
                        c.guardar(java.nio.file.Path.of(s.nextLine()));
                        System.out.println("Catálogo guardado");
                    }
                    case "6" -> {
                        System.out.println("Archivo:");
                        if(!s.hasNextLine())return;
                        Catalogo candidato=Catalogo.cargar(java.nio.file.Path.of(s.nextLine()));
                        c=candidato;
                        System.out.println("Catálogo cargado");
                    }
                    default -> System.out.println("Opción inválida");
                }
            }catch(IllegalArgumentException|IllegalStateException|java.io.IOException e){
                System.out.println("Error: "+e.getMessage());
            }
        }
    }
}
