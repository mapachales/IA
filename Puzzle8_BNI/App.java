public class App {
    public static void main(String[] args) throws Exception {
        String initialState = "7621 3458";
        String goalState = "12345678 ";

        // BFS
        SearchTree treeBFS = new SearchTree(initialState, goalState);
        treeBFS.breadthFirstSearch();

        // DFS
        SearchTree treeDFS = new SearchTree(initialState, goalState);
        treeDFS.depthFirstSearch();

        // Costo Uniforme
        SearchTree treeUCS = new SearchTree(initialState, goalState);
        treeUCS.uniformCostSearch();

        // Profundidad Iterativa
        SearchTree treeIDS = new SearchTree(initialState, goalState);
        treeIDS.iterativeDeepeningSearch();
    }
}