import java.util.Queue;

public class SearchTree {
    Node root;
    String initialState;
    String goalState;

    public SearchTree (String initialState, String goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
        this.root = new Node(initialState, parent: null);
    }

    public void breadthFirstSearch() {
        Node currentNode;
        // 1. Buscar el nodo raiz y agregarlo a la cola
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        // 2. Mienstras la cola no este vacia, hacer lo siguiente:
        while (!queue.isEmpty()) {
            // 3. Sacar el primer nodo de la cola y verificar si es el nodo objetivo
            currentNode = queue.poll();
            if (currentNode.getState.equals(goalState)) {
                System.out.println("Goal state found:" + currentNode.getState());
                return;
            }
        }
    }

}
