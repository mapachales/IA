public class Node {
    private String state;
    private int g;           // Costo del camino desde el nodo inicial hasta este nodo
    private int h;           // Estimación de lo que falta para llegar a la meta
    private Node parent;

    public Node(int g, int h, Node parent, String state) {
        this.g = g;
        this.h = h;
        this.parent = parent;
        this.state = state;
    }

    // Función de evaluación: f(n) = g(n) + h(n)
    public int f() {
        return g + h;
    }

    // Getters
    public String getState() { return state; }
    public int getG() { return g; }
    public int getH() { return h; }
    public Node getParent() { return parent; }
}