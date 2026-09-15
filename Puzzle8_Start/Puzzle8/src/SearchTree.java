import Puzzle8.Node;
import java.util.*;

public class SearchTree {
    Node root;
    String initialState;
    String goalState;

    public SearchTree(String initialState, String goalState){
        this.initialState = initialState;
        this.goalState = goalState;
        // g=0 porque la raíz no costó ningún paso.
        // h se calcula comparando el estado inicial contra el goal
        int rootH = NodeUtils.calculateHamming(initialState, goalState);
        this.root = new Node(0, rootH, null, initialState);
    }

    public void aStarSearch() {
        long startTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long startMemory = runtime.totalMemory() - runtime.freeMemory();
        Set<String> visited = new HashSet<>();
        Node currentNode;

        // Ordena por f() de menor a mayor para seguir A* Search
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::f));
        queue.add(root);

        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            visited.add(currentNode.getState());

            if (currentNode.getState().equals(goalState)) {
                System.out.println("Goal state found: " + currentNode.getState());
                printPath(currentNode);
                printMetrics(currentNode, startTime, startMemory);
                return;
            }

            List<Node> children = NodeUtils.generateChildren(currentNode, goalState);
            for (Node child : children) {
                if (!visited.contains(child.getState()))
                    queue.add(child);
            }
        }

        printMetrics(null, startTime, startMemory);
    }

    private void printMetrics(Node goalNode, long startTime, long startMemory) {
        Runtime runtime = Runtime.getRuntime();
        long endMemory = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = Math.max(0, endMemory - startMemory);
        long elapsedTime = (System.nanoTime() - startTime) / 1_000_000;
        int steps = 0;

        for (Node node = goalNode; node != null && node.getParent() != null; node = node.getParent()) {
            steps++;
        }

        System.out.println("Pasos necesarios: " + steps);
        System.out.println("Tiempo: " + elapsedTime + " ms");
        System.out.println("Espacio de memoria utilizado: " + memoryUsed + " bytes");
    }

    private void printPath(Node node) {
        if (node == null) {
            return;
        }
        printPath(node.getParent());
        System.out.println(NodeUtils.formatState(node.getState()));
    }
}