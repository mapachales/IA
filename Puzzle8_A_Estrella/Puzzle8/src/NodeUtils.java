import java.util.ArrayList;
import java.util.List;

public class NodeUtils {

    // Intercambia dos posiciones
    private static String swapPositions(String state, int pos1, int pos2) {
        char[] arr = state.toCharArray();
        char temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
        return new String(arr);
    }

    // Heuristica (Contar cuantas casillas no coinciden con el estado final)
    public static int calculateHamming(String state, String goalState) {
        int mismatches = 0;
        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i) != goalState.charAt(i) && state.charAt(i) != ' ') {
                mismatches++;
            }
        }
        return mismatches;
    }

    public static List<Node> generateChildren(Node parent, String goalState) {
        List<Node> successors = new ArrayList<>();
        int zeroPos = parent.getState().indexOf(" ");

        int[][] adjacentPositions = {
            {1, 3}, {0, 2, 4}, {1, 5}, {0, 4, 6}, {1, 3, 5, 7},
            {2, 4, 8}, {3, 7}, {4, 6, 8}, {5, 7}
        };

        for (int adjPos : adjacentPositions[zeroPos]) {
            String newState = swapPositions(parent.getState(), zeroPos, adjPos);
             // calcular g y h del hijo antes de crearlo
            int newG = parent.getG() + 1;
            int newH = calculateHamming(newState, goalState);
            successors.add(new Node(newG, newH, parent, newState));
        }

        return successors;
    }

    public static String formatState(String state) {
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < state.length(); i++) {
            formatted.append(state.charAt(i));
            if ((i + 1) % 3 == 0) {
                formatted.append("\n");
            } else {
                formatted.append(" ");
            }
        }
        return formatted.toString();
    }

}
