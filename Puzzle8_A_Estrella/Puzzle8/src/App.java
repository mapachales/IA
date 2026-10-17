public class App {

    public static void main(String[] args) throws Exception {
        // Estado inicial y estado objetivo.
        String initialState = "12345678 "; 
        String goalState = "1234567 8"; 

        // Crear y ejecutar objetivo.
        SearchTree searchTree = new SearchTree(initialState, goalState);
        searchTree.aStarSearch();
        
        System.out.println("Fin del programa.");
    }
}