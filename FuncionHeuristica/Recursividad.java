package FuncionHeuristica;

public class Recursividad {

    public static void main(String[] args) {
        
        System.out.print(factorial(5));
        System.out.print(factorialRecursivo(5));
        
    }

static int factorial (int x) {
    int f = 1;
    for (int i = 1; i <= x; i++) {
        f *= i;
    }
    return f;   
}

static int factorialRecursivo (int x) {
   return x == 0 ? 1 : x * factorialRecursivo(x - 1);
}
