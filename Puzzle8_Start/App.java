public class App {

    public static void main (String[] args) throws Exception {
       // Matriz 3x3 = 9
        String initialState = "1234 2345"; // Initial State random
        String goalState = "12345678";
        SearchTree searchTree = new SearchTree(initialState, goalState);
        searchTree.breadthFirstSearch();
        System.out.println(x: "End");
    }

}
