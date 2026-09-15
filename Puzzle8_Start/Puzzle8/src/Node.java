package Puzzle8;

public class Node {
    private String state; // Estado inicial
    private int g; // Costo de inicio a nodo
    private int h; // Cuanto falta para llegar al goal
    private Node parent; // Nodo que vino antes de

    // Constructor
    public Node(int g, int h, Node parent, String state) {
        this.g = g;
        this.h = h;
        this.parent = parent;
        this.state = state;
    }

    // f(n) = g(n) + h(n)
    public int f() {
        int f = g + h;
        return f;
    }

    // Getters 
    public String getState() {
        return state;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public Node getParent() {
        return parent;
    }

    // Setters (Solo los atributos que pueden cambiar)
    public void setG(int g) {
        this.g = g;
    }

    public void setH(int h) {
        this.h = h;
    }

}
