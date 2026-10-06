import java.util.*;
public class Prueba {
    static void verificar(boolean c) {
        if(!c) throw new AssertionError("Multilista incorrecta");
    }
    public static void main(String[] args) {
        Main.Multilista m=new Main.Multilista(5);
        m.conectar(0,1);
        m.conectar(2,1);
        m.conectar(1,3);
        m.conectar(3,0);
        verificar(!m.conectar(1,0));
        verificar(new HashSet<>(m.vecinos(0)).equals(Set.of(1,3)));
        verificar(new HashSet<>(m.vecinos(1)).equals(Set.of(0,2,3)));
        verificar(new HashSet<>(m.vecinos(2)).equals(Set.of(1)));
        verificar(new HashSet<>(m.vecinos(3)).equals(Set.of(0,1)));
        verificar(m.vecinos(4).isEmpty());
        for(int v=0; v<5; v++) for(int w:m.vecinos(v)) verificar(m.vecinos(w).contains(v));
        try {
            m.conectar(0,5);
            throw new AssertionError("Faltó validar");
        }catch(IllegalArgumentException correcto){
        }
        try {
            m.conectar(0,0);
            throw new AssertionError("Faltó validar");
        }catch(IllegalArgumentException correcto){
        }
        System.out.println("Pruebas correctas");
    }
}
