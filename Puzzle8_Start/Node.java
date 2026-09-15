package Puzzle8_Start;

public class Node {
    private String state; // Estado inicial
    private int g; // Costo de inicio a nodo
    private int h; // Cuanto falta para llegar al goal
    private Node parent; // Nodo que vino antes de
    private List<Node> childs; // Nodos que vinieron despues de

    // f(n) = g(n) + h(n)
    public int f() {
        int f = g + h;
        return f;
    }

}
