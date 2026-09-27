import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class SearchTree {
    Node root;
    String initialState;
    String goalState;

    public SearchTree(String initialState, String goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
        this.root = new Node(initialState, null);
    }

    // Imprimir costos
    private void printMetrics(String algoName, Node goalNode, int iterations, long startTime) {
        double totalSeconds = (System.nanoTime() - startTime) / 1_000_000_000.0;
        
        System.out.println("Resultados del algoritmo:" + algoName);
        if (goalNode != null) {
            System.out.println("Goal State: Alcanzado.");
            System.out.println("Profundidad de la busqueda: " + goalNode.getDepth());
            System.out.println("Iteraciones: " + iterations);
            System.out.println("Tiempo Total tomado para resolver:" + totalSeconds);
        } else {
            System.out.println("Goal State: No alcanzado.");
        }
        System.out.println();
    }

    // Busqueda en anchura (BFS)
    public void breadthFirstSearch() {
        long startTime = System.nanoTime();
        Set<String> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        
        queue.add(root);
        int iterations = 0;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            visited.add(currentNode.getState());
            iterations++;

            if (currentNode.getState().equals(goalState)) {
                printMetrics("BFS", currentNode, iterations, startTime);
                return;
            }

            List<Node> children = NodeUtils.generateChildren(currentNode);
            for (Node child : children) {
                if (!visited.contains(child.getState())) {
                    queue.add(child);
                }
            }
        }
    }

    // Busqueda en profundidad (DFS)
    public void depthFirstSearch() {
        long startTime = System.nanoTime();
        Set<String> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        
        stack.push(root);
        int iterations = 0;

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            iterations++;

            if (currentNode.getState().equals(goalState)) {
                printMetrics("DFS", currentNode, iterations, startTime);
                return;
            }

            if (!visited.contains(currentNode.getState())) {
                visited.add(currentNode.getState());
                List<Node> children = NodeUtils.generateChildren(currentNode);
                for (Node child : children) {
                    if (!visited.contains(child.getState())) {
                        stack.push(child);
                    }
                }
            }
        }
    }

    // Consto uniforme (UCS)
    public void uniformCostSearch() {
        long startTime = System.nanoTime();
        Set<String> visited = new HashSet<>();
        // PriorityQueue para sacar el de menor costo primero
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        
        pq.add(root);
        int iterations = 0;

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            iterations++;

            if (currentNode.getState().equals(goalState)) {
                printMetrics("UCS", currentNode, iterations, startTime);
                return;
            }

            if (!visited.contains(currentNode.getState())) {
                visited.add(currentNode.getState());
                List<Node> children = NodeUtils.generateChildren(currentNode);
                for (Node child : children) {
                    if (!visited.contains(child.getState())) {
                        child.setCost(currentNode.getCost() + 1); // cada paso cuesta 1
                        pq.add(child);
                    }
                }
            }
        }
    }

    // Profundidad iterativa (IDS)
    public void iterativeDeepeningSearch() {
        long startTime = System.nanoTime();
        int iterations = 0;

        // Probar de 0 a 50
        for (int limit = 0; limit <= 50; limit++) {
            Set<String> visited = new HashSet<>();
            Stack<Node> stack = new Stack<>();
            
            stack.push(root);

            while (!stack.isEmpty()) {
                Node currentNode = stack.pop();
                iterations++;

                if (currentNode.getState().equals(goalState)) {
                    printMetrics("IDS", currentNode, iterations, startTime);
                    return;
                }

                // Expandir si la profundidad del nodo actual es menor que el límite
                if (currentNode.getDepth() < limit) {
                    if (!visited.contains(currentNode.getState())) {
                        visited.add(currentNode.getState());
                        List<Node> children = NodeUtils.generateChildren(currentNode);
                        for (Node child : children) {
                            if (!visited.contains(child.getState())) {
                                stack.push(child);
                            }
                        }
                    }
                }
            }
        }
    }
}