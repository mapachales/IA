public class RecursividadSimple {

    public static void main(String[] args) {
        System.out.println( factorial(5));
        System.out.println( factorial(5));
    }

    static int factorial (int x) {
        int f = 1;
        for (int i = 1; i <= x; i++) {
            f *= i;
        }
        return f;   
    }

    static int fact(int x) {
        return x == 0 ? 1 : x * fact(x - 1);    
    }

}
