import java.util.*;

public class SearchTree {
    Node root;
    String initialState;
    String goalState;

    public SearchTree(String initialState, String goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
        int rootH = NodeUtils.calculateHamming(initialState, goalState);
        this.root = new Node(0, rootH, null, initialState);
    }

    public void aStarSearch() {
        long startTime = System.nanoTime();
        Set<String> visited = new HashSet<>();

        // Cola de prioridad que ordena los nodos de menor a mayor 
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::f));
        queue.add(root);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            // Si ya visitamos este estado, lo ignoramos
            if (visited.contains(currentNode.getState())) {
                continue;
            }
            visited.add(currentNode.getState());

            // Si encontramos el objetivo, mostramos el camino y terminamos
            if (currentNode.getState().equals(goalState)) {
                System.out.println("¡Meta encontrada con éxito!\n");
                printPath(currentNode);
                printMetrics(currentNode, startTime);
                return;
            }

            // Generamos los hijos y los agregamos a la cola de prioridad
            for (Node child : NodeUtils.generateChildren(currentNode, goalState)) {
                if (!visited.contains(child.getState())) {
                    queue.add(child);
                }
            }
        }

        System.out.println("No se encontró una solución.");
    }

    // Imprime paso a paso el camino desde el inicio hasta la meta recursivamente
    private void printPath(Node node) {
        if (node == null) return;
        printPath(node.getParent());
        System.out.println(NodeUtils.formatState(node.getState()));
        System.out.println("---------");
    }

    // Muestra métricas
    private void printMetrics(Node goalNode, long startTime) {
        long elapsedTime = (System.nanoTime() - startTime) / 1_000_000;
        int steps = 0;
        
        for (Node node = goalNode; node != null && node.getParent() != null; node = node.getParent()) {
            steps++;
        }

        System.out.println("Pasos necesarios: " + steps);
        System.out.println("Tiempo de ejecución: " + elapsedTime + " ms");
    }
}