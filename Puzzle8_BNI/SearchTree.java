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

    // Constructor.
    public SearchTree(String initialState, String goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
        this.root = new Node(initialState, null);
    }

    // Imprimir costos
    private void printMetrics(String algoName, Node goalNode, int iterations, long startTime) {
        double totalSeconds = (System.nanoTime() - startTime) / 1_000_000_000.0;
        
        System.out.println("Resultados del algoritmo: " + algoName);
        if (goalNode != null) {
            System.out.println("Goal State: Alcanzado.");
            System.out.println("Profundidad de la busqueda: " + goalNode.getDepth());
            System.out.println("Iteraciones: " + iterations);
            System.out.println("Tiempo Total tomado para resolver: " + totalSeconds);
        } else {
            System.out.println("Goal State: No alcanzado.");
        }
        System.out.println();
    }

    // Busqueda en anchura (BFS)
    public void breadthFirstSearch() {
        long startTime = System.nanoTime();
        Set<String> visited = new HashSet<>(); // Evitar repetir estados.
        Queue<Node> queue = new LinkedList<>();
        
        queue.add(root);
        int iterations = 0;

        // Mientras haya nodos en la cola, se extrae el primero y se guarda el estado como visitado.
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            visited.add(currentNode.getState());
            iterations++;

            // Si coincide, se imprimen los resultados.
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

        // Extraer ultimo nodo en la pila y marcarlo como visitado, luego generar sus hijos y agregarlos a la pila.
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            iterations++;

            // Si coincide, se imprimen los resultados.
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

            // Si coincide, se imprimen los resultados.
            if (currentNode.getState().equals(goalState)) {
                printMetrics("UCS", currentNode, iterations, startTime);
                return;
            }

            // Verifica si ya fue visitado, si no, lo marca y lo empuja a la pila.
            if (!visited.contains(currentNode.getState())) {
                visited.add(currentNode.getState());
                List<Node> children = NodeUtils.generateChildren(currentNode);
                for (Node child : children) {
                    if (!visited.contains(child.getState())) {
                        child.setCost(currentNode.getCost() + 1); // Cada paso cuesta 1.
                        pq.add(child);
                    }
                }
            }
        }
    }

    public void iterativeDeepeningSearch() {
        long startTime = System.nanoTime();
        int iterations = 0;

        // Incrementa el limite de profundidad de 0 a 50.
        for (int limit = 0; limit <= 50; limit++) {
            Stack<Node> stack = new Stack<>(); // Cada nuevo limite reinicia la pila e inserta el nodo raiz.
            stack.push(root);

            while (!stack.isEmpty()) {
                Node currentNode = stack.pop();
                iterations++;

                // Verificaa si alcanzo la meta.
                if (currentNode.getState().equals(goalState)) {
                    printMetrics("IDS", currentNode, iterations, startTime);
                    return;
                }
                
                // Si la profundidad es menor al limite, genera sus hijos y los ingresa a la pila. Si se alcanza el limite, detiene la expansion.
                if (currentNode.getDepth() < limit) {
                    List<Node> children = NodeUtils.generateChildren(currentNode);
                    for (Node child : children) {
                        stack.push(child);
                    }
                }
            }
        }
    }
}