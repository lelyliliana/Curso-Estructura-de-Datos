import java.util.*;
public class Main {
    static boolean balanceado(String texto) {
        Deque<Character> pila = new ArrayDeque<>();
        for (char c : texto.toCharArray()) {
            if (c == '(' || c == '[') pila.push(c);
            else if (c == ')' || c == ']') {
                if (pila.isEmpty()) return false;
                char apertura = pila.pop();
                if ((c == ')' && apertura != '(') || (c == ']' && apertura != '[')) return false;
            }
        }
        return pila.isEmpty();
    }
    public static void main(String[] args) {
        for (String s : new String[]{
            "([x])", "([)]", "("
        }) System.out.println(s + " -> " + balanceado(s));
    }
}
