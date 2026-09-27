public class Node {
    private String state;
    private Node parent;
    private int depth;
    private int cost;

    public Node(String state, Node parent) {
        this.state = state;
        this.parent = parent;
        // Si no tiene padre la profundidad es 0, de lo contrario es la profundidad del padre + 1
        this.depth = (parent == null) ? 0 : parent.getDepth() + 1;
        this.cost = 0;
    }

    // Getters & Setters
    public String getState() { 
        return state; }

    public Node getParent() { 
        return parent; }
    
    public int getDepth() { 
        return depth; }

    public int getCost() { 
        return cost; }

    public void setState(String state) { 
        this.state = state; }

    public void setParent(Node parent) { 
        this.parent = parent; }

    public void setDepth(int depth) { 
        this.depth = depth; }

    public void setCost(int cost) { 
        this.cost = cost; }
}